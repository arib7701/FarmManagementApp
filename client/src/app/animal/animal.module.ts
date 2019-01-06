import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TypeComponent } from './type/type.component';
import { DetailTypeComponent } from './detail-type/detail-type.component';
import { DetailAnimalComponent } from './detail-animal/detail-animal.component';
import { AnimalComponent } from './animal.component';

import { animalRoutes } from './animal.routes';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  imports: [CommonModule, RouterModule.forChild(animalRoutes), SharedModule],
  declarations: [
    TypeComponent,
    DetailTypeComponent,
    DetailAnimalComponent,
    AnimalComponent
  ]
})
export class AnimalModule {}
