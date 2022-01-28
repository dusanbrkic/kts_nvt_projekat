import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import Sto from 'src/app/model/Sto';
import Zona from 'src/app/model/Zona';
import { ZonaService } from 'src/app/services/zona-service.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Base64Service } from 'src/app/utils/base64.service';

@Component({
  selector: 'app-layout-admin',
  templateUrl: './layout-admin.component.html',
  styleUrls: ['./layout-admin.component.scss'],
})
export class LayoutAdminComponent implements OnInit {
  zone!: Zona[];
  selectedZona!: Zona;

  displayModal: boolean = false;
  selectedSto!: Sto;
  newNaziv: string = '';
  newBroj: number = 0;
  errors: boolean = false;

  displayModalNewZone: boolean = false;
  newZoneNaziv: string = '';

  displayModalNazivZone: boolean = false;
  updateNaziv: string = '';

  @ViewChild("zoneTemplateDiv")
  zoneTemplateDiv!: ElementRef;

  templateURL: any;

  zoneGenericImgSrc: any = "http://localhost:4200/assets/generic/generic_zone.png";

  novaZonaTemplatePic: any = {};
  novaZonaTemplatePicPreview: any = this.zoneGenericImgSrc;
  novaZonaTemplateCancelBtnDisabled: any = true;

  @ViewChild('templatePicUpload')
  fileUpload: any;

  constructor(
    private elem: ElementRef,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private zonaService: ZonaService,
    private sanitizer: DomSanitizer,
    private base64Service: Base64Service,
  ) { }

  ngOnInit(): void {
    this.zonaService.zone$.subscribe((value) => {
      this.zone = value;
      console.log(value);
    });

    this.zonaService.loadZones(() => {
      this.selectedZona = this.zone[0];
      this.templateURL = this.getTemplatePic(this.selectedZona.templateBase64);
    });

  }

  onTemplatePicUpload(event: any) {
    this.novaZonaTemplatePic = event.currentFiles[0];
    this.novaZonaTemplatePicPreview = this.novaZonaTemplatePic.objectURL;
    this.novaZonaTemplateCancelBtnDisabled = false;
  }

  cancelProfilePicBtnClicked(event: any) {
    this.novaZonaTemplatePic = {};
    this.novaZonaTemplatePicPreview = this.zoneGenericImgSrc;
    this.novaZonaTemplateCancelBtnDisabled = true;
  }

  doNothing(event: any) {
    this.fileUpload.clear();
  }

  getTemplatePic(stringPic: string) {
    if (stringPic.length > 0) {
      return this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(this.base64Service.decode(stringPic)));
    } else return this.zoneGenericImgSrc;

  }

  onDragEnded(event: any) {
    let element = event.source.getRootElement();
    let boundingClientRect = element.getBoundingClientRect();
    let parentPosition = this.getPosition(element);
    console.log(element.offsetLeft);
    console.log(element.offsetTop);
    console.log(
      'x: ' + (boundingClientRect.x - parentPosition.left),
      'y: ' + (boundingClientRect.y - parentPosition.top)
    );
    this.selectedZona.stolovi.forEach((sto) => {
      if (sto.id == element.id) {
        this.zonaService.updateTable(
          {
            ...sto,
            x: sto.x + boundingClientRect.x - parentPosition.left,
            y: sto.y + boundingClientRect.y - parentPosition.top,
          },
          this.selectedZona
        );
      }
    });
    event.source._dragRef.reset();
  }

  getPosition(el: any) {
    let x = 0;
    let y = 0;
    while (el && !isNaN(el.offsetLeft) && !isNaN(el.offsetTop)) {
      x += el.offsetLeft - el.scrollLeft;
      y += el.offsetTop - el.scrollTop;
      el = el.offsetParent;
    }
    return { top: y, left: x };
  }

  addTable() {
    const uuid = Math.floor(Math.random() * (1000000 - 0 + 1) + 0);
    this.zonaService.addTable(
      {
        id: uuid,
        zauzet: false,
        brojMesta: 4,
        x: 0,
        y: 0,
        naziv: 'Novi sto',
        porudzbinaId: -1,
      },
      this.selectedZona
    );
  }

  deleteTable() {
    this.zonaService.removeTable(this.selectedSto, this.selectedZona);
    this.displayModal = false;
  }

  selectSto(sto: Sto) {
    this.errors = false;
    this.selectedSto = sto;
    this.displayModal = true;
    this.newNaziv = sto.naziv;
    this.newBroj = sto.brojMesta;
  }

  save() {
    console.log(this.selectedZona);
    this.zonaService.saveZone(this.selectedZona, (response: any) => {
      this.messageService.add({
        severity: response.ok ? "success" : "error",
        summary: response.body,
        detail: 'Izmene sačuvane',
        life: 3000,
      });
    });
  }

  saveTable() {
    if (this.newNaziv === '' || this.newBroj === null) {
      this.errors = true;
      return;
    }
    this.errors = false;
    this.zonaService.updateTable(
      { ...this.selectedSto, naziv: this.newNaziv, brojMesta: this.newBroj },
      this.selectedZona
    );
    this.displayModal = false;
  }

  addZone() {
    this.displayModalNewZone = true;
  }

  addNewZone() {
    if (this.newZoneNaziv === '') {
      return;
    }

    let novaZona = {
      id: Math.floor(Math.random() * (1000000 - 0 + 1) + 0),
      naziv: this.newZoneNaziv,
      stolovi: [],
      templateBase64: '',
    };

    const that = this;
    if (Object.keys(this.novaZonaTemplatePic).length !== 0) {
      this.base64Service.encode(this.novaZonaTemplatePic, async (slikaString: any) => {
        novaZona.templateBase64 = slikaString;
        await that.zonaService.addZone(novaZona, (response: any) => {
          this.messageService.add({
            severity: response.ok ? 'success' : 'error',
            summary: response.body,
            detail: response.body,
            life: 3000,
          });
        });
      });
    } else {
      novaZona.templateBase64 = "";
      that.zonaService.addZone(novaZona, (response: any) => {
        this.messageService.add({
          severity: response.ok ? 'success' : 'error',
          summary: response.ok ? 'Success' : 'Error',
          detail: response.body,
          life: 3000,
        });
      });
    }

    this.novaZonaTemplatePic = {};
    this.novaZonaTemplatePicPreview = this.zoneGenericImgSrc;

    this.displayModalNewZone = false;
  }

  deleteZone() {
    this.confirmationService.confirm({
      message:
        'Da li Ste sigurni da želite da obrišete zonu ' +
        this.selectedZona.naziv +
        '?',
      header: 'Potvrda',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.zonaService.removeZone(this.selectedZona,
          (response: any) => {
            this.messageService.add({
              severity: response.ok ? "success" : "error",
              summary: response.body,
              detail: 'Zona obrisana',
              life: 3000,
            })
          });
        this.selectedZona = this.zone[0];
      },
    });
  }

  openNazivModal() {
    this.displayModalNazivZone = true;
    this.updateNaziv = this.selectedZona.naziv;
  }

  changeNaziv() {
    if (this.updateNaziv.length === 0) {
      return;
    }
    this.zonaService.updateZone({
      ...this.selectedZona,
      naziv: this.updateNaziv,
    });
    this.displayModalNazivZone = false;
  }
}
