import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  cities: string[] = [
    'Singapore',
    'Kuala Lumpur',
    'Tokyo',
    'Bangkok',
    'Hong Kong',
    'Beijing'
  ];
  form!: FormGroup;


  constructor(
    private fb: FormBuilder,
    private router: Router
  ) { }

  ngOnInit(): void {
    for (let a of localStorage.getItem('additionalCities')?.split('|') || []) {
      this.cities.push(a);
    }
    this.form = this.fb.group({
      city: this.fb.control('', [Validators.minLength(3), Validators.required])
    })
  }

  // used to add new cities
  processForm() {
    const cityName = this.toCapitalCase(this.form.get('city')?.value as string);
    if (this.cities.includes(cityName)) {
      alert('This city is already in the list');
    } else {
      this.cities.push(cityName);
      const additionalCities = localStorage.getItem('additionalCities');
      if (additionalCities == null) {
        localStorage.setItem('additionalCities', cityName);
      } else {
        localStorage.setItem(
          'additionalCities',
          additionalCities + '|' + cityName
        );
      }
    }
    this.form.reset();
  }

  toCapitalCase(str: string): string {
    const arr = str.split(" ");
    for (let i = 0; i < arr.length; i++) {
      arr[i] = arr[i].charAt(0).toUpperCase() + arr[i].slice(1);
    }
    return arr.join(' ');
  }

  go(city: string) {
    const params = {
      fields: 10
    }
    this.router.navigate(
      ['/weather', city],
      { queryParams: params }
    )
  }
}
