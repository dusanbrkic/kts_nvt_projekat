import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import Jelo from '../model/Jelo';
import { BehaviorSubject } from 'rxjs';
import { LazyLoadEvent } from 'primeng/api';

@Injectable({
  providedIn: 'root',
})
export class JeloService {
  private readonly _jelaSource = new BehaviorSubject<Jelo[]>([]);

  readonly jela$ = this._jelaSource.asObservable();

  constructor(private http: HttpClient) { }

  getJela(): Jelo[] {
    return this._jelaSource.getValue();
  }

  private _setJela(jela: Jelo[]): void {
    this._jelaSource.next(jela);
  }

  async loadAllJela(){
    const httpZahtev = await this.http
      .get(environment.baseUrl + 'jela/all').toPromise()
      .then((data: any) => {
        console.log("jela load")
        this._setJela(data);
        console.log(data)
      });
  }

  async loadJela(event: LazyLoadEvent, brojJelaUpdate: any) {
    console.log(event);
    let naziv;
    let kategorijaJela;
    let tipJela;
    if (event.filters?.naziv?.value === undefined) {
      naziv = '';
    } else {
      naziv = event.filters?.naziv?.value;
    }
    if (event.filters?.kategorijaJela?.value?.value === undefined) {
      kategorijaJela = null;
    } else {
      kategorijaJela = event.filters?.kategorijaJela?.value?.value;
    }
    if (event.filters?.tipJela?.value?.value === undefined) {
      tipJela = null;
    } else {
      tipJela = event.filters?.tipJela?.value?.value;
    }
    let params: any = {
      first: event.first,
      rows: event.rows,
      naziv: naziv,
      kategorijaJela: kategorijaJela,
      tipJela: tipJela,
      sortField: event.sortField,
      sortOrder: event.sortOrder,
    };
    if (params.kategorijaJela === null) {
      delete params.kategorijaJela;
    }
    if (params.tipJela === null) {
      delete params.tipJela;
    }
    await this.http
      .get(environment.baseUrl + 'jela/page/322/', { params })
      .subscribe((data: any) => {
        this._setJela(data.jela);
        brojJelaUpdate(data.totalItems);
      });
  }

  async addJelo(jelo: Jelo, callback: any) {
    this.http
      .post(environment.baseUrl + 'jela', jelo, {
        "responseType": 'json',
        "observe": 'response'
      })
      .subscribe((response: any) => {
        jelo = response.body;
        const jela: Jelo[] = [...this.getJela(), jelo];
        callback(response);
        this._setJela(jela);
      });
  }

  async removeJelo(jeloId: number) {
    const http = await this.http
      .delete(environment.baseUrl + 'jela/' + jeloId)
      .toPromise()
      .then((data: any) => {
        const jela: Jelo[] = this.getJela().filter((j) => j.id !== jeloId);
        this._setJela(jela);
      });
  }

  async updateJelo(jelo: Jelo) {
    this.http
      .post(environment.baseUrl + 'jela', jelo)
      .subscribe((data: any) => {
        jelo = data;
        const jela: Jelo[] = this.getJela().map((j) =>
          j.id === jelo.id ? jelo : j
        );
        this._setJela(jela);
      });
  }

  getJeloById(jeloId: number, callback: any) {
    (this.http.get(environment.baseUrl + "jela/id/" + jeloId)).subscribe((data: any) => {
      callback(data)
    })
  }

  /*getJeloClient(id:number):Jelo | undefined{
    return this.getJela().find(j=>j.id===id)
  }

  loadJela():any{
    return this.http.get(environment.baseUrl + "jela/all");
  }

  getJeloById(jeloId: any): any {
    return this.http.get<any>(environment.baseUrl + 'jela/id/' + jeloId);
  }

  getJeloByNaziv(jeloId: string): any {
    return this.http.get<any>(environment.baseUrl + 'jela/naziv/' + jeloId);
  }

  addJelo(jelo: any): any {
    return this.http.post<any>(environment.baseUrl + 'jela', jelo);
  }

  updateJelo(jelo: any): any {
    return this.http.put<any>(environment.baseUrl + 'jela', jelo);
  }

  deleteJelo(jeloId: any): any {
    return this.http.delete(environment.baseUrl + 'jela/' + jeloId);
  }*/

  loadJelaTest(): void {
  //   const jela: Jelo[] = [
  //     {
  //       id: 1,
  //       naziv: 'Jelo 1',
  //       trenutnaCena: 250.0,
  //       vremePripremeMils: 30000,
  //       opis: 'Opis 1',
  //       kategorijaJela: 'PREDJELO',
  //       tipJela: 'LUX',
  //     },
  //     {
  //       id: 2,
  //       naziv: 'Jelo 2',
  //       trenutnaCena: 250.0,
  //       vremePripremeMils: 30000,
  //       opis: 'Opis 2',
  //       kategorijaJela: 'PREDJELO',
  //       tipJela: 'LUX',
  //     },
  //     {
  //       id: 3,
  //       naziv: 'Jelo 3',
  //       trenutnaCena: 250.0,
  //       vremePripremeMils: 30000,
  //       opis: 'Opis 3',
  //       kategorijaJela: 'PREDJELO',
  //       tipJela: 'LUX',
  //     },
  //   ];
  //   this._setJela(jela);
  }
}
