import { Component, OnInit } from '@angular/core';
import { Quotation } from '../quotation';
import { QuotationService } from '../quotation.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-quotation-list',
  templateUrl: './quotation-list.component.html',
  styleUrls: ['./quotation-list.component.css']
})
export class QuotationListComponent implements OnInit {

  quotations: Quotation[];

  constructor(private quotationService: QuotationService,
    private router: Router) { }

  ngOnInit(): void {
    this.getQuotations();
  }

  private getQuotations(){
    this.quotationService.getQuotationsList().subscribe(data => {
      this.quotations = data;
    });
  }
}
