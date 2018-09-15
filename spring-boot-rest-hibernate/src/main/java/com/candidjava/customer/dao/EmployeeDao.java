package com.candidjava.customer.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.json.JSONException;

import com.candidjava.customer.model.Employee;

@Transactional
public interface EmployeeDao {
	List<Employee> getAllEmployee();
	Employee getEmployee(long employeeId);
	Map employeeAddUpdate(String str) throws JSONException;
	Map deleteEmployee(String str, HttpServletResponse response) throws JSONException;
	Map deleteEmployee(long employeeId);
	Map filterData(String str)throws JSONException;
	Map filterSearch(String str)throws JSONException;
	String findByEmail(String email,long employeeId);
}
