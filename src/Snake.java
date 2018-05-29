import java.awt.EventQueue;
import javax.swing.JFrame;


public class Snake extends JFrame {

    private Snake() {

        StartGame();
    }

    private void StartGame() {

        add(new Board());

        setResizable(false);
        pack();

        setTitle("Ussimäng");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame window = new Snake();
            window.setVisible(true);
        });
    }
}