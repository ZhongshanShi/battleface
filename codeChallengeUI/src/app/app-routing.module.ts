import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
// import { EmployeeListComponent } from './employee-list/employee-list.component';
import { QuotationListComponent } from './quotation-list/quotation-list.component';

import { CreateQuotationComponent } from './create-quotation/create-quotation.component';
// import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
// import { EmployeeDetailsComponent } from './employee-details/employee-details.component';

const routes: Routes = [
  // {path: 'employees', component: EmployeeListComponent},
  
  {path: 'create-quotation', component: CreateQuotationComponent},
  {path: 'quotations', component: QuotationListComponent}
  // {path: '', redirectTo: 'quotations', pathMatch: 'full'}

  // {path: 'update-employee/:id', component: UpdateEmployeeComponent},
  // {path: 'employee-details/:id', component: EmployeeDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
