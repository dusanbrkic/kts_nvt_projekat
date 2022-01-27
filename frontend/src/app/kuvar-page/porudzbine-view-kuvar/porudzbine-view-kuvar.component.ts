import { Component, OnInit } from '@angular/core';
import { MenuItem, MessageService } from 'primeng/api';
import JeloPorudzbine from 'src/app/model/JeloPorudzbine';
import Porudzbina from '../../model/Porudzbina';
import { PorudzbinaService } from '../../services/porudzbina.service';

@Component({
  selector: 'app-porudzbine-view-kuvar',
  templateUrl: './porudzbine-view-kuvar.component.html',
  styleUrls: ['./porudzbine-view-kuvar.component.scss'],
})
export class PorudzbineViewKuvarComponent implements OnInit {
  novePorudzbine!: Porudzbina[];

  jelaUPripremi!: JeloPorudzbine[];

  displayModal: boolean = false;

  responsiveOptions: any[];

  items!: MenuItem[];

  tekst: string = '';
  naslov: string = '';

  constructor(
    private porudzbinaService: PorudzbinaService,
    private messageService: MessageService
  ) {
    this.responsiveOptions = [
      {
        breakpoint: '1024px',
        numVisible: 3,
        numScroll: 3,
      },
      {
        breakpoint: '768px',
        numVisible: 2,
        numScroll: 2,
      },
      {
        breakpoint: '560px',
        numVisible: 1,
        numScroll: 1,
      },
    ];
  }

  ngOnInit(): void {
    this.porudzbinaService.porudzbineZaPripremuKuvar();
    this.porudzbinaService.porudzbine$.subscribe(value=>{
      this.novePorudzbine=value;
      //console.log(value);
    });
    this.porudzbinaService.jelaUPripremi()
    this.porudzbinaService.jelaPorudzbine$.subscribe(value=>{
      this.jelaUPripremi=value;
      console.log(value);
    });

    // this.porudzbinaService.loadPorudzbineTest();
    // this.porudzbinaService.porudzbine$.subscribe((value) => {
    //   this.novePorudzbine = this.porudzbinaService.porudzbineZaPripremuKuvar();
    //   this.jelaUPripremi = this.porudzbinaService.jelaUPripremi();
    //   console.log(value);
    // });
  }

  showModalDialog(tekst: string, naslov: string) {
    this.tekst = tekst;
    this.naslov = naslov;
    this.displayModal = true;
  }

  preuzmiPorudzbinu(porudzbina: Porudzbina) {
    this.porudzbinaService.savePorudzbina(
      {
        ...porudzbina,
        jelaPorudzbine: porudzbina.jelaPorudzbine.map((j) =>
          j.statusJela === 'KREIRANO' ? { ...j, statusJela: 'PREUZETO' } : j
        ),
      },
      false
    );
  }

  spremiJelo(jelo: JeloPorudzbine) {
    this.porudzbinaService.spremiJelo(jelo)
    this.ngOnInit();
  }
}
