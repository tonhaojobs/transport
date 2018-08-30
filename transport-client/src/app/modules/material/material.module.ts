import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { 
  MatButtonModule, 
  MatInputModule, 
  ErrorStateMatcher, 
  ShowOnDirtyErrorStateMatcher, 
  MatSelectModule
} from '@angular/material';

import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    MatButtonModule,
    MatInputModule,
    MatSelectModule
  ],
  
  exports: [
    MatButtonModule,
    MatInputModule,
    MatSelectModule
  ],
  
  providers: [
    {provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher}
  ],

  declarations: []
})
export class MaterialModule { }
