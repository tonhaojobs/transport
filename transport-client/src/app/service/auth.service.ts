import { AbstractService } from './abstract.service';
import { Injectable } from '@angular/core';
import { Usuario } from '../model/entity/usuario';
import { Response, Http } from '@angular/http';
import { Observable } from 'rxjs-compat';


@Injectable()
export class AuthService extends AbstractService {

  authenticated = false;
  relativePath: string = "/auth";

  constructor(public http: Http) { 
    super(http);
  }

  login(usuario : Usuario) : Observable<any> {

    const action = "/signin";

    return this.http.post(this.url + this.relativePath + action, 
      { 'usernameOrEmail': usuario.login, 'password': usuario.password }/*, 
      { headers: this.getHeaders() }*/
    )
    .map((response: Response) =>{
        const token = response.json() && response.json();                            
        return response.json();
      })
      .catch((error: any) => Observable.throw(error.json().error || 'Error de Servidor'));
  }
}