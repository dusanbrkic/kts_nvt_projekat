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

  loadZaposleni(event: LazyLoadEvent) {
    let size:number = event.rows || 10;
    let page:number = event.first || 0;
    page = page/size;
    let sortBy:string = event.sortField?.toUpperCase() || "";
    let sortDesc:boolean = event.sortOrder && event.sortOrder > 0 ? false : true;
    let pretragaIme:string = event.filters?.ime?.value || "";
    let pretragaPrezime:string = event.filters?.prezime?.value || "";
    let filterTipZaposlenja:string = event.filters?.tipZaposlenja?.value?.map(function(elem:any) { return elem.value; }).join(",") || "";

    let params = { page, size, sortBy, sortDesc, pretragaIme, pretragaPrezime, filterTipZaposlenja }

    this.http.get<Zaposleni[]>(environment.baseUrl + "zaposleni/allPaged", {params}).subscribe((data: any) => {
      //sredi datum
      data.map(function(elem:any){
        elem.datumRodjenja = new Date(elem.datumRodjenja);
      })

      this._setZaposleni(data);
    });

  }

  async addZaposleni(zap: Zaposleni, alertSuccessCallback:any) {
    //TO DO: dodati zaposlenog na back
    // kod ispod treba da se izvrsi samo ako uspe poziv na back
    const httpZaposleni = await this.http.post(environment.baseUrl + "zaposleni/add", zap, {responseType : 'text'}).toPromise()

    // const zaposleni: Zaposleni[] = [...this.getZaposleni(), zap]
    // this._setZaposleni(zaposleni)
  }

  removeZaposleni(zap: Zaposleni) {
    //TO DO: izbrisati zaposlenog sa backa
    // kod ispod treba da se izvrsi samo ako uspe poziv na back
    const zaposleni: Zaposleni[] = this.getZaposleni().filter(z => z.identificationNumber !== zap.identificationNumber)
    this._setZaposleni(zaposleni)
  }

  updateJelo(zap: Zaposleni) {
    //TO DO: update zaposlenog na backu
    // kod ispod treba da se izvrsi samo ako uspe poziv na back
    const zaposleni: Zaposleni[] = this.getZaposleni().map(z => z.identificationNumber === zap.identificationNumber ? zap : z)
    this._setZaposleni(zaposleni)
  }

  /*getAllZaposleni() : any {
    return this.http.get(environment.baseUrl+ "zaposleni/all");
  }

  getZaposleniById(zaposleniId: any) : any {
    return this.http.get(environment.baseUrl+ "zaposleni/id/" + zaposleniId);
  }*/

  loadZaposleniTest() {
    const zaposleni: Zaposleni[] = [{
      ime: 'Marko',
      prezime: 'Markovic',
      pol: 'MUSKI',
      datumRodjenja: new Date(),
      trenutnaPlata: 200000,
      tipZaposlenja: 'GLAVNI_KUVAR',
      slikaString: '',
      identificationNumber: '124123',
    },]
    this._setZaposleni(zaposleni)
  }
}
