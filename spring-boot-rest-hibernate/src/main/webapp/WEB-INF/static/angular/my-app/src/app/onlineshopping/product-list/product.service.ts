import { Injectable } from '@angular/core';
import { CommonService } from '../../commonservice/common.service';
import {Observable} from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  baseUrl = environment.baseUrl;

  constructor(private commonService:CommonService) { }

 
  getAllProducts():Observable<any>{
        return this.commonService.get(this.baseUrl+'products/product-list');
  }

  getSerchProducts(search:any):Observable<any>{
    return this.commonService.post(this.baseUrl+'products/search-product',search);
  }
}
