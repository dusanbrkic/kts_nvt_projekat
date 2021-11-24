import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PorudzbinaService {

  constructor(
    private http:HttpClient
  ) { }

  getPorudzbine(): any {
    this.http.get(environment.baseUrl + "porudzbine");
  }

  getPorudzbinaById(porudzbinaId : any) : any{
    return this.http.get(environment.baseUrl + "porudzbine/" + porudzbinaId);
  }

  getPorudzbineByStatus(status : any) : any{
    return this.http.get(environment.baseUrl + "porudzbine/status/" + status);
  }

  getPorudzbineByKonobar(konobarId : any) : any{
    return this.http.get(environment.baseUrl + "porudzbine/konobar/" + konobarId);
  }

  napraviPorudzbinu(porudzbina : any) : any{
    return this.http.post(environment.baseUrl + "porudzbine/" + porudzbina, porudzbina);
  }

  izmeniPorudzbinu(porudzbina : any) : any{
    return this.http.put(environment.baseUrl + "porudzbine/", porudzbina);
  }

  deletePorudzbina(porudzbinaId : any) : any{
    return this.http.delete(environment.baseUrl + "porudzbine/" + porudzbinaId);
  }
}
