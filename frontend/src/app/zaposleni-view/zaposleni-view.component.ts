import { Component, OnInit } from '@angular/core';
import { LazyLoadEvent, MenuItem, MessageService } from 'primeng/api';
import Zaposleni from '../model/Zaposleni';

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

  constructor(private messageService: MessageService) {}

  ngOnInit(): void {
    this.zaposleni = [
      {
        ime: 'Marko',
        prezime: 'Markovic',
        pol: 'MUSKI',
        datumRodjenja: new Date(),
        trenutnaPlata: 200000,
        tipZaposlenja: 'GLAVNI_KUVAR',
        slikaString: '',
        identificationNumber: '124123',
      },
    ];

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
        command: () => this.deleteProduct(this.selectedZ),
      },
    ];
  }

  loadZaposleni(event: LazyLoadEvent) {
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

  deleteProduct(z: Zaposleni) {
    this.zaposleni = this.zaposleni.filter(
      (za) => za.identificationNumber !== z.identificationNumber
    );
    this.messageService.add({
      severity: 'info',
      summary: 'Zaposleni obrisan',
      detail: z.ime + ' ' + z.prezime,
    });
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

  saveProduct() {
    this.submitted = true;

    if (
      this.newZaposleni.ime.trim() &&
      this.newZaposleni.prezime.trim() &&
      this.newZaposleni.trenutnaPlata > 0
    ) {
      this.zaposleni.push(this.newZaposleni);
      this.messageService.add({
        severity: 'success',
        summary: 'Successful',
        detail: 'Kreiran novi zaposleni',
        life: 3000,
      });

      this.zaposleni = [...this.zaposleni];
      this.zaposleniDialog = false;
    }
  }
}
