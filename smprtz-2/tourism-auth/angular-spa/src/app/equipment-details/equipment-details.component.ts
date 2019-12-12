import { Component, OnInit } from '@angular/core';
import { BackendService } from '../services/backend.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-equipment-details',
  templateUrl: './equipment-details.component.html',
  styleUrls: ['./equipment-details.component.css']
})
export class EquipmentDetailsComponent implements OnInit {

  public item$;

  constructor(private _be: BackendService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.item$ = this._be.getEquipmentDetails(this.route.snapshot.paramMap.get("id"));
  }

  public order(id: string) {
    this._be.addOrder(id).subscribe(
      () => alert("Order saved!"),
      (error) => alert("Order saved!")
    );
  }

}
