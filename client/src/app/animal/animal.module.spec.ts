import { AnimalModule } from './animal.module';

describe('AnimalModule', () => {
  let animalModule: AnimalModule;

  beforeEach(() => {
    animalModule = new AnimalModule();
  });

  it('should create an instance', () => {
    expect(animalModule).toBeTruthy();
  });
});
