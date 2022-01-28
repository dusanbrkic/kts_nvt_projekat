import { Component, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { LazyLoadEvent, MenuItem, MessageService } from 'primeng/api';
import { Inplace } from 'primeng/inplace';
import Pice from 'src/app/model/Pice';
import { PiceService } from 'src/app/services/pice.service';
import { Base64Service } from 'src/app/utils/base64.service';

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

  piceGenericImgSrc: any = "http://localhost:4200/assets/generic/drinkGeneric.png";

  novoPicePic: any = {};
  novoPicePicPreview: any = this.piceGenericImgSrc;

  @ViewChild('fileUpload')
  fileUpload: any;

  cancelPicePicBtnDisabled: any = true;

  constructor(
    private piceService: PiceService,
    private messageService: MessageService,
    private sanitizer: DomSanitizer,
    private base64Service: Base64Service,
  ) {}

  ngOnInit(): void {
    //this.piceService.loadPicaTest();
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

  getPic(stringPic: string) {
    // this is how to use the sanitizer and decode stringpics
    if (stringPic.length > 0) {
      return this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(this.base64Service.decode(stringPic)));
    } else return this.piceGenericImgSrc;
  }

  onPicUpload(event: any) {
    this.novoPicePic = event.currentFiles[0];
    this.novoPicePicPreview = this.novoPicePic.objectURL;
    this.cancelPicePicBtnDisabled = false;
  }

  cancelPicBtnClicked(event: any) {
    this.novoPicePic = {};
    this.novoPicePicPreview = this.piceGenericImgSrc;
    this.cancelPicePicBtnDisabled = true;
  }

  doNothing(event: any) {
    this.fileUpload.clear();
  }


  loadPica(event: LazyLoadEvent) {
    this.loadingPica = true;
    this.lastTableLazyLoadEvent = event;
    console.log(event);
    this.piceService.loadPica(event, (brka: number) => {
      this.totalPica = brka;
    });

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
      picBase64: '',
    };
    this.submitted = false;
    this.piceDialog = true;
  }

  hideDialog() {
    this.piceDialog = false;
    this.submitted = false;
  }

  async savePice() {
    this.submitted = true;

    if (this.newPice.naziv.trim() && this.newPice.trenutnaCena > 0) {

      let that = this;
      if (Object.keys(this.novoPicePic).length !== 0) {
        this.base64Service.encode(this.novoPicePic, async (slikaString: any) => {
          that.newPice.picBase64 = slikaString;
          await that.piceService.addPice(that.newPice, (response: any) => {
            this.messageService.add({
              severity: response.ok ? 'success' : 'error',
              summary: response.ok ? 'Success' : 'Error',
              detail: response.body,
              life: 3000,
            });
          });
        });
      } else {
        that.newPice.picBase64 = "";
        await that.piceService.addPice(that.newPice, (response: any) => {
          this.messageService.add({
            severity: response.ok ? 'success' : 'error',
            summary: response.ok ? 'Success' : 'Error',
            detail: response.body,
            life: 3000,
          });
        });
      }

      this.novoPicePic = {};
      this.novoPicePicPreview = this.piceGenericImgSrc;

      this.piceDialog = false;
      this.loadPica(this.lastTableLazyLoadEvent);
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
      this.piceService.updatePice(this.pica[index]);
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

  async deletePice(pice: Pice) {
    await this.piceService.removePice(pice);
    this.messageService.add({
      severity: 'info',
      summary: 'Piće obrisano',
      detail: pice.naziv,
    });
    this.loadPica(this.lastTableLazyLoadEvent);
  }
}
