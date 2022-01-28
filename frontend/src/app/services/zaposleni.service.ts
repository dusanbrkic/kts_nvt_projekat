import { HttpClient, HttpHeaders, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LazyLoadEvent } from 'primeng/api';
import { BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';
import Zaposleni from '../model/Zaposleni';

@Injectable({
  providedIn: 'root'
})
export class ZaposleniService {

  private readonly _zaposleniSource = new BehaviorSubject<Zaposleni[]>([]);

  readonly zaposleni$ = this._zaposleniSource.asObservable();

  constructor(
    private http: HttpClient
  ) { }

  getZaposleni(): Zaposleni[] {
    return this._zaposleniSource.getValue();
  }

  private _setZaposleni(zaposleni: Zaposleni[]): void {
    this._zaposleniSource.next(zaposleni);
  }

  loadZaposleni(event: LazyLoadEvent, setTotalZaposleni:any) {
    let size:number = event.rows || 10;
    let page:number = event.first || 0;
    page = page/size;
    let sortBy:string = event.sortField?.toUpperCase() || "";
    let sortDesc:boolean = event.sortOrder && event.sortOrder > 0 ? false : true;
    let pretragaIme:string = event.filters?.ime?.value || "";
    let pretragaPrezime:string = event.filters?.prezime?.value || "";
    let filterTipZaposlenja:string = event.filters?.roleName?.value?.map(function(elem:any) { return elem.value; }).join(",") || "";

    let params = { page, size, sortBy, sortDesc, pretragaIme, pretragaPrezime, filterTipZaposlenja }

    this.http.get<Zaposleni[]>(environment.baseUrl + "zaposleni/allPaged", {
      "params" : params,
      "responseType": 'json',
      "observe": 'response'
    }).subscribe((response: any) => {
      //sredi datum
      response.body.zaposleni.map(function(elem:any){
        elem.datumRodjenja = new Date(elem.datumRodjenja);
      })

      setTotalZaposleni(response.body.totalItems);

      this._setZaposleni(response.body.zaposleni);
    });

  }

  async addZaposleni(zap: Zaposleni, alertCallback:any) {
    //TO DO: dodati zaposlenog na back
    // kod ispod treba da se izvrsi samo ako uspe poziv na back
    const httpZaposleni = await this.http.post(environment.baseUrl + "zaposleni/add", zap, {responseType : 'text', "observe": 'response'}).toPromise()
      .then((response:any)=>{
        alertCallback(response);
      })
      .catch((response:any)=>{
        alertCallback(response);
      });

    // const zaposleni: Zaposleni[] = [...this.getZaposleni(), zap]
    // this._setZaposleni(zaposleni)
  }

  async updateZaposleni(zap: Zaposleni, alertCallback:any) {
    const httpZaposleni = await this.http.post(environment.baseUrl + "zaposleni/update", zap, {responseType : 'json', "observe": 'response'}).toPromise()
    .then((response:any)=>{
      alertCallback(response);
    })
    .catch((response:any)=>{
      alertCallback(response);
    });

    // const zaposleni: Zaposleni[] = this.getZaposleni().map(z => z.identificationNumber === zap.identificationNumber ? zap : z)
    // this._setZaposleni(zaposleni)
  }

  async removeZaposleni(zap: Zaposleni, alertCallback:any) {
    //TO DO: izbrisati zaposlenog sa backa
    // kod ispod treba da se izvrsi samo ako uspe poziv na back
    const httpZaposleni = await this.http.delete(environment.baseUrl + "zaposleni/delete/" + zap.username, {responseType : 'text'}).toPromise()
    .then((response:any)=>{
      alertCallback(response);
    })
    .catch((response:any)=>{
      alertCallback(response);
    });

    const zaposleni: Zaposleni[] = this.getZaposleni().filter(z => z.username !== zap.username)
    this._setZaposleni(zaposleni)
  }

  /*getAllZaposleni() : any {
    return this.http.get(environment.baseUrl+ "zaposleni/all");
  }

  getZaposleniById(zaposleniId: any) : any {
    return this.http.get(environment.baseUrl+ "zaposleni/id/" + zaposleniId);
  }*/

  loadZaposleniTest() {
  //   const zaposleni: Zaposleni[] = [{
  //     ime: 'Marko',
  //     prezime: 'Markovic',
  //     pol: 'MUSKI',
  //     datumRodjenja: new Date(),
  //     trenutnaPlata: 200000,
  //     tipZaposlenja: 'GLAVNI_KUVAR',
  //     slikaString: '',
  //     identificationNumber: '124123',
  //   },]
  //   this._setZaposleni(zaposleni)
  }
}
