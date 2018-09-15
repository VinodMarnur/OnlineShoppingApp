import { Component, OnInit,ViewChild } from '@angular/core';
import { CustomerService } from '../service/customer.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA,MatDialogConfig} from '@angular/material';
import { CustomerComponent } from './customer/customer.component';
import { Customer } from '../shared/customer';
import { CommonService } from '../commonservice/common.service';

import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  //customerlist:any;

  displayedColumns = ['empid', 'empname', 'email', 'address','action'];
  dataSource: MatTableDataSource<Customer>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  customer:Customer;

  customerlist:Customer[];

  constructor(private customerService:CustomerService,
    private commonService:CommonService,
    private dialog: MatDialog) {
      this.customerlist=this.getAllCustomer();  
      for(var i=0;i<=50;i++){
        this.customer=new Customer(1,'','','','');
      //  this.customerlist.push(this.customer)
      }
      // this.dataSource = new MatTableDataSource(this.customerlist);
     }


 
  ngOnInit() {
    this.customerlist=this.getAllCustomer();
  }



  getAllCustomer():any{
    let x=this;
    this.commonService.get("http://localhost:8080/customer/customer-list").subscribe(
      res=> {
      x.customerService.data=res;
      return x.customerlist=res;
      }
    );
  }


  getAllCustomers() {
    let currentState = this;
    this.customerService.getCustomers().subscribe(
      data => {
        currentState.customerlist = data;
        this.customerService.data=data;
      }
    );
  }

  customerCrud(customerId){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.position = {
      'top': '50',
      left: '50'
  };
    let dialogRef = this.dialog.open(CustomerComponent,{
      data: {
          title: "NWAS NTD"
      },
      width: '600px',
      height: '550px',
      panelClass: 'epsSelectorPanel'
  });
  let x=this;
  x.customer=new Customer(0,'','','','');
  if(customerId!=0){
      this.customerService.getCutomer(customerId).subscribe(
        res=>  dialogRef.componentInstance.customer=res
      );
      dialogRef.componentInstance.operation="Update Customer";
    }else{
      dialogRef.componentInstance.customer=x.customer;
      dialogRef.componentInstance.operation="Add Customer";
    }
    dialogRef.afterClosed().subscribe(result => {
      console.log(this.customerService.data)
      this.customerlist = this.customerService.data;  
      // this.getAllCustomer();   
    })
  }

  public dateChange(date:Date){
    
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

}
