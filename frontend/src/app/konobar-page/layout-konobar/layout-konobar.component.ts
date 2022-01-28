import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import Jelo from 'src/app/model/Jelo';
import JeloPorudzbine from 'src/app/model/JeloPorudzbine';
import Pice from 'src/app/model/Pice';
import PicePorudzbine from 'src/app/model/PicePorudzbine';
import Porudzbina from 'src/app/model/Porudzbina';
import Sto from 'src/app/model/Sto';
import Zona from 'src/app/model/Zona';
import { JeloService } from 'src/app/services/jelo.service';
import { PiceService } from 'src/app/services/pice.service';
import { PorudzbinaService } from 'src/app/services/porudzbina.service';
import { ZonaService } from 'src/app/services/zona-service.service';
import { Base64Service } from 'src/app/utils/base64.service';

@Component({
  selector: 'app-layout-konobar',
  templateUrl: './layout-konobar.component.html',
  styleUrls: ['./layout-konobar.component.scss'],
})
export class LayoutKonobarComponent implements OnInit {
  zone!: Zona[];
  selectedZona!: Zona;

  showSidebar: boolean = false;
  selectedSto!: Sto;
  selectedPorudzbina!: Porudzbina;

  jela!: Jelo[];
  filteredJela!: Jelo[];
  selectedJelo!: Jelo | null;

  pica!: Pice[];
  filteredPica!: Pice[];
  selectedPice!: Pice | null;

  creatingPice: boolean = false;
  newItemDialog: boolean = false;
  newNapomena: string = '';
  newKolicina: number = 0;

  zoneGenericImgSrc: any = "http://localhost:4200/assets/generic/generic_zone.png";

  isNewPorudzbina: boolean = false;

  constructor(
    private zonaService: ZonaService,
    private jeloService: JeloService,
    private piceService: PiceService,
    private porudzbinaService: PorudzbinaService,
    private sanitizer: DomSanitizer,
    private base64Service: Base64Service,

  ) {}

  ngOnInit(): void {
    this.zonaService.loadZones(()=>{
      this.selectedZona = this.zone[0];
    });
    this.zonaService.zone$.subscribe((value) => {
      this.zone = value;
      console.log(value);
    });

    this.selectedZona = this.zone[0];

    this.jeloService.loadAllJela();
    this.jeloService.jela$.subscribe((value) => (this.jela = value));

    this.piceService.loadAllPica();
    this.piceService.pica$.subscribe((value) => (this.pica = value));
  }

  async openSidebar(sto: Sto) {
    this.selectedSto = sto;
    this.selectedJelo = null;
    this.selectedPice = null;
    console.log(this.selectedSto);
    
    this.isNewPorudzbina = false;
    let por: any;
    await this.porudzbinaService.getPorudzbinaById(
      this.selectedSto.porudzbinaId!, (response: any) => {por = response
      console.log(response)
      if (por) {
        alert("alertic")
        this.selectedPorudzbina = JSON.parse(JSON.stringify(por));
      } else {
        this.selectedPorudzbina = por;
      }
    }
    );
    this.showSidebar = true;

  }

  createNewPorudzbina() {
    this.isNewPorudzbina = true;
    this.selectedPorudzbina = {
      id: Math.floor(Math.random() * (1000000 - 0 + 1) + 0),
      statusPorudzbine: 'KREIRANO',
      datumVreme: new Date(),
      napomena: '',
      ukupnaCena: 0,
      stoId: this.selectedSto.id!,
      jelaPorudzbine: [],
      picaPorudzbine: [],
    };
  }

