package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import ranking.RankingManager;

/**
 * 이 ViewRankingRegisterPanel 클래스는 Ranking을 등록하는 화면의 Panel을 구성하고 보여주는 클래스입니다.
 * 
 * @author 이은경
 * 
 */
public class ViewRankingRegisterPanel extends JPanel {

	/** RankingManager Type의 변수 입니다. */
	private RankingManager rankingManager;
	/** JTextField Type의 변수 입니다. */
	private JTextField nameSpace;
	/** String Type의 변수 입니다. */
	private String name;

	/** */
	public ViewRankingRegisterPanel(RankingManager rankingManager) {
		this.rankingManager = rankingManager;
		init();
	}

	/** 초기값 설정을 합니다. */
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
