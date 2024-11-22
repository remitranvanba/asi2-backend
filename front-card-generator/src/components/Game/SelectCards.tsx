import React, { useState } from "react";
import { Card } from "../../types/card";
import { Player } from "../../views/Game";

interface Props {
  player: Player;
}

function isInArray(array: any[], element: any) {
  return Boolean(array.find(e => e.id === element.id) !== undefined);
}


export default function SelectCards({player}: Props) {
  

  
  const [selectedCards, setSelectedCards] = useState<Card[]>([])
  function onCardClick(card: Card) {
    let indice = selectedCards.findIndex(c => c.id === card.id)
    if (indice >= 0) {
      const newSelectedCards = selectedCards.filter(c => c.id !== card.id);
      setSelectedCards(newSelectedCards);
    } else {
      const newSelectedCards = selectedCards
      newSelectedCards.push(card);
      setSelectedCards(newSelectedCards);
    }
  }

  let rows;

  if (player.cards !== undefined && player.cards !== null) {
      console.log(selectedCards);
      rows = player.cards.map((card, i) => (
    <div key={i} className={"game-card-wrapper "} onClick={() => {onCardClick(card)}}>
        <div className="game-title-wrapper">{card?.name}</div>
        <div className="game-image-wrapper">
          <img
            className="game-image"
            src={card?.imgUrl}
            width="400px"
            height="400px"
            alt={card?.name}
          ></img>
        </div>
        <div className="game-image-wrapper"></div>
        <div className="game-pv-wrapper">PV</div>
        <div className="game-stat-wrapper">
          <div>{card?.attack} ATK</div>
          <div>{card?.defence} DEF</div>
        </div>
      </div>
  ));
  }

  return (
    <div className="game-card-container">
      <div className="game-card-container-wrapper">
      {rows}

      </div>
    </div>
  );
};
