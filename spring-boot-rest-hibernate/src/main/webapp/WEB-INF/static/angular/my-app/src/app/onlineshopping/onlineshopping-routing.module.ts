import { RouterModule }         from "@angular/router";
import { NgModule }             from '@angular/core';
import { ProductListComponent } from "./product-list/product-list.component";
import { ViewProductComponent } from "./product-list/view-product/view-product.component";
import { CartListComponent } from "./product-list/cart-list/cart-list.component";
import { OnlineComponent } from "./online/online.component";
import { PaymentComponent } from "./product-list/payment/payment.component";
const routes=[ 
              {  
                path: '',
                component: OnlineComponent,
                   children: [{
                                "path":'product-list',
                                component:ProductListComponent
                            },
                            {
                              path: 'view-product',
                              component: ViewProductComponent
                            },{
                                 path:'cart-list',
                                 component:CartListComponent,
                             },{
                                path:'check-out',
                                component:PaymentComponent
                            }]
                },
             ]

@NgModule({
    imports: [ RouterModule.forChild(routes) ],
    exports: [ RouterModule ]
  })
  export class OnlineShoppingRoutingModule {

  }