import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { lastValueFrom } from "rxjs";
import { City } from "../models";

const BASE_URI = 'http://localhost:8080/api';
@Injectable()
export class WeatherService {
  constructor(private http: HttpClient) { }

  getWeather(city: string): Promise<City> {
    return lastValueFrom(
      this.http.get<City>(BASE_URI + '/weather/' + city)
    );
  }
}
