import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class JeloService {

  constructor(private http:HttpClient ) { }

  getJela():any{
    return this.http.get(environment.baseUrl + "jela/all");
  }

  getJeloById(jeloId:any):any{
    return this.http.get<any>(environment.baseUrl + "jela/id/" + jeloId);
  }

  getJeloByNaziv(jeloId:string):any{
    return this.http.get<any>(environment.baseUrl + "jela/naziv/" + jeloId);
  }

  addJelo(jelo:any):any{
    return this.http.post<any>(environment.baseUrl + "jela", jelo);
  }

  updateJelo(jelo:any):any{
    return this.http.put<any>(environment.baseUrl + "jela", jelo);
  }

  deleteJelo(jeloId:any):any{
    return this.http.delete(environment.baseUrl+"jela/" + jeloId);
  }
}
