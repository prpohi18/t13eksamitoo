import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';

// PrimeNG
import { PanelModule} from 'primeng/panel';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { PaginatorModule } from 'primeng/paginator';

import { AppComponent } from './app.component';
import { UsersTableComponent } from './users-table/users-table.component';
import { UserDetailComponent } from './user-detail/user-detail.component';
import { AppRoutingModule } from './/app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    UsersTableComponent,
    UserDetailComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    PanelModule,
    ButtonModule,
    TableModule,
    PaginatorModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
