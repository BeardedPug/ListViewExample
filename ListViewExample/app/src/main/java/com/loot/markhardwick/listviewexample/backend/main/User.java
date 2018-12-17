package com.loot.markhardwick.listviewexample.backend.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class User {
    int id;
    String name, username, email, phone, website;
    Address adress;
    Company company;

    @Setter @Getter
    @AllArgsConstructor
    class Address {
        String street, suite, city, zipcode;
        Geo geo;

        class Geo{
            String latitude,longitude;
        }
    }

    @Setter @Getter
    @AllArgsConstructor
    class Company {
        String name, catchphrase, bs;
    }
}
