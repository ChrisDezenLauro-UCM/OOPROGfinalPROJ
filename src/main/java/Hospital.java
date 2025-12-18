package com.vetline.app;

import java.util.List;

public class Hospital {

    public String name;
    public String city;
    public String hours;
    public String address;
    public String phone;
    public List<String> services;
    public List<String> specialties;
    public String googleMaps;

    public Hospital(
        String name,
        String city,
        String hours,
        String address,
        String phone,
        List<String> services,
        List<String> specialties,
        String googleMaps
    ) {
        this.name = name;
        this.city = city;
        this.hours = hours;
        this.address = address;
        this.phone = phone;
        this.services = services;
        this.specialties = specialties;
        this.googleMaps = googleMaps;
    }
}
/* simple explaination: mao ni tig match sa hospital data and its information
*/