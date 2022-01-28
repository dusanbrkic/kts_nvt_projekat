import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReportsService {

  constructor(private http: HttpClient) { }

  async formReport(start: Date, end: Date,callback: any){

    let params: any={
      start: start.toISOString(),
      end: end.toISOString()
    }

    const httpZahtev = await this.http
      .get(environment.baseUrl + 'report',{params}).toPromise()
      .then((data: any) => {
        console.log(data)
        callback(data.dates,data.gains,data.losses,data.profits)
      });
  }


}
