import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from '../../pages/home/home.component';
import { AccountComponent } from '../../pages/account/account.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: '/home'},
  {path: 'account', component: AccountComponent}
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    CommonModule
  ],
  declarations: []
})
export class HeaderModule { }
