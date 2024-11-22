
import socket from "../socket"
import { useEffect, useState } from "react";
import { Card } from "../types/card";
// user
import { useSelector } from "react-redux";
import { selectUserId } from "../store/selectors/user.selectors";
import { fetchCardByUserId } from "../api/card";
import WaitingRoom from "../components/Game/WaitingRoom";
import SelectCards from "../components/Game/SelectCards";
import GameComponent from "../components/Game/GameComponent";

interface attackEvent {
  UsedBy: string,
  UsedAgainst: string,
  cardAttacking: Card,
  cardAttacked: Card
}

interface endTurnEvent {
  usedBy: string,
  pointRemaining: number
}

export class Player {
  username: number;
  userId: string;
  cards: Card[];
  selectedCards: Card[];
  actionPoint: number;

  constructor(username?: number, userId?: string, cards?: Card[], selectedCards?:Card[], actionPoint?: number) {
    this.username = username || 0;
    this.userId = userId || "";
    this.cards = cards || [];
    this.selectedCards = selectedCards || [];
    this.actionPoint = actionPoint || 0;
   }
}


export default function Game() {
  // rendering waiting room or other state


  const [activeComponent, setActiveComponent] = useState('A'); // Default component
  // let [componentState, setComponentState] = useState<Number>(); // waiting room = 0 => select card = 1 game = 2 => 
  // componentState = 0;
  const userId = useSelector(selectUserId);
  const usersConnected: any[] = [];
  const [opponent, setOpponent] = useState<Player>(new Player());
  
  const [mainPlayer, setMainPlayer] = useState<Player>(new Player());
  socket.removeAllListeners();
  socket.auth = { userId };
  socket.connect();
  
  socket.on("connect_error", (err) => {
    if (err.message === "invalid username") {
      console.log("username not found")
      // redirect?
    }
  });

  socket.on("users", async(users) => {
    users.forEach((user: any) => {
      user.self = user.userID === socket.id;
      usersConnected.push(user);
    });

    const newMainPlayer = usersConnected.find(user => user.self === true)
    setMainPlayer(newMainPlayer);
    if (usersConnected.length >= 2) {
      // there is an opponent
      const newOpponent = usersConnected.find(user => user.self !== true)
      setOpponent(newOpponent);
      console.log("ici" , newOpponent, newMainPlayer);
      initMach(newOpponent, newMainPlayer);
      setActiveComponent('B');

    }
  });

socket.on("user-connected", async (user) => {
    if (opponent) {
      console.log("there is already an opponent in this room");
      // return;
    }
    console.log("user-connected: ",user)
    setOpponent(new Player(user.username, user.userId));
    initMach(opponent, mainPlayer);
    setActiveComponent('B');
  })


  socket.on("connect", () => {
    //console.log(socket.id)
  });

  socket.on("disconnect", () => {
    //console.log(socket.id); // undefined
  });

  return (  
  <div>
    <div>
      <button onClick={()=> {setActiveComponent('B')}}>debug switch</button>
    </div>
    {renderComponent(mainPlayer)}</div>);

function renderComponent (player: Player) {
  // console.log(player)
  switch(activeComponent) {
    case 'A':
      return <WaitingRoom/>
    case 'B':
      return <SelectCards player={player}/>
    case 'C':
      return <GameComponent/>
  }
} 


  function initMach(opponent: Player, mainPlayer: Player) {
    displayCard(opponent, mainPlayer)
  }

  async function displayCard(opponent: Player, mainPlayer: Player) {
    let opponentCards = await fetchCardByUserId(opponent.username);
    let mainPlayerCards = await fetchCardByUserId(mainPlayer.username);
    setMainPlayer(new Player(mainPlayer.username, mainPlayer.userId, mainPlayerCards, [], 0));
    setOpponent(new Player(opponent.username, opponent.userId, opponentCards, [], 0));
  }
}

