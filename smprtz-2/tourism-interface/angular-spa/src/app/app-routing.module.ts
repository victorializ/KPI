import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EquipmentListComponent } from './equipment-list/equipment-list.component';
import { EquipmentDetailsComponent } from './equipment-details/equipment-details.component';
import { OrderListComponent } from './order-list/order-list.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { AuthGuard } from './services/auth-guard.servise';

/*canActivate: [AuthGuard]*/
const routes: Routes = [
  { path: '',   redirectTo: '/equipment', pathMatch: 'full' },
  { path: 'equipment', component: EquipmentListComponent},
  { path: 'equipment/:id', component: EquipmentDetailsComponent},
  { path: "order", component: OrderListComponent },
  { path: "admin", component: AdminPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
