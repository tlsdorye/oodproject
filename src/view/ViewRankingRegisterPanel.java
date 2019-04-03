package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import ranking.RankingManager;

/**
 * �� ViewRankingRegisterPanel Ŭ������ Ranking�� ����ϴ� ȭ���� Panel�� �����ϰ� �����ִ� Ŭ�����Դϴ�.
 * 
 * @author ������
 * 
 */
public class ViewRankingRegisterPanel extends JPanel {

	/** RankingManager Type�� ���� �Դϴ�. */
	private RankingManager rankingManager;
	/** JTextField Type�� ���� �Դϴ�. */
	private JTextField nameSpace;
	/** String Type�� ���� �Դϴ�. */
	private String name;

	/** */
	public ViewRankingRegisterPanel(RankingManager rankingManager) {
		this.rankingManager = rankingManager;
		init();
	}

	/** �ʱⰪ ������ �մϴ�. */
	public void init() {

	}

	/**  */
	public void keyPressed() {
		name = nameSpace.getText();
		register(name);
		// register(nameSpace.getText());
	}

	/** */
	public void register(String name) {
		// rankingManager.register(name);
	}
}
