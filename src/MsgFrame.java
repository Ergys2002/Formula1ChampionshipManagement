import javax.swing.*;
import java.awt.*;
public class MsgFrame extends JFrame {
    public MsgFrame(String title, String msg) {
        setTitle(title);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton button = new JButton("Close");

        button.addActionListener(e -> MsgFrame.this.dispose());

        JLabel label = new JLabel(msg);
        add(button,BorderLayout.SOUTH);

        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label,BorderLayout.CENTER);


    }
}
