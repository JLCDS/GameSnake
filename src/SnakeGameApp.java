import javax.swing.*;

public class SnakeGameApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SnakeGame snakeGame = new SnakeGame();
                MainMenu menu = new MainMenu();
                menu.setSnakeGame(snakeGame);
            }
        });
    }
}
