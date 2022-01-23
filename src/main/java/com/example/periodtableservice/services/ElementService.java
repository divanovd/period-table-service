package com.example.periodtableservice.services;

import com.example.periodtableservice.dtos.UpdateElementDto;
import com.example.periodtableservice.entities.Element;

import java.util.List;

/**
 * Service layer that serves elements.
 */
public interface ElementService {
   void saveAll(final List<Element> elements);
   List<Element> findAll(final String groupBlock, final String period);
   Element findElementByAtomicNumber(final int atomicNumber);
   Element updateElementPartially(final int atomicNumber, final UpdateElementDto updateElementDto) throws NoSuchFieldException, IllegalAccessException;
}
