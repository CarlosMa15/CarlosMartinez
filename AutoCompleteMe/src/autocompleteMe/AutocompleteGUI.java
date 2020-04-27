package autocompleteMe;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.BorderFactory;
import javax.swing.LayoutStyle;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MouseInputAdapter;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class AutocompleteGUI extends JFrame {
	public static void main(String[] args) {

	    // read in the terms from a file
	    String filename = "src/autocompleteME/movies.txt";
	    In in = new In(filename);
	    int N = in.readInt();
	    Term[] terms = new Term[N];
	    for (int i = 0; i < N; i++) {
	        double weight = in.readDouble();       // read the next weight
	        in.readChar();                         // scan past the tab
	        String query = in.readLine();          // read the next query
	        terms[i] = new Term(query, weight);    // construct the term
	    }

	    // read in queries from standard input and print out the top k matching terms
	    int k = 5;
	    Autocomplete autocomplete = new Autocomplete(terms);
	    while (StdIn.hasNextLine()) {
	        String prefix = StdIn.readLine();
	        Term[] results = autocomplete.allMatches(prefix);
	        for (int i = 0; i < Math.min(k, results.length); i++)
	            StdOut.println(results[i]);
	    }
	}
}