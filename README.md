# Chess Game

This repository contains two versions of a Chess game: 1 Player and 2 Player. Both are Maven projects with a Java backend and HTML/CSS/JavaScript frontend.

## Project Structure

- ChessGame 1Player (Two players on one screen)
- ChessGame 2Player (Two players on separate screens)

## How to Run

Follow these steps to run either version of the Chess game:

1. Choose the version you want to run:
   - For two players on one screen: Open the `ChessGame 1Player` folder
   - For two players on separate screens: Open the `ChessGame 2Player` folder

2. Start the server:
   - Navigate to `ChessGame 1Player/src/main/java/server`
   - Run `GameWebSocketServer.java`

3. Launch the client:
   - Navigate to `ChessGame 1Player/src/main/java/client`
   - Open `index.html` in a web browser

For the 2Player version, repeat step 3 on another device or browser window to connect the second player.

## Game Modes

- **ChessGame 1Player**: Two players take turns on the same screen.
- **ChessGame 2Player**: Two players can play remotely on separate screens on the same network.

## Requirements

- Java Development Kit (JDK)
- Maven
- Web browser

## Additional Information

- The backend is written in Java
- The frontend uses HTML, CSS, and JavaScript (handleClient)

For more detailed information about each version, please refer to the README files within the respective project folders.
