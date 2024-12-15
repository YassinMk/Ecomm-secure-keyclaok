import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent {

  constructor(private httpClient: HttpClient) { }

  products: any = [];

  ngOnInit() {
    this.httpClient.get('http://localhost:8087/api/products')
      .subscribe({
        next: (data) => {
          this.products = data;
        },
        error: (error) => {
          console.error('There was an error!', error);
        }
      });
  }

}
