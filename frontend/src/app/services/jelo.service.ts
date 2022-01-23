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

  constructor(private http: HttpClient) {}

  getJela(): Jelo[] {
    return this._jelaSource.getValue();
  }

  private _setJela(jela: Jelo[]): void {
    this._jelaSource.next(jela);
  }

  loadJela(event: LazyLoadEvent){
    console.log(event);
    //TO DO get jela sa paginacijom i filterima
    let naziv;
    let kategorijaJela;
    let tipJela;
    if(event.filters?.naziv?.value === undefined){
      naziv = "";
    }else{
      naziv = event.filters?.naziv?.value;
    }
    if(event.filters?.kategorijaJela?.value?.value === undefined){
      kategorijaJela = null;
    }else{
      kategorijaJela = event.filters?.kategorijaJela?.value?.value;
    }
    if(event.filters?.tipJela?.value?.value === undefined){
      tipJela = null;
    }else{
      tipJela = event.filters?.tipJela?.value?.value;
    }
    


    let params:any = {
      first : event.first,
      rows : event.rows,
      naziv: naziv,
      kategorijaJela: kategorijaJela,
      tipJela: tipJela,
      sortField:event.sortField,
      sortOrder:event.sortOrder,
    };
    if(params.kategorijaJela===null){
      delete params.kategorijaJela;
    }
    if(params.tipJela===null){
      delete params.tipJela;
    }
    (this.http.get(environment.baseUrl + "jela/page/322/",{params})).subscribe((data:any)=>{
      //console.log(data.jela);
      this._setJela(data.jela);
    })
  }

  addJelo(jelo: Jelo){
    //TO DO: dodati jelo na back
    // kod ispod treba da se izvrsi samo ako uspe poziv na back
    const jela: Jelo[]=[...this.getJela(),jelo]
    this._setJela(jela)
  }

  removeJelo(jelo: Jelo){
    //TO DO: izbrisati jelo sa backa
    // kod ispod treba da se izvrsi samo ako uspe poziv na back
    const jela: Jelo[]=this.getJela().filter(j=>j.id!==jelo.id)
    this._setJela(jela)
  }

  updateJelo(jelo: Jelo){
    //TO DO: update jela na backu
    // kod ispod treba da se izvrsi samo ako uspe poziv na back
    const jela: Jelo[]=this.getJela().map(j=>j.id===jelo.id ? jelo : j)
    this._setJela(jela)
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
    const jela: Jelo[] = [
      {
        id: 1,
        naziv: 'Jelo 1',
        trenutnaCena: 250.0,
        vremePripremeMils: 30000,
        opis: 'Opis 1',
        kategorijaJela: 'PREDJELO',
        tipJela: 'LUX',
      },
      {
        id: 2,
        naziv: 'Jelo 2',
        trenutnaCena: 250.0,
        vremePripremeMils: 30000,
        opis: 'Opis 2',
        kategorijaJela: 'PREDJELO',
        tipJela: 'LUX',
      },
      {
        id: 3,
        naziv: 'Jelo 3',
        trenutnaCena: 250.0,
        vremePripremeMils: 30000,
        opis: 'Opis 3',
        kategorijaJela: 'PREDJELO',
        tipJela: 'LUX',
      },
    ];
    this._setJela(jela)
  }
}
