import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';
import JeloPorudzbine from '../model/JeloPorudzbine';
import PicePorudzbine from '../model/PicePorudzbine';
import Porudzbina from '../model/Porudzbina';

@Injectable({
  providedIn: 'root',
})
export class PorudzbinaService {
  private readonly _porudzbineSource = new BehaviorSubject<Porudzbina[]>([]);

  readonly porudzbine$ = this._porudzbineSource.asObservable();

  private readonly _jelaPorudzbineSource = new BehaviorSubject<JeloPorudzbine[]>([]);

  readonly jelaPorudzbine$ = this._jelaPorudzbineSource.asObservable();

  constructor(private http: HttpClient) {}

  getPorudzbine(): Porudzbina[] {
    return this._porudzbineSource.getValue();
  }

  private _setPorudzbine(porudzbine: Porudzbina[]): void {
    this._porudzbineSource.next(porudzbine);
  }

  getJelaPorudzbine(): JeloPorudzbine[] {
    return this._jelaPorudzbineSource.getValue();
  }

  private _setJelaPorudzbine(jelaPorudzbine: JeloPorudzbine[]): void {
    this._jelaPorudzbineSource.next(jelaPorudzbine);
  }

  async getPorudzbinaById(porudzbinaId: number, callback:any): Promise<Porudzbina> {
    //TO DO poziv na back umesto ovaj for each
    let por: Porudzbina
    if (porudzbinaId == -1)
      return por!;
    const httpZahtev = await this.http.get<Porudzbina>(environment.baseUrl + "porudzbine/" + porudzbinaId, {
      "responseType": 'json',
      "observe": 'response'
    }).toPromise()
    .then((response: any) => {
      console.log(response.body)
      callback(response.body)
      });
    return por!;
  }

  async addJeloToPorudzbina(jelo: JeloPorudzbine, porudzbinaId: number, callback:any) {
    const httpZahtev = await this.http
      .post(environment.baseUrl + 'jelo-porudzbine/', jelo,  {responseType : 'json', "observe": 'response'}).toPromise()
      .then((response:any)=>{
        callback(response.body)
      })
    
  }

  async addPiceToPorudzbina(pice: PicePorudzbine, porudzbinaId: number, callback:any) {
    const httpZahtev = await this.http
      .post(environment.baseUrl + 'pice-porudzbine/', pice,  {responseType : 'json', "observe": 'response'}).toPromise()
      .then((response:any)=>{
        callback(response.body)
      })
    
  }

  async savePorudzbina(porudzbina: Porudzbina, isNewPorudzbina: boolean, callback:any) {
    //TO DO dodati na back

    if (!isNewPorudzbina) {
      const httpZahtev = await this.http
      .post(environment.baseUrl + 'porudzbine/preuzmiPorudzbinu/' + porudzbina.id, {responseType : 'json', "observe": 'response'}).toPromise()
      .then((response:any)=>{
        this.jelaUPripremi();
        this.porudzbineZaPripremuKuvar();
      })

      // const porudzbine = this.getPorudzbine().map((p) =>
      //   p.id === porudzbina.id ? porudzbina : p
      // );
      // this._setPorudzbine(porudzbine);
    } else {
      const httpZahtev = await this.http
      .post(environment.baseUrl + 'porudzbine/', porudzbina,  {responseType : 'json', "observe": 'response'}).toPromise()
      .then((response:any)=>{
        callback(response.body);
      })
      // const porudzbine = [...this.getPorudzbine(), porudzbina];
      // this._setPorudzbine(porudzbine);
    }
  }

  async porudzbineZaPripremuKuvar() {
    
    let porudzbine: Porudzbina[];
    const httpZahtev = await this.http
      .get(environment.baseUrl + 'porudzbine/zaKuvara').toPromise()
      .then((data: any) => {
        porudzbine = data;
        console.log(porudzbine);
        this._setPorudzbine(porudzbine);
      });
  }

  async porudzbineZaPripremuSanker() {
    // TO DO umesto ovog, treba na backu nace sve porudzbine
    let porudzbine: Porudzbina[];
    let answ;
    const httpZahtev = await this.http
      .get(environment.baseUrl + 'porudzbine/zaSankera')
      .toPromise()
      .then((data: any) => {
        porudzbine = data;
        console.log(porudzbine);
        this._setPorudzbine(porudzbine);
      });

    /*
    const porudzbine = this.getPorudzbine().filter(
      (p) => p.statusPorudzbine === 'KREIRANO' && p.picaPorudzbine.length > 0
    );
    return porudzbine;
    */
  }

  async jelaUPripremi() {
    
    let jela:  JeloPorudzbine[]
    let answ;
    const httpZahtev = await this.http
      .get(environment.baseUrl + 'jelo-porudzbine/preuzeta').toPromise()
      .then ((data: any) => {
        jela = data;
        this._setJelaPorudzbine(jela)
      });
  }

