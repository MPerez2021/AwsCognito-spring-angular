import { AuthService } from './../../services/auth.service';
import { SignIn } from './../../models/sign-in';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  showPassword: boolean = false;
  loginData = {} as SignIn;
  constructor(private authSrv: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  onShowPassword() {
    this.showPassword = !this.showPassword
  }

  onSignIn(loginData: SignIn) {
    this.authSrv.login(loginData).subscribe(accessToken =>{
      localStorage.setItem('token', accessToken.message)
      this.router.navigateByUrl('/home')
    })
  }
}
