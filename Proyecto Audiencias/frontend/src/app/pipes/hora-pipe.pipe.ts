import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'hora'
})
export class HoraPipe implements PipeTransform {
  transform(value: string): string {
    if (!value) return '-';
    const [hours, minutes] = value.split(':'); // Extrae HH y MM
    return `${hours}:${minutes}`;
  }
}
