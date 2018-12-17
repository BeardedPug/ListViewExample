package com.loot.markhardwick.listviewexample.backend.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * POJO for jackson deserialization of users
 */

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class User {
    int id;
    String name, username, email, phone, website;
    Address address;
    Company company;

    @ToString
    @Setter @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Address {
        String street, suite, city, zipcode;
        Geo geo;

        @ToString
        @Setter @Getter
        @AllArgsConstructor
        @NoArgsConstructor
        public class Geo {
            String lat, lng;
        }
    }

    @ToString
    @Setter @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Company {
        String name, catchPhrase, bs;
    }
}

