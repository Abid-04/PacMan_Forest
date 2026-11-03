# PacMan Forest

A 2D forest exploration and survival game developed in Java using Swing and AWT.

Players navigate a tile-based world, collect rewards, avoid obstacles, and survive encounters with ghosts. As players collect rewards, additional enemies appear, increasing the difficulty.

The project demonstrates object-oriented programming, enemy AI, collision detection, tile-based map rendering, camera movement, and graphical user interface development.

## Features

- **Forest exploration:** Navigate a large tile-based environment featuring grass, trees, water, and bridges.
- **Enemy AI:** Ghosts patrol the map and chase the player when within detection range.
- **Dynamic difficulty:** Collecting rewards introduces additional enemies.
- **Health system:** Enemy collisions reduce the player's health.
- **Reward collection:** Collect items to increase your score.
- **Collision detection:** Trees and water restrict player movement.
- **Scrolling camera:** The camera follows the player through the game world.
- **Character animations:** The player's appearance changes based on movement.
- **Game Over system:** Restart gameplay after losing all health.


## Screenshots

### Forest Exploration
![PacMan Forest Gameplay](screenshots/gameplay_01.png)

### Game Over
![PacMan Forest Game Over](screenshots/gameplay_02.png)


## Technologies Used

- **Java** — Core game logic and object-oriented programming
- **Java Swing** — Game window, event handling, and UI components
- **Java AWT** — Graphics rendering and collision detection
- **Java Collections** — Managing enemies, rewards, and game objects
- **Eclipse IDE** — Development environment

## Game Controls

| Key | Action |
|---|---|
| ↑ | Move up |
| ↓ | Move down |
| ← | Move left |
| → | Move right |

## Getting Started

### Prerequisites

- Java Development Kit (JDK) compatible with the project's Java version
- Eclipse IDE or another Java development environment

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/Abid-04/PacMan_Forest.git
   ```

2. Open Eclipse IDE.
3. Select **File → Import → General → Existing Projects into Workspace**.
4. Select the cloned project directory.
5. Verify that the project uses a compatible JDK.
6. Run the Java class containing the `main()` method.

The game starts directly without a main menu.

## Project Structure

| Class | Responsibility |
|---|---|
| `Player.java` | Player movement, animations, and collision bounds |
| `Enemies.java` | Ghost movement, detection, and pursuit behavior |
| `Panel.java` | Map rendering, camera movement, rewards, scoring, health, and game logic |

## Future Improvements

- Main menu and pause functionality
- Persistent high-score tracking
- More enemy types and movement patterns
- Additional maps and exploration areas
- Improved collision detection
- Sound effects and background music

## Author

Developed by **Abid**.

GitHub: [Abid-04](https://github.com/Abid-04)

## License

No license has been specified for this project.
