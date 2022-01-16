import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import Porudzbina from '../model/Porudzbina';
import { PorudzbinaService } from '../services/porudzbina.service';

@Component({
  selector: 'app-porudzbine-view-kuvar',
  templateUrl: './porudzbine-view-kuvar.component.html',
  styleUrls: ['./porudzbine-view-kuvar.component.scss']
})
export class PorudzbineViewKuvarComponent implements OnInit {

  novePorudzbine!: Porudzbina[];

  porudzbineUPripremi!: Porudzbina[];

  displayModal: boolean = false;
  napomena : string='';

  constructor(private porudzbinaService:PorudzbinaService,private messageService: MessageService) { }

  ngOnInit(): void {
    this.porudzbinaService.loadPorudzbineTest();
    this.novePorudzbine=this.porudzbinaService.getPorudzbine();
    this.porudzbineUPripremi=[];
  }

  showModalDialog(napomena: string) {
    this.displayModal = true;
    this.napomena=napomena;
  }

}
