package com.bbva.mtch.lib.r001.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.apx.exception.db.DuplicateKeyException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.apx.exception.db.TimeoutException;
import com.bbva.mtch.dto.employees.EmployeesDTO;

/**
 * The MTCHR001Impl class...
 */
public class MTCHR001Impl extends MTCHR001Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(MTCHR001Impl.class);
	private static final String REGEX_RFC = "^[A-Z]{3,4}[0-9]{6}[0-9A-Z]{3}$";
	private static final String REGEX_EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	private static final String REGEX_PHONE = "^[2-9]{1}[0-9]{9}$";

	@Override
	public EmployeesDTO insert(EmployeesDTO employee) {
		int resultado = 0;
		try {
			String rfc = Pattern.matches(REGEX_RFC,employee.getRfc()) ? employee.getRfc():"";
			String email = Pattern.matches(REGEX_EMAIL, employee.getEmail()) ? employee.getEmail():"";
			String phone = Pattern.matches(REGEX_PHONE,employee.getPhone()) ? employee.getPhone():"";
			if((rfc.isEmpty()) || (email.isEmpty()) || (phone.isEmpty())) {
				resultado = 0;
				LOGGER.error("Uno de los campos de entrada es incorrecto");
			}
			else {
				resultado = this.jdbcUtils.update("insert",employee.getEmployeeNumber(),employee.getName()
						  ,employee.getDepartment(),rfc,email
						  ,phone,employee.getAddress(),employee.getRegistrationDate()
						  ,employee.getStatus(),employee.getSalary());
			}
			
		}
		catch(DuplicateKeyException e) {
			
			LOGGER.error("Ocurrio un problema, se duplico la key en la tabla EMPLOYEES");
			addAdvice("MTCH01317004");
		}
		
		catch(TimeoutException e) {
			
			LOGGER.error("Ocurrio un problema, se excedio el tiempo para insertar en la tabla employees");
			addAdvice("MTCH01317005");
			
		}
		LOGGER.info("El resultado del insert es {}",resultado);
		return employee;
	}

	@Override
	public EmployeesDTO update(EmployeesDTO employee) {
		int resultado = 0;
		try {
			String rfc = Pattern.matches(REGEX_RFC,employee.getRfc()) ? employee.getRfc():"";
			String email = Pattern.matches(REGEX_EMAIL, employee.getEmail()) ? employee.getEmail():"";
			String phone = Pattern.matches(REGEX_PHONE,employee.getPhone()) ? employee.getPhone():"";
			if((rfc.isEmpty()) || (email.isEmpty()) || (phone.isEmpty())) {
				resultado = 0;
				LOGGER.error("Uno de los campos de entrada es incorrecto");
			}
			else {
				resultado = this.jdbcUtils.update("update",employee.getName(),employee.getDepartment()
						  ,rfc,email,phone,employee.getAddress(),employee.getRegistrationDate()
						  ,employee.getStatus(),employee.getSalary(),employee.getEmployeeNumber());
				
			}
			
		}
		catch(TimeoutException e) {
			
			LOGGER.error("Ocurrio un problema, se excedio el tiempo para insertar en la tabla employees");
			addAdvice("MTCH01317005");
			
		}
		LOGGER.info("El resultado del update es {}",resultado);
		return employee;
	}

	@Override
	public EmployeesDTO delete(EmployeesDTO employee) {
		int resultado = 0;
		try {
				resultado = this.jdbcUtils.update("delete",employee.getEmployeeNumber());
			}
		catch(TimeoutException e) {
			
			LOGGER.error("Ocurrio un problema, se excedio el tiempo para borrar en la tabla employees");
			addAdvice("MTCH01317005");
			
		}
		LOGGER.info("El resultado del delete es {}",resultado);
		return employee;
	}
	
	
	@Override
	public EmployeesDTO getByName(EmployeesDTO employee) {
		LOGGER.info("Entrando al metodo getByName");
		EmployeesDTO employeeProv = new EmployeesDTO();
		
		try {
			List<Map<String,Object>> lista = new ArrayList<Map<String,Object>>();
			lista = this.jdbcUtils.queryForList("getName", employee.getName());
			if(lista.size()>1) {
				LOGGER.info("BUSCANDO PRIMER COINCIDENCIA EN REGISTROS ENCONTRADOS");
				
				for(Map<String,Object> mapa : lista) {
					if(employee.getName().equals(mapa.get("EMPLOYEE_NAME").toString())) {
						
						employeeProv.setEmployeeNumber(Integer.parseInt(mapa.get("EMPLOYEE_NUMBER").toString()));
						employeeProv.setName(mapa.get("EMPLOYEE_NAME").toString());
						employeeProv.setDepartment(mapa.get("EMPLOYEE_DEPARTMENT").toString());
						employeeProv.setRfc(mapa.get("EMPLOYEE_RFC").toString());
						employeeProv.setEmail(mapa.get("EMPLOYEE_EMAIL").toString());						
						employeeProv.setPhone(mapa.get("EMPLOYEE_PHONE").toString());
						employeeProv.setAddress(mapa.get("EMPLOYEE_ADDRESS").toString());
						employeeProv.setRegistrationDate((Date) mapa.get("EMPLOYEE_REGISTRATION_DATE"));
						employeeProv.setStatus(Integer.parseInt(mapa.get("EMPLOYEE_STATUS").toString()));
						employeeProv.setSalary(Integer.parseInt(mapa.get("SALARY").toString()));
						break;
					}
				}
				
				LOGGER.info("PRIMER COINCIDENCIA EN REGISTROS ENCONTRADA");
			}
			else {
				
				LOGGER.info("UNICO REGISTRO ENCONTRADO");
				
				employeeProv.setEmployeeNumber(Integer.parseInt(lista.get(0).get("EMPLOYEE_NUMBER").toString()));
				employeeProv.setName(lista.get(0).get("EMPLOYEE_NAME").toString());
				employeeProv.setDepartment(lista.get(0).get("EMPLOYEE_DEPARTMENT").toString());
				employeeProv.setRfc(lista.get(0).get("EMPLOYEE_RFC").toString());
				employeeProv.setEmail(lista.get(0).get("EMPLOYEE_EMAIL").toString());						
				employeeProv.setPhone(lista.get(0).get("EMPLOYEE_PHONE").toString());
				employeeProv.setAddress(lista.get(0).get("EMPLOYEE_ADDRESS").toString());
				employeeProv.setRegistrationDate((Date) lista.get(0).get("EMPLOYEE_REGISTRATION_DATE"));
				employeeProv.setStatus(Integer.parseInt(lista.get(0).get("EMPLOYEE_STATUS").toString()));
				employeeProv.setSalary(Integer.parseInt(lista.get(0).get("SALARY").toString()));
				
				LOGGER.info("UNICO REGISTRO GUARDADO");
				}
			}
			catch(NoResultException e) {
				
				LOGGER.error("No existe el registro en la Base de datos");
				addAdvice("MNEO01317008");
			}
			
			LOGGER.info("Saliendo del metodo getByName");
			return employeeProv;
	}

	@Override
	public List<EmployeesDTO> getAll() {
		LOGGER.info("Entrando al metodo obtener todos los employees");
		List<EmployeesDTO> employees = new ArrayList<>();
		
		try {
				Map<String,Object> parametros = new HashMap<>();
				parametros.put("status", 1);
				List<Map<String,Object>> resultados = new ArrayList<Map<String,Object>>();
				resultados = this.jdbcUtils.queryForList("getTodo",parametros);
				EmployeesDTO employee = new EmployeesDTO();
				if(resultados.size()>1) {
					LOGGER.info("entrando a for");
					for(Map<String,Object> mapa : resultados) {
							employee.setEmployeeNumber(Integer.parseInt(mapa.get("EMPLOYEE_NUMBER").toString()));
							employee.setName(mapa.get("EMPLOYEE_NAME").toString());
							employee.setDepartment(mapa.get("EMPLOYEE_DEPARTMENT").toString());
							employee.setRfc(mapa.get("EMPLOYEE_RFC").toString());
							employee.setEmail(mapa.get("EMPLOYEE_EMAIL").toString());		
							employee.setPhone(mapa.get("EMPLOYEE_PHONE").toString());
							employee.setAddress(mapa.get("EMPLOYEE_ADDRESS").toString());
							employee.setRegistrationDate((Date) mapa.get("EMPLOYEE_REGISTRATION_DATE"));
							employee.setStatus(Integer.parseInt(mapa.get("EMPLOYEE_STATUS").toString()));
							employee.setSalary(Integer.parseInt(mapa.get("SALARY").toString()));
							employees.add(employee);
						}
				}
				else {
					employee.setEmployeeNumber(Integer.parseInt(resultados.get(0).get("EMPLOYEE_NUMBER").toString()));
					employee.setName(resultados.get(0).get("EMPLOYEE_NAME").toString());
					employee.setDepartment(resultados.get(0).get("EMPLOYEE_DEPARTMENT").toString());
					employee.setRfc(resultados.get(0).get("EMPLOYEE_RFC").toString());
					employee.setEmail(resultados.get(0).get("EMPLOYEE_EMAIL").toString());						
					employee.setPhone(resultados.get(0).get("EMPLOYEE_PHONE").toString());
					employee.setAddress(resultados.get(0).get("EMPLOYEE_ADDRESS").toString());
					employee.setRegistrationDate((Date) resultados.get(0).get("EMPLOYEE_REGISTRATION_DATE"));
					employee.setStatus(Integer.parseInt(resultados.get(0).get("EMPLOYEE_STATUS").toString()));
					employee.setSalary(Integer.parseInt(resultados.get(0).get("SALARY").toString()));
					employees.add(employee);
				}
			}
		catch(NoResultException e) {
			
			LOGGER.error("No existió el registro en la Base de datos");
			addAdvice("MTCHO01317008");
		}
		catch(TimeoutException e) {
			
			LOGGER.error("Ocurrio un problema, se excedio el tiempo para consultar en la tabla employees");
			addAdvice("MTCH01317005");
		}
		return employees;
	}
	
}
