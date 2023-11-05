import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JTextField nombreField;
    private JButton iniciarJuegoButton;
    private JButton verPuntuacionesButton;
    private SnakeGame snakeGame;

    public MainMenu() {
        setTitle("Menú Principal");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel nombrePanel = new JPanel();
        nombreField = new JTextField(20);
        nombrePanel.add(new JLabel("Nombre: "));
        nombrePanel.add(nombreField);
        add(nombrePanel, BorderLayout.NORTH);


        iniciarJuegoButton = new JButton("Iniciar Juego");
        iniciarJuegoButton.addActionListener(new ActionListener() {
            @Override


            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = nombreField.getText();
                snakeGame.startGame(nombreUsuario);
                dispose();
            }
        });


        verPuntuacionesButton = new JButton("Ver Puntuaciones");
        verPuntuacionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(iniciarJuegoButton);
        buttonsPanel.add(verPuntuacionesButton);
        add(buttonsPanel, BorderLayout.CENTER);


        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));

        JLabel desarrolladorLabel = new JLabel("Información del Desarrollador:");
        desarrolladorLabel.setFont(new Font("Arial", Font.BOLD, 16));
        infoPanel.add(desarrolladorLabel);

        JLabel nombreLabel = new JLabel("Nombre: Juan Lopez");
        infoPanel.add(nombreLabel);

        JLabel identificacionLabel = new JLabel("Identificación: 202023451");
        infoPanel.add(identificacionLabel);

        JLabel facultadLabel = new JLabel("Facultad: Ingenieria");
        infoPanel.add(facultadLabel);

        JLabel escuelaLabel = new JLabel("Escuela: Sistemas y computacion");
        infoPanel.add(escuelaLabel);

        JLabel añoLabel = new JLabel("Año: 2023");
        infoPanel.add(añoLabel);

        JLabel cursoLabel = new JLabel("Curso: 7 ");
        infoPanel.add(cursoLabel);


        ImageIcon logoIcon = new ImageIcon("src/logo-uptc.png");
        JLabel logoLabel = new JLabel(logoIcon);
        infoPanel.add(logoLabel);

        add(infoPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setSnakeGame(SnakeGame snakeGame) {
        this.snakeGame = snakeGame;
    }
}
