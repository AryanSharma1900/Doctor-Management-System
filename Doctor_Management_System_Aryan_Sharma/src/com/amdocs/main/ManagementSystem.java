package com.amdocs.main;
import java.util.List;
import java.util.Scanner;

import com.amdocs.Doctor.DoctorDAOInterface;
import com.amdocs.Doctor.DoctorDOA;
import com.amdocs.exception.DoctorNotFoundException;
import com.amdocs.pojos.Doctor;


public class ManagementSystem 
{
	  public static void main(String[] args) 
	  {
	        Scanner scanner = new Scanner(System.in);
	        DoctorDAOInterface doctorDAO = new DoctorDOA();

	        int choice = 0;
	        do {
	            System.out.println("Enter your choice:");
	            System.out.println("1. Add new doctor");
	            System.out.println("2. Update doctor fees");
	            System.out.println("3. Delete doctor");
	            System.out.println("4. View all doctors");
	            System.out.println("5. Find doctor by specialization");
	            System.out.println("6. Find doctors who’s fees is less than all doctors of given shift");
	            System.out.println("7. Count number of doctors by shift");
	            System.out.println("8. Exit");

	            choice = scanner.nextInt();
	            scanner.nextLine(); 

	            switch (choice) 
	            {
	                case 1:// Add new doctor
	                    System.out.println("Enter doctor name:");
	                    String name = scanner.nextLine();
	                    System.out.println("Enter mobile number:");
	                    String mobileNumber = scanner.nextLine();
	                    System.out.println("Enter specialization:");
	                    String specialization = scanner.nextLine();
	                    System.out.println("Enter available shift (morning/evening):");
	                    String shift = scanner.nextLine();
	                    System.out.println("Enter fees:");
	                    double fees = scanner.nextDouble();
	                    scanner.nextLine(); 

	                    Doctor newDoctor = new Doctor(0, name, mobileNumber, specialization, shift, fees);
	                    int doctorId = doctorDAO.addDoctor(newDoctor);
	                    System.out.println("Doctor added with ID: " + doctorId);
	                    break;
	                case 2:// Update doctor fees
	                    System.out.println("Enter doctor ID:");
	                    int updateDoctorId = scanner.nextInt();
	                    System.out.println("Enter new fees:");
	                    double newFees = scanner.nextDouble();
	                    boolean updated = doctorDAO.updateDoctorFees(updateDoctorId, newFees);
	                    if (updated) 
	                    {
	                        System.out.println("Doctor fees updated successfully");
	                    } 
	                    else 
	                    {
	                        System.out.println("Doctor not found");
	                    }
	                    break;
	                case 3:// Delete doctor
	                    System.out.println("Enter doctor ID:");
	                    int deleteDoctorId = scanner.nextInt();
	                    int deletedCount = doctorDAO.deleteDoctor(deleteDoctorId);
	                    System.out.println(deletedCount + " doctor(s) deleted");
	                    break;
	                case 4:// View all doctors
	                    List<Doctor> allDoctors = doctorDAO.showAllDoctors();
	                    if(allDoctors.size()==0)
	                    {
	                    	System.out.println("No Doctor is available");
	                    }
	                    for (Doctor doctor : allDoctors) 
	                    {
	                        System.out.println(doctor);
	                    }
	                    break;
	                case 5:// Doctor by specialization
	                    System.out.println("Enter specialization:");
	                    String specializationSearch = scanner.nextLine();
	                    List<Doctor> doctorsBySpecialization = doctorDAO.searchBySpecialization(specializationSearch);
	                    if(doctorsBySpecialization!=null && doctorsBySpecialization.size()==0)
	                    {
	                    	//System.out.println("Doctor(s) specialized in "+specializationSearch+" not found");
	                    	throw new DoctorNotFoundException("No Doctor(s) available");
	                    }
	                    else
	                    {
		                    for (Doctor doctor : doctorsBySpecialization) 
		                    {
		                        System.out.println(doctor);
		                    }
	                    }
	                    break;
	                case 6:// Doctors with fees is less than all doctors of given shift
	                    System.out.println("Enter shift (morning/evening):");
	                    String shiftSearch = scanner.nextLine();
	                    List<Doctor> doctorsByFeesAndShift = doctorDAO.searchByFeesAndShift(shiftSearch);
	                    if(doctorsByFeesAndShift.size()==0)
	                    {
	                    	System.out.println("No Doctor(s) available as per requirement");
	                    }
	                    for (Doctor doctor : doctorsByFeesAndShift) 
	                    {
	                        System.out.println(doctor);
	                    }
	                    break;
	                case 7:// Number of doctors by shift
	                    System.out.println("Enter shift (morning/evening):");
	                    String countShift = scanner.nextLine();
	                    int doctorCount = doctorDAO.countDoctorsByShift(countShift);
	                    if(doctorCount==0)
	                    {
	                    	throw new DoctorNotFoundException("No Doctors in the shift");
	                    }
	                    System.out.println("Number of doctors for shift " + countShift + ": " + doctorCount);
	                    break;
	                case 8:// Exit 
	                    System.out.println("Exiting...");
	                    break;
	                default:
	                    System.out.println("Invalid choice");
	                    break;
	            }
	        } while (choice != 8);
	        scanner.close();
	  }
}
