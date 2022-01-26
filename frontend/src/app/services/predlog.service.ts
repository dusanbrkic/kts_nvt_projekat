import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import Jelo from '../model/Jelo';
import Predlog from '../model/Predlog';

@Injectable({
  providedIn: 'root'
})
export class PredlogService {

  private readonly _predloziSource = new BehaviorSubject<Predlog[]>([]);

  readonly predlozi$ = this._predloziSource.asObservable();

  constructor(private http: HttpClient) { }

  getPredlozi(): Predlog[] {
    return this._predloziSource.getValue();
  }

  private _setPredlozi(predlozi: Predlog[]): void {
    this._predloziSource.next(predlozi);
  }

  addPredlog(tipIzmene: string,jelo?:Jelo,staroJeloId?:number){
    const newPredlog: Predlog={
      tipIzmene: tipIzmene,
      novoJelo: jelo,
      staroJeloId: staroJeloId,
    }

    //dodati na back i vratiti novi id

    const predlozi=[...this.getPredlozi(),{...newPredlog,id:Math.floor(Math.random() * (1000000 - 0 + 1) + 0),}]
    this._setPredlozi(predlozi)

  }
}
