import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import Porudzbina from '../../model/Porudzbina';
import { PorudzbinaService } from '../../services/porudzbina.service';

@Component({
  selector: 'app-porudzbine-view-sanker',
  templateUrl: './porudzbine-view-sanker.component.html',
  styleUrls: ['./porudzbine-view-sanker.component.scss']
})
export class PorudzbineViewSankerComponent implements OnInit {

  porudzbine!: Porudzbina[]
  displayModal: boolean = false;
  napomena : string='';

  constructor(private porudzbinaService:PorudzbinaService,private messageService: MessageService) { }

  ngOnInit(): void {
    this.porudzbinaService.loadPorudzbineTest()
    this.porudzbinaService.porudzbine$.subscribe(value=>this.porudzbine=this.porudzbinaService.porudzbineZaPripremuSanker())
  }

  showModalDialog(napomena: string) {
    this.displayModal = true;
    this.napomena=napomena;
  }

  pripremiPica(porudzbina: Porudzbina){
    /*porudzbina.picaPorudzbine.forEach(pice => {
      this.messageService.add({severity:'success', summary:'Service Message', detail:'Via MessageService'});
    });*/
    this.porudzbinaService.spremiPica(porudzbina)
  }

  undoAction(){

  }

  reloadPorudzbine(){
    
  }

}
