import { Component, OnInit } from '@angular/core';
import { LazyLoadEvent, MenuItem, MessageService } from 'primeng/api';
import Zaposleni from '../model/Zaposleni';
import { ZaposleniService } from '../services/zaposleni.service';

@Component({
  selector: 'app-zaposleni-view',
  templateUrl: './zaposleni-view.component.html',
  styleUrls: ['./zaposleni-view.component.scss'],
})
export class ZaposleniViewComponent implements OnInit {
  zaposleni!: Zaposleni[];
  loading: boolean = false;
  totalZaposleni: number = 100;

  tipovi!: any[];
  polovi!: any[];

  clonedZaposleni: { [s: string]: Zaposleni } = {};

  selectedZ!: Zaposleni;
  items!: MenuItem[];
  editing: boolean = false;
  today: Date = new Date();

  zaposleniDialog: boolean = false;
  newZaposleni!: Zaposleni;
  submitted: boolean = false;

  private lastTableLazyLoadEvent!: LazyLoadEvent;

  constructor(
    private messageService: MessageService,
    private zaposleniService: ZaposleniService
  ) {}

  ngOnInit(): void {
    this.zaposleniService.loadZaposleniTest();
    this.zaposleniService.zaposleni$.subscribe((value) => {
      this.zaposleni = value;
      console.log(value);
    });

    this.tipovi = [
      { value: 'SANKER', name: 'Sanker' },
      { value: 'KONOBAR', name: 'Konobar' },
      { value: 'KUVAR', name: 'Kuvar' },
      { value: 'GLAVNI_KUVAR', name: 'Glavni kuvar' },
      { value: 'MENADZER', name: 'Menadzer' },
    ];

    this.polovi = [
      { value: 'MUSKI', name: 'Muski' },
      { value: 'ZENSKI', name: 'Zenski' },
    ];

    this.items = [
      {
        label: 'Delete',
        icon: 'pi pi-fw pi-times',
        command: () => this.deleteZaposleni(this.selectedZ),
      },
    ];
  }

  loadZaposleni(event: LazyLoadEvent) {
    this.loading = true;
    this.lastTableLazyLoadEvent = event;

    console.log(event);

    //load zaposleni here from backend with pagination
    this.zaposleniService.loadZaposleni(event)


    this.loading = false
  }

  onRowEditInit(z: Zaposleni) {
    this.editing = true;
    this.clonedZaposleni[z.identificationNumber] = { ...z };
  }

  onRowEditSave(z: Zaposleni, index: number) {
    if (
      z.trenutnaPlata > 0 &&
      z.ime !== '' &&
      z.prezime !== '' &&
      z.datumRodjenja < new Date()
    ) {
      delete this.clonedZaposleni[z.identificationNumber];
      this.editing = false;
      this.zaposleniService.updateJelo(this.zaposleni[index])
      this.messageService.add({
        severity: 'success',
        summary: 'Success',
        detail: 'Zaposleni ažuriran',
      });
    } else {
      this.zaposleni[index] = this.clonedZaposleni[z.identificationNumber];
      delete this.clonedZaposleni[z.identificationNumber];
      this.editing = false;
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Greška',
      });
    }
  }

  onRowEditCancel(z: Zaposleni, index: number) {
    this.zaposleni[index] = this.clonedZaposleni[z.identificationNumber];
    delete this.clonedZaposleni[z.identificationNumber];
    this.editing = false;
  }

  deleteZaposleni(z: Zaposleni) {
    this.zaposleniService.removeZaposleni(z)
    this.messageService.add({
      severity: 'info',
      summary: 'Zaposleni obrisan',
      detail: z.ime + ' ' + z.prezime,
    });
    this.loadZaposleni(this.lastTableLazyLoadEvent)
  }

  openNew() {
    this.newZaposleni = {
      ime: '',
      prezime: '',
      pol: 'MUSKI',
      datumRodjenja: new Date(),
      tipZaposlenja: 'KONOBAR',
      slikaString: '',
      identificationNumber:
        '' + Math.floor(Math.random() * (1000000 - 0 + 1) + 0),
      trenutnaPlata: 0,
    };
    this.submitted = false;
    this.zaposleniDialog = true;
  }

  hideDialog() {
    this.zaposleniDialog = false;
    this.submitted = false;
  }

  saveZaposleni() {
    this.submitted = true;

    if (
      this.newZaposleni.ime.trim() &&
      this.newZaposleni.prezime.trim() &&
      this.newZaposleni.trenutnaPlata > 0
    ) {
      this.zaposleniService.addZaposleni(this.newZaposleni)
      this.messageService.add({
        severity: 'success',
        summary: 'Successful',
        detail: 'Kreiran novi zaposleni',
        life: 3000,
      });
      this.zaposleniDialog = false;
      this.loadZaposleni(this.lastTableLazyLoadEvent)
    }
  }
}
