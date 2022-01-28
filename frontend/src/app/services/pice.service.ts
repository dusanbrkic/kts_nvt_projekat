import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LazyLoadEvent } from 'primeng/api';
import { BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';
import Pice from '../model/Pice';

@Injectable({
  providedIn: 'root',
})
export class PiceService {
  private readonly _picaSource = new BehaviorSubject<Pice[]>([]);

  readonly pica$ = this._picaSource.asObservable();

  constructor(private http: HttpClient) {}

  getPica(): Pice[] {
    return this._picaSource.getValue();
  }

  private _setPica(pica: Pice[]): void {
    this._picaSource.next(pica);
  }

  async loadAllPica(){
    const httpZahtev = await this.http
      .get(environment.baseUrl + 'pice/all').toPromise()
      .then((data: any) => {
        this._setPica(data);
      });
  }

  async loadPica(event: LazyLoadEvent, brojPicaUpdate: any) {
    //TO DO get pica sa paginacijom i filterima
    console.log(event);
    let naziv;
    if (event.filters?.naziv?.value === undefined) {
      naziv = '';
    } else {
      naziv = event.filters?.naziv?.value;
    }
    let params: any = {
      first: event.first,
      rows: event.rows,
      naziv: naziv,
      sortField: event.sortField,
      sortOrder: event.sortOrder,
    };
    await this.http
      .get(environment.baseUrl + 'pice/page/', { params })
      .subscribe((data: any) => {
        this._setPica(data.pica);
        brojPicaUpdate(data.totalItems);
      });
  }

  async addPice(pice: Pice) {
    //console.log(pice);
    this.http
      .post(environment.baseUrl + 'pice', pice)
      .subscribe((data: any) => {
        pice = data;
        const pica: Pice[] = [...this.getPica(), pice];
        this._setPica(pica);
      });
  }

  async removePice(pice: Pice) {
    const http = await this.http
      .delete(environment.baseUrl + 'pice/' + pice.id)
      .toPromise()
      .then((data: any) => {
        const pica: Pice[] = this.getPica().filter((p) => p.id !== pice.id);
        this._setPica(pica);
      });
  }

  async updatePice(pice: Pice) {
    this.http
      .post(environment.baseUrl + 'pice', pice)
      .subscribe((data: any) => {
        pice = data;
        const pica: Pice[] = this.getPica().map((p) =>
          p.id === pice.id ? pice : p
        );
        this._setPica(pica);
      });
  }

  /*getPiceClient(id:number):Pice| undefined{
    return this.getPica().find(p=>p.id===id)
  }

  getPiceById(piceId: any): any {
    return this.http.get<any>(environment.baseUrl + 'pice/id/' + piceId);
  }*/

  loadPicaTest(): void {
    const pica: Pice[] = [
      {
        id: 1,
        naziv: 'Pice 1',
        trenutnaCena: 120.0,
      },
      {
        id: 2,
        naziv: 'Pice 2',
        trenutnaCena: 120.0,
      },
      {
        id: 3,
        naziv: 'Pice 3',
        trenutnaCena: 120.0,
      },
      {
        id: 4,
        naziv: 'Pice 4',
        trenutnaCena: 120.0,
      },
    ];
    this._setPica(pica);
  }
}
