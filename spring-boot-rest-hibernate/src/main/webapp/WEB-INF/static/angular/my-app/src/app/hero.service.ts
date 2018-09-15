import { Injectable } from '@angular/core';

@Injectable()
export class HeroService {

  constructor() { }

 hero:any;
 herolist:any[];
  getAllHeros(){
 this.herolist=[{
      'name':'Punith',
      'age':45,
      'dob':'28-04-188',
      'recent-movie-acted':'Rajakumar'
    }
    ,{
      'name':'Punith',
      'age':20,
      'dob':'28-04-188',
      'recent-movie-acted':'Rajakumar'
    }
    ,{
      'name':'Punith',
      'age':20,
      'dob':'28-04-188',
      'recent-movie-acted':'Rajakumar'
    }
    ,{
      'name':'Punith',
      'age':20,
      'dob':'28-04-188',
      'recent-movie-acted':'Rajakumar'
    }]
    return this.herolist;
  }

  getHero(id){
    this.hero={
      'name':'Punith',
      'age':20,
      'dob':'28-04-188',
      'recent-movie-acted':'Rajakumar'
    }
    return this.hero;
  }

}