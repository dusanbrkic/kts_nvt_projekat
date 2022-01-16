import JeloPorudzbine from "./JeloPorudzbine";
import PicePorudzbine from "./PicePorudzbine"

export default interface Porudzbina{
    id: number;
    statusPorudzbine: string;
    datumVreme: Date;
    napomena: string;
    ukupnaCena: number;
    konobarId: number;
    stoId: number;
    jelaPorudzbine: JeloPorudzbine[];
    picaPorudzbine: PicePorudzbine[]; 
}