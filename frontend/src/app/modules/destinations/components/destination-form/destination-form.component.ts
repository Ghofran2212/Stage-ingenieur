import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DestinationService } from '../../../../core/services/destination.service';

@Component({
  selector: 'app-destination-form',
  templateUrl: './destination-form.component.html',
  styleUrls: ['./destination-form.component.scss']
})
export class DestinationFormComponent implements OnInit {
  form: FormGroup;
  loading = false;
  saving  = false;
  error   = '';
  isEditMode = false;
  destinationId: number | null = null;
  imagePreview = '';

  regions    = ['Nord','Sud','Centre','Est','Ouest','Grand Tunis','Sahara','Côte'];
  categories = ['Plage','Montagne','Désert','Ville','Patrimoine','Nature','Oasis','Médina'];

  constructor(
    private fb: FormBuilder,
    private destinationService: DestinationService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.form = this.fb.group({
      nom:         ['', [Validators.required, Validators.minLength(2)]],
      description: ['', [Validators.required, Validators.minLength(10)]],
      region:      ['', Validators.required],
      categorie:   ['', Validators.required],
      prix:        ['', [Validators.required, Validators.min(0)]],
      imageUrl:    ['', Validators.required]
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.destinationId = +id;
      this.loading = true;
      this.destinationService.findById(this.destinationId).subscribe({
        next: (d) => { this.form.patchValue(d); this.imagePreview = d.imageUrl; this.loading = false; },
        error: () => { this.loading = false; this.error = 'Destination introuvable.'; }
      });
    }
  }

  get nom()         { return this.form.get('nom')!; }
  get description() { return this.form.get('description')!; }
  get region()      { return this.form.get('region')!; }
  get categorie()   { return this.form.get('categorie')!; }
  get prix()        { return this.form.get('prix')!; }
  get imageUrl()    { return this.form.get('imageUrl')!; }

  onImageUrlChange(): void {
    this.imagePreview = this.form.get('imageUrl')?.value || '';
  }

  onSubmit(): void {
    if (this.form.invalid) { this.form.markAllAsTouched(); return; }
    this.saving = true; this.error = '';
    const obs = this.isEditMode
      ? this.destinationService.update(this.destinationId!, this.form.value)
      : this.destinationService.save(this.form.value);
    obs.subscribe({
      next: () => { this.saving = false; this.router.navigate(['/destinations']); },
      error: (e) => { this.saving = false; this.error = e.error?.message || 'Erreur lors de la sauvegarde.'; }
    });
  }

  cancel(): void { this.router.navigate(['/destinations']); }
}