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
    DetailAnimalWeightComponent
  ]
})
export class AnimalModule {}
