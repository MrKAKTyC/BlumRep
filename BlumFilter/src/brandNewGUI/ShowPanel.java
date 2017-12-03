package brandNewGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.DebugGraphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import blumPackage.Hasher;
import util.ActionTypes;

public class ShowPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Hasher hash;
	private JLabel[][] labels;
	private int cols;
	private int rows;
	private JScrollPane JSP;

	public ShowPanel(int Size, Hasher hash) {
		this.setLayout(new GridLayout(1, 1));
		this.hash = hash;
		this.cols = 10;
		JSP = new JScrollPane();
		JPanel GridPanel = new JPanel();
		this.rows = (Size % cols == 0 ? Size / cols : Size / cols + 1);
		GridPanel.setLayout(new GridLayout(rows, cols));
		setMinimumSize(new Dimension(150, 100));
//		JLabel header = new JLabel("Стан бітів в фільтрі");
//		this.add(header);
//		header.setVisible(true);
		JSP.add(GridPanel);
		JSP.setViewportView(GridPanel);
		labels = new JLabel[rows][cols];
		System.out.println(rows);
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length && Size > 0; j++, Size--) {
				labels[i][j] = new JLabel();
				labels[i][j].setText("0");
				labels[i][j].setHorizontalAlignment(JLabel.CENTER);
				GridPanel.add(labels[i][j]);
			}
		}
	this.add(JSP);
	this.setMaximumSize(new Dimension(200, 300));
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