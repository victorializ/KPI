import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EquipmentListComponent } from './equipment-list/equipment-list.component';
import { EquipmentDetailsComponent } from './equipment-details/equipment-details.component';
import { OrderListComponent } from './order-list/order-list.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { AuthGuard, AdminAuthGuard } from './services/auth-guard.service';
import { MatMenuModule, MatToolbarModule, MatCardModule } from '@angular/material';
import {MatRadioModule} from '@angular/material';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { BackendService } from "./services/backend.service";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import {MatInputModule} from '@angular/material/input'
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { OrderDetailsComponent } from './order-details/order-details.component';
import { EquipmentCardComponent } from './equipment-card/equipment-card.component';

@NgModule({
  declarations: [
    AppComponent,
    EquipmentListComponent,
    EquipmentDetailsComponent,
    OrderListComponent,
    AdminPageComponent,
    LoginComponent,
    OrderDetailsComponent,
    EquipmentCardComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    MatMenuModule,
    MatButtonModule,
    MatToolbarModule,
    MatInputModule,
    MatCardModule,
    HttpClientModule,
    MatIconModule,
    MatRadioModule
  ],
  providers: [AuthGuard, AdminAuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
