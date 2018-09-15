import { Component, OnInit } from '@angular/core';
import { CommonService } from '../../../commonservice/common.service';
import { AlertifyService } from '../../../alertify/alertify.service';
import { OnlineShoppingService } from '../../online-shopping.service';

@Component({
  selector: 'app-cart-list',
  templateUrl: './cart-list.component.html',
  styleUrls: ['./cart-list.component.css']
})
export class CartListComponent implements OnInit {

  constructor(private commonService:CommonService,
              private alertifyServiece:AlertifyService,
              private onlineShoppingService:OnlineShoppingService) { }
  cartlist:any=[];
  totalCartAmount:number=0;
  filepath:string="../assets/ProductImages/";
  ngOnInit() {
    this.getCartList();
  }

  getCartList(){
    let x=this;
    console.log(localStorage.getItem("userId")+" ")
    if(localStorage.getItem("userId")!=undefined){
      x.commonService.post("http://localhost:8080/cart/cart-products",{"userId":localStorage.getItem("userId")}).subscribe(res=>{
        x.cartlist=res;
      });
    }
  }

  getCartPrice(cartlist){
    this.totalCartAmount=0;
    for(let cart of cartlist){
      this.totalCartAmount+=cart.cartProductCount*cart.product.price;
    }
    return this.totalCartAmount;
  }

  incrementDecrementProductCount(cartProductId,actionType,cartProductCount){
    if(localStorage.getItem("userId")!=undefined){
      let data={
        "productId":cartProductId,
        "userId":parseInt(localStorage.getItem("userId")),
        "actionType":actionType
      }
      if(actionType==="decrement" && cartProductCount===1){
           this.alertifyServiece.error("product range >=1"); 
      }else{
        this.commonService.post("http://localhost:8080/cart/increment-decrement-product-count",data).subscribe(res=>{
          this.getCartList();
        });
      }
    }
  }

  removeFromCart(cartId,productName){
    if(localStorage.getItem("userId")!=undefined){
      let data={
          "cartId":cartId,
         "userId":parseInt(localStorage.getItem("userId"))
      }
     if(confirm("Are you sure to delete "+productName)){
           this.commonService.post("http://localhost:8080/cart/delete-product-from-cart",data).subscribe(res=>{
          this.getCartList();
          this.onlineShoppingService.checkcartCount();
          this.alertifyServiece.success(productName+" removed from cart")
        });
      }
    }
  }
}
