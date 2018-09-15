import { Injectable } from '@angular/core';
import { CommonService } from '../commonservice/common.service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Customer } from '../shared/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  
  baseUrl = environment.baseUrl;

  data:Customer[];

  constructor(private commonService:CommonService) { 
  }

  getCustomers(): Observable<any> {
    return this.commonService.get(this.baseUrl+"customer/customer-list");
  } 

  getAllCustomer():any{
    let x=this;
    this.commonService.get(this.baseUrl+"customer/customer-list").subscribe(
      res=> {
      return x.data=res;
      }
    );
  }

  getCutomer(id:number):Observable<Customer>{
    return this.commonService.post(this.baseUrl+"customer/customer/1",{});
  } 

  addCustomer(data:Customer):Observable<any>{
    return this.commonService.post(this.baseUrl+"customer/customer/add-update",data);
  }

}
