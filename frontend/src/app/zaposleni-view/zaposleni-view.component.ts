import { Component, OnInit } from '@angular/core';
import Zaposleni from '../model/Zaposleni';

@Component({
  selector: 'app-zaposleni-view',
  templateUrl: './zaposleni-view.component.html',
  styleUrls: ['./zaposleni-view.component.scss']
})
export class ZaposleniViewComponent implements OnInit {

  zaposleni!: Zaposleni[];

  constructor() { }

  ngOnInit(): void {
    this.zaposleni=[{
      ime: 'Marko',
      prezime: 'Markovic',
      pol: 'MUSKI',
      datumRodjenja: new Date(),
      trenutnaPlata: 200000,
      tipZaposlenja: 'GLAVNI_KUVAR',
      slikaString: '',
      identificationNumber: '124123'
    }]
  }

}
