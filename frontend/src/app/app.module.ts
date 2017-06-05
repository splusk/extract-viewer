import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { CuppaDataGridModule } from 'cuppa-ng2-grid/cuppa-ng2-dataGrid';
import { DataTableModule } from 'ng2-flex-table';

import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    CuppaDataGridModule, DataTableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }