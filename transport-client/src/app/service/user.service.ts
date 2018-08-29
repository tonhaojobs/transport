import { AuthService } from './auth.service';
import { Http } from '@angular/http';
import { AbstractService } from './abstract.service';
import { Injectable } from '@angular/core';

@Injectable()
export class UserService extends AbstractService{

  constructor(
    public http: Http, 
    private authService : AuthService) {
    
      super(http);
  }
}
