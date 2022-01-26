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

  constructor(private http: HttpClient) {}

  getPorudzbine(): Porudzbina[] {
    return this._porudzbineSource.getValue();
  }

  private _setPorudzbine(porudzbine: Porudzbina[]): void {
    this._porudzbineSource.next(porudzbine);
  }

  getPorudzbinaById(porudzbinaId: number): Porudzbina {
    //TO DO poziv na back umesto ovaj for each
    let por: Porudzbina;
    this.getPorudzbine().forEach((p) => {
      if (p.id === porudzbinaId) {
        por = p;
        return;
      }
    });
    return por!;
  }

  addJeloToPorudzbina(jelo: JeloPorudzbine, porudzbinaId: number) {
    const porudzbine = this.getPorudzbine().map((p) =>
      p.id === porudzbinaId
        ? { ...p, jelaPorudzbine: [...p.jelaPorudzbine, jelo] }
        : p
    );
    this._setPorudzbine(porudzbine);
  }

  addPiceToPorudzbina(pice: PicePorudzbine, porudzbinaId: number) {
    const porudzbine = this.getPorudzbine().map((p) =>
      p.id === porudzbinaId
        ? { ...p, picaPorudzbine: [...p.picaPorudzbine, pice] }
        : p
    );
    this._setPorudzbine(porudzbine);
  }

  savePorudzbina(porudzbina: Porudzbina, isNewPorudzbina: boolean) {
    //TO DO dodati na back

    if (!isNewPorudzbina) {
      const porudzbine = this.getPorudzbine().map((p) =>
        p.id === porudzbina.id ? porudzbina : p
      );
      this._setPorudzbine(porudzbine);
    } else {
      const porudzbine = [...this.getPorudzbine(), porudzbina];
      this._setPorudzbine(porudzbine);
    }
  }

  porudzbineZaPripremuKuvar(): Porudzbina[] {
    // TO DO umesto ovog, treba na backu nace sve porudzbine
    const porudzbine = this.getPorudzbine().filter(
      (p) => p.statusPorudzbine === 'KREIRANO' && p.jelaPorudzbine.length > 0
    );
    return porudzbine;
  }

  async porudzbineZaPripremuSanker() {
    // TO DO umesto ovog, treba na backu nace sve porudzbine
    let porudzbine: Porudzbina[];
    let answ;
    await this.http
      .get(environment.baseUrl + '/porudzbine/zaSankera')
      .subscribe((data: any) => {
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

  jelaUPripremi(): JeloPorudzbine[] {
    //TO DO umesto ovoga sa backa dobaviti
    let jela: JeloPorudzbine[] = [];
    this.getPorudzbine().forEach((p) => {
      p.jelaPorudzbine.forEach((j) => {
        if (j.statusJela === 'PREUZETO') {
          jela.push(j);
        }
      });
    });
    return jela;
  }

  spremiJelo(jelo: JeloPorudzbine) {
    // TO DO poslati na back
    const porudzbine = this.getPorudzbine().map((p) =>
      p.id === jelo.porudzbinaId
        ? {
            ...p,
            jelaPorudzbine: p.jelaPorudzbine.map((j) =>
              j.id === jelo.id ? { ...j, statusJela: 'PRIPREMLJENO' } : j
            ),
          }
        : p
    );
    this._setPorudzbine(porudzbine);
  }

  spremiPica(porudzbina: Porudzbina) {
    this.http
      .get(environment.baseUrl + 'porudzbine/spremiPica/' + porudzbina.id)
      .subscribe((data: any) => {
        const porudzbine = this.getPorudzbine().map((p) =>
          p.id === porudzbina.id
            ? {
                ...p,
                picaPorudzbine: p.picaPorudzbine.map((pice) =>
                  pice.statusPica === 'KREIRANO'
                    ? { ...pice, statusPica: 'PRIPREMLJENO' }
                    : pice
                ),
              }
            : p
        );
        this._setPorudzbine(porudzbine);
      });
    this.porudzbineZaPripremuSanker();
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
        konobarId: 1,
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
        konobarId: 1,
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
        konobarId: 1,
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
        konobarId: 1,
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
        konobarId: 1,
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
