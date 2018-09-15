import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { CommonService } from '../commonservice/common.service';

@Injectable({
  providedIn: 'root'
})
export class OnlineShoppingService {
    private count = new Subject<number>();
    constructor(private commonService :CommonService){
    }

    ngOnInit(){
    }

    getCartCount(){
        if(localStorage.getItem("userId")!=undefined){
            this.commonService.post("http://localhost:8080/cart/cart-count",{"userId":localStorage.getItem("userId")}).subscribe(res=>{
                this.count.next(res.cartCount);
                // return parseInt(res.cartCount);
            })
        }
     }
    checkcartCount(){
        this.getCartCount();
        return this.count.asObservable();
    }
}
