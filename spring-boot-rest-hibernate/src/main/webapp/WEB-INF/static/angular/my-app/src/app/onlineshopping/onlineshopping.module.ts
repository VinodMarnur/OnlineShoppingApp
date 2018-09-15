import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductListComponent } from './product-list/product-list.component';
import { OnlineShoppingRoutingModule } from './onlineshopping-routing.module';
import { AppMaterialModule } from '../app-material/app-material.module';
import { ViewProductComponent } from './product-list/view-product/view-product.component';
import { CartListComponent } from './product-list/cart-list/cart-list.component';
import { OnlineComponent } from './online/online.component';
import { PaymentComponent } from './product-list/payment/payment.component';
import { UserAddressComponent } from './product-list/user-address/user-address.component';
import { FormsModule } from '../../../node_modules/@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    OnlineShoppingRoutingModule,
    AppMaterialModule,
    FormsModule
  ],
  declarations: [ ProductListComponent, 
                  ViewProductComponent, 
                  CartListComponent,
                  OnlineComponent, 
                  PaymentComponent, 
                  UserAddressComponent
                ],
  entryComponents:[UserAddressComponent]
})
export class OnlineshoppingModule { }
