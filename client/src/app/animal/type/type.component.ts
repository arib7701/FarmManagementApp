import { Component, OnInit, OnDestroy } from '@angular/core';
import { TypeService } from 'src/app/services/type.service';
import { Type } from 'src/app/models/type';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-type',
  templateUrl: './type.component.html',
  styleUrls: ['./type.component.css']
})
export class TypeComponent implements OnInit, OnDestroy {
  types: Type[];
  subscribptionType: Subscription;

  constructor(private typeService: TypeService) { }

  ngOnInit() {

    this.subscribptionType = this.typeService.getAllType().subscribe( types => {
      this.types = types;
    }, error => {
      console.log('Error getting the types');
    });

  }

  ngOnDestroy() {
    if (this.subscribptionType !== undefined)  {
      this.subscribptionType.unsubscribe();
    }
  }

}
