import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpListDatatableComponent } from './emp-list-datatable.component';

describe('EmpListDatatableComponent', () => {
  let component: EmpListDatatableComponent;
  let fixture: ComponentFixture<EmpListDatatableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmpListDatatableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmpListDatatableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
