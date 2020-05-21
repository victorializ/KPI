import { Component, OnInit } from '@angular/core';
import { BackendService } from '../services/backend.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

  public orders;
  public text;

  constructor(private _be: BackendService) { }

  ngOnInit() {
    this.orders = this._be.getBookingsList();
  }
}
