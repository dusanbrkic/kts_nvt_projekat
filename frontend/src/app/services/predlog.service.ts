import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LazyLoadEvent } from 'primeng/api';
import { BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';
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

  async loadPredlozi(event: LazyLoadEvent,callback: any) {
    let size: number = event.rows || 10;
    let page: number = event.first || 0;
    page = page / size;

    let tip;

    if (event.filters?.tip?.value?.value === undefined) {
      tip = null;
    } else {
      tip = event.filters?.tip?.value?.value;
    }

    let params: any = {
      size: size,
      page: page,
      tip: tip,
    };

    if (params.tip === null) {
      delete params.tip;
    }

    const wait= await this.http
      .get(environment.baseUrl + 'predlog', { params }).toPromise()
      .then((data: any) => {
        console.log(data)
        this._setPredlozi(data.predlozi);
        callback(data.totalItems)
      });
  }

  async updatePredlog(predlog: Predlog, status: string) {
    //TO DO poslati na back
    const wait = await this.http
      .put(environment.baseUrl + 'predlog', { ...predlog,status: status }).toPromise()
      .then((data: any) => {
        const predlozi = this.getPredlozi().filter(p=> p.id!==predlog.id)
        this._setPredlozi(predlozi);
      });
  }

  addPredlog(tipIzmene: string, callback:any, jelo?: Jelo, staroJeloId?: number) {
    const newPredlog: Predlog = {
      status: 'NOV',
      tipIzmene: tipIzmene,
      novoJelo: jelo,
      staroJeloId: staroJeloId,
    };

    //dodati na back i vratiti novi id

    this.http
      .post(environment.baseUrl + 'predlog', newPredlog, {
        "responseType": 'json',
        "observe": 'response'
      })
      .subscribe((response: any) => {
        callback(response)
        const predlozi = [
          ...this.getPredlozi(),
          { ...newPredlog, id: response.body.id },
        ];
        this._setPredlozi(predlozi);
      });
  }

  loadTest() {
  //   const predlozi = [
  //     {
  //       id: 1,
  //       status: 'NOV',
  //       tipIzmene: 'BRISANJE',
  //       staroJeloId: 1,
  //     },
  //     {
  //       id: 2,
  //       status: 'NOV',
  //       tipIzmene: 'IZMENA',
  //       staroJeloId: 1,
  //       novoJelo: {
  //         id: 1,
  //         naziv: 'Jelo 1',
  //         trenutnaCena: 250.0,
  //         vremePripremeMils: 30000,
  //         opis: 'Test izmena',
  //         kategorijaJela: 'PREDJELO',
  //         tipJela: 'LUX',
  //       },
  //     },
  //     {
  //       id: 3,
  //       status: 'NOV',
  //       tipIzmene: 'DODAVANJE',
  //       novoJelo: {
  //         id: 4234,
  //         naziv: 'Jelo 1',
  //         trenutnaCena: 250.0,
  //         vremePripremeMils: 30000,
  //         opis: 'Test dodavanje',
  //         kategorijaJela: 'PREDJELO',
  //         tipJela: 'LUX',
  //       },
  //     },
  //   ];
  //   this._setPredlozi(predlozi);
  }
}
