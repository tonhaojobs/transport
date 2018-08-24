import { Usuario } from './../../model/entity/usuario';
import { Perfil } from './../../model/interface/perfil.generates';
import { FromValidator } from './../../validator/from-validator';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

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
  perfilFormControl : FormControl;
  matcher : FromValidator;
  
  perfis : Perfil[] = [
    {key: '1', value: 'Colaborador'},
    {key: '2', value: 'Motorista'}
  ];

  constructor() {
    
    this.loginFormControl = new FormControl('', [Validators.required, Validators.email]);
    this.passwordFormControl = new FormControl('', [Validators.required]/*, Validators.pattern(this.passwordPattern)]*/);
    this.perfilFormControl = new FormControl('', [Validators.required]);
    this.matcher = new FromValidator();
  }

  ngOnInit() {
    this.usuario = new Usuario();
  }

  logon() {

    if(!this.loginFormControl.invalid && !this.passwordFormControl.invalid && !this.perfilFormControl.invalid) {

    }
  }

}