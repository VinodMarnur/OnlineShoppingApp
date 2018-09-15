import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import {HttpClient,HttpResponse}  from '@angular/common/http'
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class CommonService {

  config:any={url:'http://localhost:8080'};
  getData:any=[];

  baseUrl = environment.baseUrl;

  constructor(private httpClient:HttpClient) { }

  post(url:string,postdata:any):Observable<any>{  
    return this.httpClient.post<any>(url,postdata);
  }

  get(url:string):Observable<any>{
    return  this.httpClient.get<any>(url,{responseType: 'json'})
  }
}
