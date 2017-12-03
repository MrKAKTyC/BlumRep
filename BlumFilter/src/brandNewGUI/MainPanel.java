package brandNewGUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import util.ActionTypes;

public class MainPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1842656000773991539L;
	private JLabel Status_Label;
	private JLabel Chance_Label;
	private JTextField String_for_Work;
	private JButton Add_B;
	private JButton Chek_B;
	private JButton Remove_From_Filt;
	private JButton Re_Init_B;
	private String LastElem = null;
	private MainFrame mainFrame;

	public MainPanel(final MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		String_for_Work = new JTextField();
		String_for_Work.setMaximumSize(new Dimension(1000, 20));
		String_for_Work.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Status_Label.setText("");
			}
		});
		this.add(String_for_Work);
		Chance_Label = new JLabel("Шанс похибки 0%");
//		Chance_Label.setAlignmentX(JLabel.CENTER);
		this.add(Chance_Label); // Add		
		this.add(buttonPanel);
		Add_B = new JButton("Додати до фільтра");
		Add_B.addActionListener(this);
		buttonPanel.add(Add_B);
		Chek_B = new JButton("Перевірити на наявність"); // Check
		Chek_B.addActionListener(this);
		buttonPanel.add(Chek_B);
		Remove_From_Filt = new JButton("Видалити з фільтра"); // Remove
		Remove_From_Filt.addActionListener(this);
		buttonPanel.add(Remove_From_Filt);
		Re_Init_B = new JButton("Новий фільтр");
		Re_Init_B.addActionListener(this);
		buttonPanel.add(Re_Init_B);
		Status_Label = new JLabel("");
//		Status_Label.setHorizontalAlignment(JLabel.CENTER);
		this.add(Status_Label);
		this.setMaximumSize(new Dimension(250, 300));
	}

	public void setString_for_Work(String string_for_Work) {
		String_for_Work.setText(string_for_Work);
	}

	// Actions Perfomed

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Add_B.equals(e.getSource())) {
			String element = String_for_Work.getText();
			if (element.length() == 0) {
				JOptionPane.showMessageDialog(null, "Немає чого добавляти в фільтр", "Увага",
						JOptionPane.WARNING_MESSAGE);
			} else {
				if (LastElem != null) {
					mainFrame.changeSetView(LastElem, ActionTypes.CLEAN);
				}
				Status_Label.setText(mainFrame.getFiltr().addToSet(element));
				mainFrame.changeSetView(element, ActionTypes.ADD);
				LastElem = element;
				int chance = (int) (mainFrame.getFiltr().CalculateFalsePositv() * 100);
				Chance_Label.setText("Шанс похибки " + (chance < 1 ? "<1" : chance) + "%");
				mainFrame.changeHistory(element, ActionTypes.ADD);
			}
		} else if (Chek_B.equals(e.getSource())) {
			String element = String_for_Work.getText();
			if (element.length() == 0) {
				JOptionPane.showMessageDialog(null, "Немає чого шукати в фільтрі", "Увага",
						JOptionPane.WARNING_MESSAGE);
			} else {
				if (LastElem != null) {
					mainFrame.changeSetView(LastElem, ActionTypes.CLEAN);
				}
				Status_Label.setText(mainFrame.getFiltr().findInSet(String_for_Work.getText()));
				mainFrame.changeSetView(element, ActionTypes.FIND);
				LastElem = element;
			}

		} else if (Remove_From_Filt.equals(e.getSource())) {
			String element = String_for_Work.getText();
			if (element.length() == 0) {
				JOptionPane.showMessageDialog(null, "Немає чого видаляти з фільтра", "Увага",
						JOptionPane.WARNING_MESSAGE);
			} else {
				if (LastElem != null) {
					mainFrame.changeSetView(LastElem, ActionTypes.CLEAN);
				}
				Status_Label.setText(mainFrame.getFiltr().removeFromSet(element));
				mainFrame.changeSetView(element, ActionTypes.REMOVE);
				LastElem = element;
				int chance = (int) (mainFrame.getFiltr().CalculateFalsePositv() * 100);
				Chance_Label.setText("Шанс похибки " + (chance < 1 ? "<1" : chance) + "%");
				mainFrame.changeHistory(element, ActionTypes.REMOVE);
			}
		} else if (Re_Init_B.equals(e.getSource())) {
			mainFrame.dispose();
			new InitFrame();
		}
	}
}