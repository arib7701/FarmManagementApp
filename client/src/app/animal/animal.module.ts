import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TypeComponent } from './type/type.component';
import { DetailTypeComponent } from './detail-type/detail-type.component';
import { DetailAnimalComponent } from './detail-animal/detail-animal.component';
import { AnimalComponent } from './animal.component';

import { animalRoutes } from './animal.routes';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { DetailAnimalEditComponent } from './detail-animal-edit/detail-animal-edit.component';
import { DetailAnimalCreateComponent } from './detail-animal-create/detail-animal-create.component';
import { DetailAnimalWeightEditComponent } from './detail-animal-weight-edit/detail-animal-weight-edit.component';
import { DetailAnimalWeightComponent } from './detail-animal-weight/detail-animal-weight.component';
import { DetailAnimalDeliveryComponent } from './detail-animal-delivery/detail-animal-delivery.component';
import { DetailAnimalDeliveryEditComponent } from './detail-animal-delivery-edit/detail-animal-delivery-edit.component';
import { TypeCreateComponent } from './type-create/type-create.component';
import { TypeEditComponent } from './type-edit/type-edit.component';
import { DetailAnimalCreateBatchComponent } from './detail-animal-create-batch/detail-animal-create-batch.component';
import { DetailAnimalWeightBatchComponent } from './detail-animal-weight-batch/detail-animal-weight-batch.component';
import { DetailAnimalMatingComponent } from './detail-animal-mating/detail-animal-mating.component';
import { DetailAnimalMatingEditComponent } from './detail-animal-mating-edit/detail-animal-mating-edit.component';

@NgModule({
  imports: [CommonModule, RouterModule.forChild(animalRoutes), SharedModule],
  declarations: [
    TypeComponent,
    DetailTypeComponent,
    DetailAnimalComponent,
    AnimalComponent,
    DetailAnimalEditComponent,
    DetailAnimalCreateComponent,
    DetailAnimalWeightEditComponent,
    DetailAnimalWeightComponent,
    DetailAnimalDeliveryComponent,
    DetailAnimalDeliveryEditComponent,
    TypeCreateComponent,
    TypeEditComponent,
    DetailAnimalCreateBatchComponent,
    DetailAnimalWeightBatchComponent,
    DetailAnimalMatingComponent,
    DetailAnimalMatingEditComponent
  ]
})
export class AnimalModule {}
