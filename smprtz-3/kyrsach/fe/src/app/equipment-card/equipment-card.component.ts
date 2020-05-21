import { Component, OnInit, Input } from '@angular/core';
import { BackendService } from '../services/backend.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-equipment-card',
  templateUrl: './equipment-card.component.html',
  styleUrls: ['./equipment-card.component.css']
})
export class EquipmentCardComponent implements OnInit {

  public item$;
  public image;
  @Input() id: string; 
  @Input() date: string; 

  constructor(private _be: BackendService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.item$ = this._be.getEquipmentDetails(this.id);
  }
}
