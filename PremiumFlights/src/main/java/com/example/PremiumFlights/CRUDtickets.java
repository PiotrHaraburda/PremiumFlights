package com.example.PremiumFlights;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CRUDtickets {
    private String arrival;
    List<Integer> date = new ArrayList<Integer>(3); //[day, month, year]
    private String departure;
    private String flightID;
    private int hours;
    private int minutes;
    private String id;
    private String plane;
    private int price;
    private int quantity;
    List<Integer> time = new ArrayList<Integer>(2); //[hour,minute]
    private String transfers;
    private String type;
}
