package com.example.periodtableservice.utils;

import com.example.periodtableservice.dtos.ElementDto;
import com.example.periodtableservice.dtos.ElementDtoShort;
import com.example.periodtableservice.dtos.UpdateElementDto;
import com.example.periodtableservice.entities.Element;
import org.mapstruct.Mapper;

@Mapper
public interface ElementMapper {

    UpdateElementDto toUpdateElementDtoFromElement(Element element);

    ElementDto toElementDto(Element element);

    ElementDtoShort toElementDtoShort(Element element);
}
