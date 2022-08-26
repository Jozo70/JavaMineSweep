import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;


public class MSMineSweeper extends JFrame {

        private JLabel statusbar;
        JButton Skin_1 =new JButton("Default Skin");


        public MSMineSweeper() {

            initUI();
        }

        private void initUI() {

            statusbar = new JLabel("");
            add(statusbar, BorderLayout.SOUTH);


            /// Button Frames
            //JFrame butFrame = new JFrame("UI Buttons");
            //JButton Skin_1 =new JButton("Default Skin");
            //butFrame.add(Skin_1);

            add(new MSBoard(statusbar));

            setResizable(false);
            pack();

            setTitle("Minesweeper");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public static void main(String[] args) {

            EventQueue.invokeLater(() -> {

                var ex = new MSMineSweeper();
                ex.setVisible(true);
            });
        }
    }
