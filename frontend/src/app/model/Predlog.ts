import Jelo from "./Jelo";

export default interface Predlog{
    id?: number;
    tipIzmene: string,
    novoJelo?: Jelo,
    staroJeloId?: number,
    status: string,
}