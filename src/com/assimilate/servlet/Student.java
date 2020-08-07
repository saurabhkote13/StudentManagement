package com.assimilate.servlet;

public class Student {
	private Integer roll_number;
	public Student(Integer roll_number, String name, String department, String year, Double contact_number) {
		super();
		this.roll_number = roll_number;
		this.name = name;
		this.department = department;
		this.year = year;
		this.contact_number = contact_number;
	}
	private String name;
	private String department;
	private String year;
	private Double contact_number;
	@Override
	public String toString() {
		return "Student [roll_number=" + roll_number + ", name=" + name + ", department=" + department + ", year="
				+ year + ", contact_number=" + contact_number + "]";
	}
	public Integer getRoll_number() {
		return roll_number;
	}
	public void setRoll_number(Integer roll_number) {
		this.roll_number = roll_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Double getContact_number() {
		return contact_number;
	}
	public void setContact_number(Double contact_number) {
		this.contact_number = contact_number;
	}
}
