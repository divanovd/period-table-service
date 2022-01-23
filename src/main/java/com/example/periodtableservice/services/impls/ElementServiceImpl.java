package com.example.periodtableservice.services.impls;

import com.example.periodtableservice.dtos.UpdateElementDto;
import com.example.periodtableservice.entities.Element;
import com.example.periodtableservice.exceptions.ElementException;
import com.example.periodtableservice.exceptions.messages.ExceptionMessages;
import com.example.periodtableservice.repositories.ElementRepository;
import com.example.periodtableservice.services.ElementService;
import com.example.periodtableservice.utils.ElementMapper;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ElementServiceImpl implements ElementService {

    @Autowired
    private final ElementRepository elementRepository;
    private final ElementMapper elementMapper = Mappers.getMapper(ElementMapper.class);

    @Override
    public void saveAll(final List<Element> elements) {
        elements.forEach(this.elementRepository::save);
    }

    @Override
    public List<Element> findAll(final String groupBlock, final String period) {
        List<Element> elements;
        //Validate the filters
        if (!StringUtils.isEmpty(groupBlock)) {
            if (!StringUtils.isEmpty(period)) {
                elements = this.elementRepository.findElementsByGroupBlockAndPeriod(groupBlock, Integer.parseInt(period));
            } else {
                elements = this.elementRepository.findElementsByGroupBlock(groupBlock);
            }
        } else if (!StringUtils.isEmpty(period)) {
            elements = this.elementRepository.findElementsByPeriod(Integer.parseInt(period));
        } else {
            elements = this.elementRepository.findAll();
        }
        return elements;
    }

    @Override
    public Element findElementByAtomicNumber(final int atomicNumber) {
        return this.elementRepository.findElementByAtomicNumber(atomicNumber);
    }

    @Override
    @Transactional
    public Element updateElementPartially(final int atomicNumber, final UpdateElementDto elementDto) throws NoSuchFieldException, IllegalAccessException {
        final Element element = this.findElementByAtomicNumber(atomicNumber);
        //Proper response if no element is found.
        if (Objects.isNull(element)) {
            throw new ElementException(ExceptionMessages.ELEMENTS_FIND_BY_ATOMIC_NUMBER_NULL + atomicNumber);
        }
        this.updateFieldsInternally(elementDto, element);
        this.elementRepository.save(element);
        return element;
    }

    private void updateFieldsInternally(final UpdateElementDto elementDto, final Element element) throws IllegalAccessException, NoSuchFieldException {
        //Usage of reflection to update only the necessary fields.
        final Field[] allFields = elementDto.getClass().getDeclaredFields();
        for (Field f : allFields) {
            f.setAccessible(true);
            Object value = f.get(elementDto);
            if (value != null) {
                //if not null field update it.
                Field elementField = element.getClass().getDeclaredField(f.getName());
                elementField.setAccessible(true);
                elementField.set(element, value);
            }
        }
    }

}
