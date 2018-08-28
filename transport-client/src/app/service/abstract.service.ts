import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';

@Injectable()
export class AbstractService {

  protected http: Http;
  protected url = "http://localhost:8080/transport/api";

  constructor(http : Http) {
    this.http = http;
  }

  getHeaders() {
    //const token = sessionStorage.getItem('token');
    const headers = new Headers();

    headers.set('Content-Type', 'application/json');
    headers.set('Accept', 'application/json');
    //headers.set('Authorization', sessionStorage.getItem('token'));

    //headers.append('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');
    //headers.append('Access-Control-Allow-Headers', '*');

    //headers.append('Authorization', token);

    return headers;
  }
}
