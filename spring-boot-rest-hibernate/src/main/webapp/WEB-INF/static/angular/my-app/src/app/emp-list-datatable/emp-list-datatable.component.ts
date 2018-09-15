import { Component,  OnInit, ViewChild } from '@angular/core';

import { EmployeeService } from '../employee.service';

import {Employee} from '../shared/employee';
import { CountryService } from '../service/country.service';
import { MatDialog , MatPaginator,MatDialogRef,MatDialogConfig,MatSort, MatTableDataSource } from '@angular/material';

import { StateService } from '../service/state.service';
import { CityService } from '../service/city.service';
import { HttpClient } from 'selenium-webdriver/http';
import { CommonService } from '../commonservice/common.service';


@Component({
  selector: 'app-emp-list-datatable',
  templateUrl: './emp-list-datatable.component.html',
  styleUrls: ['./emp-list-datatable.component.css']
})
export class EmpListDatatableComponent implements OnInit {

  customerlist:any[]=[];
  displayedColumns: string[] = ['id','name', 'email', 'dob','doj'];
 
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  data:any;

  // Pagination
  length:number;
  pageIndex:number = 1;
  pageSize:number = 10;
  pageSizeOptions:number[] = [5, 10, 25, 50, 100];


  constructor(   private employeeService:EmployeeService,
                 private countryService:CountryService,
                 private stateService:StateService,
                 private cityService:CityService,
                 private dialog: MatDialog,
                 private commonService:CommonService) { }


  ngOnInit() {
    // this.loadData();
}

// loadData() {
//      let postData={
//       "pageIndex":this.pageIndex,
//       "pageSize":this.pageSize
//     }
//     this.commonService.post("employee-list",postData)
//     .subscribe(data => {
//         this.setPagination(data['data'], data['meta'].pageIndex, data['meta'].pageSize);
//         //this.dataSource = new ServersDataSource(data['data']);
//     });
// }


// setPagination(length, startIndex, pageSize) {
//   this.length = length;
//   this.pageIndex = startIndex;
//   this.pageSize = pageSize;
// }

// onPaginateChange(event) {
//    this.pageIndex = event.pageIndex;
//    this.pageSize = event.pageSize;
//    this.loadData();
//  }

}
