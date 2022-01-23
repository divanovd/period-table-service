package com.example.periodtableservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElementDto {
    private int atomic_number;
    private String name;
    private String alternative_name;
    private String symbol;
    private String appearance;
    private String discovery;
    //Can't find such field.
    //private String discoveryYear;
    private int period;
    private String group_block;
}
