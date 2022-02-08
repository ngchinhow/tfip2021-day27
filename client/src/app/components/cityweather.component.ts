import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { City } from '../models';
import { WeatherService } from '../serivces/weather.service';

@Component({
  selector: 'app-cityweather',
  templateUrl: './cityweather.component.html',
  styleUrls: ['./cityweather.component.css']
})
export class CityweatherComponent implements OnInit {
  city!: City;

  constructor(
    private activatedRoute: ActivatedRoute,
    private weatherSvc: WeatherService
  ) { }

  ngOnInit(): void {
    const city = this.activatedRoute.snapshot.params['city'];
    const fields = this.activatedRoute.snapshot.queryParams['fields'];
    console.info(fields);
    this.weatherSvc.getWeather(city)
      .then(result => {
        console.info(result);
        this.city = result;
      })
      .catch(error => {
        throw new Error('could not get weather for city: ' + city);
      });
  }
}
