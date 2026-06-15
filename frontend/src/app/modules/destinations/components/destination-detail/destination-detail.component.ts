import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Destination } from '../../../../core/models/destination';
import { DestinationService } from '../../../../core/services/destination.service';
import { AuthService } from '../../../../core/services/auth.service';

@Component({
  selector: 'app-destination-detail',
  templateUrl: './destination-detail.component.html',
  styleUrls: ['./destination-detail.component.scss']
})
export class DestinationDetailComponent implements OnInit {
  destination: Destination | null = null;
  loading = true;
  error = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private destinationService: DestinationService,
    public authService: AuthService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.destinationService.findById(+id).subscribe({
        next: (d) => { this.destination = d; this.loading = false; },
        error: () => { this.loading = false; this.error = 'Destination introuvable.'; }
      });
    }
  }

  reserve(): void {
    if (this.destination) this.router.navigate(['/reservations/new', this.destination.id]);
  }

  goBack(): void { this.router.navigate(['/destinations']); }
}