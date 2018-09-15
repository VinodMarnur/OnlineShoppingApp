import { Component, OnInit } from '@angular/core';

import { Customer } from '../../shared/customer';
import { CustomerService } from '../../service/customer.service';
import {  Router, ActivatedRoute } from '@angular/router';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  customer=new Customer(0,'','','','');

  operation:string="";

  constructor(private customerService:CustomerService,
              private route: ActivatedRoute,
              private router:Router,
              public dialogRef: MatDialogRef<CustomerComponent>
              ) { }

  ngOnInit() {

  }

  public onClickSubmit(value){
    if(this.customer.customerId==0 || this.customer.customerId==null){
        this.customerService.addCustomer(this.customer).subscribe(res=>console.log(res))
      }else{
        this.customerService.addCustomer(this.customer)
        .subscribe(result => {
          this.customerService.data.push(result);
          this.dialogRef.close();
        },
        error => {
        console.log(error)
        });
      }
  }

}
