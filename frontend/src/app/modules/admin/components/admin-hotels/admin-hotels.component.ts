import { Component, OnInit } from '@angular/core';
import { HotelService } from '../../../../core/services/hotel.service';
import { DestinationService } from '../../../../core/services/destination.service';
import { Hotel } from '../../../../core/models/hotel';
import { Destination } from '../../../../core/models/destination';

@Component({
  selector: 'app-admin-hotels',
  templateUrl: './admin-hotels.component.html'
})
export class AdminHotelsComponent implements OnInit {

  hotels: Hotel[] = [];
  destinations: Destination[] = [];

  hotel: Hotel = {
    nom: '',
    etoiles: 3,
    prixParNuit: 0,
    imageUrl: '',
    destinationId: 0
  };

  constructor(
    private hotelService: HotelService,
    private destinationService: DestinationService
  ) {}

  ngOnInit(): void {
    this.loadHotels();

    this.destinationService.findAll().subscribe(data => {
      this.destinations = data;
    });
  }

  loadHotels(): void {
    this.hotelService.findAll().subscribe(data => {
      this.hotels = data;
    });
  }

  save(): void {
    this.hotelService.save(this.hotel).subscribe(() => {

      this.hotel = {
        nom: '',
        etoiles: 3,
        prixParNuit: 0,
        imageUrl: '',
        destinationId: 0
      };

      this.loadHotels();
    });
  }

  delete(id: number): void {
    this.hotelService.delete(id).subscribe(() => {
      this.loadHotels();
    });
  }
}