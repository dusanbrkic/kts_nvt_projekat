import Jelo from "./Jelo";

export default interface JeloPorudzbine{
    id: number;
    kolicina: number;
    napomena: string;
    statusJela: string;
    jelo: Jelo,
    porudzbinaId: number;
}