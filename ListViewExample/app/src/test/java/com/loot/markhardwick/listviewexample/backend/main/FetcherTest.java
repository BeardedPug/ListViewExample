package com.loot.markhardwick.listviewexample.backend.main;

import org.junit.Test;

import static com.loot.markhardwick.listviewexample.backend.main.Fetcher.fetchUsers;
import static org.junit.Assert.*;

public class FetcherTest {

    @Test
    public void fetchUsersTest() {
        fetchUsers();
        assertNotEquals(null, Fetcher.users);
        //On the url given it should be ten but in case it were used with a different amount have tested finding more than one
        assert(Fetcher.users.length >= 1);
    }
}