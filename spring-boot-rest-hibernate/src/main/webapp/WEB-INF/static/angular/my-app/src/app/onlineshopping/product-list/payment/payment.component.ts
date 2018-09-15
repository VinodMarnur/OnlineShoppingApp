import { Component, OnInit } from '@angular/core';
import { CommonService } from '../../../commonservice/common.service';
import { AlertifyService } from '../../../alertify/alertify.service';
import { MatDialog,MatDialogConfig } from '../../../../../node_modules/@angular/material';
import { UserAddressComponent } from '../user-address/user-address.component';


@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  shippingAddress:any={};
  billingAddress:any={};
  shippingaddresslist:any=[];
  billingaddresslist:any=[];
  constructor(private commonService:CommonService,
              private alertifyServiece:AlertifyService,
              private dialog: MatDialog) { 
                this.getContactAddressList();
              }
  
  cartlist:any=[];
  totalCartAmount:number=0;
  filepath:string="../assets/ProductImages/";
  
  ngOnInit() {
    this.getCartList();
    this.getContactAddressList();
  }

  getCartList(){
       let x=this;
      if(localStorage.getItem("userId")!=undefined){
          x.commonService.post("http://localhost:8080/cart/cart-products",
          {"userId":localStorage.getItem("userId")}).subscribe(res=>{
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

  addAddress(userId){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.position = {
      'top': '50',
       left: '50'
    };
    let dialogRef = this.dialog.open(UserAddressComponent,{
      data: {
          title: "Add Biiling Addres"
       },
       width: '600px',
       height: '600px',
       position: { left: '50px',
                  top:'50px'
                },
       panelClass: 'epsSelectorPanel'
  });

  if(localStorage.getItem("userId")!=undefined){
     dialogRef.componentInstance.title="Add Address";
     dialogRef.componentInstance.isAdded=false;
     dialogRef.componentInstance.userId=parseInt(localStorage.getItem("userId"));
        let data={
          userId:localStorage.getItem("userId")
        }
        dialogRef.componentInstance.user=data;
     }
  dialogRef.afterClosed().subscribe(result => {
      if(dialogRef.componentInstance.isAdded==true){
        this.commonService.post("http://localhost:8080/user/get-contact-address-list",{userId:localStorage.getItem("userId")}).subscribe(res=>{
          this.shippingaddresslist=res;
          this.billingaddresslist=res;
        })
      }
  })

 
 }
 getContactAddressList(){
      this.commonService.post("http://localhost:8080/user/get-contact-address-list",{userId:localStorage.getItem("userId")}).subscribe(res=>{
        this.shippingaddresslist=res;
        this.billingaddresslist=res;
      })
  }

  getShippingAddress(contactAddressId){
    let x=this;
    let data={
        "contactAddressId":contactAddressId,
        "userId":localStorage.getItem("userId")
    }
    this.commonService.post("http://localhost:8080/user/get-contact-address",data).subscribe(res=>{
        x.shippingAddress=res;
        //x.user=x..user;
       // x.city=x.address.city;
    });

  }

  getBillingAddress(contactAddressId){
    let x=this;
    let data={
        "contactAddressId":contactAddressId,
        "userId":localStorage.getItem("userId")
    }
    this.commonService.post("http://localhost:8080/user/get-contact-address",data).subscribe(res=>{
        x.billingAddress=res;
    });

  }
}
