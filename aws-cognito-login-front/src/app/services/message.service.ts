import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MessageService {


  token = localStorage.getItem('token')
  header = new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':`Bearer ${this.token}`
  })
  constructor(private httpClient: HttpClient) {}

  getUserMessage(){
    return this.httpClient.get<any>(`${environment.apiUrl}/message/user-message`, {headers: this.header});
  }

  getAdminMessage(){
    return this.httpClient.get<any>(`${environment.apiUrl}/message/admin-message`, {headers: this.header});

  }
}
