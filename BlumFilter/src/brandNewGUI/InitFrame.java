package brandNewGUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InitFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = -3311263499552159626L;
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private JTextField Filter_Size_Field;
	private JTextField Hash_Count_Field;
	private JButton Start_Button;

	public InitFrame() {
		this.setTitle("Ініціалізація фільтра");
		JPanel init = new JPanel();
		this.add(init);

		GridLayout BL = new GridLayout(3, 2, 5, 5);
		init.setLayout(BL);
		labels.add(new JLabel("Розмір фільтру"));
		labels.get(0).setHorizontalAlignment(JLabel.CENTER);
		labels.add(new JLabel("Кількість хеш функцій(від 1 до 11)"));
		labels.get(1).setHorizontalAlignment(JLabel.CENTER);
		labels.add(new JLabel(""));
		labels.get(2).setHorizontalAlignment(JLabel.CENTER);

		init.add(labels.get(0));
		Filter_Size_Field = new JTextField();
		init.add(Filter_Size_Field);
		init.add(labels.get(1));
		Hash_Count_Field = new JTextField();
		init.add(Hash_Count_Field);
		init.add(labels.get(2));
		Start_Button = new JButton("Почати");

		Start_Button.addActionListener(this);
		init.add(Start_Button);
		pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int Size = Integer.parseInt(Filter_Size_Field.getText());
			int Hash_c = Integer.parseInt(Hash_Count_Field.getText());
			if (Size - Hash_c < 0) {
				JOptionPane.showMessageDialog(null, "Фільтр менший кількості хеш функцій", "Помилка",
						JOptionPane.ERROR_MESSAGE);
			} else if (Hash_c > 11) {
				JOptionPane.showMessageDialog(null, "Обрано недоступну кількість хеш-функцій", "Помилка",
						JOptionPane.ERROR_MESSAGE);
			} else {
				this.dispose();
				new MainFrame(Size, Hash_c);
			}
		} catch (NumberFormatException | NullPointerException NPE) {
			JOptionPane.showMessageDialog(null, "Недопустимі значення", "Помилка", JOptionPane.ERROR_MESSAGE);
		}

	}
}