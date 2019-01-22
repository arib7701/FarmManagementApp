import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Delivery } from 'src/app/models/delivery';
import { DeliveryService } from 'src/app/services/delivery.service';

@Component({
  selector: 'app-detail-animal-delivery',
  templateUrl: './detail-animal-delivery.component.html',
  styleUrls: ['./detail-animal-delivery.component.css']
})
export class DetailAnimalDeliveryComponent implements OnInit, OnDestroy {
  @Input()
  idAnimal: number;

  @Input()
  sexAnimal: string;

  subscriptionDeliveries: Subscription;
  deliveries: Delivery[];
  noDeliveries = false;

  constructor(private deliveryService: DeliveryService) { }

  ngOnInit() {
    console.log('init ', this.idAnimal, this.sexAnimal);
    this.subscriptionDeliveries = this.deliveryService
      .getAllDeliveryByAnimalId(this.idAnimal)
      .subscribe(
        deliveries => {
          this.deliveries = deliveries;
          console.log('sub ', deliveries, this.idAnimal, this.sexAnimal);
          if (this.deliveries.length > 0) {
            console.log('Success getting deliveries', this.deliveries);
          } else {
            this.noDeliveries = true;
          }
        },
        error => {
          console.log('Error getting deliveries');
        }
      );
  }

  ngOnDestroy() {
    if (this.subscriptionDeliveries !== undefined) {
      this.subscriptionDeliveries.unsubscribe();
    }
  }

}
