import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JeloService } from '../services/jelo.service';

@Component({
  selector: 'app-jelo',
  templateUrl: './jelo.component.html',
  styleUrls: ['./jelo.component.scss']
})
export class JeloComponent implements OnInit {

  jeloId : any;
  jelo : any;

  constructor(
    private route      : ActivatedRoute,
    private jeloService: JeloService,
  ) { }

  ngOnInit(): void {
    this.jeloId = this.route.snapshot.paramMap.get("id");
    this.getJelo();
  }

  getJelo(): void{
    this.jeloService.getJeloById(this.jeloId).subscribe((data:any)=>{
      this.jelo = data;
      console.log(this.jelo);
    })
  }

}
