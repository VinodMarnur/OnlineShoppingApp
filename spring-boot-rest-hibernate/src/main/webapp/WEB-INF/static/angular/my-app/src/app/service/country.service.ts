import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CountryService {
  countryList:any=[
               {'countryId':1,'countryName':'India'},
               {'countryId':2,'countryName':'Austrelia'},
               {'countryId':3,'countryName':'Bangladesh'},
               {'countryId':4,'countryName':'Korea'},
               {'countryId':5,'countryName':'Japan'}]
  constructor(private httpClient:HttpClient) { 

  }
  public getCoutnry(){
    return this.countryList;
  }
  
}
