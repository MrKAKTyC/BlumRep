package brandNewGUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import blumPackage.Hasher;
import util.ActionTypes;

public class ShowPanel extends JScrollPane {
	private static final long serialVersionUID = 1L;
	private Hasher hash;
	private JLabel[][] labels;
	private int cols;
	private int rows;
	private int Size;

	public ShowPanel(int Size) {
		this.Size = Size;
		this.hash = new Hasher(3,Size);
		this.cols = 10;
		JPanel GridPanel = new JPanel();
		this.rows = (Size % cols == 0 ? Size / cols : Size / cols + 1);
		GridPanel.setLayout(new GridLayout(rows, cols));
//		GridPanel.setPreferredSize(new Dimension(150, 100));
		this.add(GridPanel);
		this.setViewportView(GridPanel);
		labels = new JLabel[rows][cols];
		System.out.println(rows);
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length && Size > 0; j++, Size--) {
				labels[i][j] = new JLabel("0");
				labels[i][j].setHorizontalAlignment(JLabel.CENTER);
				GridPanel.add(labels[i][j]);
			}
		}
	}

	public void changeState(String Value, ActionTypes actionType) {

		int[] coordinates = hash.hashar(Value);
		switch (actionType) {
		case ADD:
			for (int i = 0; i < coordinates.length; i++) {
				int row = coordinates[i] / cols;
				int col = coordinates[i] % cols;
				labels[row][col].setText("1");
				labels[row][col].setForeground(new Color(51, 204, 51)); // GREAN
			}
			break;
		case REMOVE:
			for (int i = 0; i < coordinates.length; i++) {
				int row = coordinates[i] / cols;
				int col = coordinates[i] % cols;
				labels[row][col].setText("0");
				labels[row][col].setForeground(new Color(230, 0, 0));  //RED
			}
			break;
		case FIND:
			for (int i = 0; i < coordinates.length; i++) {
				int row = coordinates[i] / cols;
				int col = coordinates[i] % cols;
				labels[row][col].setForeground(new Color(255, 153, 0));	 //ORANGE(YEALOW)
			}
			break;
		case CLEAN:
			for (int i = 0; i < coordinates.length; i++) {
				int row = coordinates[i] / cols;
				int col = coordinates[i] % cols;
				labels[row][col].setForeground(Color.BLACK);
			}
			break;
		}
	}
}