import { Component, OnInit, ViewChild } from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import { LazyLoadEvent, MenuItem, MessageService } from 'primeng/api';
import Zaposleni from '../model/Zaposleni';
import { Base64Service } from '../utils/base64.service';
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

  userGenericImgSrc: any = "http://localhost:4200/assets/home_images/generic_user.jpg";

  noviZaposleniprofilePic: any={};
  noviZaposleniprofilePicPreview: any = this.userGenericImgSrc;

  private lastTableLazyLoadEvent!: LazyLoadEvent;

  @ViewChild('fileUpload')
  fileUpload: any;

  cancelProfilePicBtnDisabled: any = true;

  constructor(
    private messageService: MessageService,
    private zaposleniService: ZaposleniService,
    private base64Service: Base64Service,
    private sanitizer:DomSanitizer,
  ) {}

  getProfilePic(zaposleni: Zaposleni){
    // this is how to use the sanitizer and decode stringpics
    if (zaposleni.slikaString.length > 0){
      return this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(this.base64Service.decode(zaposleni.slikaString)));
    } else return this.userGenericImgSrc;
  }

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

  onProfilePicUpload(event: any) {
    this.noviZaposleniprofilePic = event.currentFiles[0];
    this.noviZaposleniprofilePicPreview =  this.noviZaposleniprofilePic.objectURL;
    this.cancelProfilePicBtnDisabled = false;
  }

  cancelProfilePicBtnClicked(event:any){
    this.noviZaposleniprofilePic = {};
    this.noviZaposleniprofilePicPreview = this.userGenericImgSrc;
    this.cancelProfilePicBtnDisabled = true;
  }

  doNothing(event: any) {
    this.fileUpload.clear();
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

  async saveZaposleni() {
    this.submitted = true;

    if (
      this.newZaposleni.ime.trim() &&
      this.newZaposleni.prezime.trim() &&
      this.newZaposleni.trenutnaPlata > 0
    ) {

      let that = this;
      if(Object.keys(this.noviZaposleniprofilePic).length!==0){
        this.base64Service.encode(this.noviZaposleniprofilePic, async (slikaString : any) => {
          that.newZaposleni.slikaString = slikaString;
          await that.zaposleniService.addZaposleni(that.newZaposleni, (responseMessage:string) => {
            this.messageService.add({
              severity: 'success',
              summary: 'Successful',
              detail: responseMessage,
              life: 3000,
            });
          });
        });
      } else {
        that.newZaposleni.slikaString = "";
        await that.zaposleniService.addZaposleni(that.newZaposleni, (responseMessage:string) => {
          this.messageService.add({
            severity: 'success',
            summary: 'Successful',
            detail: responseMessage,
            life: 3000,
          });
        });
      }

      this.noviZaposleniprofilePic = {};
      this.noviZaposleniprofilePicPreview = this.userGenericImgSrc;

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
