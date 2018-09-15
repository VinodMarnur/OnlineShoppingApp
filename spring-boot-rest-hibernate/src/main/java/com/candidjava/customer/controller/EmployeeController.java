package com.candidjava.customer.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candidjava.customer.dao.EmployeeDao;
import com.candidjava.util.UtilClasses;


@RestController
@CrossOrigin("*")
public class EmployeeController extends UtilClasses{
	
	@Autowired
	EmployeeDao employeeDao;
	
	@RequestMapping("/employee-list")
	ResponseEntity<?> getEmployeeList(){
		return new ResponseEntity<>(employeeDao.getAllEmployee(),HttpStatus.OK);
	}
	
	@RequestMapping("/employee/{employeeId}")
	public ResponseEntity<?> getEmployee(@PathVariable long employeeId) {	
		
		return new ResponseEntity<>(employeeDao.getEmployee(employeeId),HttpStatus.OK);
	}
	
	@RequestMapping("/employee")
	public ResponseEntity<?> employeeAddUpdate(@RequestBody String str) throws JSONException, ParseException {	
		Map map=employeeDao.employeeAddUpdate(str);
		if(map.get("errorMsg").equals("")) {
			return new ResponseEntity<>(map,HttpStatus.OK);
		}else
		{
			return new ResponseEntity<>(map,HttpStatus.OK);
		}
	
	}
	
	@RequestMapping("/delete-employee")
	public Map deleteEmployee(@RequestBody String str,HttpServletResponse response) throws JSONException, ParseException {	
		return  employeeDao.deleteEmployee(str,response);
	}
	

	
	
	@RequestMapping("/filter-data")
	public Map filterData(@RequestBody String str) throws JSONException{
		return employeeDao.filterData(str);
	}
	
	@RequestMapping("/filter-search")
	public Map filterSearch(@RequestBody String str) throws JSONException{
		return employeeDao.filterSearch(str);
	}

}
