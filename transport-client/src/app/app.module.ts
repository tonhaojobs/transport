import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { AuthService } from './service/auth.service';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './pages/home/home.component';
import { HttpModule } from '@angular/http';
import { FooterComponent } from './templates/footer/footer.component';
import { HeaderComponent } from './templates/header/header.component';
import { MaterialModule } from './modules/material/material.module';
import { HeaderModule } from './modules/header/header.module';
import { AccountComponent } from './pages/account/account.component';
import { UserService } from './service/user.service';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: '/login'},
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    FooterComponent,
    HeaderComponent,
    AccountComponent
  ],

  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    ReactiveFormsModule,
    MaterialModule,
    HeaderModule
  ],

  exports: [
    RouterModule
  ],

  providers: [AuthService, UserService],
  
  bootstrap: [AppComponent]
})
export class AppModule { }
