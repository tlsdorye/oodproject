package view;

import javax.swing.*;
import ranking.RankingData;
import ranking.RankingManager;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

/**
 * 이 ViewAIRankingPanel 클래스는 AIRanking화면의 Panel을 구성하고 보여주는 클래스입니다.
 * @author 이은경
 */
public class ViewAIRankingPanel extends JPanel {

	/** ViewTotalFrame Type의 변수 입니다. */
	private ViewTotalFrame totalFrame;
	/** JLabel Type의 변수 입니다. */
	private JLabel viewAIRanking;
	/** JButton Type의 변수 입니다. */
	private JButton backbt;
	/** images에 저장된 이미지 사용할 변수 입니다. */
	private ImageIcon image = new ImageIcon("images/black.png");
	/** image를 반환할 변수 입니다. */
	private Image im = image.getImage();
	/** RankingManager Type의 변수입니다. */
	private RankingManager rankingManager;
	/** RankingData[] Type 의 변수입니다. */
	private RankingData[] AIRankingData;

	/**
	 * ViewAIRankingPanel을 생성합니다.
	 * @param totalFrame - 명령을 전달받을 totalFrame 입니다.
	 */
	public ViewAIRankingPanel(ViewTotalFrame totalFrame) {
		this.totalFrame = totalFrame;
		viewAIRanking = new JLabel("");
		backbt = new JButton("back");
		init();
	}

	/**
	 * ViewAIRankingPanel을 생성합니다.
	 * @param AIRankingData - 명령을 전달받을 AIRankingData 입니다.
	 */
	public ViewAIRankingPanel(RankingData[] AIRankingData) {
		this.AIRankingData = AIRankingData;
	}

	/** Panel을 초기화 합니다. */
	public void init() {
		addLabel();
		addButton();
		addListener();

	}

	/** 스윙 컴포넌트가 자신의 모양을 그립니다. */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(im, 0, 0, getWidth(), getHeight(), this);
	}

	/** Label을 추가합니다. */
	public void addLabel() {
		this.add(viewAIRanking);
	}

	/** Button을 추가합니다. */
	public void addButton() {
		backbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
		this.add(backbt);
	}

	/** Button에 MouseListener를 추가합니다. */
	public void addListener() {
		backbt.addMouseListener(makeMouseListener());

	}

	/**
	 * MouseListener을 만듭니다.
	 * @return 이 MouseListener를 반환합니다.
	 */
	public MouseListener makeMouseListener() {
		return new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() == backbt) {
					backbt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
				}
			}

			public void mouseExited(MouseEvent e) {
				if (e.getSource() == backbt) {
					backbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
				}
			}

			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == backbt) {
					ViewRankingPanel();
				}
			}
		};
	}

	/** RankingPanel을 보여줍니다. */
	public void ViewRankingPanel() {
		totalFrame.showRankingPanel();

	}
}
