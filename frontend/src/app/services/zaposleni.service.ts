import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ZaposleniService {

  constructor(
    private http:HttpClient
  ) { }

  getAllZaposleni() : any {
    return this.http.get(environment.baseUrl+ "zaposleni/all");
  }

  getZaposleniById(zaposleniId: any) : any {
    return this.http.get(environment.baseUrl+ "zaposleni/id/" + zaposleniId);
  }
}
