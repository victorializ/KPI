import { Component, OnInit } from '@angular/core';
import { BackendService } from '../services/backend.service';
import { Equipment } from "../types";
import { map } from "rxjs/operators";

@Component({
  selector: 'app-equipment-list',
  templateUrl: './equipment-list.component.html',
  styleUrls: ['./equipment-list.component.css']
})
export class EquipmentListComponent implements OnInit {

  public eq;
  public name = "";
  public sort = false;

  constructor(private _be: BackendService) { }

  public loadData() {
    this.eq = this._be.getEquipmentList().pipe(
      map((res: Array<Equipment>) => this.sort ? res.sort((a, b) => a.name.toLowerCase() < b.name.toLowerCase() ? -1 : 1) : res)
    )
  }
  
  ngOnInit() {
    this.loadData();
  }

  toggleSort() {
    this.sort = !this.sort;
    this.loadData();
  }

}
