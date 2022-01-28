import { Component, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { LazyLoadEvent, MenuItem, MessageService } from 'primeng/api';
import { Inplace } from 'primeng/inplace';
import Jelo from 'src/app/model/Jelo';
import { AuthService } from 'src/app/services/auth.service';
import { JeloService } from 'src/app/services/jelo.service';
import { PredlogService } from 'src/app/services/predlog.service';
import { Base64Service } from 'src/app/utils/base64.service';

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

  jeloGenericImgSrc: any = "http://localhost:4200/assets/generic/foodGeneric.png";

  novoJeloPic: any = {};
  novoJeloPicPreview: any = this.jeloGenericImgSrc;

  @ViewChild('fileUpload')
  fileUpload: any;

  cancelJeloPicBtnDisabled: any = true;

  constructor(
    private jeloService: JeloService,
    private messageService: MessageService,
    private authService: AuthService,
    private predlogService: PredlogService,
    private sanitizer: DomSanitizer,
    private base64Service: Base64Service,
  ) { }

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
          command: () => this.predlogBrisanja(this.selectedJelo),
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

  getJeloPic(stringPic: string) {
    // this is how to use the sanitizer and decode stringpics
    if (stringPic.length > 0) {
      return this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(this.base64Service.decode(stringPic)));
    } else return this.jeloGenericImgSrc;
  }

  onPicUpload(event: any) {
    this.novoJeloPic = event.currentFiles[0];
    this.novoJeloPicPreview = this.novoJeloPic.objectURL;
    this.cancelJeloPicBtnDisabled = false;
  }

  cancelPicBtnClicked(event: any) {
    this.novoJeloPic = {};
    this.novoJeloPicPreview = this.jeloGenericImgSrc;
    this.cancelJeloPicBtnDisabled = true;
  }

  doNothing(event: any) {
    this.fileUpload.clear();
  }


  loadJela(event: LazyLoadEvent) {
    this.loading = true;
    this.lastTableLazyLoadEvent = event;

    //console.log(event);

    //load jela here from backend with pagination
    this.jeloService.loadJela(event, (brka: number) => {
      this.totalJela = brka;
    });

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

  async deleteJelo(jelo: Jelo) {
    await this.jeloService.removeJelo(jelo.id);
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
      picBase64: '',
    };
    this.submitted = false;
    this.addJeloDialog = true;
  }

  hideDialog() {
    this.addJeloDialog = false;
    this.submitted = false;
  }

  async saveJelo() {
    this.submitted = true;

    if (this.predlog) {
      this.predlogService.addPredlog('IZMENA', (response: any) => {
        this.messageService.add({
          severity: response.ok ? 'success' : 'error',
          summary: response.ok ? 'Success' : 'Error',
          detail: response.body,
          life: 3000,
        });
      }, this.newJelo, this.newJelo.id);
      this.predlog = false;
      this.addJeloDialog = false;
    } else {
      if (
        this.newJelo.naziv.trim() &&
        this.newJelo.trenutnaCena > 0 &&
        this.newJelo.vremePripremeMils > 0
      ) {

        let that = this;
        if (Object.keys(this.novoJeloPic).length !== 0) {
          this.base64Service.encode(this.novoJeloPic, async (slikaString: any) => {
            that.newJelo.picBase64 = slikaString;

            if (this.userRole === 'ROLE_MANAGER') {
              await that.jeloService.addJelo(that.newJelo, (response: any) => {
                this.messageService.add({
                  severity: response.ok ? 'success' : 'error',
                  summary: response.ok ? 'Success' : 'Error',
                  detail: response.body,
                  life: 3000,
                });
              });

              this.addJeloDialog = false;
              this.loadJela(this.lastTableLazyLoadEvent);
            } else if (this.userRole === 'ROLE_GLAVNI_KUVAR') {
              await that.predlogService.addPredlog('DODAVANJE', (response: any) => {
                this.messageService.add({
                  severity: response.ok ? 'success' : 'error',
                  summary: response.ok ? 'Success' : 'Error',
                  detail: response.body,
                  life: 3000,
                });
              }, that.newJelo, undefined);
            }
          });
        } else {
          that.newJelo.picBase64 = "";

          if (this.userRole === 'ROLE_MANAGER') {
            await that.jeloService.addJelo(that.newJelo, (response: any) => {
              this.messageService.add({
                severity: response.ok ? 'success' : 'error',
                summary: response.ok ? 'Success' : 'Error',
                detail: response.body,
                life: 3000,
              });
            });

            this.addJeloDialog = false;
            this.loadJela(this.lastTableLazyLoadEvent);
          } else if (this.userRole === 'ROLE_GLAVNI_KUVAR') {
            await that.predlogService.addPredlog('DODAVANJE', (response: any) => {
              this.messageService.add({
                severity: response.ok ? 'success' : 'error',
                summary: response.ok ? 'Success' : 'Error',
                detail: response.body,
                life: 3000,
              });
            }, that.newJelo, undefined);
          }
        }

        this.novoJeloPic = {};
        this.novoJeloPicPreview = this.jeloGenericImgSrc;

        this.addJeloDialog = false;
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
    this.predlogService.addPredlog('BRISANJE', (response: any) => {
      this.messageService.add({
        severity: response.ok ? 'success' : 'error',
        summary: response.ok ? 'Success' : 'Error',
        detail: response.body,
        life: 3000,
      });
    }, undefined, jelo.id);
  }
}