  async spremiJelo(jelo: JeloPorudzbine) {
    const httpZahtev = await this.http
      .post(environment.baseUrl + 'jelo-porudzbine/pripremi/' + jelo.id, {responseType : 'json', "observe": 'response'}).toPromise()
      .then((response:any)=>{
        this.jelaUPripremi();
        this.porudzbineZaPripremuKuvar();
      })
    
  }

  async spremiPica(porudzbina: Porudzbina) {
    const httpZahtev = await this.http
      .get(environment.baseUrl + 'porudzbine/spremiPica/' + porudzbina.id).toPromise()
      .then((response:any)=>{

        this.porudzbineZaPripremuSanker();
      })
  }

  async dostaviJelo(jelo: JeloPorudzbine, callback: any) {
    const httpZahtev = await this.http
      .post(environment.baseUrl + 'jelo-porudzbine/dostavi/' + jelo.id, {responseType : 'json', "observe": 'response'}).toPromise()
      .then((response:any)=>{
        callback(response)
      })
    
  }

  async dostaviPice(pice: PicePorudzbine, callback: any) {
    const httpZahtev = await this.http
      .post(environment.baseUrl + 'pice-porudzbine/dostavi/' + pice.id, {responseType : 'json', "observe": 'response'}).toPromise()
      .then((response:any)=>{
        callback(response)
      })
    
  }

  async naplatiPorudzbinu(porudzbina: Porudzbina, callback: any) {
    const httpZahtev = await this.http
      .post(environment.baseUrl + 'porudzbine/naplati/' + porudzbina.id, {responseType : 'json', "observe": 'response'}).toPromise()
      .then((response:any)=>{
        callback(response)
      })
    
  }

  /*loadPorudzbine(): any {
    this.http.get(environment.baseUrl + 'porudzbine');
  }

  getPorudzbinaById(porudzbinaId: any): any {
    return this.http.get(environment.baseUrl + 'porudzbine/' + porudzbinaId);
  }

  getPorudzbineByStatus(status: any): any {
    return this.http.get(environment.baseUrl + 'porudzbine/status/' + status);
  }

  getPorudzbineByKonobar(konobarId: any): any {
    return this.http.get(
      environment.baseUrl + 'porudzbine/konobar/' + konobarId
    );
  }

  napraviPorudzbinu(porudzbina: any): any {
    return this.http.post(
      environment.baseUrl + 'porudzbine/' + porudzbina,
      porudzbina
    );
  }

  izmeniPorudzbinu(porudzbina: any): any {
    return this.http.put(environment.baseUrl + 'porudzbine/', porudzbina);
  }

  deletePorudzbina(porudzbinaId: any): any {
    return this.http.delete(environment.baseUrl + 'porudzbine/' + porudzbinaId);
  }*/

