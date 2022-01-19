import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
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

  getPiceClient(id:number):Pice| undefined{
    return this.getPica().find(p=>p.id===id)
  }

  getPiceById(piceId: any): any {
    return this.http.get<any>(environment.baseUrl + 'pice/id/' + piceId);
  }

  loadPicaTest():void{
    const pica: Pice[]=[
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
    this._setPica(pica)
  }
}
