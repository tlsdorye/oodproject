package view;

import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * �� ViewTotalFrame Ŭ������ ��� GUI�� �Ѱ�����ϴ� Ŭ���� �Դϴ�.
 * @author �۹μ�
 */
public class ViewTotalFrame extends JFrame {

	/** Controller Type �� ���� �Դϴ�. */
	private Controller controller;
	/** ViewMainPanel Type �� ���� �Դϴ�. */
	private ViewMainPanel mainPanel;
	/** ViewGameModePanel Type �� ���� �Դϴ�. */
	private ViewGameModePanel gameModePanel;
	/** ViewSoloGamePanel Type �� ���� �Դϴ�. */
	private ViewSoloGamePanel soloGamePanel;
	/** View2PAndAIGamePanel Type �� ���� �Դϴ�. */
	private View2PAndAIGamePanel ZPAndAIGamePanel;
	/** ViewRankingPanel Type �� ���� �Դϴ�. */
	private ViewRankingPanel rankingPanel;
	/** ViewSoloRankingPanel Type �� ���� �Դϴ�. */
	private ViewSoloRankingPanel soloRankingPanel;
	/** ViewAIRankingPanel Type �� ���� �Դϴ�. */
	private ViewAIRankingPanel AIRankingPanel;
	/** ViewHelpPanel Type �� ���� �Դϴ�. */
	private ViewHelpPanel helpPanel;
	/** ViewKeyGuidePanel Type �� ���� �Դϴ�. */
	private ViewKeyGuidePanel controlMethodPanel;
	/** ViewProfilePanel Type �� ���� �Դϴ�. */
	private ViewProfilePanel profilePanel;
	/** ViewPausePanel Type �� ���� �Դϴ�. */
	private ViewPausePanel pausePanel;
	/** CardLayout Type �� ���� �Դϴ�. */
	private CardLayout card;
	/** KeyListener Type �� ���� �Դϴ�. */
	private KeyListener keyListener;
	/** Container Type �� ���� �Դϴ�. */
	private Container contentPane;

	/**
	 * ViewTotalFrame�� �����մϴ�.
	 * @param controller - ����� �ϰų� ����� ���޹��� controller �Դϴ�.
	 */
	public ViewTotalFrame(Controller controller) {
		this.controller = controller;
		mainPanel = new ViewMainPanel(this);
		gameModePanel = new ViewGameModePanel(this);
		soloGamePanel = new ViewSoloGamePanel(this);
		ZPAndAIGamePanel = new View2PAndAIGamePanel();
		rankingPanel = new ViewRankingPanel(this);
		soloRankingPanel = new ViewSoloRankingPanel(this);
		AIRankingPanel = new ViewAIRankingPanel(this);
		helpPanel = new ViewHelpPanel(this);
		controlMethodPanel = new ViewKeyGuidePanel(this);
		profilePanel = new ViewProfilePanel(this);
		pausePanel = new ViewPausePanel(this);
		card = new CardLayout();
		keyListener = makeKeyListener();
		init();
	}

