import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PiceService } from '../services/pice.service';

@Component({
  selector: 'app-pice',
  templateUrl: './pice.component.html',
  styleUrls: ['./pice.component.scss']
})
export class PiceComponent implements OnInit {

  piceId:any;
  pice:any;

  constructor(
    private route      : ActivatedRoute,
    private piceService: PiceService,
  ) { }

  ngOnInit(): void {
    this.piceId = this.route.snapshot.paramMap.get("id");
    this.getPice();
  }

  getPice():void{
    this.piceService.getPiceById(this.piceId).subscribe((data:any)=>{
      this.pice = data;
      console.log(this.pice);
    })
  }

}
