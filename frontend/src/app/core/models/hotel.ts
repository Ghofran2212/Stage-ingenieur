export interface Hotel {
  id?: number;
  nom: string;
  etoiles: number;
  prixParNuit: number;
  imageUrl: string;
  destinationId: number;
}