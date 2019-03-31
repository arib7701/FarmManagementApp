import { PipeTransform, Pipe } from '@angular/core';
import { Animal } from '../models/animal';

@Pipe({
    name: 'tableFilter',
    pure: false
})
export class TableFilterPipe implements PipeTransform {
    keys = [];
    transform(items: Animal[], args: any): any {

        if (args.column === 'all' && args.choice === 'all') {
            return items;
        } else {

            switch (args.column) {
                case 'name':
                return items.filter(item => item.name === args.choice);
                    break;
                case 'sex':
                    return items.filter(item => item.sex === args.choice);
                    break;
                case 'barn':
                    return items.filter(item => item.barn === args.choice);
                    break;
                case 'state':
                    return items.filter(item => item.state === args.choice);
                    break;
                case 'lastDateWeight':
                    return items.filter(item => item.lastDateWeight === args.choice);
                    break;
                case 'lastWeight':
                    return items.filter(item => item.lastWeight === args.choice);
                    break;
            }
        }
    }
}
