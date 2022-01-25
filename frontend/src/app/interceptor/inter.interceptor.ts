import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class InterInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const tokenString=localStorage.getItem('user')
    if(tokenString){
      const token=JSON.parse(tokenString)
      return next.handle(request.clone({setHeaders: { Authorization: 'Bearer ' + token.accessToken }}));
    }
    return next.handle(request);
  }
}
