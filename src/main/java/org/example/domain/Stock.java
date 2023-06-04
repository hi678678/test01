package org.example.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Stock {
    private int id;
    private String code;
    private String name;
    private String zxj;
    private String zde;
}
