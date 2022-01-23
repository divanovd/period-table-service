package com.example.periodtableservice.controllers;

import com.example.periodtableservice.dtos.ElementDto;
import com.example.periodtableservice.dtos.UpdateElementDto;
import com.example.periodtableservice.entities.Element;
import com.example.periodtableservice.exceptions.ElementException;
import com.example.periodtableservice.exceptions.messages.ExceptionMessages;
import com.example.periodtableservice.services.ElementService;
import com.example.periodtableservice.utils.ElementMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/elements")
public class ElementController {

    @Autowired
    private final ElementService elementService;
    private final ElementMapper elementMapper = Mappers.getMapper(ElementMapper.class);

    @GetMapping
    public ResponseEntity<List<?>> getAll(@RequestParam(value = "groupBlock", required = false) final String groupBlock,
                                          @RequestParam(value = "period", required = false) final String period) {
        List<Element> elements;
        StringBuilder message = new StringBuilder();
        try {
            elements = this.elementService.findAll(groupBlock, period);
        } catch (final Exception e) {
            message.append(ExceptionMessages.ELEMENTS_FIND_ALL_FAIL).append(e.getMessage());
            log.warn(message.toString());
            throw new ElementException(message.toString());
        }
        message.append(ExceptionMessages.ELEMENTS_FIND_ALL_SUCCESS);
        log.info(message.toString());
        //Transform elements to short dto if no filters are used, otherwise full dto.
        List<?> elementDtos;
        if (!StringUtils.isEmpty(groupBlock) || !StringUtils.isEmpty(period)) {
            elementDtos = elements.stream().map(this.elementMapper::toElementDto).collect(Collectors.toList());
        } else {
            elementDtos = elements.stream().map(this.elementMapper::toElementDtoShort).collect(Collectors.toList());
        }
        return new ResponseEntity<>(elementDtos, HttpStatus.OK);
    }

    @GetMapping("/{atomicNumber}")
    public ResponseEntity<ElementDto> getElementByAtomicNumber(@PathVariable final int atomicNumber) {
        final StringBuilder message = new StringBuilder();
        Element element;
        try {
            element = this.elementService.findElementByAtomicNumber(atomicNumber);
        } catch (final Exception e) {
            message.append(ExceptionMessages.ELEMENTS_FIND_BY_ATOMIC_NUMBER_FAIL).append(atomicNumber).append(e.getMessage());
            log.warn(message.toString());
            throw new ElementException(message.toString());
        }
        //Proper response if no element is found.
        if (Objects.isNull(element)) {
            message.append(ExceptionMessages.ELEMENTS_FIND_BY_ATOMIC_NUMBER_NULL).append(atomicNumber);
            log.info(message.toString());
            throw new ElementException(message.toString());
        }
        //Transform element to dto.
        final ElementDto elementDto = this.elementMapper.toElementDto(element);
        message.append(ExceptionMessages.ELEMENTS_FIND_BY_ATOMIC_NUMBER_SUCCESS);
        log.info(message.toString());
        return new ResponseEntity<>(elementDto, HttpStatus.OK);
    }

    @PatchMapping("/{atomicNumber}")
    public ResponseEntity<UpdateElementDto> updateElementPartially(@PathVariable final int atomicNumber, @RequestBody UpdateElementDto elementDto) {
        final StringBuilder message = new StringBuilder();
        Element element;
        try {
            element = this.elementService.updateElementPartially(atomicNumber, elementDto);
        } catch (final Exception e) {
            message.append(ExceptionMessages.ELEMENTS_UPDATE_BY_ATOMIC_NUMBER_FAIL).append(atomicNumber).append(e.getMessage());
            log.warn(message.toString());
            throw new ElementException(message.toString());
        }
        //Transform element to dto.
        final UpdateElementDto updateElementDto = this.elementMapper.toUpdateElementDtoFromElement(element);
        message.append(ExceptionMessages.ELEMENTS_UPDATE_BY_ATOMIC_NUMBER_SUCCESS);
        log.info(message.toString());
        return new ResponseEntity<>(updateElementDto, HttpStatus.OK);
    }
}
