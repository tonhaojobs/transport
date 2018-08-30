import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private auth: AuthService, 
    private router: Router) { }

  ngOnInit() {}

  headerVisible() {
    return this.auth.isAuthenticate();
  }

  userName() {
    return this.auth.getUserName();
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/login']);
  }

}
