import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HeroComponent} from './hero/hero.component';
import { HomeComponent } from './home/home.component';
import { HerodescComponent } from './herodesc/herodesc.component';
import { EmployeeComponent } from './employee/employee.component';
import { EmplComponent } from './employee/empl/empl.component';
import {HeroFormComponent} from './hero/hero-form/hero-form.component';
import { CustomerComponent } from './customer-list/customer/customer.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth/auth.guard';
import { ViewProductComponent } from './onlineshopping/product-list/view-product/view-product.component';


const routes: Routes = [
  { path: '', component: LoginComponent,canActivate: [AuthGuard] },
  { path: '#', loadChildren: './onlineshopping/onlineshopping.module#OnlineshoppingModule',
  
  },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent,canActivate: [AuthGuard] },
  { path: 'herodesc/:id', component:   HerodescComponent,canActivate: [AuthGuard] },
  { path: 'employee-list', component: EmployeeComponent,canActivate: [AuthGuard]},
  { path: 'add-employee', component: EmplComponent,canActivate: [AuthGuard] },
  { path: 'update-employee/:id', component: EmplComponent,canActivate: [AuthGuard] },
  { path: 'hero-form', component: HeroFormComponent,canActivate: [AuthGuard] },
  { path: 'add-customer', component: CustomerComponent,canActivate: [AuthGuard] }, 
  { path: 'customer-list', component: CustomerListComponent,canActivate: [AuthGuard] },
  { path: 'update-customer/:id', component: CustomerComponent,canActivate: [AuthGuard] },
  { path: 'heros', component: HeroComponent,canActivate: [AuthGuard] }, 
  { path: '**', redirectTo: ''}
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}