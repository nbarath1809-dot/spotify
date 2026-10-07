package com.spotify.controller;

public class ActiveUsers {

    private static int activeUsers = 0;

    public static synchronized void login() {

        activeUsers++;

    }

    public static synchronized void logout() {

        if(activeUsers>0)

            activeUsers--;

    }

    public static int getCount() {

        return activeUsers;

    }

}