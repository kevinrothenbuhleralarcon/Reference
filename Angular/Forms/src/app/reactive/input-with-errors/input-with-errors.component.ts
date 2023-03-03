import {Component, Input, OnInit} from '@angular/core';
import {AbstractControl, ControlContainer, FormControl, FormGroup, NgForm} from "@angular/forms";

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

  control = new FormGroup({
    input: new FormControl()
  });

  constructor() { }

  ngOnInit(): void {
  }

}
