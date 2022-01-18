import Sto from "./Sto";

export default interface Zona{
    id: number;
    naziv: string;
    stolovi: Sto[];
    template: string;
}