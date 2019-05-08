package obj2100;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchCard extends JPanel implements ActionListener  {

	private Color color;
	private String title;
	private File folder;
	private JLabel selectedFolderLabel;
	private JPanel searchCardMenu;
	private SearchResultPanel searchResultPanel;
	private JLabel pleaseWaitLabel;
	
	public SearchCard(String title) {
		setTitle(title);
		setLayout(new BorderLayout());
		searchCardMenu = new JPanel();
		searchCardMenu.setLayout(new GridLayout(3,2));
        searchCardMenu.setSize(600, 100);
		JLabel lab1 = new JLabel(title, JLabel.CENTER);
		searchCardMenu.add(lab1); 
		
	

		
		JButton myButton = new JButton("Velg Mappe");
        searchCardMenu.add(myButton);
        
        add(searchCardMenu, BorderLayout.NORTH);
        

		
        
        setButtonAction(myButton);
		
	//	setSize(600,100);

		
        searchResultPanel = new SearchResultPanel();
        searchResultPanel.setVisible(false); //set it to true when user has selected a folder
		add(searchResultPanel);
	    Color randomColor = RandomColorGenerator.getRandomColor();
	    color = randomColor;
		searchResultPanel.setBackground(color);
		searchCardMenu.setBackground(color);
		
	}

	private void setButtonAction(JButton myButton) {
		myButton.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  
	        	  if ( selectedFolderLabel != null) {
	        		  searchCardMenu.remove(selectedFolderLabel);
	        	  }
	      		 selectedFolderLabel = new JLabel("Please wait ....", JLabel.LEFT);
	    		 searchCardMenu.add(selectedFolderLabel); 
	    		 selectedFolderLabel.setVisible(false);
	        	  
	        	   	 
		         JFileChooser fc = new JFileChooser();
		         fc.setCurrentDirectory(new java.io.File(".")); // start at application current directory
		         fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		         int returnVal = fc.showOpenDialog(myButton);
		         if(returnVal == JFileChooser.APPROVE_OPTION) {
		        	 
		        	 
		             File yourFolder = fc.getSelectedFile();
		             System.out.println(yourFolder);
		             
		             //set searchResultPanel visibility to true
		             searchResultPanel.setVisible(true);
		             searchResultPanel.clearLeftPanel();
		             folder = yourFolder;
		             
		        	  if ( selectedFolderLabel != null) {
		        		  searchCardMenu.remove(selectedFolderLabel);
		        	  }

		             selectedFolderLabel = new JLabel("Valgt mappe: " + folder.getAbsolutePath(), JLabel.LEFT);
					 
					 searchResultPanel.setSelectedFolder(folder);

					 Thread t1 = new Thread(new Runnable() {
					     @Override
					     public void run() {
					         // code goes here.
					    	 searchResultPanel.searchPdfsInFolder();
					     }
					 });  
					 t1.start();
					 
					 
					 
					 // tell searchResult to get this path and find pdfs from there
					 
					 searchCardMenu.add(selectedFolderLabel); 
				     revalidate();
				     repaint();
		        }
		         
		         //display files of pdf type
	        	         
	        	 
	          }
	      });
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setTitle(String s) {
		this.title = s;
	}
	
	public String getTitle() {
		return title;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		
	}
	

 }

	


