import {NgModule} from '@angular/core';
import {MatButtonModule, MatCheckboxModule,MatAutocompleteModule} from '@angular/material';
@NgModule({
    imports: [MatButtonModule, MatCheckboxModule,MatAutocompleteModule],
    exports: [MatButtonModule, MatCheckboxModule,MatAutocompleteModule]
  })
  export class MaterialModule implements NgModule{ 

  }