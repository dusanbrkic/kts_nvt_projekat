import { Component, OnInit, ViewChild } from '@angular/core';
import { LazyLoadEvent, MenuItem, MessageService } from 'primeng/api';
import { Inplace } from 'primeng/inplace';
import Jelo from 'src/app/model/Jelo';
import { AuthService } from 'src/app/services/auth.service';
import { JeloService } from 'src/app/services/jelo.service';
import { PredlogService } from 'src/app/services/predlog.service';

@Component({
  selector: 'app-menu-jela',
  templateUrl: './menu-jela.component.html',
  styleUrls: ['./menu-jela.component.scss'],
})
export class MenuJelaComponent implements OnInit {
  jela!: Jelo[];
  selectedJelo!: Jelo;
  displayModal: boolean = false;
  kategorije!: any[];
  tipovi!: any[];
  loading: boolean = false;
  totalJela: number = 0;
  userRole: string = '';

  @ViewChild('jelaInplace') jelaInplace!: Inplace;

  items!: MenuItem[];
  editing: boolean = false;
  clonedJela: { [s: string]: Jelo } = {};

  addJeloDialog: boolean = false;
  newJelo!: Jelo;
  submitted: boolean = false;

  predlog: boolean = false;

  private lastTableLazyLoadEvent!: LazyLoadEvent;

  constructor(
    private jeloService: JeloService,
    private messageService: MessageService,
    private authService: AuthService,
    private predlogService: PredlogService
  ) {}

  ngOnInit(): void {
    this.userRole = this.authService.getCurrentRole();
    this.jeloService.jela$.subscribe((value) => {
      this.jela = value;
    });
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
    ];

    if (this.authService.isLoggedIn()) {
      if (this.userRole === 'ROLE_GLAVNI_KUVAR') {
        this.items.push({
          label: 'Predlog izmene',
          icon: 'pi pi-fw pi-plus',
          command: () => this.openEditJelo(this.selectedJelo),
        });
        this.items.push({
          label: 'Predlog brisanja',
          icon: 'pi pi-fw pi-times',
          command: () => this.openEditJelo(this.selectedJelo),
        });
      }
      if (this.userRole === 'ROLE_MANAGER') {
        this.items.push({
          label: 'Delete',
          icon: 'pi pi-fw pi-times',
          command: () => this.deleteJelo(this.selectedJelo),
        });
      }
    }
  }

  loadJela(event: LazyLoadEvent) {
    this.loading = true;
    this.lastTableLazyLoadEvent = event;

    //console.log(event);

    //load jela here from backend with pagination
    this.jeloService.loadJela(event);

    this.loading = false;
  }

  showModalDialog() {
    this.displayModal = true;
  }

  closeJelaInplace() {
    this.jelaInplace.deactivate();
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
      this.jeloService.updateJelo(this.jela[index]);
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
    this.jeloService.removeJelo(jelo);
    this.messageService.add({
      severity: 'info',
      summary: 'Jelo obrisano',
      detail: jelo.naziv,
    });
    this.loadJela(this.lastTableLazyLoadEvent);
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

    if (this.predlog) {
      this.predlogService.addPredlog('IZMENA', this.newJelo, this.newJelo.id);
      this.predlog = false;
    } else {
      if (
        this.newJelo.naziv.trim() &&
        this.newJelo.trenutnaCena > 0 &&
        this.newJelo.vremePripremeMils > 0
      ) {
        if (this.userRole === 'ROLE_MANAGER') {
          this.jeloService.addJelo(this.newJelo);
          this.messageService.add({
            severity: 'success',
            summary: 'Successful',
            detail: 'Kreirano novo jelo',
            life: 3000,
          });
          this.addJeloDialog = false;
          this.loadJela(this.lastTableLazyLoadEvent);
        }
        else if(this.userRole === 'ROLE_GLAVNI_KUVAR'){
          this.predlogService.addPredlog('DODAVANJE', this.newJelo, undefined);
        }
      }
    }
  }

  openEditJelo(jelo: Jelo) {
    this.newJelo = jelo;
    this.submitted = false;
    this.predlog = true;
    this.addJeloDialog = true;
  }

  predlogBrisanja(jelo: Jelo) {
    this.predlogService.addPredlog('BRISANJE', undefined, jelo.id);
  }
}
