package com.bbva.mtch.dto.employees;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The EmployeesDTO class...
 */
public class EmployeesDTO implements Serializable  {
	private static final long serialVersionUID = 2931699728946643245L;

	private int employeeNumber;
	private String name;
	private String department;
	private String RFC;
	private String email;
	private String phone;
	private String address;
	private Date registrationDate;
	private int status;
	private int salary;
	
	

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
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

	public String getRfc() {
		return RFC;
	}

	public void setRfc(String rfc) {
		this.RFC = rfc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final EmployeesDTO other = (EmployeesDTO) obj;
		return Objects.equals(address, other.address)
				&& Objects.equals(department, other.department)
				&& Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && employeeNumber == other.employeeNumber
				&& Objects.equals(phone, other.phone) && Objects.equals(RFC, other.RFC)
				&& Objects.equals(registrationDate, other.registrationDate)
				&& salary == other.salary && status == other.status;
	}

	@Override
	public String toString() {
		return "EmployeesDTO [employeeNumber=" + employeeNumber + ", employeeName=" + name
				+ ", employeeDepartment=" + department + ", employeeRFC=" + RFC + ", employeeEmail="
				+ email + ", employeePhone=" + phone + ", employeeAddress=" + address
				+ ", employeeRegistrationDate=" + registrationDate + ", employeeStatus=" + status
				+ ", employeeSalary=" + salary + "]";
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(this.employeeNumber)
			.append(this.name)
			.append(this.department)
			.append(this.RFC)
			.append(this.email)
			.append(this.phone)
			.append(this.address)
			.append(this.registrationDate)
			.append(this.status)
			.append(this.salary)
			.toHashCode();
	}
	
}
