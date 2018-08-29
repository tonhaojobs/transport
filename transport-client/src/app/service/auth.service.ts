import { AbstractService } from './abstract.service';
import { Injectable } from '@angular/core';
import { Usuario } from '../model/entity/usuario';
import { Response, Http } from '@angular/http';
import { Observable } from 'rxjs-compat';


@Injectable()
export class AuthService extends AbstractService {

  authenticated = false;
  relativePath: string = "/auth";

  constructor(protected http: Http) { 
    super(http);
  }

  login(usuario : Usuario) : Observable<any> {

    const action = "/signin";

    return this.http.post(this.url + this.relativePath + action, 
      { 'usernameOrEmail': usuario.login, 'password': usuario.password }/*, 
      { headers: this.getHeaders() }*/
    )
    .map((response: Response) =>{
        
      let token = response.json() && response.json().accessToken;      
      
      if(token) {

        window.sessionStorage.clear();

        window.sessionStorage.setItem("userName", response.json().userName);
        window.sessionStorage.setItem("token", response.json().accessToken);
        window.sessionStorage.setItem("roleName", response.json().roleName);
        
        return true;
      } else {
        return false;
      }
    })
      .catch((error: any) => Observable.throw(error.json().error || 'Error de Servidor'));
  }

  logout() {
    window.sessionStorage.clear();
  }
}

//https://thiagomelin.com.br/2017/09/04/angular-2-guard-protegendo-suas-rotas/
//https://embed.plnkr.co/KNb1No/
