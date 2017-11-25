package view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

/**
 * �� ViewMainPanel Ŭ������ Mainȭ���� Panel�� �����ϰ� �����ִ� Ŭ�����Դϴ�.
 * 
 * @author �۹μ�
 * 
 */
public class ViewMainPanel extends JPanel implements IViewPanel {

	/** ViewTotalFrame Type�� ���� �Դϴ�. */
	private ViewTotalFrame totalFrame;
	/** JButton Type�� ���� �Դϴ�. */
	private JButton gameModebt, rankingbt, helpbt, quitbt;
	/** images�� ����� �̹��� ����� ���� �Դϴ�. */
	private ImageIcon image = new ImageIcon("images/Basic.png");
	/** image�� ��ȯ�� ���� �Դϴ�. */
	private Image im = image.getImage();
	/** ��Ʈ ������ ������ ���� �Դϴ�. */
	private Color setFontColor = new Color(183, 244, 0);

	/**
	 * ViewMainPanel�� �����մϴ�.
	 * @param totalFrame - ����� ���޹��� totalFrame �Դϴ�.
	 */
	public ViewMainPanel(ViewTotalFrame totalFrame) {

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

		gameModebt = new JButton("Game Mode");
		gameModebt.setFont(new Font("Forte", Font.PLAIN, 45));
		gameModebt.setBounds(297, 290, 450, 60);
		gameModebt.setBorderPainted(false);
		gameModebt.setForeground(setFontColor);
		gameModebt.setFocusable(false);
		gameModebt.setContentAreaFilled(false);

		rankingbt = new JButton("Ranking");
		rankingbt.setFont(new Font("Forte", Font.PLAIN, 45));
		rankingbt.setBounds(297, 380, 450, 60);
		rankingbt.setBorderPainted(false);
		rankingbt.setForeground(setFontColor);
		rankingbt.setFocusable(false);
		rankingbt.setContentAreaFilled(false);

		helpbt = new JButton("Help");
		helpbt.setFont(new Font("Forte", Font.PLAIN, 45));
		helpbt.setBounds(297, 470, 450, 60);
		helpbt.setBorderPainted(false);
		helpbt.setForeground(setFontColor);
		helpbt.setFocusable(false);
		helpbt.setContentAreaFilled(false);

		quitbt = new JButton("Quit");
		quitbt.setFont(new Font("Forte", Font.PLAIN, 45));
		quitbt.setBounds(297, 560, 450, 60);
		quitbt.setBorderPainted(false);
		quitbt.setForeground(setFontColor);
		quitbt.setFocusable(false);
		quitbt.setContentAreaFilled(false);

		this.add(gameModebt);
		this.add(rankingbt);
		this.add(helpbt);
		this.add(quitbt);

	}

	/** Button�� MouseListener�� �����մϴ�. */
	public void addListener() {
		gameModebt.addMouseListener(makeMouseListener());
		rankingbt.addMouseListener(makeMouseListener());
		helpbt.addMouseListener(makeMouseListener());
		quitbt.addMouseListener(makeMouseListener());
	}

	/** MouseLister�� �����մϴ�. */
	public MouseListener makeMouseListener() {
		return new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				if (e.getSource() == gameModebt) {
					gameModebt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
				if (e.getSource() == rankingbt) {
					rankingbt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
				if (e.getSource() == helpbt) {
					helpbt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
				if (e.getSource() == quitbt) {
					quitbt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
			}

			public void mouseExited(MouseEvent e) {
				if (e.getSource() == gameModebt) {
					gameModebt.setFont(new Font("Forte", Font.PLAIN, 45));
				}
				if (e.getSource() == rankingbt) {
					rankingbt.setFont(new Font("Forte", Font.PLAIN, 45));
				}
				if (e.getSource() == helpbt) {
					helpbt.setFont(new Font("Forte", Font.PLAIN, 45));
				}
				if (e.getSource() == quitbt) {
					quitbt.setFont(new Font("Forte", Font.PLAIN, 45));
				}
			}

			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == gameModebt) {
					showGameModePanel();
				}
				if (e.getSource() == rankingbt) {
					showRankingPanel();
				}
				if (e.getSource() == helpbt) {
					showHelpPanel();
				}
				if (e.getSource() == quitbt) {
					closePentalis();
				}
			}
		};
	}

	/** GameModePanel�� �����ݴϴ�. */
	public void showGameModePanel() {
		totalFrame.showGameModePanel();
	}

	/** RankingPanel�� �����ݴϴ�. */
	public void showRankingPanel() {
		totalFrame.showRankingPanel();
	}

	/** HelpPanel�� �����ݴϴ�. */
	public void showHelpPanel() {
		totalFrame.showHelpPanel();
	}

	/** ���α׷��� �����մϴ�. */
	public void closePentalis() {
		System.exit(0);
	}

}
