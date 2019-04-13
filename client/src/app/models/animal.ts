import { Weight } from './weight';
import { Delivery } from './delivery';
import { Mating } from './mating';

export class Animal {
  id?: number;
  name?: string;
  type?: number;
  birth?: Date;
  death?: Date;
  deathCause?: string;
  arrival?: Date;
  departure?: Date;
  barn?: string;
  sex?: string;
  motherId?: number;
  fatherId: number;
  isResearch?: boolean;
  weights?: Weight[];
  deliveries?: Delivery[];
  matings?: Mating[];
  lastDateWeight?: Date;
  lastWeight?: number;
  ageYear?: number;
  ageMonth?: number;
  state?: string;
  retired?: boolean;
  readyMating: boolean;
}
