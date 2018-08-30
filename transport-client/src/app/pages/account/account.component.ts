import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';
import { Usuario } from '../../model/entity/usuario';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  usuario: Usuario;
  
  constructor(private userService: UserService, private authService: AuthService) { }

  ngOnInit() {

    this.usuario = new Usuario();

    let userName = this.authService.getUserName();
    let token = this.authService.getActiveToken(); 

    this.loadUser(userName, token)
    .subscribe(data => {

      this.usuario = data;
    },
    
    (error: any) => {
 
    });
  }

  loadUser(userName: string, token: string) {
    //alert(this.authService.getUserName());
    return this.userService.getUserProfile(userName, token);
  }

}
