import { Injectable } from '@angular/core';

import alertify from 'alertify.js';

@Injectable({
  providedIn: 'root'
})

export class AlertifyService {

  constructor() { 
    alertify.logPosition("top right");
  }

  public success(messsage:string):any{
    alertify.logPosition("top right");
    alertify.success(messsage);
  }

  public error(messsage:string):any{
    alertify.logPosition("top right");
    alertify.error(messsage);
  }


  public confirm(){
    alertify.confirm('a callback will be invoked on cancel.').set('oncancel', function(closeEvent){ alertify.error('Cancel');} ); 
  }
}
