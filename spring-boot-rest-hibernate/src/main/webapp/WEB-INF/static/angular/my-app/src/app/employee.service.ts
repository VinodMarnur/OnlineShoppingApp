import { Injectable } from '@angular/core';
import {Employee} from './shared/employee';
import { CommonService } from './commonservice/common.service';
import {Observable} from 'rxjs';
@Injectable()
export class EmployeeService {
  employees:any[];
  emp:Employee[];
  constructor(private commonService:CommonService) { }

    getAllEmployees(){
    this.employees= [
    {
      "id": 1,
      "first_name": "Sebastian",
      "dob": "28-11-1992",
      "email": "sebastian@codingthesmartway.com"
    },
    {
      "id": 2,
      "first_name": "Steve",
     "dob": "28-11-1992",
      "email": "steve@codingthesmartway.com"
    },
    {
      "id": 3,
      "first_name": "Ann",
      "dob": "28-11-1992",
      "email": "ann@codingthesmartway.com"
    }
  ]
  return this.employees;
  }

  getAllEmp(){
    this.emp=[new Employee(1,'sachin','sachin@gmail.com','mumbai',1,1,1),
              new Employee(2,'Ravi','sunil@gmail.com','Mandya',1,1,1),
              new Employee(3,'kiran','sunil@gmail.com','Mandya',1,1,1),
              new Employee(4,'guru','sunil@gmail.com','Mandya',1,1,1),
              new Employee(5,'king','sunil@gmail.com','Mandya',1,1,1),
              new Employee(6,'kiran','sunil@gmail.com','Mandya',1,1,1),
              new Employee(7,'shankar','sunil@gmail.com','Mandya',1,1,1),
              new Employee(8,'raju','sunil@gmail.com','Mandya',1,1,1),
              new Employee(9,'somu','sunil@gmail.com','Mandya',1,1,1)];
      return this.emp;
  }

  getEmployye(id){
    for (let e of this.employees) {
        if(e.id==1){
          return e;
        }
    }
  }


  getEmp(id:number){
    for (let e of this.emp) {
        if(e.id==id){
          return e;
        }
    }
  }


  getEmployee(employeeId):any{
      return  this.commonService.get('http://localhost:8080/employee/'+employeeId);
  }

  getEmployeeDetails(pageIndex,pageSize){
      for(let x of this.emp){
      
      }
  }

}