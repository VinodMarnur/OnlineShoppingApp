import { NgModule } from '@angular/core';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CommonModule } from '@angular/common';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

import {MatDialogModule, MatTableModule,MatPaginatorModule,MatSortModule} from '@angular/material';
// import { DatePickerModule } from 'angular-material-datepicker';

@NgModule({
  imports: [   CommonModule, 
               MatToolbarModule, 
               MatButtonModule, 
               MatCardModule, 
               MatInputModule, 
               MatDialogModule, 
               MatTableModule,
               MatProgressSpinnerModule,
               MatPaginatorModule,
               MatSortModule,
              //  DatePickerModule
              ],
  exports: [
              MatToolbarModule,
              MatCardModule,
              MatInputModule,
              MatFormFieldModule,
              MatButtonModule,
              MatDialogModule, 
              MatTableModule,
              MatProgressSpinnerModule,
              MatPaginatorModule,
              MatSortModule,
              // DatePickerModule
  ]
})
export class AppMaterialModule {}
