package com.loot.markhardwick.listviewexample.backend.main;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * Class to grab json array of users from:
 * https://jsonplaceholder.typicode.com/users
 * and turn response into an array of objects
 */

@Slf4j
public class Fetcher extends AsyncTask<String,String,User[]> {
   static User[] users = new User[0];

    static void fetchUsers() {
        log.debug("fetchUsers() called");
        URL url;
        HttpURLConnection con;
        try {
            String urlAddress = "https://jsonplaceholder.typicode.com/users";
            url = new URL(urlAddress);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            int status = con.getResponseCode();
            log.debug("Http Response Code: {}", status);
            if (getBaseResponseCode(status) == 2){
                InputStream inputStream = con.getInputStream();
                handleResponse(inputStream);
            } else {
                log.error("Http Error Code: {}", status);
            }
        } catch (java.io.IOException e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
        }
    }

    private static void handleResponse(InputStream inputStream){
        log.debug("handleResponse() called");
        ObjectMapper mapper = new ObjectMapper();
        try {
            users = mapper.readValue(inputStream, User[].class);
            log.debug("New contents of Users:");
            log.debug(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users));
            log.debug("Successful response handle");
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
        }
    }

    private static int getBaseResponseCode(int x) {
        while (x > 10) {
            x = x / 10;
        }
        return x;
    }

    public static User[] getUsers(){
        return users;
    }

    @Override
    protected User[] doInBackground(String ... voids) {
        fetchUsers();
        users.notify();
        return users;
    }
}
