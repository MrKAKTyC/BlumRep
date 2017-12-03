package brandNewGUI;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import blumPackage.Filter;
import util.ActionTypes;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -1717632182207908343L;
	private Filter filtr;
	private MainPanel main_p;
	private ShowPanel show_p;
	private HistoryPanel history_p;

	public MainFrame(int Size, int HashCount) {
		this.setTitle("Фільтр Блума");
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		filtr = new Filter(Size, HashCount); // ALARM REWORK!!!
		this.main_p = new MainPanel(this);
		this.show_p = new ShowPanel(filtr.getSetSize(),filtr.getHasher());
		this.history_p = new HistoryPanel(this);
		this.add(main_p);
		this.add(show_p);
		this.add(history_p);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(650, 300);
		this.setResizable(false);
	}

	public Filter getFiltr() {
		return filtr;
	}

	public void setFiltr(Filter filtr) {
		this.filtr = filtr;
	}

	public void changeHistory(String element, ActionTypes ActionType) {
		if (ActionType == ActionTypes.ADD) {
			history_p.AddToHistory(element);
		} else {
			history_p.Remove_From_History(element);
		}
	}

	public void changeSetView(String element, ActionTypes ActionType) {
		this.show_p.changeState(element, ActionType);
	}

	public void SetElement(String element) {
		this.main_p.setString_for_Work(element);
	}
}