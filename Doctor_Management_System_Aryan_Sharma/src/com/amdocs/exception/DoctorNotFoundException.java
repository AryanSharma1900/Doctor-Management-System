package com.amdocs.exception;
public class DoctorNotFoundException extends RuntimeException 
{
  private String message;
  public DoctorNotFoundException()
  {
	  
  }
  public DoctorNotFoundException(String message)
  {
	  this.message = message;
  }
@Override
	public String toString() 
	{
		return "DoctorNotFoundException [message=" + message + "]";
	}
  
}
