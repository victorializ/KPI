import { Component, OnInit } from '@angular/core';
import { BackendService } from '../services/backend.service';
import { Order, Equipment } from '../types';
import { Router, ActivatedRoute } from '@angular/router';
import { map, tap, concatMap } from "rxjs/operators";

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  public id;
  public order;
  public text;
  public rating;

  constructor(private _be: BackendService, private route: ActivatedRoute) { }

  ngOnInit() {
    this._be.getBooking(this.route.snapshot.paramMap.get("id")).subscribe(
      (res: Order) => {
        this.order = res;
        this.id = res.equipmentId;
      }
    );
  }

  sendFeedback() {
    this._be.postFeedback(this.route.snapshot.paramMap.get("id"), 
    this.text, this.rating, this.order.equipmentId).subscribe(
      () => alert("Feedback saved!"),
      (error) => alert("Feedback saved!")
    );
  }
}
