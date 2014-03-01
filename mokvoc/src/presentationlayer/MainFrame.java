package presentationlayer;

import businesslayer.*;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainFrame extends JFrame{

	private static final int mainFrameWidth = 600;
	private static final int mainFrameHeight = 700;
	private JMenuBar menuBar;
	private	JTabbedPane tabbedPane;
	private	JPanel panel1;
	private	JPanel panel2;
	private	JPanel panel3;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(mainFrameWidth, mainFrameHeight);


		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		fillMenuBar();

		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create the tab pages
		createNewWordPage();
		createShowHistoryPage();
		createSearchPage();


		// Create a tabbed pane
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab( "Insert new word", panel1 );
		tabbedPane.addTab( "Show history", panel2 );
		tabbedPane.addTab( "Search", panel3 );
		topPanel.add( tabbedPane, BorderLayout.CENTER );

	}
	
	private void fillMenuBar () {
		
		// create File menu and Exit menu item
	      JMenu fileMenu = new JMenu( "File" );
	      fileMenu.setMnemonic( 'F' );
	      JMenuItem aboutItem = new JMenuItem( "About..." );
	      aboutItem.setMnemonic( 'A' );
	      aboutItem.addActionListener(
	         new ActionListener() {
	            public void actionPerformed( ActionEvent e )
	            {
	               JOptionPane.showMessageDialog( MainFrame.this,
	                  "This is an example\nof using menus",
	                  "About", JOptionPane.PLAIN_MESSAGE );
	            }
	         }
	      );
	      fileMenu.add( aboutItem );

	      JMenuItem exitItem = new JMenuItem( "Exit" );
	      exitItem.setMnemonic( 'x' );
	      exitItem.addActionListener(
	         new ActionListener() {
	            public void actionPerformed( ActionEvent e )
	            {
	               System.exit( 0 );
	            }
	         }
	      );
	      fileMenu.add( exitItem );
	      menuBar.add(fileMenu);
	}

	private void createNewWordPage() {
		panel1 = new JPanel();
		panel1.setLayout( null );

		final JTextField wordTF = new JTextField();
		wordTF.setBounds(214, 22, 217, 20);
		panel1.add(wordTF);

		final JLabel wordLbl = new JLabel("word");
		wordLbl.setBounds(10, 22, 150, 20);
		panel1.add(wordLbl);

		final JLabel pronLbl = new JLabel("pronunciation");
		pronLbl.setBounds(10, 53, 150, 20);
		panel1.add(pronLbl);

		final JTextField pronTF = new JTextField();
		pronTF.setBounds(214, 53, 217, 20);
		panel1.add(pronTF);

		final JLabel meaningLbl = new JLabel("meaning(s)");
		meaningLbl.setBounds(10, 92, 150, 20);
		panel1.add(meaningLbl);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(214, 92, 217, 163);
		panel1.add(scrollPane);

		final JTextArea meaningTA = new JTextArea();
		scrollPane.setViewportView(meaningTA);

		final JTextField synonymTF = new JTextField();
		synonymTF.setBounds(214, 282, 217, 20);
		panel1.add(synonymTF);

		final JTextField oppositTF = new JTextField();
		oppositTF.setBounds(214, 313, 217, 20);
		panel1.add(oppositTF);

		final JLabel oppositLbl = new JLabel("opposit(s)");
		oppositLbl.setBounds(10, 313, 150, 20);
		panel1.add(oppositLbl);

		final JLabel synonymLbl = new JLabel("synonym(s)");
		synonymLbl.setBounds(10, 282, 150, 20);
		panel1.add(synonymLbl);

		final JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(214, 374, 217, 163);
		panel1.add(scrollPane_1);

		final JTextArea sampleTA = new JTextArea();
		scrollPane_1.setViewportView(sampleTA);

		final JLabel sampleLbl = new JLabel("sample(s)");
		sampleLbl.setBounds(10, 374, 150, 20);
		panel1.add(sampleLbl);

		final JButton insertBtn = new JButton("insert");
		
		insertBtn.setBounds(64, 562, 89, 23);
		panel1.add(insertBtn);

		final JButton clearBtn = new JButton("clear");
		clearBtn.setBounds(458, 562, 89, 23);
		panel1.add(clearBtn);
		businesslayer.Manipulator( wordTF.getText(), pronTF.getText(), meaningTA.getText(), oppositTF.getText()
				, sampleTA.getText(), Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis() ) );
		class LocalActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == insertBtn) {
					
					if ( businesslayer.Manipulator( wordTF.getText(), pronTF.getText(), meaningTA.getText(), oppositTF.getText()
							, sampleTA.getText(), Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis() ) )
						makeClear();
						
				}
				else if (e.getSource() == clearBtn) {
					makeClear();
				}
				
			}

			void makeClear() {
				wordTF.setText("");
				pronTF.setText("");
				meaningTA.setText("");
				synonymTF.setText("");
				oppositTF.setText("");
				sampleTA.setText("");
			}
			
		}
		insertBtn.addActionListener(new LocalActionListener());
		clearBtn.addActionListener(new LocalActionListener());
	}

	private void createShowHistoryPage() {
		panel2 = new JPanel();
		panel2.setLayout( null );

		Choice orderByCh = new Choice();
		orderByCh.setBounds(214, 17, 215, 20);
		orderByCh.add("Insertion date");
		orderByCh.add("Alphabetic");
		orderByCh.add("Repeatition");
		panel2.add(orderByCh);

		final JTextArea meaningTA = new JTextArea();
		meaningTA.setBounds(214, 110, 215, 161);
		meaningTA.setBackground(Color.WHITE);
		meaningTA.setEditable(false);
		panel2.add(meaningTA);

		final JTextArea sampleTA = new JTextArea();
		sampleTA.setBounds(214, 359, 215, 161);
		panel2.add(sampleTA);
		sampleTA.setBackground(Color.WHITE);
		sampleTA.setEditable(false);

		JLabel orderByLbl = new JLabel("order by");
		orderByLbl.setBounds(10, 17, 150, 20);
		panel2.add(orderByLbl);

		final JTextField wordTF = new JTextField();
		wordTF.setBackground(Color.WHITE);
		wordTF.setEditable(false);
		wordTF.setBounds(214, 48, 217, 20);
		panel2.add(wordTF);

		final JLabel wordLbl = new JLabel("word");
		wordLbl.setBounds(10, 48, 150, 20);
		panel2.add(wordLbl);

		final JLabel pronLbl = new JLabel("pronunciation");
		pronLbl.setBounds(10, 79, 150, 20);
		panel2.add(pronLbl);

		final JTextField pronTF = new JTextField();
		pronTF.setBackground(Color.WHITE);
		pronTF.setEditable(false);
		pronTF.setBounds(214, 79, 217, 20);
		panel2.add(pronTF);

		final JLabel meaningLbl = new JLabel("meaning(s)");
		meaningLbl.setBounds(10, 110, 150, 20);
		panel2.add(meaningLbl);

		final JTextField synonymTF = new JTextField();
		synonymTF.setBackground(Color.WHITE);
		synonymTF.setEditable(false);
		synonymTF.setBounds(214, 282, 217, 20);
		panel2.add(synonymTF);

		final JTextField oppositTF = new JTextField();
		oppositTF.setBackground(Color.WHITE);
		oppositTF.setEditable(false);
		oppositTF.setBounds(214, 313, 217, 20);
		panel2.add(oppositTF);

		final JLabel oppositLbl = new JLabel("opposit(s)");
		oppositLbl.setBounds(10, 313, 150, 20);
		panel2.add(oppositLbl);

		final JLabel synonymLbl = new JLabel("synonym(s)");
		synonymLbl.setBounds(10, 282, 150, 20);
		panel2.add(synonymLbl);

		final JLabel sampleLbl = new JLabel("sample(s)");
		sampleLbl.setBounds(10, 374, 150, 20);
		panel2.add(sampleLbl);

		final JButton previousBtn = new JButton("<   Previous");
		previousBtn.setBounds(64, 562, 96, 23);
		panel2.add(previousBtn);

		final JButton nextBtn = new JButton("Next   >");
		nextBtn.setBounds(458, 562, 96, 23);
		panel2.add(nextBtn);
		
		final JPanel changeJP = new JPanel();
		changeJP.setBounds(214, 562, 215, 23);
		changeJP.setLayout(null);
		
		final JButton deleteBtn = new JButton("delete");
		deleteBtn.setBounds(0, 0, 63, 23);
		changeJP.add(deleteBtn);
		
		final JButton updateBtn = new JButton("update");
		updateBtn.setBounds(148, 0, 67, 23);
		changeJP.add(updateBtn);
		
		panel2.add(changeJP);
		
		class LocalActionListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == previousBtn) {
				}
				else if (e.getSource() == nextBtn) {
					
				}
				else if (e.getSource() == deleteBtn) {
					
				}
				else if (e.getSource() == updateBtn) {
					
				}
			}
		}
		previousBtn.addActionListener(new LocalActionListener());
		nextBtn.addActionListener(new LocalActionListener());
		deleteBtn.addActionListener(new LocalActionListener());
		updateBtn.addActionListener(new LocalActionListener());
	}

	private void createSearchPage()	{
		panel3 = new JPanel();
		panel3.setLayout( null );

		final JTextArea meaningTA = new JTextArea();
		meaningTA.setBounds(214, 90, 215, 161);
		meaningTA.setBackground(Color.WHITE);
		meaningTA.setEditable(false);
		panel3.add(meaningTA);

		final JTextArea sampleTA = new JTextArea();
		sampleTA.setBounds(214, 359, 215, 161);
		panel3.add(sampleTA);
		sampleTA.setBackground(Color.WHITE);
		sampleTA.setEditable(false);

		final JTextField wordTF = new JTextField();
		wordTF.setBackground(Color.WHITE);
		wordTF.setBounds(214, 28, 217, 20);
		panel3.add(wordTF);

		final JLabel wordLbl = new JLabel("word");
		wordLbl.setBounds(10, 28, 150, 20);
		panel3.add(wordLbl);

		final JLabel pronLbl = new JLabel("pronunciation");
		pronLbl.setBounds(10, 59, 150, 20);
		panel3.add(pronLbl);

		final JTextField pronTF = new JTextField();
		pronTF.setBackground(Color.WHITE);
		pronTF.setEditable(false);
		pronTF.setBounds(214, 59, 217, 20);
		panel3.add(pronTF);

		final JLabel meaningLbl = new JLabel("meaning(s)");
		meaningLbl.setBounds(10, 90, 150, 20);
		panel3.add(meaningLbl);

		final JTextField synonymTF = new JTextField();
		synonymTF.setBackground(Color.WHITE);
		synonymTF.setEditable(false);
		synonymTF.setBounds(214, 282, 217, 20);
		panel3.add(synonymTF);

		final JTextField oppositTF = new JTextField();
		oppositTF.setBackground(Color.WHITE);
		oppositTF.setEditable(false);
		oppositTF.setBounds(214, 313, 217, 20);
		panel3.add(oppositTF);

		final JLabel oppositLbl = new JLabel("opposit(s)");
		oppositLbl.setBounds(10, 313, 150, 20);
		panel3.add(oppositLbl);

		final JLabel synonymLbl = new JLabel("synonym(s)");
		synonymLbl.setBounds(10, 282, 150, 20);
		panel3.add(synonymLbl);

		final JLabel sampleLbl = new JLabel("sample(s)");
		sampleLbl.setBounds(10, 374, 150, 20);
		panel3.add(sampleLbl);

		final JButton findBtn = new JButton("Find");
		findBtn.setBounds(25, 550, 96, 23);
		panel3.add(findBtn);
		
		final JPanel changeJP = new JPanel();
		changeJP.setBounds(214, 550, 215, 23);
		changeJP.setLayout(null);
		
		final JButton deleteBtn = new JButton("delete");
		deleteBtn.setBounds(0, 0, 63, 23);
		changeJP.add(deleteBtn);
		
		final JButton updateBtn = new JButton("update");
		updateBtn.setBounds(148, 0, 67, 23);
		changeJP.add(updateBtn);
		panel3.add(changeJP);
		
		class LocalActionListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == findBtn) {
					
				}
				if (e.getSource() == deleteBtn) {
					
				}
				if (e.getSource() == updateBtn) {
					
				}
			}
		}
		
		findBtn.addActionListener(new LocalActionListener());
		deleteBtn.addActionListener(new LocalActionListener());
		updateBtn.addActionListener(new LocalActionListener());
	}
	
}
