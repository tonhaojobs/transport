import { Usuario } from '../../model/entity/usuario';
import { FromValidator } from '../../validator/from-validator';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { AuthService } from '../../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario : Usuario;
  loginFormControl : FormControl;
  passwordPattern = "^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{4,}$";
  passwordFormControl : FormControl;
  //perfilFormControl : FormControl;
  matcher : FromValidator;
  error = '';
  loading = false;

  constructor(
    private authService: AuthService, 
    private router: Router) {
    
    this.loginFormControl = new FormControl('', [Validators.required, Validators.email]);
    this.passwordFormControl = new FormControl('', [Validators.required]/*, Validators.pattern(this.passwordPattern)]*/);
   // this.perfilFormControl = new FormControl('', [Validators.required]);
    this.matcher = new FromValidator();
  }

  ngOnInit() {
    this.usuario = new Usuario();
  }

  login() {

    this.loading = true;

    if(this.loginFormControl.valid && this.passwordFormControl.valid) {
      
      this.authService.login(this.usuario)
      .subscribe(data => {
        if(data === true) {
          
          this.router.navigate(['/']);
        } 
      },
      error =>{
        console.log(error);
        
        if(error == 'Unauthorized') {
          this.error = 'E-mail e/ou Senha incorreto(s)';
        }
        //this.error = error;
        this.loading = false;
      });
    }
  }
}