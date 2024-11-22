import { CardDto } from "./cardDto";

export interface Player {
    userId: string,
    username: string,
    cardList: CardDto[]
}