import javax.swing.*;

public class Engine
{
    public static JFrame frame;
    public static void main(String[] args)
    {
        try 
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception ex){}

        frame = new JFrame("Trends");
        frame.setSize(1000,618);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Search());
        frame.setVisible(true);
    }

    public static void displayGraph(Graph g)
    {
        frame.removeAll();
        frame.add(g);
        frame.setVisible(true);
    }
}