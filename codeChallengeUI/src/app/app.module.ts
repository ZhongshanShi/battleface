import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
// import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { FormsModule} from '@angular/forms';
import { QuotationListComponent } from './quotation-list/quotation-list.component';
import { CreateQuotationComponent } from './create-quotation/create-quotation.component'
// import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
// import { EmployeeDetailsComponent } from './employee-details/employee-details.component'

@NgModule({
  declarations: [
    AppComponent,
    EmployeeListComponent,
    QuotationListComponent,
    CreateQuotationComponent
    // CreateEmployeeComponent,
    // UpdateEmployeeComponent,
    // EmployeeDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
