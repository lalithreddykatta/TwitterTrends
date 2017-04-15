import java.awt.event.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Search extends JComponent implements ActionListener
{
    static JComboBox search;
    public Search()
    {
        setLayout(new AbsoluteLayout());
        java.util.List myWords = new ArrayList<String>();
        myWords.add("bike");
        myWords.add("car");
        myWords.add("cap");
        myWords.add("cape");
        myWords.add("canadian");
        myWords.add("caprecious");
        myWords.add("catepult");

        StringSearchable searchable = new StringSearchable(myWords);
        search = new AutocompleteJComboBox(searchable);
        add(search,new AbsoluteConstraints(240,100,40,20));
    }

    public void actionPerformed(ActionEvent evt)
    {

    }

    public void paintComponent(Graphics g)
    {

    }
}
