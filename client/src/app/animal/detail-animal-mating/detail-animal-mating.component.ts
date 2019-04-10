import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs';
import { Mating } from 'src/app/models/mating';
import { MatingService } from 'src/app/services/mating.service';

@Component({
  selector: 'app-detail-animal-mating',
  templateUrl: './detail-animal-mating.component.html',
  styleUrls: ['./detail-animal-mating.component.css']
})
export class DetailAnimalMatingComponent implements OnInit, OnDestroy {
  @Input()
  idAnimal: number;

  @Input()
  sexAnimal: string;

  subscriptionMatings: Subscription;
  matings: Mating[];
  noMatings = false;

  constructor(private matingService: MatingService) {}

  ngOnInit() {
    this.subscriptionMatings = this.matingService
      .getAllMatingByAnimalId(this.idAnimal)
      .subscribe(
        matings => {
          this.matings = matings;
          if (this.matings.length > 0) {
            console.log('Success getting matings');
          } else {
            this.noMatings = true;
          }
        },
        error => {
          console.log('Error getting matings');
        }
      );
  }

  ngOnDestroy() {
    if (this.subscriptionMatings !== undefined) {
      this.subscriptionMatings.unsubscribe();
    }
  }

}
