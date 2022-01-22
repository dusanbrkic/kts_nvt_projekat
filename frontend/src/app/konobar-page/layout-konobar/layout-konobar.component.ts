import { Component, OnInit } from '@angular/core';
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

  isNewPorudzbina: boolean = false;

  constructor(
    private zonaService: ZonaService,
    private jeloService: JeloService,
    private piceService: PiceService,
    private porudzbinaService: PorudzbinaService
  ) {}

  ngOnInit(): void {
    this.zonaService.loadZoneTest();
    this.porudzbinaService.loadPorudzbineTest();
    this.zonaService.zone$.subscribe((value) => {
      this.zone = value;
      console.log(value);
    });

    this.selectedZona = this.zone[0];

    this.jeloService.loadJelaTest();
    this.jeloService.jela$.subscribe((value) => (this.jela = value));

    this.piceService.loadPicaTest();
    this.piceService.pica$.subscribe((value) => (this.pica = value));
  }

  openSidebar(sto: Sto) {
    this.selectedSto = sto;
    this.selectedJelo = null;
    this.selectedPice = null;
    console.log(this.selectedSto);
    this.showSidebar = true;
    this.isNewPorudzbina = false;
    let por = this.porudzbinaService.getPorudzbinaById(
      this.selectedSto.porudzbinaId!
    );
    if (por) {
      this.selectedPorudzbina = JSON.parse(JSON.stringify(por));
    } else {
      this.selectedPorudzbina = por;
    }
  }

  createNewPorudzbina() {
    this.isNewPorudzbina = true;
    this.selectedPorudzbina = {
      id: Math.floor(Math.random() * (1000000 - 0 + 1) + 0),
      statusPorudzbine: 'KREIRANA',
      datumVreme: new Date(),
      napomena: '',
      ukupnaCena: 0,
      konobarId: -1,
      stoId: this.selectedSto.id!,
      jelaPorudzbine: [],
      picaPorudzbine: [],
    };
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
        };
        this.selectedPorudzbina.picaPorudzbine.push(newItem);
      } else {
        let newItem: JeloPorudzbine = {
          id: Math.floor(Math.random() * (1000000 - 0 + 1) + 0),
          kolicina: this.newKolicina,
          napomena: this.newNapomena,
          statusJela: 'KREIRANO',
          jelo: this.selectedJelo!,
          porudzbinaId: this.selectedPorudzbina.id,
        };
        this.selectedPorudzbina.jelaPorudzbine.push(newItem);
      }
      this.selectedPorudzbina.statusPorudzbine='KREIRANO'
      this.hideDialog();
    }
  }

  savePorudzbina() {
    this.porudzbinaService.savePorudzbina(
      this.selectedPorudzbina,
      this.isNewPorudzbina
    );
    if (this.isNewPorudzbina) {
      this.zonaService.updateTable(
        { ...this.selectedSto, porudzbinaId: this.selectedPorudzbina.id,zauzet: true },
        this.selectedZona
      );
    }
  }

  naplatiPorudzbinu(){
    this.porudzbinaService.savePorudzbina({...this.selectedPorudzbina,statusPorudzbine: 'NAPLACENO'},false)
    this.zonaService.updateTable(
      { ...this.selectedSto, porudzbinaId: null,zauzet: false },
      this.selectedZona
    );
    this.showSidebar=false
  }

  dostavi(pice: PicePorudzbine){
    pice.statusPica="DOSTAVLJENO"
  }

  dostaviJelo(jelo: JeloPorudzbine){
    jelo.statusJela="DOSTAVLJENO"
  }
}
