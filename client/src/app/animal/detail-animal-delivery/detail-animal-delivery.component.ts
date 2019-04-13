import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Delivery } from 'src/app/models/delivery';
import { DeliveryService } from 'src/app/services/delivery.service';
import { MatingService } from 'src/app/services/mating.service';
import { Mating } from 'src/app/models/mating';

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
  subscriptionMatings: Subscription;
  deliveries: Delivery[];
  matings: Mating[];
  noDeliveries = false;
  pregnancyLength = new Array<string>();

  constructor(private deliveryService: DeliveryService, private matingService: MatingService) {}

  ngOnInit() {
    this.subscriptionMatings = this.matingService
    .getAllMatingByAnimalId(this.idAnimal)
    .subscribe(
      matings => {
        this.matings = matings;
        if (this.matings.length > 0) {
          console.log('Success getting matings');
          this.getDeliveries();
        }
      },
      error => {
        console.log('Error getting matings');
      }
    );
  }

  getDeliveries() {
    this.subscriptionDeliveries = this.deliveryService
    .getAllDeliveryByAnimalId(this.idAnimal)
    .subscribe(
      deliveries => {
        this.deliveries = deliveries;
        if (this.deliveries.length > 0) {
          console.log('Success getting deliveries');
          this.getPregnancyLength();
        } else {
          this.noDeliveries = true;
        }
      },
      error => {
        console.log('Error getting deliveries');
      }
    );
  }

  getPregnancyLength() {

    for (let i = 0; i < this.deliveries.length; i++) {

      const motherId = this.deliveries[i].motherId;
      const fatherId = this.deliveries[i].fatherId;

      const intersection = new Array<Mating>();
      // tslint:disable-next-line:max-line-length
      intersection.push(this.matings.find(mating => mating.motherId === motherId && mating.fatherId === fatherId && mating.successfull === true));

      const days = 1000 * 3600 * 24;

      for (const inter of intersection) {
        const expectedLength = Math.abs(new Date(inter.expectedDate).getTime() - new Date(inter.date).getTime()) / days;
        const fivePercent = (expectedLength * 10) / 100;
        const currentLength = Math.abs(new Date(this.deliveries[i].date).getTime() - new Date(inter.date).getTime()) / days;

        if (currentLength > expectedLength + fivePercent) {
          this.pregnancyLength.push('+');
        } else if (currentLength < expectedLength - fivePercent) {
          this.pregnancyLength.push('-');
        } else {
          this.pregnancyLength.push('=');
        }
      }
    }
  }

  ngOnDestroy() {
    if (this.subscriptionDeliveries !== undefined) {
      this.subscriptionDeliveries.unsubscribe();
    }
  }
}
