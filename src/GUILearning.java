import javax.swing.*;
import java.awt.*;

public class GUILearning {

    public static void main (String[] args) {

        JFrame frame = new JFrame(); //creates an instance of a frame
        frame.setTitle("Learning Gui"); //sets the title of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of app when window closed
        frame.setResizable(false); //prevents frame from being re-sized
        frame.setSize(420, 420); //sets the x-dimension and y-dimension of our frame
        frame.setVisible(true); //makes the frame visible

        ImageIcon image = new ImageIcon("memez.jpg"); //create an image icon
        frame.setIconImage(image.getImage()); //change icon of frame
        frame.getContentPane().setBackground(Color.CYAN); //change the color of background

    }

}
//https://www.youtube.com/watch?v=Kmgo00avvEw @6.52