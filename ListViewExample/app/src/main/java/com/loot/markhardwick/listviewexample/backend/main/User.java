package com.loot.markhardwick.listviewexample.backend.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * POJO for jackson deserialization of users
 */

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
class User {
    int id;
    String name, username, email, phone, website;
    Address address;
    Company company;

    @Setter @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    class Address {
        String street, suite, city, zipcode;
        Geo geo;

        @Setter @Getter
        @AllArgsConstructor
        @NoArgsConstructor
        class Geo {
            String lat, lng;
        }
    }

    @Setter @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    class Company {
        String name, catchPhrase, bs;
    }
}

