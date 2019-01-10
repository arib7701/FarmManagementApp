import { Weight } from './weight';

export class Animal {
  id?: number;
  name?: string;
  type?: number;
  birth?: Date;
  death?: Date;
  arrival?: Date;
  departure?: Date;
  barn?: string;
  sex?: string;
  motherId?: number;
  fatherId: number;
  isresearch?: boolean;
  weights?: Weight[];
  age?: number;
  status?: string;
}
