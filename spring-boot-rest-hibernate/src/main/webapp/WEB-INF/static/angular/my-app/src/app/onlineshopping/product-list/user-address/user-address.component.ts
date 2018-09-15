import { Component, OnInit } from '@angular/core';
import { MatDialogRef} from '@angular/material';
import { CommonService } from '../../../commonservice/common.service';
import { AlertifyService } from '../../../alertify/alertify.service';

@Component({
  selector: 'app-user-address',
  templateUrl: './user-address.component.html',
  styleUrls: ['./user-address.component.css']
})
export class UserAddressComponent implements OnInit {
  user:any={};
  countrylist:any=[];
  statelist:any=[];
  citylist:any=[];
  title:string="";
  isAdded:boolean=false;
  countryId:number=0;
  stateId:number=0;
  cityId:number=0;
  userId:number=0;
  constructor(private commonService:CommonService,
              private alertifyService:AlertifyService,
              public dialogRef: MatDialogRef<UserAddressComponent>) { }

  ngOnInit() {
    this.loadCountry();
  }

  check(){
    console.log(this.user)
  }

  onClickSubmit(data){
      this.commonService.post("http://localhost:8080/user/save-contact-address",data).subscribe(res=>{
      console.log(res.status) 
      if(res.status===200){
            this.alertifyService.success("Address Added Successfully");
            this.isAdded=true;
            this.dialogRef.close();

        }else{
          this.alertifyService.error(res.msg);
        }
      });
  }

  loadCountry(){
   let x=this;
    this.commonService.get("http://localhost:8080/common/contry-list").
    subscribe(res=>{
      x.countrylist=res;
    })
  }

  loadState(countryId){
    let x=this;
    let data={
      "countryId":countryId
    }
    this.commonService.post("http://localhost:8080/common/state-list",data).
    subscribe(res=>{
         x.statelist=res;
         x.countryId=0;
         x.stateId=0;
         x.cityId=0;
         x.citylist=[];
     })
   }

   loadCity(stateId){
    let x=this;
    let data={
      "stateId":stateId
    }
    this.commonService.post("http://localhost:8080/common/city-list",data).
    subscribe(res=>{
         x.citylist=res;
     })
   }

}

