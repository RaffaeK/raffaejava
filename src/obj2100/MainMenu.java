package obj2100;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainMenu extends JPanel {
	
	MenuBar mbar = null;
	MenuItem  closeButton = null;
	MenuItem  addCardButton = null;
	Menu tools = null;
	public MainMenu() {
	
		mbar = new MenuBar();
		Menu      m = new Menu("Fil");
		tools = new Menu("Verktøy");
				  
		closeButton = new MenuItem("Avslutt");
		addCardButton = new MenuItem("Lag et nytt kort");
		  
		m.add(closeButton);
		tools.add(addCardButton);
		  
		mbar.add(m);
	    mbar.add(tools);
	}
	
	public MenuBar getMenuBar() {
		return mbar;
	}
	
	public MenuItem getCloseButton() {
		return closeButton;
	}
	
	public MenuItem getAddCardButton() {
		return addCardButton;
	}
	
	public void addMenuItem(MenuItem item) {
		tools.add(item);
	}
	
}
