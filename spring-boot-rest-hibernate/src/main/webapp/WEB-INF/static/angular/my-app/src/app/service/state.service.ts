import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StateService {
  stateList:any=[
                {'stateId':1,'stateName':'Karnataka','countryId':'1'},
                {'stateId':2,'stateName':'Chennai','countryId':'1'},
                {'stateId':3,'stateName':'Mumbai','countryId':'1'},
                {'stateId':4,'stateName':'Rajastan','countryId':'1'}];
  constructor() { 

  }
  state:any=[];
  public getState():any{
    return this.stateList;
  }

  public getStates(countryId:number):any{
    this.state=[];
    for(let state of this.stateList){
      if(state.countryId==countryId)
      {
        this.state.push(state);
      }

    }
    
    this.state.sort( function(name1, name2) {
	    if ( name1.stateName < name2.stateName ){
	    	return -1;
	    }else if( name1.stateName > name2.stateName ){
	        return 1;
	    }else{
	    	return 0;	
	    }
	});

    return this.state;
  }
}
