import { Component, OnInit } from '@angular/core';
import { BackendService } from '../services/backend.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Equipment } from '../types';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-equipment-details',
  templateUrl: './equipment-details.component.html',
  styleUrls: ['./equipment-details.component.css']
})
export class EquipmentDetailsComponent implements OnInit {

  public id;
  public feedbacks;
  public name;
  public price;
  public type;
  public description;
  public rating;

  constructor(private _be: BackendService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get("id");
    this._be.getEquipmentDetails(this.id).pipe(
      tap((res: Equipment) => {
        this.name = res.name,
        this.price = res.price,
        this.type = res.type,
        this.description = res.description,
        this.rating = res.rating
      })
    ).subscribe()
    this._be.getFeedbacksByEquipmentList(this.id).subscribe(
      res => {
        this.feedbacks = res
      }
    )
  }

  public order() {
    this._be.addBooking(this.id).subscribe(
      () => alert("Order saved!"),
      () => alert("Order save failed!")
    );
  }

  public isAdmin() {
    return this._be.isLoggedIn() && this._be.isAdmin();
  }

  public update() {
    this._be.updateEquipment(this.id, this.name, this.type, this.price, 
      this.rating, this.description).subscribe(
      () => alert("Updated!"),
      () => alert("Update failed!")
    )
  }

  public delete() {
    this._be.deleteEquipment(this.id).subscribe(
      () => alert("Deleted!"),
      () => alert("Delete failed!")
    )
  }
}