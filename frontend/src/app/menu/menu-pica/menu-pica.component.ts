import { Component, OnInit, ViewChild } from '@angular/core';
import { LazyLoadEvent, MenuItem, MessageService } from 'primeng/api';
import { Inplace } from 'primeng/inplace';
import Pice from 'src/app/model/Pice';
import { PiceService } from 'src/app/services/pice.service';

@Component({
  selector: 'app-menu-pica',
  templateUrl: './menu-pica.component.html',
  styleUrls: ['./menu-pica.component.scss'],
})
export class MenuPicaComponent implements OnInit {
  pica!: Pice[];
  loadingPica: boolean = false;
  totalPica: number = 0;
  loading: boolean = false;

  @ViewChild('picaInplace') picaInplace!: Inplace;

  items!: MenuItem[];
  selectedPice!: Pice;
  editing: boolean = false;
  clonedPica: { [s: string]: Pice } = {};

  newPice!: Pice;
  submitted: boolean = false;
  piceDialog: boolean = false;

  private lastTableLazyLoadEvent!: LazyLoadEvent;

  constructor(
    private piceService: PiceService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.piceService.loadPicaTest();
    this.piceService.pica$.subscribe((value) => {
      this.pica = value;
      console.log(value);
    });
    this.totalPica = this.pica.length;

    this.items = [
      {
        label: 'Delete',
        icon: 'pi pi-fw pi-times',
        command: () => this.deletePice(this.selectedPice),
      },
    ];
  }

  loadPica(event: LazyLoadEvent) {
    this.loadingPica = true;
    this.lastTableLazyLoadEvent = event;

    console.log(event);

    //load pica here from backend with pagination
    this.piceService.loadPica(event)


    this.loadingPica = false;
  }

  closePicaInplace() {
    this.picaInplace.deactivate();
  }

  openNew() {
    this.newPice = {
      naziv: '',
      id: Math.floor(Math.random() * (1000000 - 0 + 1) + 0),
      trenutnaCena: 0,
    };
    this.submitted = false;
    this.piceDialog = true;
  }

  hideDialog() {
    this.piceDialog = false;
    this.submitted = false;
  }

  savePice() {
    this.submitted = true;

    if (
      this.newPice.naziv.trim() &&
      this.newPice.trenutnaCena > 0
    ) {
      this.piceService.addPice(this.newPice)
      this.messageService.add({
        severity: 'success',
        summary: 'Successful',
        detail: 'Kreirano novo piće',
        life: 3000,
      });
      this.piceDialog = false;
      this.loadPica(this.lastTableLazyLoadEvent)
    }
  }

  onRowEditInit(pice: Pice) {
    this.editing = true;
    this.clonedPica[pice.id] = { ...pice };
  }

  onRowEditSave(pice: Pice, index: number) {
    if (pice.trenutnaCena > 0 && pice.naziv !== '') {
      delete this.clonedPica[pice.id];
      this.editing = false;
      this.piceService.updatePice(this.pica[index])
      this.messageService.add({
        severity: 'success',
        summary: 'Success',
        detail: 'Piće ažurirano',
      });
    } else {
      this.pica[index] = this.clonedPica[pice.id];
      delete this.clonedPica[pice.id];
      this.editing = false;
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Greška',
      });
    }
  }

  onRowEditCancel(pice: Pice, index: number) {
    this.pica[index] = this.clonedPica[pice.id];
    delete this.clonedPica[pice.id];
    this.editing = false;
  }

  deletePice(pice: Pice) {
    this.piceService.removePice(pice)
    this.messageService.add({
      severity: 'info',
      summary: 'Piće obrisano',
      detail: pice.naziv,
    });
    this.loadPica(this.lastTableLazyLoadEvent)
  }
}
