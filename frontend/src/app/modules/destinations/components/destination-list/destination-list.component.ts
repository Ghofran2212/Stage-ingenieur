import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Destination } from '../../../../core/models/destination';
import { DestinationService } from '../../../../core/services/destination.service';
import { AuthService } from '../../../../core/services/auth.service';

@Component({
  selector: 'app-destination-list',
  templateUrl: './destination-list.component.html',
  styleUrls: ['./destination-list.component.scss']
})
export class DestinationListComponent implements OnInit {
  destinations: Destination[] = [];
  filtered: Destination[] = [];
  loading = true;
  searchTerm = '';
  selectedRegion = '';
  selectedCategorie = '';
  deleteId: number | null = null;
  successMsg = '';
  errorMsg = '';
  regions: string[] = [];
  categories: string[] = [];

  constructor(
    public authService: AuthService,
    private destinationService: DestinationService,
    private router: Router
  ) {}

  ngOnInit(): void { this.load(); }

  load(): void {
    this.loading = true;
    this.destinationService.findAll().subscribe({
      next: (data) => {
        this.destinations = data;
        this.filtered = data;
        this.regions    = [...new Set(data.map(d => d.region).filter(Boolean))];
        this.categories = [...new Set(data.map(d => d.categorie).filter(Boolean))];
        this.loading = false;
      },
      error: () => { this.loading = false; this.errorMsg = 'Erreur lors du chargement.'; }
    });
  }

  applyFilters(): void {
    this.filtered = this.destinations.filter(d => {
      const matchSearch = !this.searchTerm ||
        d.nom.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        d.description.toLowerCase().includes(this.searchTerm.toLowerCase());
      const matchRegion = !this.selectedRegion    || d.region    === this.selectedRegion;
      const matchCat    = !this.selectedCategorie || d.categorie === this.selectedCategorie;
      return matchSearch && matchRegion && matchCat;
    });
  }

  resetFilters(): void {
    this.searchTerm = '';
    this.selectedRegion = '';
    this.selectedCategorie = '';
    this.filtered = [...this.destinations];
  }

  confirmDelete(id: number): void { this.deleteId = id; }
  cancelDelete(): void            { this.deleteId = null; }

  delete(): void {
    if (!this.deleteId) return;
    this.destinationService.delete(this.deleteId).subscribe({
      next: () => {
        this.successMsg = 'Destination supprimée.';
        this.deleteId = null;
        this.load();
        setTimeout(() => this.successMsg = '', 3000);
      },
      error: () => { this.errorMsg = 'Erreur lors de la suppression.'; this.deleteId = null; }
    });
  }

  goToReserve(id: number): void { this.router.navigate(['/reservations/new', id]); }
  goToEdit(id: number): void    { this.router.navigate(['/destinations/admin/edit', id]); }
  goToDetail(id: number): void  { this.router.navigate(['/destinations', id]); }

  get isAdmin(): boolean    { return this.authService.isAdmin; }
  get isLoggedIn(): boolean { return this.authService.isLoggedIn; }
}