import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import Sto from '../model/Sto';
import Zona from '../model/Zona';

@Injectable({
  providedIn: 'root',
})
export class ZonaService {
  private readonly _zoneSource = new BehaviorSubject<Zona[]>([]);

  readonly zone$: Observable<Zona[]> = this._zoneSource.asObservable();

  constructor() {}

  getZones(): Zona[] {
    return this._zoneSource.getValue();
  }

  private _setZones(zone: Zona[]): void {
    this._zoneSource.next(zone);
  }

  loadZones() {
    //TO DO: ucitavanje sa backa
  }

  saveZone(): boolean {
    //TO DO: sacuvati zonu na back;
    // true-uspeh false-greska( uraditi reload ako dodje do greske)

    return true;
  }

  addZone(zone: Zona): void {
    //TO DO: dodati zonu na back
    // kod ispod treba da se izvrsi samo ako uspe poziv na back
    const zones: Zona[] = [...this.getZones(), zone];
    this._setZones(zones);
  }

  removeZone(zona: Zona): void {
    //TO DO: izbrisati zonu sa backa
    // kod ispod treba da se izvrsi samo ako uspe poziv na back
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

  loadZoneTest() {
    const zone: Zona[] = [
      {
        id: 1,
        naziv: 'Prizemlje',
        stolovi: [
          {
            id: 1,
            zauzet: false,
            brojMesta: 4,
            x: 651.5,
            y: 227.046875,
            naziv: 'Sto 1',
            porudzbinaId: -1,
          },
          {
            id: 2,
            zauzet: true,
            brojMesta: 3,
            x: 195.5,
            y: 469.609375,
            naziv: 'Sto 2',
            porudzbinaId: 1
          }
        ],
        template: '/assets/zones/zone1.png',
      },
      {
        id: 2,
        naziv: 'Sprat I',
        stolovi: [
          {
            id: 3,
            zauzet: true,
            brojMesta: 4,
            x: 30,
            y: 30,
            naziv: 'Sto 1',
            porudzbinaId: 2,
          },
          {
            id: 4,
            zauzet: true,
            brojMesta: 4,
            x: 150,
            y: 150,
            naziv: 'Sto 2',
            porudzbinaId: 3,

          },
        ],
        template: '/assets/zones/zone2.png',
      },
    ];
    this._setZones(zone);
  }
}
