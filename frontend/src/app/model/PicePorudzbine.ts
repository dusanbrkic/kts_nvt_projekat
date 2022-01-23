import Pice from "./Pice";

export default interface PicePorudzbine{
    id: number;
    kolicina: number;
    napomena: string;
    statusPica: string;
    pice: Pice,
    porudzbinaId: number;
}