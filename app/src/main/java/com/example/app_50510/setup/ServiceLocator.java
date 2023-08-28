package com.example.app_50510.setup;

import java.util.ArrayList;
import java.util.List;

import grupo10.medicalappointments.model.repositories.DoctorsRepository;
import grupo10.medicalappointments.model.repositories.MedicalAppointmentsRepository;
import grupo10.medicalappointments.model.repositories.http.DoctorsRepository_Http;
import grupo10.medicalappointments.model.repositories.http.MedicalAppointmentsRepository_Http;
import grupo10.medicalappointments.model.repositories.memory.DoctorsRepository_Memory;
import grupo10.medicalappointments.model.repositories.memory.MedicalAppointmentsRepository_Memory;

public class ServiceLocator {
    private static ServiceLocator instance = null;

    private ServiceLocator() {
    }

    public static ServiceLocator getInstance() {
        if (instance == null) {
            synchronized (ServiceLocator.class) {
                instance = new ServiceLocator();
            }
        }
        return instance;
    }

    public DoctorsRepository getDoctorsRepository() {
        // return new DoctorsRepository_Memory();
        return new DoctorsRepository_Http();
    }

    public MedicalAppointmentsRepository getMedicalAppointmentsRepository() {
        // return new MedicalAppointmentsRepository_Memory();
        return new MedicalAppointmentsRepository_Http();
    }
}
