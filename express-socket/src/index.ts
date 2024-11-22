// src/index.ts
import express, { Express, Request, Response } from "express";
import dotenv from "dotenv";

import { createServer } from "http";
import { DefaultEventsMap, Server, Socket } from "socket.io";


interface customSocket extends Socket<DefaultEventsMap, DefaultEventsMap, DefaultEventsMap, any> {
  username?: string
}

const mongoose = require("mongoose");
mongoose.set("strictQuery", false);
const mongoDBURL = "mongodb://127.0.0.1:27017/socket-game?authSource=admin";
dotenv.config();

const cors = require("cors");
const app: Express = express();
const port = process.env.PORT || 4000;

const httpServer = createServer();
const io = new Server(httpServer, {
  // options
  cors: {
    origin: "*",
  },
});

app.use(cors({
  origin: "http://localhost:5173"
}))



app.use(express.json());


io.use((socket: customSocket, next) => {
  const username = socket.handshake.auth.userId;
  console.log(socket.handshake.auth)
  if (!username || username.userId === -1) {
    return next(new Error("invalid username"));
  }
  socket.username = username;
  next();
});


io.on("connection", (socket: any) => {
  const users = [];
  if (io.of("/").sockets.size > 2) {
    // too much user
  }
  for (let [id, socket] of io.of("/").sockets as any) {
    users.push({
      userID: id,
      username: socket.username,
    });
  }
  socket.emit("users", users);
  // ...
});

io.on("connection", (socket: customSocket) => {
  // notify existing users
  socket.broadcast.emit("user-connected", {
    userID: socket.id,
    username: socket.username,
  });
});
httpServer.listen(3000);


app.listen(port, () => {
  console.log(`[server]: Server is running at http://localhost:${port}`);
});

app.post("/", (req: express.Request, res: express.Response) => {
  const body = req.body || null; 
    console.log(body)
    io.on("connection", socket => {
      socket.join("room-a1b2c3");
    })

    io.to("room-a1b2c3").emit("i'm a message");
    res.status(200).json({
      return: "everything went according to the keikaku"
    })
});

// mongoose
//   .connect(mongoDBURL, {})
//   .then(() => {
//     console.log("Connection Successfull");
//   })
//   .catch((err: any) => {
//     console.log(err, "Received an Error");
//   });
