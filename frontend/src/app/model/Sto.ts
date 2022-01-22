import Porudzbina from "./Porudzbina";

export default interface Sto{
    id?: number;
    zauzet:  boolean;
    brojMesta: number;
    x: number;
    y: number;
    naziv: string;
    porudzbinaId: (number | null);
}