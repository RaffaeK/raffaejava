package obj2100;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.util.*;
import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener {
	
	
	SearchCard previousPanel = null;
	SearchCard currentPanel = null; 
	ArrayList<SearchCard> listOfCards = null;
	MainMenu mainMenu = null;
	int number = 0;
	public MainWindow() {
		this.setTitle("PDFsearch");
		this.setSize(1000, 600);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		
		// setter vinduet i midten//
		this.setLocationRelativeTo(null);
		
		
		mainMenu = new MainMenu();
		
		setMenuBar(mainMenu.getMenuBar());
	      
		mainMenu.getCloseButton().addActionListener(this);
	    mainMenu.getAddCardButton().addActionListener(this);
	      
	      addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
				  System.exit(0); }
		      });
	      
	      listOfCards = new ArrayList<SearchCard>();
	 
	}
	public void actionPerformed(ActionEvent evt) {
		 String arg = evt.getActionCommand();
		  if(arg.equals("Avslutt")) System.exit(0);
		  if(arg.equals("Lag et nytt kort")) {

			  //hvis currentPanel er satt betyr det at vi må skjule det før vi viser den neste
			  if ( currentPanel != null ) {
				  currentPanel.setVisible(false);
			  }
			  
			  SearchCard newSearchCard = new SearchCard("KortNummer " + number++);
			  currentPanel = newSearchCard;
			  currentPanel.setVisible(true);
		      this.add(newSearchCard, BorderLayout.CENTER);
		      
		      listOfCards.add(newSearchCard);
		      
		      MenuItem menuItem = new MenuItem(newSearchCard.getTitle());
		      menuItem.addActionListener(new ActionListener() {
		          public void actionPerformed(ActionEvent e) {

		        	  //switch the damn panel
		        	  // which panel is it? 
		        	  
		        	  Integer index = getIndexOfSwitchedCard(newSearchCard);
		        	  SearchCard searchCardClicked = listOfCards.get(index);
		              System.out.println(searchCardClicked.getTitle() + " clicked");
		              
		             //hide the previous search
		    		  if ( currentPanel != null ) {
						  currentPanel.setVisible(false);
					  }
		    		  
		    		  currentPanel = searchCardClicked;
				      currentPanel.setVisible(true);
		    		  
		        	 
		          }

				private Integer getIndexOfSwitchedCard(SearchCard searchCard) {
					String t = searchCard.getTitle();
		        	  String lastChar = t.substring(t.length() - 1);
		        	  Integer index = Integer.parseInt(lastChar);
					return index;
				}
		      });
		      
		      mainMenu.addMenuItem(menuItem);
		      this.revalidate();
		      this.repaint();
		}
	   
	   }
	

		 
	}

