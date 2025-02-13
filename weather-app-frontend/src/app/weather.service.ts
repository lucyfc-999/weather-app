import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {
  private apiUrl = 'http://localhost:9090/api/weather';

  constructor(private http: HttpClient) {}

  getWeather(city: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/search?city=${city}`);
  }
}
