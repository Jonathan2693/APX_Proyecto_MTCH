package com.bbva.mtch.lib.r001;

import java.util.List;

import com.bbva.mtch.dto.employees.EmployeesDTO;

/**
 * The  interface MTCHR001 class...
 */
public interface MTCHR001 {

	public EmployeesDTO insert(EmployeesDTO employee);
	public EmployeesDTO update(EmployeesDTO employee);
	public EmployeesDTO delete(EmployeesDTO employee);
	public EmployeesDTO getByName(EmployeesDTO employee);
	public List<EmployeesDTO> getAll();
	
	
}
