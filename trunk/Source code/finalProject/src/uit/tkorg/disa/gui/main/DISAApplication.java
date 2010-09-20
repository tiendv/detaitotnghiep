package uit.tkorg.disa.gui.main;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.GroupLayout;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DISAApplication extends JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuItem jMenuItem0;
	private JMenuItem jMenuItem5;
	private JMenuItem jMenuItem6;
	private JMenuItem jMenuItem7;
	private JMenu jMenu0;
	private JMenuItem jMenuItem1;
	private JMenuItem jMenuItem8;
	private JMenuItem jMenuItem9;
	private JMenuItem jMenuItem14;
	private JMenuItem jMenuItem10;
	private JMenuItem jMenuItem11;
	private JMenuItem jMenuItem12;
	private JMenuItem jMenuItem13;
	private JMenu jMenu1;
	private JMenuItem jMenuItem2;
	private JMenu jMenu2;
	private JMenuItem jMenuItem3;
	private JMenu jMenu3;
	private JMenuItem jMenuItem4;
	private JMenuItem jMenuItem15;
	private JMenu jMenu4;
	private JMenuBar jMenuBar0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";

	public DISAApplication() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		setJMenuBar(getJMenuBar0());
		setSize(320, 240);
	}

	private JMenuBar getJMenuBar0() {
		if (jMenuBar0 == null) {
			jMenuBar0 = new JMenuBar();
			jMenuBar0.add(getJMenu0());
			jMenuBar0.add(getJMenu1());
			jMenuBar0.add(getJMenu2());
			jMenuBar0.add(getJMenu3());
			jMenuBar0.add(getJMenu4());
		}
		return jMenuBar0;
	}

	private JMenu getJMenu4() {
		if (jMenu4 == null) {
			jMenu4 = new JMenu();
			jMenu4.setText("Help");
			jMenu4.setOpaque(false);
			jMenu4.add(getJMenuItem4());
			jMenu4.add(getJMenuItem15());
		}
		return jMenu4;
	}

	private JMenuItem getJMenuItem15() {
		if (jMenuItem15 == null) {
			jMenuItem15 = new JMenuItem();
			jMenuItem15.setText("About");
		}
		return jMenuItem15;
	}

	private JMenuItem getJMenuItem4() {
		if (jMenuItem4 == null) {
			jMenuItem4 = new JMenuItem();
			jMenuItem4.setText("Help");
		}
		return jMenuItem4;
	}

	private JMenu getJMenu3() {
		if (jMenu3 == null) {
			jMenu3 = new JMenu();
			jMenu3.setText("Option");
			jMenu3.setOpaque(false);
			jMenu3.add(getJMenuItem3());
		}
		return jMenu3;
	}

	private JMenuItem getJMenuItem3() {
		if (jMenuItem3 == null) {
			jMenuItem3 = new JMenuItem();
			jMenuItem3.setText("Configuration");
		}
		return jMenuItem3;
	}

	private JMenu getJMenu2() {
		if (jMenu2 == null) {
			jMenu2 = new JMenu();
			jMenu2.setText("Classification");
			jMenu2.setOpaque(false);
			jMenu2.add(getJMenuItem2());
		}
		return jMenu2;
	}

	private JMenuItem getJMenuItem2() {
		if (jMenuItem2 == null) {
			jMenuItem2 = new JMenuItem();
			jMenuItem2.setText("jMenuItem0");
		}
		return jMenuItem2;
	}

	private JMenu getJMenu1() {
		if (jMenu1 == null) {
			jMenu1 = new JMenu();
			jMenu1.setText("Edit");
			jMenu1.setOpaque(false);
			jMenu1.add(getJMenuItem1());
			jMenu1.add(getJMenuItem8());
			jMenu1.add(getJMenuItem9());
			jMenu1.add(getJMenuItem14());
			jMenu1.add(getJMenuItem10());
			jMenu1.add(getJMenuItem11());
			jMenu1.add(getJMenuItem12());
			jMenu1.add(getJMenuItem13());
		}
		return jMenu1;
	}

	private JMenuItem getJMenuItem13() {
		if (jMenuItem13 == null) {
			jMenuItem13 = new JMenuItem();
			jMenuItem13.setText("Select all");
		}
		return jMenuItem13;
	}

	private JMenuItem getJMenuItem12() {
		if (jMenuItem12 == null) {
			jMenuItem12 = new JMenuItem();
			jMenuItem12.setText("Unmark all");
		}
		return jMenuItem12;
	}

	private JMenuItem getJMenuItem11() {
		if (jMenuItem11 == null) {
			jMenuItem11 = new JMenuItem();
			jMenuItem11.setText("Unmark entries");
		}
		return jMenuItem11;
	}

	private JMenuItem getJMenuItem10() {
		if (jMenuItem10 == null) {
			jMenuItem10 = new JMenuItem();
			jMenuItem10.setText("Mark entries");
		}
		return jMenuItem10;
	}

	private JMenuItem getJMenuItem14() {
		if (jMenuItem14 == null) {
			jMenuItem14 = new JMenuItem();
			jMenuItem14.setText("Delete");
		}
		return jMenuItem14;
	}

	private JMenuItem getJMenuItem9() {
		if (jMenuItem9 == null) {
			jMenuItem9 = new JMenuItem();
			jMenuItem9.setText("Paste");
		}
		return jMenuItem9;
	}

	private JMenuItem getJMenuItem8() {
		if (jMenuItem8 == null) {
			jMenuItem8 = new JMenuItem();
			jMenuItem8.setText("Copy");
		}
		return jMenuItem8;
	}

	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("Cut");
		}
		return jMenuItem1;
	}

	private JMenu getJMenu0() {
		if (jMenu0 == null) {
			jMenu0 = new JMenu();
			jMenu0.setText("File");
			jMenu0.add(getJMenuItem0());
			jMenu0.add(getJMenuItem5());
			jMenu0.add(getJMenuItem6());
			jMenu0.add(getJMenuItem7());
		}
		return jMenu0;
	}

	private JMenuItem getJMenuItem7() {
		if (jMenuItem7 == null) {
			jMenuItem7 = new JMenuItem();
			jMenuItem7.setText("Exit");
		}
		return jMenuItem7;
	}

	private JMenuItem getJMenuItem6() {
		if (jMenuItem6 == null) {
			jMenuItem6 = new JMenuItem();
			jMenuItem6.setText("Save");
		}
		return jMenuItem6;
	}

	private JMenuItem getJMenuItem5() {
		if (jMenuItem5 == null) {
			jMenuItem5 = new JMenuItem();
			jMenuItem5.setText("Open file");
		}
		return jMenuItem5;
	}

	private JMenuItem getJMenuItem0() {
		if (jMenuItem0 == null) {
			jMenuItem0 = new JMenuItem();
			jMenuItem0.setText("New database");
		}
		return jMenuItem0;
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
				DISAApplication frame = new DISAApplication();
				frame.setDefaultCloseOperation(DISAApplication.EXIT_ON_CLOSE);
				frame.setTitle("DISAApplication");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
