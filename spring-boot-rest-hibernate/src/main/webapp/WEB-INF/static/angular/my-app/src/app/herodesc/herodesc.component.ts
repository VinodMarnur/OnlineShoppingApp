import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HeroService} from '../hero.service';


@Component({
  selector: 'app-herodesc',
  templateUrl: './herodesc.component.html',
  styleUrls: ['./herodesc.component.css']
})
export class HerodescComponent implements OnInit {

  constructor(private route: ActivatedRoute,public heroService:HeroService) { }
  Id:any;
  ngOnInit() {
         this.route.params.subscribe(params => {
            this.Id = params["id"];
            console.log(this.Id)
        });
        this.heroService.getHero(1);
  }

}