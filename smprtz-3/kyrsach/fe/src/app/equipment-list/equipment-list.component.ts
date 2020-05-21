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
  filter: string = "all";
  types: string[] = ['all', 'backpack', 'tent', 'clothing', 'sleeping back'];

  constructor(private _be: BackendService) { }

  public loadData() {
    this.eq = this._be.getEquipmentList();
  }

  public corresponds(name, type) {
    return (name.includes(this.name) || this.name === '') 
      && (this.filter === type || this.filter === 'all')
  }

  ngOnInit() {
    this.loadData();
  }
}
