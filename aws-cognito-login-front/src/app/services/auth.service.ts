import { SignIn } from './../models/sign-in';
import { environment } from './../../environments/environment';
import { SignUp } from './../models/sign-up';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponseMessageDto } from '../models/response-message-dto';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  constructor(private httpClient: HttpClient) { }

  login(loginData: SignIn):Observable<any>{
    return this.httpClient.post<any>(`${environment.apiUrl}/auth/sign-in`, loginData);
  }

  register(registerData: SignUp):Observable<ResponseMessageDto>{
    return this.httpClient.post<any>(`${environment.apiUrl}/auth/sign-up`, registerData);
  }

  getUserDetails():Observable<User>{
    let token = localStorage.getItem('token')
    const header = new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization':`Bearer ${token}`
    })
    return this.httpClient.get<User>(`${environment.apiUrl}/auth/user-details`, {headers:header});
  }
}
