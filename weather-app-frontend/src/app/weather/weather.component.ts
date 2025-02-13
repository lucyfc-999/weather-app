import { Component, OnInit } from '@angular/core';
import { WeatherService } from '../weather.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-weather',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './weather.component.html',
  styleUrl: './weather.component.css'
})
export class WeatherComponent implements OnInit{
  title = 'Weather App';
  city: string = '';
  weatherData: any = null;

  constructor(private weatherService: WeatherService) { }
  ngOnInit(): void {}
  // Método para obtener el clima de la ciudad
  getWeather() {
    if (this.city) {
      this.weatherService.getWeather(this.city).subscribe({
        next: (data) => {
          this.weatherData = data;
          console.log('Weather data:', data);
        },
        error: (error) => {
          console.error('Error fetching weather data:', error);
        },
        complete: () => {
          console.log('Weather data fetching complete.');
        }
      });  
    }
  }
}
