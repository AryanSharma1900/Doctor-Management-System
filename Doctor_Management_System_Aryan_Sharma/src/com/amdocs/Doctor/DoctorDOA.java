package com.amdocs.Doctor;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.pojos.Doctor;



public class DoctorDOA implements DoctorDAOInterface {
    private List<Doctor> doctors;

    public DoctorDOA() {
        this.doctors = new ArrayList<>();
    }

    @Override
    public int addDoctor(Doctor doctor) {
        int newDoctorId = doctors.size() + 1;
        doctor.setDoctorId(newDoctorId);
        doctors.add(doctor);
        return newDoctorId;
    }

    @Override
    public int deleteDoctor(int doctorId) {
        int deletedCount = 0;
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getDoctorId() == doctorId) 
            {
                doctors.remove(i);
                deletedCount++;
                i--; 
            }
        }
        return deletedCount;
    }

    @Override
    public boolean updateDoctorFees(int doctorId, double newFees) {
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorId() == doctorId) {
                doctor.setFees(newFees);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Doctor> showAllDoctors() {
        return new ArrayList<>(doctors);
    }

    @Override
    public List<Doctor> searchBySpecialization(String specialization) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getSpecialization().equalsIgnoreCase(specialization)) {
                result.add(doctor);
            }
        }
        return result;
    }

    @Override
    public List<Doctor> searchByFeesAndShift(String shift) {
        List<Doctor> result = new ArrayList<>();
        double lowestFees = Double.MAX_VALUE;
        for (Doctor doctor : doctors) {
            if (doctor.getAvailableShift().equalsIgnoreCase(shift)) {
                if (doctor.getFees() < lowestFees) {
                    lowestFees = doctor.getFees();
                    result.clear(); // Clear previous results
                    result.add(doctor);
                } else if (doctor.getFees() == lowestFees) {
                    result.add(doctor);
                }
            }
        }
        return result;
    }

    @Override
    public int countDoctorsByShift(String shift) {
        int count = 0;
        for (Doctor doctor : doctors) {
            if (doctor.getAvailableShift().equalsIgnoreCase(shift)) {
                count++;
            }
        }
        return count;
    }
}
