package com.example.periodtableservice.repositories;

import com.example.periodtableservice.entities.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElementRepository extends JpaRepository<Element, Long> {

    @Query("SELECT e FROM Element e WHERE e.atomic_number = ?1")
    Element findElementByAtomicNumber(final int atomicNumber);

    @Query("SELECT e FROM Element e WHERE e.group_block LIKE CONCAT('%',:groupBlock,'%') AND period = :period")
    List<Element> findElementsByGroupBlockAndPeriod(final String groupBlock, final int period);
    @Query("SELECT e FROM Element e WHERE e.group_block LIKE CONCAT('%',:groupBlock,'%')")
    List<Element> findElementsByGroupBlock(final String groupBlock);
    @Query("SELECT e FROM Element e WHERE e.period = ?1")
    List<Element> findElementsByPeriod(final int period);
}
