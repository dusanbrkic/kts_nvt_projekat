import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LazyLoadEvent } from 'primeng/api';
import { BehaviorSubject } from 'rxjs';
import Jelo from '../model/Jelo';
import Predlog from '../model/Predlog';

@Injectable({
  providedIn: 'root',
})
export class PredlogService {
  private readonly _predloziSource = new BehaviorSubject<Predlog[]>([]);

  readonly predlozi$ = this._predloziSource.asObservable();

  constructor(private http: HttpClient) {}

  getPredlozi(): Predlog[] {
    return this._predloziSource.getValue();
  }

  private _setPredlozi(predlozi: Predlog[]): void {
    this._predloziSource.next(predlozi);
  }

  loadPredlozi(event: LazyLoadEvent){

  }

  updatePredlog(predlog: Predlog,status: string){
    //TO DO poslati na back
    const predlozi=this.getPredlozi().map(p=> p.id===predlog.id ? {...p,status: status} : p)
    this._setPredlozi(predlozi)
  }

  addPredlog(tipIzmene: string, jelo?: Jelo, staroJeloId?: number) {
    const newPredlog: Predlog = {
      status: 'NOV',
      tipIzmene: tipIzmene,
      novoJelo: jelo,
      staroJeloId: staroJeloId,
    };

    //dodati na back i vratiti novi id

    const predlozi = [
      ...this.getPredlozi(),
      { ...newPredlog, id: Math.floor(Math.random() * (1000000 - 0 + 1) + 0) },
    ];
    this._setPredlozi(predlozi);
  }

  loadTest() {
    const predlozi = [
      {
        id: 1,
        status: 'NOV',
        tipIzmene: 'BRISANJE',
        staroJeloId: 1,
      },
      {
        id: 2,
        status: 'NOV',
        tipIzmene: 'IZMENA',
        staroJeloId: 1,
        novoJelo: {
          id: 1,
          naziv: 'Jelo 1',
          trenutnaCena: 250.0,
          vremePripremeMils: 30000,
          opis: 'Test izmena',
          kategorijaJela: 'PREDJELO',
          tipJela: 'LUX',
        },
      },
      {
        id: 3,
        status: 'NOV',
        tipIzmene: 'DODAVANJE',
        novoJelo:{
            id: 4234,
            naziv: 'Jelo 1',
            trenutnaCena: 250.0,
            vremePripremeMils: 30000,
            opis: 'Test dodavanje',
            kategorijaJela: 'PREDJELO',
            tipJela: 'LUX',
        }
      },
    ];
    this._setPredlozi(predlozi)
  }
}
