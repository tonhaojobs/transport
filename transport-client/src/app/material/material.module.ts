import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule, MatInputModule } from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    MatButtonModule,
    MatInputModule
  ],
  
  exports: [
    MatButtonModule,
    MatInputModule
  ],
  
  declarations: []
})
export class MaterialModule { }
