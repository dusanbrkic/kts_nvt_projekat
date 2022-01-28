import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import Porudzbina from '../../../model/Porudzbina';
import { PorudzbinaService } from '../../../services/porudzbina.service';

@Component({
  selector: 'app-porudzbine-view-sanker',
  templateUrl: './porudzbine-view-sanker.component.html',
  styleUrls: ['./porudzbine-view-sanker.component.scss']
})
export class PorudzbineViewSankerComponent implements OnInit {

  porudzbine!: Porudzbina[]
  displayModal: boolean = false;
  napomena : string='';

  openIdInput: boolean = false;
  idCallback: any;
  idCloseCallback: any;

  constructor(private porudzbinaService:PorudzbinaService,private messageService: MessageService) { }

  ngOnInit(): void {
    this.porudzbinaService.porudzbineZaPripremuSanker();
    this.porudzbinaService.porudzbine$.subscribe(value=>{
      this.porudzbine=value;
    });

    this.idCloseCallback=()=>{
      this.openIdInput=false;
    }
  }

  test(test:any){
    console.log(test);
  }

  showModalDialog(napomena: string) {
    this.displayModal = true;
    this.napomena=napomena;
  }

  pripremiPica(porudzbina: Porudzbina){
    this.openIdInput=true
    this.idCallback=()=>{
      this.porudzbinaService.spremiPica(porudzbina)
      this.openIdInput=false
    }
  }

  undoAction(){

  }

  reloadPorudzbine(){
    
  }

}
