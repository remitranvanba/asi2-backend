import { DefaultEventsMap, Socket } from "socket.io";

interface customSocket extends Socket<DefaultEventsMap, DefaultEventsMap, DefaultEventsMap, any> {
    username?: string
  }
// verify username exist 
// todo verify user?
export const middleware = (socket: customSocket, next: any) => {
    const username = socket.handshake.auth.userId;
    console.log(socket.handshake.auth)
    if (!username || username.userId === -1) {
      return next(new Error("invalid username"));
    }
    socket.username = username;
    next();
};