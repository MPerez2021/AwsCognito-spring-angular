import { MessageService } from './../services/message.service';
import { AuthService } from './../services/auth.service';
import { User } from './../models/user';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  showDetails: boolean = false
  userLogged = {} as User
  userRole:string = ''
  constructor(private authSrv: AuthService, private messageSrv: MessageService) { }


  ngOnInit(): void {
    this.authSrv.getUserDetails().subscribe(user=> this.userLogged = user)
  }

  getUserDetails(role:string){
    this.showDetails = !this.showDetails
    this.getRoleMessage(role)
  }

  getRoleMessage(role: String){
    if (role === 'ROLE_USER') {
      this.getUserMessage()
    }else{
      this.getAdminMessage()
    }
  }

  getUserMessage(){
    this.messageSrv.getUserMessage().subscribe(response => this.userRole = response.message)
  }

  getAdminMessage(){
    this.messageSrv.getAdminMessage().subscribe(response => this.userRole = response.message)
  }


}
