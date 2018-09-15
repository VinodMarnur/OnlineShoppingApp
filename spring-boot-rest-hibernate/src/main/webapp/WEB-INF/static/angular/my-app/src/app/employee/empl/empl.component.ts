import { Component, OnInit,Inject } from '@angular/core';
import { EmployeeService } from '../../employee.service';
import { Employee } from '../../shared/employee';
import { StateService } from '../../service/state.service';
import { CityService } from '../../service/city.service';
import { AlertifyService } from '../../alertify/alertify.service';
import { MatDialogRef} from '@angular/material';
import { CommonService } from '../../commonservice/common.service';

@Component({
  selector: 'app-empl',
  templateUrl: './empl.component.html',
  styleUrls: ['./empl.component.css']
})

export class EmplComponent implements OnInit {

  issubmit:boolean=false;
  disablebutton:string='';
  x:string="sachin";
  empdata:any={};
  errorMessage:any[];
  today: any;
  date:Date;
  currentDate:Date;
  countrylist:any=[];
  statelist:any=[];
  citylist:any=[];
  operation:string="";
  added:boolean=false;
  
  emp:Employee=new Employee(0,'','','',0,0,0);
  
  constructor(private employeeService:EmployeeService,
              private commonService:CommonService,
              private stateService:StateService,
              private cityService:CityService,
              private alertifyService:AlertifyService
              ,public dialogRef: MatDialogRef<EmplComponent>
              ) { }

  ngOnInit() {
    this.currentDate=new Date();
  }

  onClickSubmit(value){
    this.issubmit=false;
    if(this.empdata.employeeId==0 || this.empdata.employeeId==null){
    this.commonService.post('http://localhost:8080/employee',value).subscribe(res=>{
      if(res.errorMsg.length>0){
          this.alertifyService.error(res.errorMsg);
        }else{
          this.alertifyService.success("Employee added Successfully");
          this.dialogRef.close();   
          this.added=true;      
        }
       
    })
    }else{
      this.commonService.post('http://localhost:8080/employee',value).subscribe(res=>{
      if(res.errorMsg.length>0){
          this.alertifyService.error(res.errorMsg);
        }else{
          this.alertifyService.success("Employee Update Successfully");
          this.dialogRef.close();
        }
      })
    }
    this.issubmit=true;
  }

    convertToDate(date:string){
       return this.today = new Date(date).toISOString().split('T')[0];
    }

    getId(){
       return Math.max.apply(Math,this.employeeService.emp.map(function(emp){return emp.id;}));
    }

    populateState(countryId:number){
      this.alertifyService.success("something");
      this.statelist=this.stateService.getStates(countryId);
    }

    populateCity(stateId:number){
      this.citylist=this.cityService.getCityies(stateId);
    }
}



