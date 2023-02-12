import { AuthService } from './../../services/auth.service';
import { SignUp } from './../../models/sign-up';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  showPassword: boolean = false;
  registerData = {} as SignUp;
  registerResponse:string = ''
  erroMessage:string = ''
  constructor(private authSrv: AuthService) { }

  ngOnInit(): void {
  }
  onShowPassword(){
    this.showPassword = !this.showPassword
  }

  onSignUp(registerData: SignUp){
    this.authSrv.register(registerData).subscribe(response=> {
      this.registerResponse = response.message
    })
  }
}
