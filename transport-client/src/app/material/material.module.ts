import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { 
  MatButtonModule, 
  MatInputModule, 
  ErrorStateMatcher, 
  ShowOnDirtyErrorStateMatcher 
} from '@angular/material';

import { FormsModule } from '@angular/forms';

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
  
  providers: [
    {provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher}
  ],

  declarations: []
})
export class MaterialModule { }
