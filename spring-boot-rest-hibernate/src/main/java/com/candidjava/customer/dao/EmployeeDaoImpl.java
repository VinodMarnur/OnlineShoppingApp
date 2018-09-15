package com.candidjava.customer.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candidjava.customer.model.Employee;
import com.candidjava.util.UtilClasses;

@Service
public class EmployeeDaoImpl extends UtilClasses implements EmployeeDao{
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	JSONObject obj=null;
	
	Map map;
	
	
	@Override
	public List<Employee> getAllEmployee() {	
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.desc("employeeId"));
		List<Employee> employees = criteria.list();
		return employees;
	}

	@Override
	public Map deleteEmployee(long employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployee(long employeeId) {
		Session session = sessionFactory.getCurrentSession();
		Employee emp = session.get(Employee.class,employeeId);
		return emp;
	}

	@Override
	public Map employeeAddUpdate(String str) throws JSONException {
		Employee emp=new Employee();
		obj=new JSONObject(str);
		map=new HashMap();
		String errorMsg="";
		Session session = sessionFactory.getCurrentSession();
		
		if(!obj.isNull("employeeId")) {
			emp.setEmployeeId(obj.getLong("employeeId"));
		}else {
			emp.setEmployeeId(0);
		}
		
		if(!obj.isNull("employeeName")) {
			String input=obj.getString("employeeName");
			String output = input.substring(0, 1).toUpperCase() + input.substring(1);
			emp.setEmployeeName(output);
		}
		
		if(!obj.isNull("email")) {
			emp.setEmail(obj.getString("email"));
			if(emp.getEmail()!=null && !emp.getEmail().equals("")  ){
				String email="";
				if(!obj.isNull("employeeId")) {
				email=findByEmail(emp.getEmail(),obj.getLong("employeeId"));
				}else{
					email=findByEmail(emp.getEmail(),0);
				}
				if(email.equals(emp.getEmail()))
				{
					errorMsg="Entered Email already exist,";
				}
			}
		}
				
		if(!obj.isNull("doj")) {
			emp.setDoj(obj.getString("doj"));
		}
		
		if(!obj.isNull("dob")) {
			emp.setDob(obj.getString("dob"));
		}
		
		errorMsg+=checkFields(emp);
		if(errorMsg.length()>0) {
			map.put("errorMsg", errorMsg.substring(0, errorMsg.length()-1));
		}else {
		System.out.println(emp.getEmployeeId()+"  employee Id");
			if(emp.getEmployeeId()==0) {
				session.save(emp);
				map.put("msg", "Employee Added Successfully");
			}else {
				session.update(emp);
				map.put("msg", "Employee Updated Successfully");
			}
			map.put("errorMsg","");
		}
		
		return map;
	}
	
	public String checkFields(Employee emp) {
		String errorMsg="";
		if(padQuotes(emp.getEmployeeName()).equals("")) {
			errorMsg="Enter Employee Name,";	
		}
		return errorMsg;
	}

	@Override
	public Map deleteEmployee(String str, HttpServletResponse response) throws JSONException {
		// TODO Auto-generated method stub
		obj=new JSONObject(str);
		long employeeId=0;
		Object persistentInstance=null;
		map=new HashMap();
		Session session = sessionFactory.getCurrentSession();
		if(!obj.isNull("employeeId")) {
			employeeId=obj.getLong("employeeId");
		}
		try {
			persistentInstance = employeeDao.getEmployee(employeeId);
		}catch(Exception e) {
			System.out.println("coming");
			return (Map) map.put("msg", "Entered employee Id not found");
		}
		
	    if (persistentInstance != null && employeeId!=0) {
	        session.delete(persistentInstance);
	        map.put("msg", "Employee Deleted Successsfully");
	        response.setStatus(200);
	    }else {
	    	 map.put("msg", "Employee Id is not exist");
	    	 response.setStatus(402);
	    }
		return map;
	}

	@Override
	public Map filterData(String str) throws JSONException {
		map=new HashMap();
		obj=new JSONObject(str);
		int pageIndex=0;
		int pageSize=0;
		String order="desc";
		String searchKey="";
		String orderName="employeeId";
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Employee.class);
		Criteria dataLength=session.createCriteria(Employee.class);
		if (!obj.isNull("pageSize")) {
			pageSize = obj.getInt("pageSize");
			if(pageSize<=0) {
				pageSize=5;
			}
		} else {
			pageSize = 5;
		}

		if (!obj.isNull("pageIndex")) {
			pageIndex = obj.getInt("pageIndex") ;
		}
		
		if (!obj.isNull("order")) {
			order = obj.getString("order");
		}

		if (!obj.isNull("orderName")) {
			orderName = obj.getString("orderName");
		}
					
		
		if(!obj.isNull("searchKey")) {
			searchKey="%"+obj.getString("searchKey")+"%";
		}
		
		if(searchKey.length()>4) {
			criteria.add(Restrictions.or(Restrictions.like("employeeName", searchKey),
			Restrictions.like("employeeName", searchKey),
			Restrictions.like("email", searchKey),
			Restrictions.like("email", searchKey),
			Restrictions.like("dob", searchKey),
			Restrictions.like("doj", searchKey)));
			
			dataLength.add(Restrictions.or(Restrictions.like("employeeName", searchKey),
					Restrictions.like("employeeName", searchKey),
					Restrictions.like("email", searchKey),
					Restrictions.like("email", searchKey),
					Restrictions.like("dob", searchKey),
					Restrictions.like("doj", searchKey)));
		}
		
		criteria.setFirstResult(pageIndex * pageSize);
		criteria.setMaxResults(pageSize);
		
//		//criteria.setProjection(Projections.distinct(Employee.class));
//		if(!obj.isNull("orderName") && !obj.isNull("orderType")) {
//			if(obj.getString("orderType").equals("asc")) {
//				criteria.addOrder(Order.asc(obj.getString("orderName")));
//				dataLength.addOrder(Order.asc(obj.getString("orderName")));
//			}else {
//				criteria.addOrder(Order.desc(obj.getString("orderName")));
//				dataLength.addOrder(Order.desc(obj.getString("orderName")));
//			}
//		
//		}
//		
		
		//ordering data
		if (order.toLowerCase().equals("desc")) {
			criteria.addOrder(Order.desc(orderName));
		} else {
			criteria.addOrder(Order.asc(orderName));
		}
		
		Map meta=new HashMap();
		meta.put("length", dataLength.list().size());
		meta.put("pageIndex", pageIndex);
		meta.put("pageSize", pageSize);
		
		System.out.println("criteria.list()"+criteria.list());
		
		map.put("data", criteria.list());
		map.put("meta", meta);
		return map;
	}

	@Override
	public String findByEmail(String email,long employeeId) {
		Session session=sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Employee.class);
		crit.add(Restrictions.eq("email",email));
		if(employeeId!=0) {
			crit.add(Restrictions.ne("employeeId",employeeId));
		}
		List<Employee> employee = crit.list();
		if(employee.size()>0) {
			return employee.get(0).getEmail();
		}else {
			return "";
		}

	}

	@Override
	public Map filterSearch(String str) throws JSONException {
		map=new HashMap();
		obj=new JSONObject(str);
		int pageIndex=0;
		int pageSize=0;
		String order="desc";
		String orderName="employeeId";
		String serachKey="";
						
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Employee.class);
		Criteria dataLength=session.createCriteria(Employee.class);
				
		if (!obj.isNull("pageSize")) {
			pageSize = obj.getInt("pageSize");
		} else {
			pageSize = 5;
		}

		if (!obj.isNull("pageIndex")) {
			pageIndex = obj.getInt("pageIndex") ;
		}
		
		if(!obj.isNull("searchKey")) {
			serachKey="%"+obj.getString("searchKey")+"%";
		}
		
		criteria.add(Restrictions.or(Restrictions.like("employeeName", serachKey),
		Restrictions.like("employeeName", serachKey),
		Restrictions.like("email", serachKey),
		Restrictions.like("email", serachKey),
		Restrictions.like("dob", serachKey),
		Restrictions.like("doj", serachKey)));
		
		dataLength.add(Restrictions.or(Restrictions.like("employeeName", serachKey),
				Restrictions.like("employeeName", serachKey),
				Restrictions.like("email", serachKey),
				Restrictions.like("email", serachKey),
				Restrictions.like("dob", serachKey),
				Restrictions.like("doj", serachKey)));
		
		
		criteria.setFirstResult(pageIndex * pageSize);
		criteria.setMaxResults(pageSize);
		
		
		if(!obj.isNull("orderName") && !obj.isNull("orderType")) {
			if(obj.getString("orderType").equals("asc")) {
				criteria.addOrder(Order.asc(obj.getString("orderName")));
				dataLength.addOrder(Order.asc(obj.getString("orderName")));
			}else {
				criteria.addOrder(Order.desc(obj.getString("orderName")));
				dataLength.addOrder(Order.desc(obj.getString("orderName")));
			}
		}
		
		if (order.toLowerCase().equals("desc")) {
			criteria.addOrder(Order.desc(orderName));
		} else {
			criteria.addOrder(Order.asc(orderName));
		}
			
		Map meta=new HashMap();
		meta.put("length", dataLength.list().size());
		meta.put("pageIndex", pageIndex);
		meta.put("pageSize", pageSize);
//		meta.put("pageSize", pageSize);
		
		map.put("data", criteria.list());
		map.put("meta", meta);
		System.out.println("res======"+map.toString());
		return map;
	}

}
