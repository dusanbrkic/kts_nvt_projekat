import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import Pice from '../model/Pice';

@Injectable({
  providedIn: 'root',
})
export class PiceService {
  constructor(private http: HttpClient) {}

  getPiceById(piceId: any): any {
    return this.http.get<any>(environment.baseUrl + 'pice/id/' + piceId);
  }

  getPicaTest(): Pice[] {
    return [
      {
        id: 1,
        naziv: 'Pice 1',
        trenutnaCena: 120.0,
      },
      {
        id: 1,
        naziv: 'Pice 1',
        trenutnaCena: 120.0,
      },
      {
        id: 1,
        naziv: 'Pice 1',
        trenutnaCena: 120.0,
      },
      {
        id: 1,
        naziv: 'Pice 1',
        trenutnaCena: 120.0,
      },
    ];
  }
}
