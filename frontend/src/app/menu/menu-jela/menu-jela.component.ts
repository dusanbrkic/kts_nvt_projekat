import { Component, OnInit, ViewChild } from '@angular/core';
import { LazyLoadEvent, MenuItem, MessageService } from 'primeng/api';
import { Inplace } from 'primeng/inplace';
import Jelo from 'src/app/model/Jelo';
import { JeloService } from 'src/app/services/jelo.service';

@Component({
  selector: 'app-menu-jela',
  templateUrl: './menu-jela.component.html',
  styleUrls: ['./menu-jela.component.scss']
})
export class MenuJelaComponent implements OnInit {

  jela!: Jelo[];
  selectedJelo!: Jelo;
  displayModal: boolean = false;
  kategorije!: any[];
  tipovi!: any[];
  loading: boolean = false;
  totalJela: number = 0;

  @ViewChild('jelaInplace') jelaInplace!: Inplace;

  items!: MenuItem[];
  editing: boolean = false;
  clonedJela: { [s: string]: Jelo } = {};

  addJeloDialog: boolean=false;
  newJelo!:Jelo;
  submitted: boolean=false;

  predlog: boolean=false;

  constructor(private jeloService: JeloService,private messageService: MessageService) { }

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

    this.items = [
      {
        label: 'Opis',
        icon: 'pi pi-fw pi-search',
        command: () => this.showModalDialog(),
      },
      {
        label: 'Predlog izmene',
        icon: 'pi pi-fw pi-plus',
        command: () => this.editJelo(this.selectedJelo),
      },
      {
        label: 'Predlog brisanja',
        icon: 'pi pi-fw pi-times',
        command: () => {},
      },
      {
        label: 'Delete',
        icon: 'pi pi-fw pi-times',
        command: () => this.deleteJelo(this.selectedJelo),
      },
    ];
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

  showModalDialog() {
    this.displayModal = true;
  }

  closeJelaInplace(){
    this.jelaInplace.deactivate()
  }

  onRowEditInit(jelo: Jelo) {
    this.editing = true;
    this.clonedJela[jelo.id] = { ...jelo };
  }

  onRowEditSave(jelo: Jelo, index: number) {
    if (
      jelo.trenutnaCena > 0 &&
      jelo.naziv !== '' &&
      jelo.vremePripremeMils > 0
    ) {
      delete this.clonedJela[jelo.id];
      this.editing = false;
      this.messageService.add({
        severity: 'success',
        summary: 'Success',
        detail: 'Jelo ažurirano',
      });
    } else {
      this.jela[index] = this.clonedJela[jelo.id];
      delete this.clonedJela[jelo.id];
      this.editing = false;
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Greška',
      });
    }
  }

  onRowEditCancel(jelo: Jelo, index: number) {
    this.jela[index] = this.clonedJela[jelo.id];
    delete this.clonedJela[jelo.id];
    this.editing = false;
  }

  deleteJelo(jelo: Jelo) {
    this.jela = this.jela.filter(
      (j) => j.id !== jelo.id
    );
    this.messageService.add({
      severity: 'info',
      summary: 'Jelo obrisano',
      detail: jelo.naziv,
    });
  }

  openNew() {
    this.newJelo = {
      naziv: '',
      opis: '',
      kategorijaJela: 'DEZERT',
      tipJela: 'LUX',
      id: Math.floor(Math.random() * (1000000 - 0 + 1) + 0),
      trenutnaCena: 0,
      vremePripremeMils: 0,
    };
    this.submitted = false;
    this.addJeloDialog = true;
  }

  hideDialog() {
    this.addJeloDialog = false;
    this.submitted = false;
  }

  saveJelo() {
    this.submitted = true;

    if(this.predlog){
      this.predlog=false;
    }else{
      if (
        this.newJelo.naziv.trim() &&
        this.newJelo.trenutnaCena > 0 &&
        this.newJelo.vremePripremeMils > 0
      ) {
        this.jela.push(this.newJelo);
        this.messageService.add({
          severity: 'success',
          summary: 'Successful',
          detail: 'Kreirano novo jelo',
          life: 3000,
        });
  
        this.jela = [...this.jela];
        this.addJeloDialog = false;
      }
    }

  }

  editJelo(jelo:Jelo){
    this.newJelo = jelo
    this.submitted = false;
    this.predlog= true
    this.addJeloDialog = true;
  }

}
