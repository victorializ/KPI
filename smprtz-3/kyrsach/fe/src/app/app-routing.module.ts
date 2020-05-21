import { NgModule } from '@angular/core';
import { Routes, RouterModule, CanActivate} from '@angular/router';
import { EquipmentListComponent } from './equipment-list/equipment-list.component';
import { EquipmentDetailsComponent } from './equipment-details/equipment-details.component';
import { LoginComponent } from "./login/login.component";
import { OrderListComponent } from './order-list/order-list.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { OrderDetailsComponent } from './order-details/order-details.component';
import { AuthGuard, AdminAuthGuard } from './services/auth-guard.service';

const routes: Routes = [
  { path: '',   redirectTo: '/equipment', pathMatch: 'full' },
  { path: 'equipment', component: EquipmentListComponent},
  { path: 'equipment/:id', component: EquipmentDetailsComponent},
  { path: "login", component: LoginComponent },
  { path: "order", component: OrderListComponent, canActivate: [AuthGuard] },
  { path: "order/:id", component: OrderDetailsComponent, canActivate: [AuthGuard] },
  { path: "admin", component: AdminPageComponent, canActivate: [AdminAuthGuard]  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
