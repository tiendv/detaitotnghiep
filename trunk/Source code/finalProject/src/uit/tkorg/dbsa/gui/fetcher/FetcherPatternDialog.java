package uit.tkorg.dbsa.gui.fetcher;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.GroupLayout;

//VS4E -- DO NOT REMOVE THIS LINE!
public class FetcherPatternDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public FetcherPatternDialog() {
		initComponents();
	}

	public FetcherPatternDialog(Frame parent) {
		super(parent);
		initComponents();
	}

	public FetcherPatternDialog(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public FetcherPatternDialog(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public FetcherPatternDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public FetcherPatternDialog(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public FetcherPatternDialog(Dialog parent) {
		super(parent);
		initComponents();
	}

	public FetcherPatternDialog(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public FetcherPatternDialog(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public FetcherPatternDialog(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public FetcherPatternDialog(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public FetcherPatternDialog(Window parent) {
		super(parent);
		initComponents();
	}

	public FetcherPatternDialog(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public FetcherPatternDialog(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public FetcherPatternDialog(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public FetcherPatternDialog(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		setSize(458, 348);
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				FetcherPatternDialog dialog = new FetcherPatternDialog();
				dialog
						.setDefaultCloseOperation(FetcherPatternDialog.DISPOSE_ON_CLOSE);
				dialog.setTitle("FetcherPatternDialog");
				dialog.setLocationRelativeTo(null);
				dialog.getContentPane().setPreferredSize(dialog.getSize());
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}

}
