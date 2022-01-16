import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';
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

  loadPorudzbine(): any {
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
  }

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
            jeloId: 1,
            porudzbinaId: 1,
          },
          {
            id: 2,
            kolicina: 1,
            napomena: 'Napomena dsadadas',
            statusJela: 'KREIRANO',
            jeloId: 1,
            porudzbinaId: 1,
          },
        ],
        picaPorudzbine: [
          {
            id: 1,
            kolicina: 2,
            napomena: 'Napomena dsadadas',
            statusPica: 'KREIRANO',
            piceId: 1,
            porudzbinaId: 1,
          },
          {
            id: 2,
            kolicina: 1,
            napomena: '',
            statusPica: 'KREIRANO',
            piceId: 2,
            porudzbinaId: 1,
          },
        ],
      },
      {
        id: 2,
        statusPorudzbine: 'KREIRANO',
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
            statusPica: 'KREIRANO',
            piceId: 1,
            porudzbinaId: 2,
          },
        ],
      },
    ];
    this._setPorudzbine(porudzbine);
  }
}
