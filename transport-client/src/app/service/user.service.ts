import { AuthService } from './auth.service';
import { Http, RequestOptions, Headers } from '@angular/http';
import { AbstractService } from './abstract.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../model/entity/usuario';

@Injectable()
export class UserService extends AbstractService {

  relativePath: string = "/user";

  constructor(
    public http: Http, 
    private authService : AuthService) {
      super(http);
  }

  getUserProfile(userName: string, token: string) : Observable<Usuario>{
    
    this.action = "/"+userName;

    let myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json'); 
    myHeaders.append('Authorization', 'Bearer '+token);   
	
    let options = new RequestOptions({ headers: myHeaders });
    
    const result: Observable<Usuario> = 
    this.http.get(this.url + this.relativePath + this.action, options).map(res => res.text() ? res.json() : {});

    return result;
  }
}
