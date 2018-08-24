import { Perfil } from './../../model/interface/perfil.generates';
import { FromValidator } from './../../validator/from-validator';
import { 
  Component, 
  OnInit 
} from '@angular/core';

import {
  FormControl,
  Validators 
} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginFormControl : FormControl;
  passwordFormControl : FormControl;
  matcher : FromValidator;
  perfis : Perfil[] = [
    {key: '1', value: 'Colaborador'},
    {key: '2', value: 'Motorista'}
  ];

  constructor() {
    
    this.loginFormControl = new FormControl('', [Validators.required, Validators.email]);
    this.passwordFormControl = new FormControl('', [Validators.required, Validators.email]);
    this.matcher = new FromValidator();
  }

  ngOnInit() {
  }

  teste() {

    if(!this.loginFormControl.invalid)
    alert("oi");
  }

}