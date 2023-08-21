package com.example.app_50510.setup;

import java.util.ArrayList;
import java.util.List;

import grupo10.medicalappointments.model.repositories.DoctorsRepository;
import grupo10.medicalappointments.model.repositories.memory.DoctorsRepository_Memory;

public class ServiceLocator {
    private static ServiceLocator instance = null;

    private ServiceLocator() {}

    public static ServiceLocator getInstance() {
        if (instance == null) {
            synchronized(ServiceLocator.class) {
                instance = new ServiceLocator();
            }
        }
        return instance;
    }

    public DoctorsRepository getDoctorsRepository(){
        return new DoctorsRepository_Memory();
    }
}
