import { Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { OnlineShoppingService } from '../onlineshopping/online-shopping.service';

@Component({
  selector: 'app-header1',
  templateUrl: './header.component.html',
  styles: [
    `.angular-logo {
        margin: 0 4px 3px 0;
        height: 35px;
        vertical-align: middle;
    }
    .fill-remaining-space {
      flex: 1 1 auto;
    }
    `
  ]
})
export class HeaderComponent implements OnInit {
  isLoggedIn: boolean;
  count:number=0;
  constructor(private authService: AuthService,
              private onlineShoppingService:OnlineShoppingService) { 
              this.isLoggedIn = this.authService.isLoggedIn;
              this.authService.checkLoggedIn()
                .subscribe((data)=>{
                  this.isLoggedIn = data;
                });
                this.onlineShoppingService.checkcartCount().subscribe(res=>{
                    this.count=res;
                })
  }

  ngOnInit() {
    
  }

  onLogout() {
    this.count=0;
    this.authService.logout();
  }

}
