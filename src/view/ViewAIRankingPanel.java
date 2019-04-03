package view;

import javax.swing.*;
import ranking.RankingData;
import ranking.RankingManager;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

/**
 * �� ViewAIRankingPanel Ŭ������ AIRankingȭ���� Panel�� �����ϰ� �����ִ� Ŭ�����Դϴ�.
 * @author ������
 */
public class ViewAIRankingPanel extends JPanel {

	/** ViewTotalFrame Type�� ���� �Դϴ�. */
	private ViewTotalFrame totalFrame;
	/** JLabel Type�� ���� �Դϴ�. */
	private JLabel viewAIRanking;
	/** JButton Type�� ���� �Դϴ�. */
	private JButton backbt;
	/** images�� ����� �̹��� ����� ���� �Դϴ�. */
	private ImageIcon image = new ImageIcon("images/black.png");
	/** image�� ��ȯ�� ���� �Դϴ�. */
	private Image im = image.getImage();
	/** RankingManager Type�� �����Դϴ�. */
	private RankingManager rankingManager;
	/** RankingData[] Type �� �����Դϴ�. */
	private RankingData[] AIRankingData;

	/**
	 * ViewAIRankingPanel�� �����մϴ�.
	 * @param totalFrame - ����� ���޹��� totalFrame �Դϴ�.
	 */
	public ViewAIRankingPanel(ViewTotalFrame totalFrame) {
		this.totalFrame = totalFrame;
		viewAIRanking = new JLabel("");
		backbt = new JButton("back");
		init();
	}

	/**
	 * ViewAIRankingPanel�� �����մϴ�.
	 * @param AIRankingData - ����� ���޹��� AIRankingData �Դϴ�.
	 */
	public ViewAIRankingPanel(RankingData[] AIRankingData) {
		this.AIRankingData = AIRankingData;
	}

	/** Panel�� �ʱ�ȭ �մϴ�. */
	public void init() {
		addLabel();
		addButton();
		addListener();

	}

	/** ���� ������Ʈ�� �ڽ��� ����� �׸��ϴ�. */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(im, 0, 0, getWidth(), getHeight(), this);
	}

	/** Label�� �߰��մϴ�. */
	public void addLabel() {
		this.add(viewAIRanking);
	}

	/** Button�� �߰��մϴ�. */
	public void addButton() {
		backbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
		this.add(backbt);
	}

	/** Button�� MouseListener�� �߰��մϴ�. */
	public void addListener() {
		backbt.addMouseListener(makeMouseListener());

	}

	/**
	 * MouseListener�� ����ϴ�.
	 * @return �� MouseListener�� ��ȯ�մϴ�.
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

	/** RankingPanel�� �����ݴϴ�. */
	public void ViewRankingPanel() {
		totalFrame.showRankingPanel();

	}
}
