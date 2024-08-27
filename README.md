# Chess Game

This repository contains two versions of a Chess game: 1 Player and 2 Player. Both are Maven projects with a Java backend and HTML/CSS/JavaScript frontend.

## Project Structure

- ChessGame 1Player (Two players on one screen)
- ChessGame 2Player (Two players on separate screens)

## Important Note
To run the ChessGame 2Player version, you need to open two instances of the HTML file. This can be done either in two different tabs of the same browser or in two separate browsers because it requires 2 players to start the game.

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
   - If the above doesn't work:
     - Locate the `index.html` file in your file system
     - Double Click on it to Run Or
     - Copy the full file path
     - Paste the path into your web browser's address bar Ex-`file:///C:/Users/Amrit%20Rout/Desktop/AmritRout-21BEC0294-main/ChessGame%201Player/src/main/java/client/index.html`

For the 2Player version, repeat step 3 on another device or browser window.

## Game Modes

- **ChessGame 1Player**: Two players take turns on the same screen.
- **ChessGame 2Player**: Two players can play remotely on separate screens on the same network.

## Requirements

- Java Development Kit (JDK)
- Maven

## Demo
-**ChessGame 1Player**
[![Watch the video](https://img.youtube.com/vi/_2VAKpT2BIps/0.jpg)](https://www.youtube.com/watch?v=_2VAKpT2BIps)

-**ChessGame 2Player**

[![Watch the video](https://img.youtube.com/vi/_b1Lc07bxJwY/0.jpg)](https://www.youtube.com/watch?v=_b1Lc07bxJwY)

## Troubleshooting

If you encounter any issues while running the game, try the following:

1. If the chess grid is not visible:
   - On Windows, press Ctrl + F5 to perform a hard refresh and clear the browser cache
   - On Mac, press Cmd + Shift + R to perform a hard refresh
   - Alternatively, open the browser's developer tools (usually F12), go to the Network tab, and check "Disable cache" before reloading the page

2. If the game doesn't connect to the server:
   - Ensure that the `GameWebSocketServer.java` is running
   - Check your firewall settings to make sure it's not blocking the connection

3. If you see any JavaScript errors:
   - Open the browser's developer tools (usually F12) and check the Console tab for error messages

## Additional Information

- The backend is written in Java
- The frontend uses HTML, CSS, and JavaScript (Client)
