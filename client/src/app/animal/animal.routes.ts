import { Routes, RouterModule } from '@angular/router';
import { AnimalComponent } from './animal.component';
import { TypeComponent } from './type/type.component';
import { DetailAnimalComponent } from './detail-animal/detail-animal.component';
import { DetailTypeComponent } from './detail-type/detail-type.component';

export const animalRoutes: Routes = [
  {
    path: '',
    component: AnimalComponent,
    children: [
      {
        path: '',
        component: TypeComponent
      },
      {
        path: 'type/:type',
        component: DetailTypeComponent
      },
      {
        path: ':id',
        component: DetailAnimalComponent
      }
    ]
  }
];
