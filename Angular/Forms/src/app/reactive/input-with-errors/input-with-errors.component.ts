import {Component, Input, OnInit} from '@angular/core';
import {AbstractControl, FormControl} from "@angular/forms";

@Component({
  selector: 'app-input-with-errors',
  templateUrl: './input-with-errors.component.html',
})
export class InputWithErrorsComponent implements OnInit {

  @Input()
  label: string = '';

  @Input()
  name: string = '';

  @Input()
  type: string = '';

  @Input()
  control!: AbstractControl<any, any> | null;

  constructor() { }

  ngOnInit(): void {
  }

}