  loadPorudzbineTest(): void {
    const porudzbine: Porudzbina[] = [
      {
        id: 1,
        statusPorudzbine: 'KREIRANO',
        datumVreme: new Date(),
        napomena: 'Napomena dDS sD dasd ',
        ukupnaCena: 1040.0,
        stoId: 1,
        jelaPorudzbine: [
          {
            id: 1,
            kolicina: 2,
            napomena: 'Napomena dsadadas',
            statusJela: 'KREIRANO',
            porudzbinaId: 1,
            jelo: {
              id: 1,
              naziv: 'Jelo 1',
              trenutnaCena: 250.0,
              vremePripremeMils: 30000,
              opis: 'Opis 1',
              kategorijaJela: 'PREDJELO',
              tipJela: 'LUX',
            },
          },
          {
            id: 2,
            kolicina: 1,
            napomena: 'Napomena dsadadas',
            statusJela: 'KREIRANO',
            porudzbinaId: 1,
            jelo: {
              id: 1,
              naziv: 'Jelo 1',
              trenutnaCena: 250.0,
              vremePripremeMils: 30000,
              opis: 'Opis 1',
              kategorijaJela: 'PREDJELO',
              tipJela: 'LUX',
            },
          },
        ],
        picaPorudzbine: [
          {
            id: 1,
            kolicina: 2,
            napomena: 'Napomena dsadadas',
            statusPica: 'KREIRANO',
            porudzbinaId: 1,
            pice: {
              id: 1,
              naziv: 'Pice 1',
              trenutnaCena: 120.0,
            },
            piceId: 1,
          },
          {
            id: 2,
            kolicina: 1,
            napomena: '',
            statusPica: 'KREIRANO',
            porudzbinaId: 1,
            pice: {
              id: 2,
              naziv: 'Pice 2',
              trenutnaCena: 120.0,
            },
            piceId: 2,
          },
        ],
      },
      {
        id: 2,
        statusPorudzbine: 'DOSTAVLJENO',
        datumVreme: new Date(),
        napomena: 'Napomena dDS sD dasd ',
        ukupnaCena: 1040.0,
        stoId: 1,
        jelaPorudzbine: [],
        picaPorudzbine: [
          {
            id: 3,
            kolicina: 4,
            napomena: '',
            statusPica: 'DOSTAVLJENO',
            porudzbinaId: 2,
            pice: {
              id: 1,
              naziv: 'Pice 1',
              trenutnaCena: 120.0,
            },
            piceId: 3,
          },
        ],
      },
      {
        id: 3,
        statusPorudzbine: 'PRIPREMLJENO',
        datumVreme: new Date(),
        napomena: 'Napomena dDS sD dasd ',
        ukupnaCena: 1040.0,
        stoId: 1,
        jelaPorudzbine: [],
        picaPorudzbine: [
          {
            id: 3,
            kolicina: 4,
            napomena: '',
            statusPica: 'PRIPREMLJENO',
            porudzbinaId: 2,
            pice: {
              id: 1,
              naziv: 'Pice 1',
              trenutnaCena: 120.0,
            },
            piceId: 3,
          },
        ],
      },
      {
        id: 4,
        statusPorudzbine: 'KREIRANO',
        datumVreme: new Date(),
        napomena: 'Napomena dDS sD dasd ',
        ukupnaCena: 10400.0,
        stoId: 5,
        jelaPorudzbine: [
          {
            id: 1,
            kolicina: 2,
            napomena: 'Napomena dsadadas',
            statusJela: 'KREIRANO',
            porudzbinaId: 4,
            jelo: {
              id: 1,
              naziv: 'Jelo 1',
              trenutnaCena: 250.0,
              vremePripremeMils: 30000,
              opis: 'Opis 1',
              kategorijaJela: 'PREDJELO',
              tipJela: 'LUX',
            },
          },
          {
            id: 2,
            kolicina: 1,
            napomena: 'Napomena dsadadas',
            statusJela: 'KREIRANO',
            porudzbinaId: 4,
            jelo: {
              id: 1,
              naziv: 'Jelo 1',
              trenutnaCena: 250.0,
              vremePripremeMils: 30000,
              opis: 'Opis 1',
              kategorijaJela: 'PREDJELO',
              tipJela: 'LUX',
            },
          },
        ],
        picaPorudzbine: [
          {
            id: 1,
            kolicina: 2,
            napomena: 'Napomena dsadadas',
            statusPica: 'KREIRANO',
            porudzbinaId: 4,
            pice: {
              id: 1,
              naziv: 'Pice 1',
              trenutnaCena: 120.0,
            },
            piceId: 1,
          },
          {
            id: 2,
            kolicina: 1,
            napomena: '',
            statusPica: 'KREIRANO',
            porudzbinaId: 4,
            pice: {
              id: 2,
              naziv: 'Pice 2',
              trenutnaCena: 120.0,
            },
            piceId: 2,
          },
        ],
      },
      {
        id: 5,
        statusPorudzbine: 'KREIRANO',
        datumVreme: new Date(),
        napomena: 'Napomena dDS sD dasd ',
        ukupnaCena: 10400.0,
        stoId: 5,
        jelaPorudzbine: [
          {
            id: 1,
            kolicina: 2,
            napomena: 'Napomena dsadadas',
            statusJela: 'PREUZETO',
            porudzbinaId: 5,
            jelo: {
              id: 1,
              naziv: 'Jelo 1',
              trenutnaCena: 250.0,
              vremePripremeMils: 30000,
              opis: 'Opis 1',
              kategorijaJela: 'PREDJELO',
              tipJela: 'LUX',
            },
          },
          {
            id: 2,
            kolicina: 1,
            napomena: 'Napomena dsadadas',
            statusJela: 'PREUZETO',
            porudzbinaId: 5,
            jelo: {
              id: 1,
              naziv: 'Jelo 1',
              trenutnaCena: 250.0,
              vremePripremeMils: 30000,
              opis: 'Opis 1',
              kategorijaJela: 'PREDJELO',
              tipJela: 'LUX',
            },
          },
        ],
        picaPorudzbine: [
          {
            id: 1,
            kolicina: 2,
            napomena: 'Napomena dsadadas',
            statusPica: 'KREIRANO',
            porudzbinaId: 5,
            pice: {
              id: 1,
              naziv: 'Pice 1',
              trenutnaCena: 120.0,
            },
            piceId: 1,
          },
          {
            id: 2,
            kolicina: 1,
            napomena: '',
            statusPica: 'KREIRANO',
            porudzbinaId: 5,
            pice: {
              id: 2,
              naziv: 'Pice 2',
              trenutnaCena: 120.0,
            },
            piceId: 2,
          },
        ],
      },
    ];
    this._setPorudzbine(porudzbine);
  }
}
