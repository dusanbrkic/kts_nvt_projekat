import { Component, OnInit } from '@angular/core';
import { LazyLoadEvent, MenuItem } from 'primeng/api';
import Jelo from 'src/app/model/Jelo';
import Predlog from 'src/app/model/Predlog';
import { JeloService } from 'src/app/services/jelo.service';
import { PredlogService } from 'src/app/services/predlog.service';

@Component({
  selector: 'app-predlozi-view',
  templateUrl: './predlozi-view.component.html',
  styleUrls: ['./predlozi-view.component.scss'],
})
export class PredloziViewComponent implements OnInit {
  predlozi!: Predlog[];
  loading: boolean = false;
  totalPredlozi: number = 0;
  selectedPredlog!: Predlog;

  items!: MenuItem[];

  tipovi!: any[];

  modalDialog: boolean=false;

  jelo!: Jelo;

  lastLazyLoadEvent!: LazyLoadEvent;

  constructor(private predlogService: PredlogService,private jeloService: JeloService) {}

  ngOnInit(): void {
    this.jeloService.loadJelaTest()
    this.predlogService.loadTest();
    this.predlogService.predlozi$.subscribe((value) => {
      this.predlozi = value;
    });

    this.tipovi = [
      { value: 'DODAVANJE', name: 'Dodavanje' },
      { value: 'BRISANJE', name: 'Brisanje' },
      { value: 'IZMENA', name: 'Izmena' },
    ];

    this.items = [
      {
        label: 'Opis',
        icon: 'pi pi-fw pi-search',
        command: () => this.showModalDialog(),
      },
    ];
  }

  loadPredlozi(event: LazyLoadEvent) {
    this.loading=true;

    this.predlogService.loadPredlozi(event)
    this.lastLazyLoadEvent=event;

    this.loading=false;
  }

  updatePredlog(predlog: Predlog, status: string){
    this.predlogService.updatePredlog(predlog,status)
    this.loadPredlozi(this.lastLazyLoadEvent)
  }

  showModalDialog(){
    this.modalDialog=true;
    if(this.selectedPredlog.staroJeloId){
      this.jelo=this.jeloService.getJeloById(this.selectedPredlog.staroJeloId!)
    }
  }
}
