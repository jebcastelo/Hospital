package com.lmig;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class Hospital implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Care[] employees;
	public Patient[] patients;

	public static void main(String[] args) {
		Hospital h = new Hospital();
		h.employees = new Care[4];
		h.patients = new Patient[3];
		
		// TODO Auto-generated method stub
		h.employees[0]=new Doctor("John","Doe",30,"M","MD","active");
		h.employees[1]= new Nurse("Jane","Doe",25,"F",true);
		h.employees[2]= new Doctor("Jim","Beam",50,"M","GP","inactive");
		h.employees[3]= new Nurse("Sue","Smith",35,"F",false);

		BloodPressure bp = new BloodPressure();
		h.patients[0]=new Patient("Patient1","Doe",30,"M","head",bp);
		h.patients[1]=new Patient("Patient2","Doe",25,"F","toe",bp);
		h.patients[2]=new Patient("Patient3","Doe",50,"M","throat",bp);
		
		System.out.println(h.patients[0].first+h.patients[0].last);
		
        try {
            FileOutputStream fileOut = new FileOutputStream("employee.data");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(h);
//            out.writeObject(c);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in employee.data");
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        try {
            FileInputStream fileIn = new FileInputStream("employee.data");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            h = (Hospital) in.readObject();
            //c = (Cat) in.readObject();
            in.close();
            fileIn.close();
            System.out.println(h.employees);
//            System.out.println(e.cat[0].name);
//            System.out.println(e.cat[1].name);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
             System.out.println(ex);
        }
        
        
	}

}

class Person implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String first;
	public String last;
	public int age;
	public String gender;
}

class Doctor extends Person implements Care {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String specialty;
	public String status;

	public Doctor(String first, String last, int age, String gender, String specialty, String status ) { 
		this.first=first;
		this.last=last;
		this.age=age;
		this.gender="M";
		this.specialty="MD";
		this.status=status;
	}
	
	public double takeTemperature(Patient p) {
		double temp=101.9;
		return temp;		
	}

	public String readChart(Patient p) {
		String chartEval = "sick";
		return chartEval;
	}

	public BloodPressure readBloodPressure(Patient p) {
		BloodPressure bp = new BloodPressure();
		bp.systolic = 110;
		bp.dyastolic = 80;
		return bp;
	}
}

class Patient extends Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String ailment;
	public BloodPressure bp;

	public Patient(String first, String last, int age, String gender, String ailment, BloodPressure bp) { 
		this.first=first;
		this.last=last;
		this.age=age;
		this.gender="M";
		this.ailment="ailment";
		this.bp=bp;
	}
}

class Nurse extends Person implements Care {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean rn;
	
	public double takeTemperature(Patient p) {
		double temp=101.9;
		return temp;
	}

	public Nurse(String first, String last, int age, String gender, boolean rn) { 
		this.first=first;
		this.last=last;
		this.age=age;
		this.gender="M";
		this.rn=true;
	}
	
	public String readChart(Patient p) {
		String chartEval = "sick";
		return chartEval;
	}

	public BloodPressure readBloodPressure(Patient p) {
		BloodPressure bp = new BloodPressure();
		bp.systolic = 110;
		bp.dyastolic = 80;
		return bp;
	}
}

class BloodPressure implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int systolic;
	public int dyastolic;
}

interface Care {
	double takeTemperature(Patient p);

	String readChart(Patient p);

	BloodPressure readBloodPressure(Patient p);
}

