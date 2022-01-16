import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { LazyLoadEvent } from 'primeng/api';
import { Inplace } from 'primeng/inplace';
import Jelo from '../model/Jelo';
import Pice from '../model/Pice';
import { JeloService } from '../services/jelo.service';
import { PiceService } from '../services/pice.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss'],
})
export class MenuComponent implements OnInit {
  jela!: Jelo[];
  selectedJelo!: Jelo;
  displayModal: boolean = false;
  kategorije!: any[];
  tipovi!: any[];
  loading: boolean = false;
  totalJela: number = 0;

  pica!: Pice[];
  loadingPica: boolean= false;
  totalPica: number =0;

  @ViewChild('jelaInplace') jelaInplace!: Inplace;
  @ViewChild('picaInplace') picaInplace!: Inplace;

  constructor(private jeloService: JeloService,private piceService:PiceService) {}

  ngOnInit(): void {
    this.jeloService.loadJelaTest();
    this.jela = this.jeloService.getJela();
    this.totalJela = this.jela.length;
    this.kategorije = [
      { value: 'PREDJELO', name: 'Predjelo' },
      { value: 'GLAVNO_JELO', name: 'Glavno jelo' },
      { value: 'SALATA', name: 'Salata' },
      { value: 'OBROK_SALATA', name: 'Obrok salata' },
      { value: 'DEZERT', name: 'Dezert' },
    ];
    this.tipovi = [
      { value: 'LUX', name: 'Lux' },
      { value: 'BUSINESS', name: 'Buisness' },
      { value: 'BASIC', name: 'Basic' },
      { value: 'BUDGET', name: 'Budget' },
    ];

    this.piceService.loadPicaTest()
    this.pica=this.piceService.getPica();
    this.totalPica=this.pica.length;
  }

  loadJela(event: LazyLoadEvent) {
    //this.loading = true;

    console.log(event);

    setTimeout(() => {
      /*this.customerService.getCustomers({lazyEvent: JSON.stringify(event)}).then(res => {
                this.customers = res.customers;
                this.totalRecords = res.totalRecords;
                this.loading = false;
            })*/
    }, 1000);
  }

  loadPica(event: LazyLoadEvent) {
    //this.loadingPica = true;

    console.log(event);

    setTimeout(() => {
      /*this.customerService.getCustomers({lazyEvent: JSON.stringify(event)}).then(res => {
                this.customers = res.customers;
                this.totalRecords = res.totalRecords;
                this.loading = false;
            })*/
    }, 1000);
  }

  showModalDialog(jelo: Jelo) {
    this.displayModal = true;
    this.selectedJelo = jelo;
  }

  closeJelaInplace(){
    this.jelaInplace.deactivate()
  }

  closePicaInplace(){
    this.picaInplace.deactivate()
  }
}
