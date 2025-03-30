import javax.swing.*;
import java.awt.*;
class TransparentPanel extends JFrame
{
    JPanel p1,p2;

    public TransparentPanel() throws InterruptedException {
        createAndShowGUI();
    }

    private void createAndShowGUI() throws InterruptedException {
        // Set title and default close operation
        setTitle("Transparent Panel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        ImageIcon icon = new ImageIcon(getClass().getResource("/blackjack.png"));
        JLabel label = new JLabel(icon);
        setContentPane(label);


        // Set a background for JFrame
        label.setIcon(icon = new ImageIcon(getClass().getResource("/table.jpg")));

        // Set some layout, say FlowLayout
        setLayout(new BorderLayout());


        // Create a JPanel
        p1=new JPanel();

        // Set the background, black with 125 as alpha value
        // This is less transparent
        JButton button = new JButton("Exit");
        p1.setBackground(new Color(0,0,0,0));
        p1.add(button);

        // Create another JPanel
        p2=new JPanel();

        // This is more transparent than the previous
        // one
        p2.setBackground(new Color(0,0,0,65));

        // Set some size to the panels
        p1.setPreferredSize(new Dimension(250,150));
        p2.setPreferredSize(new Dimension(250,150));

        // Add the panels to the JFrame
        add(button,BorderLayout.SOUTH);

        // Set the size of the JFrame and
        // make it visible
        setSize(600,400);
        setVisible(true);
    }


    public static void main(String args[])
    {
        // Run in the EDT
        SwingUtilities.invokeLater(new Runnable(){
            public void run()
            {
                try {
                    new TransparentPanel();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
