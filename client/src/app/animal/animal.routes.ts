import { Routes, RouterModule } from '@angular/router';
import { AnimalComponent } from './animal.component';
import { TypeComponent } from './type/type.component';
import { DetailAnimalComponent } from './detail-animal/detail-animal.component';
import { DetailTypeComponent } from './detail-type/detail-type.component';
import { DetailAnimalEditComponent } from './detail-animal-edit/detail-animal-edit.component';
import { DetailAnimalCreateComponent } from './detail-animal-create/detail-animal-create.component';

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
        path: 'types/:type',
        component: DetailTypeComponent
      },
      {
        path: '/:id',
        component: DetailAnimalComponent
      },
      {
        path: 'edit/:id',
        component: DetailAnimalEditComponent
      },
      {
        path: 'add',
        component: DetailAnimalCreateComponent
      }
    ]
  }
];
