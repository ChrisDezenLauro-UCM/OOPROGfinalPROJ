package com.vetline.app;

import java.util.*;

public class HospitalData {

    public static List<Hospital> getAllHospitals() {

        List<Hospital> list = new ArrayList<>();

        // =================== CEBU CITY ===================
        // Hospital 1: Doc John's Veterinary Clinic - Lahug
        list.add(new Hospital(
            "Doc John's Veterinary Clinic - Lahug",
            "Cebu City",
            // Clinic Operating Hours
            "9:00 AM – 11:00 PM Mon–Fri\n9:00 AM – 5:00 PM Sat–Sun",
            "935K Salinas Dr, Cebu City, 6000 Cebu",
            "0927 631 4605",
            // Services (shortened into one list)
            List.of(
                "Pet Examination", "Emergency Care", "Vaccinations",
                "Grooming", "Microchipping", "Pet Food",
                "Surgery", "Laboratory Tests", "Pet Accessories",
                "Pet Boarding", "Routine Check-ups", "Spaying/Neutering",
                "Dental Care", "Parasite Control"
            ),
            // Animal Specialties
            List.of(
                "Companion Animals", "Dogs", "Cats"
            ),
            "https://maps.app.goo.gl/2ifDnksGu6zU3fcD9"
        ));

        // Hospital 2: Cebu Animal Doctor's Hospital - Main Branch
        list.add(new Hospital(
            "Cebu Animal Doctor's Hospital - Main Branch",
            "Cebu City",
            "Open 24 Hours Mon–Sun",
            "Ground floor RCDC Bldg. Gov. M. Cuenco Avenue, Banilad, Cebu City",
            "0956 308 6855",
            List.of(
                "Consultation", "Grooming", "Vaccination", "Deworming",
                "Laboratories", "Radiography", "Ultrasoundography",
                "Minor & Major Surgeries", "Orthopedic Surgery",
                "Dental Care", "Chemotherapy", "Avian Medicine",
                "Exotic Medicine"
            ),
            List.of(
                "Companion Animals", "Dogs", "Cats", "Small Mammals",
                "Rabbits", "Guinea Pigs", "Hamsters", "Rats & Mice",
                "Avians", "Parrots", "Lovebirds", "Cocatiels",
                "Exotic Pets", "Snakes", "Lizards", "Turtles & Tortoises"
            ),
            "https://maps.app.goo.gl/nHfXKyY8Bz55xKvt5"
        ));

        // Hospital 3: Cebu Veterinary Doctors - Main Hospital
        list.add(new Hospital(
            "Cebu Veterinary Doctors - Main Hospital",
            "Cebu City",
            "8:00 AM – 5:00 PM Mon–Sun",
            "Unit 107-109 Marijoy Bldg. F. Ramos St. Sta. Cruz, Cebu City 6000",
            "0956 336 8928 / 0933 823 8523",
            List.of(
                "General Consultation", "Laboratory Services",
                "Emergency Care", "Critical Care", "Dental Care",
                "Surgery and Anesthesia", "Confinement and Boarding",
                "Pharmacy and Prescription",
                "Shuttle Services and Pet Ambulance",
                "Grooming", "Microchipping",
                "Vaccination & Deworming", "Parasitic Control",
                "Diagnostic Imaging"
            ),
            List.of(
                "Companion Animals", "Dogs", "Cats",
                "Avians and Exotic Pets"
            ),
            "https://maps.app.goo.gl/8GrN5TWGXYT7gizz9"
        ));

        // Hospital 4: Happy Tails Veterinary Clinic 24/7 Emergency
        list.add(new Hospital(
            "Happy Tails Veterinary Clinic 24/7 Emergency",
            "Cebu City",
            "10:00 AM – 6:00 PM Mon–Sun\n24/7 Emergency",
            "Ground Floor, Unit 2 Labangon Town Center, Cebu City, 6000 Cebu",
            "0954 298 4479",
            List.of(
                "Consultation", "Vaccination", "Deworming",
                "Surgery", "Home Services", "Dental Services",
                "Whelping Assistance", "Wound Cleaning and Debridement",
                "Wound Dressing", "Laboratory Tests"
            ),
            List.of(
                "Companion Animals", "Dogs", "Cats"
            ),
            "https://maps.app.goo.gl/o2WWR1sQVcdGgxgh6"
        ));

        // =================== MANDAUE CITY ===================
        // Hospital 1: Cebu Veterinary Doctors - Mandaue Branch
        list.add(new Hospital(
            "Cebu Veterinary Doctors - Mandaue Branch",
            "Mandaue City",
            "9:00 AM – 6:00 PM Mon–Sat\nClosed on Sundays",
            "Co Tiao King Bldg, North Road Basak, Mandaue City, Cebu",
            "0926 470 1208",
            List.of(
                "General Consultation", "Laboratory Services",
                "Emergency Care", "Critical Care", "Dental Care",
                "Surgery and Anesthesia", "Confinement and Boarding",
                "Pharmacy and Prescription",
                "Shuttle Services and Pet Ambulance",
                "Grooming", "Microchipping",
                "Vaccination & Deworming", "Parasitic Control",
                "Diagnostic Imaging"
            ),
            List.of(
                "Companion Animals", "Dogs", "Cats",
                "Avians and Exotic Pets"
            ),
            "https://maps.app.goo.gl/fGT5cmojBwbuyQxA7"
        ));

        // Hospital 2: Animal Wellness Veterinary Clinic - ParkMall
        list.add(new Hospital(
            "Animal Wellness Veterinary Clinic - ParkMall",
            "Mandaue City",
            "10:00 AM – 7:00 PM Mon–Sun",
            "Alfresco, Park Mall Dr, Mandaue, 6014 Cebu",
            "0917 771 7148",
            List.of(
                "Consultation", "Vaccination and Deworming",
                "Basic Laboratory Exam", "Pet Pick-Up",
                "Ultrasound", "Pet Day Care", "Pet Supplies"
            ),
            List.of(
                "Companion Animals", "Dogs", "Cats"
            ),
            "https://maps.app.goo.gl/pHKdyYxJNirXDdvA9"
        ));

        // Hospital 3: Aycardo Veterinary Center Inc. - Mandaue Branch
        list.add(new Hospital(
            "Aycardo Veterinary Center Inc. - Mandaue Branch",
            "Mandaue City",
            "9:00 AM – 12:00 PM Mon–Sat\n1:00 PM – 6:00 PM Mon–Sat\nClosed on Sundays",
            "North Road Highway, Basak, Mandaue (eskina Canduman)",
            "(0994) 852-1241",
            List.of(
                "Consultation", "Vaccination", "Parasite Control",
                "Spay/Neuter", "Hemogram", "Clinical Chemistry",
                "Digital X-Ray", "Ultrasound", "Cytology",
                "Urinalysis", "Eye Services", "Ear Services",
                "Soft Tissue Surgeries", "Microchipping & Certificates",
                "Pharmacy"
            ),
            List.of(
                "Companion Animals", "Dogs", "Cats"
            ),
            "https://maps.app.goo.gl/Jmfpr8etpEYbYMUT8"
        ));

        // =================== LAPU-LAPU CITY ===================
        // Hospital 1: Cebu Veterinary Doctors - Mactan Branch
        list.add(new Hospital(
            "Cebu Veterinary Doctors - Mactan Branch",
            "Lapu-Lapu City",
            "8:00 AM – 5:00 PM Mon–Sat\nClosed on Sundays",
            "CVD Centrum, Kagudoy Road, Lapu-Lapu, 6015 Cebu",
            "0905 285 3082",
            List.of(
                "General Consultation", "Laboratory Services",
                "Emergency Care", "Critical Care", "Dental Care",
                "Surgery and Anesthesia", "Confinement and Boarding",
                "Pharmacy and Prescription",
                "Shuttle Services and Pet Ambulance",
                "Grooming", "Microchipping",
                "Vaccination & Deworming", "Parasitic Control",
                "Diagnostic Imaging"
            ),
            List.of(
                "Companion Animals", "Dogs", "Cats",
                "Avians and Exotic Pets"
            ),
            "https://maps.app.goo.gl/xF7dX1LFAmAXL34M6"
        ));

        // Hospital 2: Cebu Animal Doctors Hospital Lapulapu Branch
        list.add(new Hospital(
            "Cebu Animal Doctors Hospital - Lapu-Lapu Branch",
            "Lapu-Lapu City",
            "Open 24 Hours Mon–Sun",
            "7XP9+4G2, Collinwood Subdivision Rd, Lapu-Lapu, Cebu",
            "0927 870 5157",
            List.of(
                "Consultation", "Grooming", "Vaccination", "Deworming",
                "Laboratories", "Radiography", "Ultrasoundography",
                "Minor & Major Surgeries", "Orthopedic Surgery",
                "Dental Care", "Chemotherapy", "Avian Medicine",
                "Exotic Medicine"
            ),
            List.of(
                "Companion Animals", "Dogs", "Cats", "Small Mammals",
                "Rabbits", "Guinea Pigs", "Hamsters", "Rats & Mice",
                "Avians", "Parrots", "Lovebirds", "Cocatiels",
                "Exotic Pets", "Snakes", "Lizards", "Turtles & Tortoises"
            ),
            "https://maps.app.goo.gl/WRSMGwxZc7ebrqXGA"
        ));

        // Hospital 3: Doc John's Veterinary Clinic - LLC
        list.add(new Hospital(
            "Doc John's Veterinary Clinic - LLC",
            "Lapu-Lapu City",
            "9:00 AM – 5:00 PM Tue–Sun\nClosed on Mondays",
            "P&G Enterprise Bldg, Tiangue Rd, Babag 1, Lapu-Lapu, 6015 Cebu",
            "0945 270 7636",
            List.of(
                "Pet Examination", "Emergency Care", "Vaccinations",
                "Grooming", "Microchipping", "Pet Food",
                "Surgery", "Laboratory Tests", "Pet Accessories",
                "Pet Boarding", "Routine Check-ups",
                "Spaying/Neutering", "Dental Care", "Parasite Control"
            ),
            List.of(
                "Companion Animals", "Dogs", "Cats"
            ),
            "https://maps.app.goo.gl/BHeqmKfpxuGYMNtd9"
        ));

        return list;
    }

    public static List<Hospital> filterByCity(String city) {
        List<Hospital> result = new ArrayList<>();
        for (Hospital h : getAllHospitals()) {
            if (h.city.equals(city)) {
                result.add(h);
            }
        }
        return result;
    }
}
/*Simple explaination: A data box para sa tanan mga clinics so that e match ra siya sa "View Pages nato" its like a gigantic container thats filtered and filled
with hospital information*/
