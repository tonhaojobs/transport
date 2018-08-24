import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { FromValidator } from '../../validator/from-validator';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  matcher = new FromValidator();

}
