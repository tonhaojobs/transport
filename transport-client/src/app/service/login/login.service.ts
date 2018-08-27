import { Injectable } from '@angular/core';
import { Usuario } from '../../model/entity/usuario';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class LoginService {

  authenticated = false;

  constructor(private http: HttpClient) { }

  logon(usuario: Usuario, callback) {

    const headers = new HttpHeaders(usuario ? {
      authorization : 'Basic ' + btoa(usuario.login + ':' + usuario.password)
    } : {});

    this.http.get('user', {headers: headers}).subscribe(response => {
      
      if (response['name']) {
          this.authenticated = true;
      } else {
          this.authenticated = false;
      }
      return callback && callback();
    });
  }
}
