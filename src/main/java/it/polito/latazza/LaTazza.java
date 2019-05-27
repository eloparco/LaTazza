package it.polito.latazza;

/*import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;*/

import it.polito.latazza.data.DataImpl;
import it.polito.latazza.data.DataInterface;
import it.polito.latazza.gui.MainSwing;

public class LaTazza {

	public static void main(String[] args) {
		/*try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} 
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		}
		catch (InstantiationException e) {
			// handle exception
		}
		catch (IllegalAccessException e) {
			// handle exception
		}*/
	    
        
		DataInterface data = new DataImpl();
		new MainSwing(data);
	}

}
