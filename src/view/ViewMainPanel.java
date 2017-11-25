package view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

/**
 * 이 ViewMainPanel 클래스는 Main화면의 Panel을 구성하고 보여주는 클래스입니다.
 * 
 * @author 송민석
 * 
 */
public class ViewMainPanel extends JPanel implements IViewPanel {

	/** ViewTotalFrame Type의 변수 입니다. */
	private ViewTotalFrame totalFrame;
	/** JButton Type의 변수 입니다. */
	private JButton gameModebt, rankingbt, helpbt, quitbt;
	/** images에 저장된 이미지 사용할 변수 입니다. */
	private ImageIcon image = new ImageIcon("images/Basic.png");
	/** image를 반환할 변수 입니다. */
	private Image im = image.getImage();
	/** 폰트 색상을 지정할 변수 입니다. */
	private Color setFontColor = new Color(183, 244, 0);

	/**
	 * ViewMainPanel을 생성합니다.
	 * @param totalFrame - 명령을 전달받을 totalFrame 입니다.
	 */
	public ViewMainPanel(ViewTotalFrame totalFrame) {

		this.totalFrame = totalFrame;
		init();
	}

	/** 초기값 설정을 합니다. */
	public void init() {
		addButton();
		addListener();
		setLayout(null);
	}

	/** 스윙 컴포넌트가 자신의 모양을 그립니다. */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(im, 0, 0, getWidth(), getHeight(), this);
	}

	/** Button의 기본 설정을 세팅합니다. */
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

	/** Button에 MouseListener를 삽입합니다. */
	public void addListener() {
		gameModebt.addMouseListener(makeMouseListener());
		rankingbt.addMouseListener(makeMouseListener());
		helpbt.addMouseListener(makeMouseListener());
		quitbt.addMouseListener(makeMouseListener());
	}

	/** MouseLister를 구현합니다. */
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

	/** GameModePanel을 보여줍니다. */
	public void showGameModePanel() {
		totalFrame.showGameModePanel();
	}

	/** RankingPanel을 보여줍니다. */
	public void showRankingPanel() {
		totalFrame.showRankingPanel();
	}

	/** HelpPanel을 보여줍니다. */
	public void showHelpPanel() {
		totalFrame.showHelpPanel();
	}

	/** 프로그램을 종료합니다. */
	public void closePentalis() {
		System.exit(0);
	}

}
