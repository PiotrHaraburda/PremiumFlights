package com.example.PremiumFlights;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CRUDaccounts {
    private String name;
    private String surname;
    private String login;
    private String password;
    private boolean online;
    List<Integer> birthDate = new ArrayList<Integer>(3); //[day, month, year]
    private String authKey;
}
