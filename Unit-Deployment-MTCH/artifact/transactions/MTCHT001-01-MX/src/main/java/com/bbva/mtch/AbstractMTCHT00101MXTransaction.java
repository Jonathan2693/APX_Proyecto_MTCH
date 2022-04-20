package com.bbva.mtch;

import com.bbva.elara.transaction.AbstractTransaction;
import com.bbva.mtch.dto.employees.EmployeesDTO;
import java.util.List;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractMTCHT00101MXTransaction extends AbstractTransaction {

	public AbstractMTCHT00101MXTransaction(){
	}


	/**
	 * Return value for input parameter selector
	 */
	protected String getSelector(){
		return (String)this.getParameter("selector");
	}

	/**
	 * Return value for input parameter employeeIn
	 */
	protected EmployeesDTO getEmployeein(){
		return (EmployeesDTO)this.getParameter("employeeIn");
	}

	/**
	 * Set value for EmployeesDTO output parameter employeeOut
	 */
	protected void setEmployeeout(final EmployeesDTO field){
		this.addParameter("employeeOut", field);
	}

	/**
	 * Set value for List<EmployeesDTO> output parameter employeesOut
	 */
	protected void setEmployeesout(final List<EmployeesDTO> field){
		this.addParameter("employeesOut", field);
	}
}
