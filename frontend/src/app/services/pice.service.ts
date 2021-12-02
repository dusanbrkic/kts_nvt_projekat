import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PiceService {

  constructor(private http:HttpClient) { }

  getPiceById(piceId:any):any{
    return this.http.get<any>(environment.baseUrl + "pice/id/" + piceId);
  }
}
