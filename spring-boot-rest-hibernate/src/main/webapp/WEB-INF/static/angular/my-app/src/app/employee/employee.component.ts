import { Component,  OnInit, ViewChild,ElementRef } from '@angular/core';
import { EmployeeService } from '../employee.service';
import {Employee} from '../shared/employee';
import { CountryService } from '../service/country.service';
import { MatDialog , MatPaginator,MatDialogRef,MatDialogConfig,MatSort, MatTableDataSource } from '@angular/material';
import { EmplComponent } from './empl/empl.component';
import { StateService } from '../service/state.service';
import { CityService } from '../service/city.service';
import { CommonService } from '../commonservice/common.service';
import { AlertifyService } from '../alertify/alertify.service';
import * as XLSX from 'xlsx';
@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})

export class EmployeeComponent implements OnInit {


  @ViewChild('TABLE') table: ElementRef;


  employees:any;
  employeeslist:any[];
  empdata:any;
  employee:Employee=new Employee(0,'','','',0,0,0);
  currentDate:Date;
  emplComponent: MatDialogRef<EmplComponent>;


  searchKey:string="";

  length:number=0;

  pageSize:number=0;

  pageIndex:number=0;

  sortType:string="";
  
  pageSizeOptions:number[]=[5,10,20,50,100]; 

  displayedColumns: string[] = ['index','employeeId','employeeName', 'email', 'dob','doj','actions'];
  // dataSource = new MatTableDataSource(this.employeeslist);
  dataSource;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private employeeService:EmployeeService,
              private countryService:CountryService,
              private stateService:StateService,
              private cityService:CityService,
              private dialog: MatDialog,
              private commonService:CommonService,
              private alertifyService:AlertifyService) { 
              }

  ngOnInit() {
         this.getEmployees();
         this.currentDate=new Date();
  }

  getEmployees():any{
    let x=this;
    this.commonService.post("http://localhost:8080/filter-data",{"pageIndex":this.pageIndex,"pageSize":this.pageSize,"searchKey":this.searchKey}).subscribe(
      res=> {
       x.employeeslist=res.data;
       x.dataSource;
       x.dataSource= new MatTableDataSource(res.data);
       this.dataSource.sort = x.sort;
       x.length=res.meta.length;
       x.pageSize=res.meta.pageSize;
       x.pageIndex=res.meta.pageIndex;
      }
    );
  }
  employeeEdit(employeeId){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.position = {
      'top': '50',
       left: '50'
  };
  let dialogRef = this.dialog.open(EmplComponent,{
      data: {
          title: "NWAS NTD"
      },
      width: '600px',
      height: '450px',
      position: { left: '50px',
                  top:'50px'
                },
      panelClass: 'epsSelectorPanel'
  });
    this.empdata={};
  if(employeeId!=0){
      let x=this;
        this.employeeService.getEmployee(employeeId)
        .subscribe(res=>{
          dialogRef.componentInstance.empdata=res;
          x.empdata=res;
        });
        dialogRef.componentInstance.operation="Update Employee";
     }else{
       dialogRef.componentInstance.operation="Add Employee";
     }
    // dialogRef.componentInstance.countrylist=this.countryService.getCoutnry();
    // dialogRef.componentInstance.statelist=this.stateService.getStates(this.employee.countryId);
    // dialogRef.componentInstance.citylist=this.cityService.getCityies(this.employee.stateId);
    // dialogRef.componentInstance.empdata=this.empdata;
       dialogRef.afterClosed().subscribe(result => {
       if(dialogRef.componentInstance.added){
          this.pageIndex=0;
        }
       this.getEmployees();
    })
  }
  deleteEmployee(employeeId){
    let data={
      "employeeId":employeeId
    }
    this.commonService.post("http://localhost:8080/delete-employee",data).
      subscribe(
          this.alertifyService.success("employee Deleted"),
          this.getEmployees()
      )      
  }

  getNext(event){
    let x=this;
    let data={
      "searchKey":x.searchKey,
      "pageIndex":event.pageIndex,
      "pageSize":event.pageSize
    }
    this.commonService.post("http://localhost:8080/filter-data",data).subscribe(
    res=> {
     x.employeeslist=res.data;
     x.dataSource= new MatTableDataSource(res.data);
     x.dataSource.sort = this.sort;
     x.length=res.meta.length;
     x.pageIndex=res.meta.pageIndex;
     x.pageSize=res.meta.pageSize;
     return res;
    }
  );

  }

  applyFilter(searchKey: string) {
    let x=this;
    searchKey = searchKey.trim().toLowerCase();
    x.searchKey=searchKey
    let data={
      "searchKey":searchKey
    }
    if(searchKey.length>=3 || searchKey.length==0){
      this.commonService.post("http://localhost:8080/filter-search",data).
      subscribe(res=>{
            x.employeeslist=res.data;
            x.dataSource= new MatTableDataSource(res.data);
            x.dataSource.sort = this.sort;
            x.length=res.meta.length;
            x.pageIndex=res.meta.pageIndex;
            x.pageSize=res.meta.pageSize;
            this.alertifyService.success(res.meta.length+" search founds");
       });
    }
  }
  order(sortName,sortType){
    let x=this;
      if(sortType.length===0){
          this.sortType="asc";
      }else if(sortType==="asc"){
        this.sortType="desc";
      }else{
        this.sortType="asc";
      }
      
      let data={
        "order":this.sortType,
        "orderName":sortName
      }
      this.commonService.post("http://localhost:8080/filter-data",data).
      subscribe(res=>{
            x.employeeslist=res.data;
            x.dataSource= new MatTableDataSource(res.data);
            x.dataSource.sort = this.sort;
            x.length=res.meta.length;
            x.pageIndex=res.meta.pageIndex;
            x.pageSize=res.meta.pageSize;
       });
  }


  ExportTOExcel()
{
  // const ws: XLSX.WorkSheet=XLSX.utils.table_to_sheet(this.table.nativeElement);
  const ws: XLSX.WorkSheet = XLSX.utils.json_to_sheet(this.employeeslist);
  const wb: XLSX.WorkBook = XLSX.utils.book_new();
  XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
  
  /* save to file */
  XLSX.writeFile(wb, 'SheetJS.xlsx');
  
}
}
