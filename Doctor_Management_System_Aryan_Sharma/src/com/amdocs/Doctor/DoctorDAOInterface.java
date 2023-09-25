package com.amdocs.Doctor;
import java.util.List;

import com.amdocs.pojos.Doctor;


public interface DoctorDAOInterface 
{
    int addDoctor(Doctor doctor);
    int deleteDoctor(int doctorId);
    boolean updateDoctorFees(int doctorId, double newFees);
    List<Doctor> showAllDoctors();
    List<Doctor> searchBySpecialization(String specialization);
    List<Doctor> searchByFeesAndShift(String shift);
    int countDoctorsByShift(String shift);
}