  getTemplatePic(stringPic: string) {
    if (stringPic.length > 0) {
      return this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(this.base64Service.decode(stringPic)));
    } else return this.zoneGenericImgSrc;

  }

  openNewItemDialogForJelo() {
    this.newItemDialog = true;
    this.newKolicina = 0;
    this.newNapomena = '';
  }

  openNewItemDialogForPice() {
    this.newItemDialog = true;
    this.creatingPice = true;
    this.newKolicina = 0;
    this.newNapomena = '';
  }

  hideDialog() {
    this.newItemDialog = false;
    this.newKolicina = 0;
    this.newNapomena = '';
    this.creatingPice = false;
  }

  addItem() {
    if (this.newKolicina > 0) {
      if (this.creatingPice) {
        let newItem: PicePorudzbine = {
          id: Math.floor(Math.random() * (1000000 - 0 + 1) + 0),
          kolicina: this.newKolicina,
          napomena: this.newNapomena,
          statusPica: 'KREIRANO',
          pice: this.selectedPice!,
          porudzbinaId: this.selectedPorudzbina.id,
          piceId: Math.floor(Math.random() * (1000000 - 0 + 1) + 0),
        };

        if (!this.isNewPorudzbina){
          let addedItem: PicePorudzbine
          this.porudzbinaService.addPiceToPorudzbina(newItem, this.selectedPorudzbina.id, (response: any) => {addedItem = response
            console.log(response)
            if (addedItem) {
              this.selectedPorudzbina.picaPorudzbine.push(addedItem);
            }
          });
        }else{
          this.selectedPorudzbina.picaPorudzbine.push(newItem);
        }


      } else {
        let newItem: JeloPorudzbine = {
          id: Math.floor(Math.random() * (1000000 - 0 + 1) + 0),
          kolicina: this.newKolicina,
          napomena: this.newNapomena,
          statusJela: 'KREIRANO',
          jelo: this.selectedJelo!,
          porudzbinaId: this.selectedPorudzbina.id,
        };

        if (!this.isNewPorudzbina){
          let addedItem: JeloPorudzbine
          this.porudzbinaService.addJeloToPorudzbina(newItem, this.selectedPorudzbina.id, (response: any) => {addedItem = response
            console.log(response)
            if (addedItem) {
              this.selectedPorudzbina.jelaPorudzbine.push(addedItem);
            }
          });
        }else{
          this.selectedPorudzbina.jelaPorudzbine.push(newItem)
        }
      }
      this.selectedPorudzbina.statusPorudzbine='KREIRANO'
      this.hideDialog();
    }
  }

  savePorudzbina() {
    this.porudzbinaService.savePorudzbina(
      this.selectedPorudzbina,
      this.isNewPorudzbina,
      (response: any) => {
        this.selectedPorudzbina = response
        console.log(response)
        this.selectedSto.porudzbinaId = this.selectedPorudzbina.id
          this.zonaService.updateTable(
            { ...this.selectedSto, porudzbinaId: this.selectedPorudzbina.id,zauzet: true },
            this.selectedZona
          );

      }
      );

  }

  naplatiPorudzbinu(){
    this.porudzbinaService.naplatiPorudzbinu(this.selectedPorudzbina, (response:any) =>{
      this.zonaService.updateTable(
        { ...this.selectedSto, porudzbinaId: null,zauzet: false },
        this.selectedZona
      );
      this.showSidebar=false
      // this.porudzbinaService.getPorudzbinaById(
      // this.selectedPorudzbina.id, (response: any) => {
      //   this.selectedPorudzbina = JSON.parse(JSON.stringify(response));
      // });

    })

  }

  dostavi(pice: PicePorudzbine){
    this.porudzbinaService.dostaviPice(pice, (response:any) => {
      pice.statusPica="DOSTAVLJENO"
      this.porudzbinaService.getPorudzbinaById(
        this.selectedPorudzbina.id, (response: any) => {
          this.selectedPorudzbina = JSON.parse(JSON.stringify(response));
        });
    });

  }

  dostaviJelo(jelo: JeloPorudzbine){
    this.porudzbinaService.dostaviJelo(jelo, (response:any) => {
      jelo.statusJela="DOSTAVLJENO"
      this.porudzbinaService.getPorudzbinaById(
        this.selectedPorudzbina.id, (response: any) => {
          this.selectedPorudzbina = JSON.parse(JSON.stringify(response));
        });
    });

  }
}
