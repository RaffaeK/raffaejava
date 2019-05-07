package obj2100;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SearchResultPanel extends JPanel implements ActionListener {

	private File selectedFolder;
	private JPanel leftPanel;
	private JPanel rightPanel; 
	private JTextField searchText;
	private ArrayList<String> listOfPdfs;
	
	public SearchResultPanel() {
		setLayout(new GridLayout(1,1));
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new FlowLayout());
		Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
		leftPanel.setBorder(border);
		add(leftPanel);
		
		rightPanel = new JPanel();
		// plass this 10 characterer?
		searchText = new JTextField(10);
		rightPanel.add(searchText);
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(this);
		rightPanel.add(searchButton);

		Border border2 = BorderFactory.createLineBorder(Color.BLACK, 5);
		rightPanel.setBorder(border2);
		add(rightPanel);
	}
	
	public void setSelectedFolder(File folder) {
		this.selectedFolder = folder; 
		// TODO Auto-generated method stub
	}
	
	public void actionPerformed(ActionEvent evt) {
		 String arg = evt.getActionCommand();
		 
		 System.out.println(searchText.getText());
	}	 
	
	public void displayAllPdfsInFolder() {
        final File folder = this.selectedFolder;

        listOfPdfs = new ArrayList<String>();

//        search(".*\\.pdf", folder, listOfPdfs);
        
        File directory = folder;

        // get all the files from a directory
        File[] fList = directory.listFiles();

        //filter all PDF from list of files
        
        // add label here that says please wait
        

        
        getPDFs(fList);
        
        
        //remove it here

        if ( listOfPdfs.size() > 0 ) {
            for (String pdfName : listOfPdfs) {
                System.out.println(pdfName);
                JLabel pdfNameLabel = new JLabel(pdfName);
                
        		leftPanel.add(pdfNameLabel); 
            }
        } else {
        	// add a label in the leftPanel telling there arent any pdfs available
        }

	}

	private void getPDFs(File[] fList) {
		
		// loop all enheter (kan være både mapper og filer)
		
		if (fList != null) {
			for (File file : fList) {

				// hvis du finner fil, gjør dette
				if (file.isFile()) {
					
					//hvis filen er pdf så print navnet
	            	if(file.getName().endsWith(".pdf")) {
	            	    //it is a .pdf file!
	            		
	            		System.out.println(file.getAbsolutePath());
	            		listOfPdfs.add(file.getAbsolutePath());
	            	}
	                
	            }
				//hvis man finner enda en mappe, kjør denne funksjonen på nytt med filer oppi denne mappen
				else if (file.isDirectory()) {
	                getPDFs(file.listFiles());
	            }
	        }
		}
		
	}
	
    public static void search(final String pattern, final File folder, ArrayList<String> result) {
    	
    	if (folder != null ) {
            for (final File f : folder.listFiles()) {

                if (f.isDirectory()) {
                    search(pattern, f, result);
                    System.out.println(f);
                    System.out.println(result);
                }

                if (f.isFile()) {
                    if (f.getName().matches(pattern)) {
                        result.add(f.getAbsolutePath());
                    }
                }

            }
    	}

    }
}