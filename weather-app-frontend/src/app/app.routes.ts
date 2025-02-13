import { Routes } from '@angular/router';
import { WeatherComponent } from './weather/weather.component';

export const routes: Routes = [
    { path: '', component: WeatherComponent },  // Ruta principal
    { path: '**', redirectTo: '' }              // Redirige cualquier ruta desconocida a la raíz
];
