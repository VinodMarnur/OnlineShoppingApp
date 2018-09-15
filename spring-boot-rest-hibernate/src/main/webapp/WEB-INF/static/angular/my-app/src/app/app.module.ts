import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HelloComponent } from './hello.component';
import { HeroComponent } from './hero/hero.component';
import { HeaderComponent } from './header1/header.component';
import { HomeComponent } from './home/home.component';
import { HerodescComponent } from './herodesc/herodesc.component';
import { HeroService } from './hero.service';
import { EmployeeService } from './employee.service';
import { EmployeeComponent } from './employee/employee.component';
import { EmplComponent } from './employee/empl/empl.component';
import { HeroFormComponent } from './hero/hero-form/hero-form.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { BootstrapModalModule } from 'ng2-bootstrap-modal';
import { CityService } from './service/city.service';
import { SortPipe } from './pipes/sort.pipe';
import { AlertifyService } from './alertify/alertify.service';
import { HttpClientModule } from '@angular/common/http';
import { CommonService } from './commonservice/common.service';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CustomerComponent } from './customer-list/customer/customer.component';
import { LoginComponent } from './login/login.component';
import { AppMaterialModule } from './app-material/app-material.module';
import { AuthService } from './auth/auth.service';
import { AuthGuard } from './auth/auth.guard';
import { EmpListDatatableComponent } from './emp-list-datatable/emp-list-datatable.component';
import {TranslateModule} from '@ngx-translate/core';
import { LocalizedDatePipe } from './pipes/localized-date.pipe';
import { NgxDeleteConfirmModule } from 'ngx-delete-confirm';
@NgModule({
  imports:      [ BrowserModule, 
                  FormsModule,
                  HttpClientModule,
                  AppRoutingModule,
                  BrowserAnimationsModule,
                  MaterialModule,
                  ReactiveFormsModule,
                  BootstrapModalModule,
                  AppMaterialModule,
                  TranslateModule.forRoot(),
                  NgxDeleteConfirmModule.forRoot()
                   ],
  declarations: [ AppComponent, 
                  HelloComponent,
                  HeroComponent, 
                  HeaderComponent, 
                  HomeComponent,
                  HerodescComponent,
                  EmployeeComponent,
                  EmplComponent, 
                  HeroFormComponent, 
                  LoginComponent,
                  SortPipe,
                  LocalizedDatePipe, 
                  CustomerListComponent,
                   CustomerComponent,
                   EmpListDatatableComponent
                    ],
  bootstrap:    [ AppComponent ],
  providers:    [ HeroService, 
                  EmployeeService,
                  CityService,
                  AlertifyService,
                  CommonService,
                  AuthService,
                  AuthGuard
                ]
})
export class AppModule { }
