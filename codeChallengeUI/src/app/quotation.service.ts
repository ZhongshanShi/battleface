import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Quotation } from './quotation'

@Injectable({
  providedIn: 'root'
})
export class QuotationService {

  private baseURL = "http://localhost:8090/battleface/allQuotations";

  constructor(private httpClient: HttpClient) { }
  
  getQuotationsList(): Observable<Quotation[]>{
    return this.httpClient.get<Quotation[]>(`${this.baseURL}`);
  }

}
