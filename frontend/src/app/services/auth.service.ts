import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Login } from '../model/Login';
import { Token } from '../model/Token';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) {}

  login(auth: Login): Observable<Token> {
    return this.http.post<Token>(environment.baseUrl + 'auth/login', auth, {
      headers: this.headers,
      responseType: 'json',
    });
  }

  logout(){
    localStorage.removeItem('user')
  }

  isLoggedIn(): boolean {
    if (!localStorage.getItem('user')) {
      return false;
    }
    return true;
  }

  getCurrentRole():string{
    const tokeString=localStorage.getItem('user')
    if(!tokeString){
      return ''
    }

    const token=JSON.parse(tokeString)

    return token.role
  }
}
