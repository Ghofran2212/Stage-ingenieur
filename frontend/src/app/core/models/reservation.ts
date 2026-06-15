export interface ReservationResponse {
  id?: number;

  destinationId?: number;
  destinationNom?: string;

  userId?: number;
  userEmail?: string;

  dateDebut: string;
  dateFin: string;

  nombrePersonnes: number;

  statut: string;
}