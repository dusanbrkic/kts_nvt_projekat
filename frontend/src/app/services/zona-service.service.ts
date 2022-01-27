import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import Sto from '../model/Sto';
import Zona from '../model/Zona';

@Injectable({
  providedIn: 'root',
})
export class ZonaService {
  private readonly _zoneSource = new BehaviorSubject<Zona[]>([]);

  readonly zone$: Observable<Zona[]> = this._zoneSource.asObservable();

  constructor(
    private http: HttpClient,
  ) {}

  getZones(): Zona[] {
    return this._zoneSource.getValue();
  }

  private _setZones(zone: Zona[]): void {
    this._zoneSource.next(zone);
  }

  async loadZones(selektujZonu:any) {
    const cekaj = await this.http.get(environment.baseUrl + "zone", {
      "responseType": 'json',
      "observe": 'response'
    }).toPromise()
    .then((response: any) => {
      this._setZones(response.body);
      selektujZonu();
    });
  }

  async saveZone(selectedZone:Zona, alertCallback:any){
    // TO DO: sacuvati zonu na back;
    // true-uspeh false-greska( uraditi reload ako dodje do greske)
    const cekaj = await this.http.post(environment.baseUrl + "zone", selectedZone, {
      "responseType": 'json',
      "observe": 'response'
    }).toPromise()
    .then((response: any) => {
      this.getZones().push(response.body);
      alertCallback(response);
    }).catch(()=>{});
  }

  async addZone(zone: Zona, alertCallback:any) {
    //TO DO: dodati zonu na back
    // kod ispod treba da se izvrsi samo ako uspe poziv na back
    const cekaj = await this.http.put(environment.baseUrl + "zone", zone, {
      "responseType": 'json',
      "observe": 'response'
    }).toPromise()
    .then((response: any) => {
      console.log(response)
      alertCallback(response);
    });

    const zones: Zona[] = [...this.getZones(), zone];
    this._setZones(zones);
  }

  async removeZone(zona: Zona, alertCallback:any) {
    //TO DO: izbrisati zonu sa backa
    // kod ispod treba da se izvrsi samo ako uspe poziv na back
    const cekaj = await this.http.delete(environment.baseUrl + "zone/" + zona.id, {
      "responseType": 'json',
      "observe": 'response'
    }).toPromise()
    .then((response: any) => {
      console.log(response)
      alertCallback(response);
    });
    const zones = this.getZones().filter((z) => z.id !== zona.id);
    this._setZones(zones);
  }

  updateZone(zona: Zona): void {
    //TO DO: ovde ne mora nista, kad se uradi save tek treba upit na back
    const zones = this.getZones().map((z) => (z.id === zona.id ? zona : z));
    this._setZones(zones);
  }

  addTable(sto: Sto, zona: Zona) {
    //TO DO: ovde ne mora nista, kad se uradi save tek treba upit na back
    const stolovi: Sto[] = [...zona.stolovi, sto];
    const zones = this.getZones().map((z) =>
      z.id === zona.id ? { ...zona, stolovi: stolovi } : z
    );
    this._setZones(zones);
  }

  removeTable(sto: Sto, zona: Zona) {
    //TO DO: ovde ne mora nista, kad se uradi save tek treba upit na back
    const stolovi: Sto[] = zona.stolovi.filter((s) => s.id !== sto.id);
    const zones = this.getZones().map((z) =>
      z.id === zona.id ? { ...zona, stolovi: stolovi } : z
    );
    this._setZones(zones);
  }

  updateTable(sto: Sto, zona: Zona) {
    //TO DO: ovde ne mora nista, kad se uradi save tek treba upit na back
    const stolovi: Sto[] = zona.stolovi.map((s) => (s.id === sto.id ? sto : s));
    const zones = this.getZones().map((z) =>
      z.id === zona.id ? { ...zona, stolovi: stolovi } : z
    );
    this._setZones(zones);
  }
}
