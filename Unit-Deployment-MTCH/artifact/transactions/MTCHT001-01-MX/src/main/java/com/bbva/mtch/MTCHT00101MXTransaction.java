package com.bbva.mtch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.elara.domain.transaction.Advice;
import com.bbva.elara.domain.transaction.Severity;
import com.bbva.mtch.dto.employees.EmployeesDTO;
import com.bbva.mtch.lib.r001.MTCHR001;

/**
 * TRX para CRUD de Employees
 *
 */
public class MTCHT00101MXTransaction extends AbstractMTCHT00101MXTransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(MTCHT00101MXTransaction.class);

	/**
	 * The execute method...
	 */
	@Override
	public void execute() {
		MTCHR001 mtchR001 = this.getServiceLibrary(MTCHR001.class);
		String selector = getSelector();
		if("0".equals(selector)) {
			LOGGER.info("Entrando a la operacion insertar");
			
			EmployeesDTO employee = mtchR001.insert(getEmployeein());
			setEmployeeout(employee);
			
			LOGGER.info("Saliendo de operacion insertar");
		}
		else if("1".equals(selector)) {
			LOGGER.info("Entrando a la operacion update");
			
			EmployeesDTO employee = mtchR001.update(getEmployeein());
			setEmployeeout(employee);
			
			LOGGER.info("Saliendo de operacion update");
		}
		else if("2".equals(selector)) {
			LOGGER.info("Entrando a la operacion delete");
			
			EmployeesDTO employee = mtchR001.delete(getEmployeein());
			setEmployeeout(employee);
			
			LOGGER.info("Saliendo de operacion delete");
		}
		else if("3".equals(selector)) {
			LOGGER.info("Entrando a la operacion getByName");
			
			EmployeesDTO employee = mtchR001.getByName(getEmployeein());
			setEmployeeout(employee);
			
			LOGGER.info("Saliendo de operacion getByName");
		}
		else if("4".equals(selector)) {
			LOGGER.info("Entrando a la operacion getAll");
			
			List<EmployeesDTO> employees = mtchR001.getAll();
			setEmployeesout(employees);
			
			LOGGER.info("Saliendo de operacion getAll");
		}
		
		Advice advice = getAdvice();
		if(advice != null && "MTCH01317007".equals(advice.getCode())) {
			setSeverity(Severity.EWR);
		} else {
			setSeverity(Severity.OK);
			LOGGER.info("La operacion termino de manera exitosa");
		}
	}

}
