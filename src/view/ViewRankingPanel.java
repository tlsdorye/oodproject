package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 * �� ViewRankingPanel Ŭ������ Rankingȭ���� Panel�� �����ϰ� �����ִ� Ŭ�����Դϴ�.
 * 
 * @author ������
 * 
 */
public class ViewRankingPanel extends JPanel implements IViewPanel {
	/** ViewTotalFrame Type�� ���� �Դϴ�. */
	private ViewTotalFrame totalFrame;
	/** JButton Type�� ���� �Դϴ�. */
	private JButton soloRankingbt, AIRankingbt, backbt;
	/** images�� ����� �̹��� ����� ���� �Դϴ�. */
	private ImageIcon image = new ImageIcon("images/Basic.png");
	/** image�� ��ȯ�� ���� �Դϴ�. */
	private Image im = image.getImage();
	/** ��Ʈ ������ ������ ���� �Դϴ�. */
	private Color setFontColor = new Color(41, 230, 244);

	/**
	 * ViewRankingPanel�� �����մϴ�.
	 * @param totalFrame - ����� ���޹��� totalFrame �Դϴ�.
	 */
	public ViewRankingPanel(ViewTotalFrame totalFrame) {
		this.totalFrame = totalFrame;
		init();
	}

	/** �ʱⰪ ������ �մϴ�. */
	public void init() {
		addButton();
		addListener();
		setLayout(null);
	}

	/** ���� ������Ʈ�� �ڽ��� ����� �׸��ϴ�. */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(im, 0, 0, getWidth(), getHeight(), this);
	}

	/** Button�� �⺻ ������ �����մϴ�. */
	public void addButton() {
		soloRankingbt = new JButton("Solo Ranking");
		soloRankingbt.setFont(new Font("Forte", Font.PLAIN, 45));
		soloRankingbt.setBounds(297, 290, 450, 60);
		soloRankingbt.setBorderPainted(false);
		soloRankingbt.setForeground(setFontColor);
		soloRankingbt.setFocusable(false);
		soloRankingbt.setContentAreaFilled(false);

		AIRankingbt = new JButton("AI Ranking");
		AIRankingbt.setFont(new Font("Forte", Font.PLAIN, 45));
		AIRankingbt.setBounds(297, 380, 450, 60);
		AIRankingbt.setBorderPainted(false);
		AIRankingbt.setForeground(setFontColor);
		AIRankingbt.setFocusable(false);
		AIRankingbt.setContentAreaFilled(false);

		backbt = new JButton("back");
		backbt.setFont(new Font("Forte", Font.PLAIN, 45));
		backbt.setBounds(297, 470, 450, 60);
		backbt.setBorderPainted(false);
		backbt.setForeground(setFontColor);
		backbt.setFocusable(false);
		backbt.setContentAreaFilled(false);

		this.add(soloRankingbt);
		this.add(AIRankingbt);
		this.add(backbt);
	}

	/** Button�� MouseListener�� �����մϴ�. */
	public void addListener() {
		soloRankingbt.addMouseListener(makeMouseListener());
		AIRankingbt.addMouseListener(makeMouseListener());
		backbt.addMouseListener(makeMouseListener());
	}

	/**
	 * MouseLister�� �����մϴ�.
	 */
	public MouseListener makeMouseListener() {
		return new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() == soloRankingbt) {
					soloRankingbt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
				if (e.getSource() == AIRankingbt) {
					AIRankingbt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
				if (e.getSource() == backbt) {
					backbt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
			}

			public void mouseExited(MouseEvent e) {
				if (e.getSource() == soloRankingbt) {
					soloRankingbt.setFont(new Font("Forte", Font.PLAIN, 45));
				}
				if (e.getSource() == AIRankingbt) {
					AIRankingbt.setFont(new Font("Forte", Font.PLAIN, 45));
				}
				if (e.getSource() == backbt) {
					backbt.setFont(new Font("Forte", Font.PLAIN, 45));
				}

			}

			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == soloRankingbt) {
					showSoloRankingPanel();
				}
				if (e.getSource() == AIRankingbt) {
					showAIRankingPanel();
				}
				if (e.getSource() == backbt) {
					showMainPanel();
				}

			}
		};

	}

	/** SoloRankingPanel�� �����ݴϴ�. */
	public void showSoloRankingPanel() {
		totalFrame.showSoloRankingPanel();
	}

	/** AIRankingPanel�� �����ݴϴ�. */
	public void showAIRankingPanel() {
		totalFrame.showAIRankingPanel();
	}

	/** MainPanel�� �����ݴϴ�. */
	public void showMainPanel() {
		totalFrame.showMainPanel();
	}
}