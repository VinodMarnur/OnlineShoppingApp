import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CityService {

  cityList:any=[{'cityId':1,'cityName':'Bagalkot','stateId':'1'},
                {'cityId':2,'cityName':'Bijapur','stateyd':'1'},
                {'cityId':3,'cityName':'Hubli','stateId':'1'},
                {'cityId':4,'cityName':'Belagavi','stateId':'1'},
                {'cityId':5,'cityName':'Bidar','stateId':'1'},
                {'cityId':6,'cityName':'Thiruvallur','stateId':'2'},
                {'cityId':7,'cityName':'Chennai North','stateId':'2'},
                {'cityId':7,'cityName':'Chennai South','stateId':'2'},
                {'cityId':7,'cityName':'Chennai Central','stateId':'2'},
                {'cityId':7,'cityName':'Sriperumbudur','stateId':'2'},
              ];
  cities:any=[];
  constructor() { }
  public getCity(){
    return this.cityList;
  }

  public getCityies(stateId:number):any{
    this.cities=[];
    for(let city of this.cityList){
      if(stateId==city.stateId){
        this.cities.push(city);
      }
    }

    return this.cities;
  }
}
