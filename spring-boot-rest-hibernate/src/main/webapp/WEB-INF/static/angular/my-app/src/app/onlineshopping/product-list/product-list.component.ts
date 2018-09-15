import { Component, OnInit } from '@angular/core';
import { ProductService } from './product.service';
import { MatTableDataSource } from '@angular/material';
import { Router } from '@angular/router';
import { MatDialog , MatPaginator,MatDialogRef,MatDialogConfig,MatSort } from '@angular/material';
import { ViewProductComponent } from './view-product/view-product.component';
import { CommonService } from '../../commonservice/common.service';
import { OnlineShoppingService } from '../online-shopping.service';
import { AlertifyService } from '../../alertify/alertify.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  filepath:string="../assets/ProductImages/";
  displayedColumns = ['id', 'username', 'salary', 'age'];
  dataSource = new MatTableDataSource<any>();
  searchproduct:string;

  emplComponent: MatDialogRef<ViewProductComponent>;
  

  productList:any;
  constructor(private router: Router,
    private commonService:CommonService,
    private productService:ProductService,
    private dialog: MatDialog,
    private onlineShoppingService:OnlineShoppingService,
    private alertifyServiece:AlertifyService) { }

  ngOnInit() {
    this.getAllProducts();
  }

  getAllProducts(){
    let productlist=this;
    this.productService.getAllProducts().subscribe(data=>productlist.productList=data);
  }

  searchProduct(value){
      let productlist=this;
      let search={productName:value}
      this.productService.getSerchProducts(search).
      subscribe(
            data=>productlist.productList=data
      );
  }



  viewProductMore(productId){
    const dialogConfig = new MatDialogConfig();
      dialogConfig.position = {
        'top': '50',
         left: '50'
    };
    let dialogRef = this.dialog.open(ViewProductComponent,{
        data: {
            title: "NWAS NTD"
        },
        width: '300px',
        height: '200px',
        position: { left: '50px',
                    top:'50px'
                  },
        panelClass: 'epsSelectorPanel'
    });

    let x=this;
        this.commonService.get("http://localhost:8080/products/product/"+productId)
        .subscribe(res=>{
          console.log(res);
          dialogRef.componentInstance.productInfo=res;
        });
     }

     increaseCartCount(productId,productName){
          if(localStorage.getItem("userId")!=undefined){
            let data={
              "productId":productId,
              "userId":localStorage.getItem("userId")
            }
            this.commonService.post("http://localhost:8080/cart/add-to-cart",data).subscribe(res=>{
              this.onlineShoppingService.checkcartCount();
              this.alertifyServiece.success(productName+" is added to cart")
            })
          }
     }
  }


