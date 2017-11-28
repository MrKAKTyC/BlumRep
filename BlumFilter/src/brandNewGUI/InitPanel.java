package brandNewGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import blumPackage.Filter;

public class InitPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	ArrayList<JLabel> labels = new ArrayList<JLabel>();
	JTextField Filter_Size_Field;
	JTextField Hash_Count_Field;
	JButton Start_Button;
	Filter filtr;

	public InitPanel(final InitFrame initFrame) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		labels.add(new JLabel("Розмір фільтру"));
		labels.get(0).setHorizontalAlignment(JLabel.CENTER);
		labels.add(new JLabel("Кількість хеш функцій"));
		labels.get(1).setHorizontalAlignment(JLabel.CENTER);
		labels.add(new JLabel(""));
		labels.get(2).setHorizontalAlignment(JLabel.CENTER);

		this.add(labels.get(0));
		Filter_Size_Field = new JTextField();
		this.add(Filter_Size_Field);
		this.add(labels.get(1));
		Hash_Count_Field = new JTextField();
		this.add(Hash_Count_Field);
		this.add(labels.get(2));
		Start_Button = new JButton("Start");

		Start_Button.addActionListener(this);
		this.add(Start_Button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (Integer.parseInt(Filter_Size_Field.getText()) - Integer.parseInt(Hash_Count_Field.getText()) < 0) {
				JOptionPane.showMessageDialog(null, "Фільтр менший кількості хеш функцій", "Помилка",
						JOptionPane.ERROR_MESSAGE);
			} else {
				filtr = new Filter(Integer.parseInt(Filter_Size_Field.getText()),
						Integer.parseInt(Hash_Count_Field.getText()));
			}
		} catch (NumberFormatException | NullPointerException NPE) {
			JOptionPane.showMessageDialog(null, "Недопустимі значення", "Помилка", JOptionPane.ERROR_MESSAGE);
		}

	}
}