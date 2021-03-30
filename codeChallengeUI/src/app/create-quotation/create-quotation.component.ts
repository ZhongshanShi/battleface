import { Component, OnInit } from '@angular/core';
import { CustomerInput } from '../customer-input';
import { Employee } from '../employee';
// import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-create-quotation',
  templateUrl: './create-quotation.component.html',
  styleUrls: ['./create-quotation.component.css']
})
export class CreateQuotationComponent implements OnInit {

  customerInput: CustomerInput = new CustomerInput();
  employee: Employee = new Employee();

  constructor() { }

  ngOnInit(): void {
  }

}