	/** ViewTotalFrame �� �ʱ�ȭ �մϴ�. */
	public void init() {
		setTitle("Pentaris");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		contentPane = getContentPane();
		contentPane.setLayout(card);
		addPanel();
		setSize(1050, 700);
		setFocusable(true);
		requestFocus();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	/** ������ KeyListener�� �߰��մϴ�. */
	public void addKeyListener() {
		addKeyListener(keyListener);
	}

	/**
	 * KeyListener�� �����մϴ�.
	 * @return �� KeyListener�� ��ȯ�մϴ�.
	 */
	public KeyListener makeKeyListener() {
		return new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					spin();
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					fastDown();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					moveLeft();
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					moveRight();
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					moveDown();
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					pause();
				}
			}
		};
	}

	/** Container�� Panel�� �����մϴ�. */
	public void addPanel() {
		contentPane.add("Main", mainPanel);
		contentPane.add("Game Mode", gameModePanel);
		contentPane.add("Solo Game", soloGamePanel);
		contentPane.add("2P And AI Game", ZPAndAIGamePanel);
		contentPane.add("Ranking", rankingPanel);
		contentPane.add("Solo Ranking", soloRankingPanel);
		contentPane.add("AI Ranking", AIRankingPanel);
		contentPane.add("Help", helpPanel);
		contentPane.add("Control Method", controlMethodPanel);
		contentPane.add("Profile", profilePanel);
		contentPane.add("Pause", pausePanel);
	}

	/** SoloGame�� �����մϴ�. */
	public void soloGameStart() {
		addKeyListener();
		controller.startSoloGame();
	}

	/** 2PGame�� �����մϴ�. */
	public void ZPGameStart() {
		addKeyListener();
	}

	/** AIGame�� �����մϴ�. */
	public void AIGameStart() {
		addKeyListener();
		controller.startAIGame();
	}

	/** ȭ���� repaint �մϴ�. */
	public void update() {
		repaint();
	}

	/** 
	 * Controller�� draw �޼ҵ带 �����մϴ�. 
	 * @param g - controller�� draw �޼ҵ带 �����մϴ�.
	 */
	public void draw(Graphics g) {
		controller.draw(g);
	}

	/** SoloGame�� �����մϴ�. */
	public void soloGameOver() {
		removeKeyListener(keyListener);
		soloGamePanel.gameOver();
	}

	/** 2PGame�� �����մϴ�. */
	public void ZPGameLose() {
		
	}

	/** AIGame�� �����մϴ�. */
	public void AIGameLose() {
		removeKeyListener(keyListener);
		soloGamePanel.gameOver();
	}

	/** MainPanel�� �����ݴϴ�. */
	public void showMainPanel() {
		card.show(contentPane, "Main");
	}

	/** GameMode�� �����ϴ� Panel�� �����ݴϴ�. */
	public void showGameModePanel() {
		card.show(contentPane, "Game Mode");
	}

	/** SoloGamePanel�� �����ݴϴ�. */
	public void showSoloGamePanel() {

		card.show(contentPane, "Solo Game");
	}

	/** 2PGamePanel�� �����ݴϴ�. */
	public void show2PGamePanel() {

		card.show(contentPane, "2P And AI Game");
	}

	/** AIGamePanel�� �����ݴϴ�. */
	public void showAIGamePanel() {
		card.show(contentPane, "2P And AI Game");
	}

	/** RankingPanel�� �����ݴϴ�. */
	public void showRankingPanel() {
		card.show(contentPane, "Ranking");
	}

	/** SoloRankingPanel�� �����ݴϴ�. */
	public void showSoloRankingPanel() {
		card.show(contentPane, "Solo Ranking");
	}

	/** SoloAIRankingPanel�� �����ݴϴ�. */
	public void showAIRankingPanel() {
		card.show(contentPane, "AI Ranking");
	}

	/** HelpPanel�� �����ݴϴ�. */
	public void showHelpPanel() {
		card.show(contentPane, "Help");
	}

	/** ControlMethodPanel�� �����ݴϴ�. */
	public void showControlMethodPanel() {
		card.show(contentPane, "Control Method");
	}

	/** ProfilePanel�� �����ݴϴ�. */
	public void showProfile() {
		card.show(contentPane, "Profile");
	}

	/** PausePanel�� �����ݴϴ�. */
	public void showPausePanel() {
		card.show(contentPane, "Pause");
	}

	/** ȸ�� ����� �����մϴ�. */
	public void spin() {
		controller.spin();
	}

	/** �����̵� ����� �����մϴ�. */
	public void moveLeft() {
		controller.moveLeft();
	}

	/** �������̵� ����� �����մϴ�. */
	public void moveRight() {
		controller.moveRight();
	}

	/** �Ʒ����̵� ����� �����մϴ�. */
	public void moveDown() {
		controller.moveDown();
	}

	/** �Ʒ��� ������ �̵� ����� �����մϴ�. */
	public void fastDown() {
		controller.fastDown();
	}

	/** �Ͻ����� ����� �����մϴ�. */
	public void pause() {
		removeKeyListener(keyListener);
		controller.pause();
	}

	/** ����ϱ� ����� �����մϴ�. */
	public void resume() {
		addKeyListener(keyListener);
		controller.resume();
	}

	/** ����� ����� �����մϴ�. */
	public void restart() {
		addKeyListener(keyListener);
		controller.restart();
	}

	/** Mainȭ������ �̵� ����� �����մϴ�. */
	public void goMain() {
		controller.goMain();
	}

}
