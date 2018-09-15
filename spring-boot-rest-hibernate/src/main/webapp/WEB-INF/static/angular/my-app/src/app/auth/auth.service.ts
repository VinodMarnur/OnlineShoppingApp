import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Subject, Observable } from 'rxjs';
import { User } from './user';
import { OnlineShoppingService } from '../onlineshopping/online-shopping.service';
import { CommonService } from '../commonservice/common.service';
import { AlertifyService } from '../alertify/alertify.service';

@Injectable()
export class AuthService {

  private loggedIn = new Subject<boolean>();

  get isLoggedIn() {
   return  localStorage.getItem('userInfo')!=null;
  }

  constructor(
    private router: Router,
    private onlineShoppingService:OnlineShoppingService,
    private commonService:CommonService,
    private alertifyService:AlertifyService
  ) {}

  login(user: User) {
    if (user.userName !== '' && user.password !== '' ) {
      let data={"userName":user.userName,"password":user.password};
      this.commonService.post("http://localhost:8080/user/login",data).subscribe(res=>{
          if(res.msg==="success"){
            this.alertifyService.success("welcome Back "+user.userName);
            this.loggedIn.next(true);
            localStorage.setItem('userInfo',user.userName);
            localStorage.setItem('userId',res.userId);
            this.onlineShoppingService.checkcartCount();
            this.router.navigateByUrl('#/product-list');
          }else{
            this.alertifyService.error("invalid credential");
          }
      });
     }
  }

  logout() {
    this.loggedIn.next(false);
    localStorage.removeItem('userInfo');
    localStorage.removeItem('userId');
    this.router.navigate(['/login']);
  }

  checkLoggedIn(){
    return this.loggedIn.asObservable();
  }

  checkCartProductCount(){
     return this.onlineShoppingService.getCartCount();
  }
}
