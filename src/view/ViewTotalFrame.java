package view;

import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 이 ViewTotalFrame 클래스는 모든 GUI를 총괄담당하는 클래스 입니다.
 * @author 송민석
 */
public class ViewTotalFrame extends JFrame {

	/** Controller Type 의 변수 입니다. */
	private Controller controller;
	/** ViewMainPanel Type 의 변수 입니다. */
	private ViewMainPanel mainPanel;
	/** ViewGameModePanel Type 의 변수 입니다. */
	private ViewGameModePanel gameModePanel;
	/** ViewSoloGamePanel Type 의 변수 입니다. */
	private ViewSoloGamePanel soloGamePanel;
	/** View2PAndAIGamePanel Type 의 변수 입니다. */
	private View2PAndAIGamePanel ZPAndAIGamePanel;
	/** ViewRankingPanel Type 의 변수 입니다. */
	private ViewRankingPanel rankingPanel;
	/** ViewSoloRankingPanel Type 의 변수 입니다. */
	private ViewSoloRankingPanel soloRankingPanel;
	/** ViewAIRankingPanel Type 의 변수 입니다. */
	private ViewAIRankingPanel AIRankingPanel;
	/** ViewHelpPanel Type 의 변수 입니다. */
	private ViewHelpPanel helpPanel;
	/** ViewKeyGuidePanel Type 의 변수 입니다. */
	private ViewKeyGuidePanel controlMethodPanel;
	/** ViewProfilePanel Type 의 변수 입니다. */
	private ViewProfilePanel profilePanel;
	/** ViewPausePanel Type 의 변수 입니다. */
	private ViewPausePanel pausePanel;
	/** CardLayout Type 의 변수 입니다. */
	private CardLayout card;
	/** KeyListener Type 의 변수 입니다. */
	private KeyListener keyListener;
	/** Container Type 의 변수 입니다. */
	private Container contentPane;

	/**
	 * ViewTotalFrame을 생성합니다.
	 * @param controller - 명령을 하거나 명령을 전달받을 controller 입니다.
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

	/** ViewTotalFrame 을 초기화 합니다. */
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

	/** 설정된 KeyListener를 추가합니다. */
	public void addKeyListener() {
		addKeyListener(keyListener);
	}

	/**
	 * KeyListener를 구현합니다.
	 * @return 이 KeyListener을 반환합니다.
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

	/** Container에 Panel을 삽입합니다. */
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

	/** SoloGame을 시작합니다. */
	public void soloGameStart() {
		addKeyListener();
		controller.startSoloGame();
	}

	/** 2PGame을 시작합니다. */
	public void ZPGameStart() {
		addKeyListener();
	}

	/** AIGame을 시작합니다. */
	public void AIGameStart() {
		addKeyListener();
		controller.startAIGame();
	}

	/** 화면을 repaint 합니다. */
	public void update() {
		repaint();
	}

	/** 
	 * Controller의 draw 메소드를 실행합니다. 
	 * @param g - controller에 draw 메소드를 전달합니다.
	 */
	public void draw(Graphics g) {
		controller.draw(g);
	}

	/** SoloGame을 종료합니다. */
	public void soloGameOver() {
		removeKeyListener(keyListener);
		soloGamePanel.gameOver();
	}

	/** 2PGame을 종료합니다. */
	public void ZPGameLose() {
		
	}

	/** AIGame을 종료합니다. */
	public void AIGameLose() {
		removeKeyListener(keyListener);
		soloGamePanel.gameOver();
	}

	/** MainPanel을 보여줍니다. */
	public void showMainPanel() {
		card.show(contentPane, "Main");
	}

	/** GameMode를 선택하는 Panel을 보여줍니다. */
	public void showGameModePanel() {
		card.show(contentPane, "Game Mode");
	}

	/** SoloGamePanel을 보여줍니다. */
	public void showSoloGamePanel() {

		card.show(contentPane, "Solo Game");
	}

	/** 2PGamePanel을 보여줍니다. */
	public void show2PGamePanel() {

		card.show(contentPane, "2P And AI Game");
	}

	/** AIGamePanel을 보여줍니다. */
	public void showAIGamePanel() {
		card.show(contentPane, "2P And AI Game");
	}

	/** RankingPanel을 보여줍니다. */
	public void showRankingPanel() {
		card.show(contentPane, "Ranking");
	}

	/** SoloRankingPanel을 보여줍니다. */
	public void showSoloRankingPanel() {
		card.show(contentPane, "Solo Ranking");
	}

	/** SoloAIRankingPanel을 보여줍니다. */
	public void showAIRankingPanel() {
		card.show(contentPane, "AI Ranking");
	}

	/** HelpPanel을 보여줍니다. */
	public void showHelpPanel() {
		card.show(contentPane, "Help");
	}

	/** ControlMethodPanel을 보여줍니다. */
	public void showControlMethodPanel() {
		card.show(contentPane, "Control Method");
	}

	/** ProfilePanel을 보여줍니다. */
	public void showProfile() {
		card.show(contentPane, "Profile");
	}

	/** PausePanel을 보여줍니다. */
	public void showPausePanel() {
		card.show(contentPane, "Pause");
	}

	/** 회전 명령을 전달합니다. */
	public void spin() {
		controller.spin();
	}

	/** 왼쪽이동 명령을 전달합니다. */
	public void moveLeft() {
		controller.moveLeft();
	}

	/** 오른쪽이동 명령을 전달합니다. */
	public void moveRight() {
		controller.moveRight();
	}

	/** 아래로이동 명령을 전달합니다. */
	public void moveDown() {
		controller.moveDown();
	}

	/** 아래로 빠르게 이동 명령을 전달합니다. */
	public void fastDown() {
		controller.fastDown();
	}

	/** 일시정지 명령을 전달합니다. */
	public void pause() {
		removeKeyListener(keyListener);
		controller.pause();
	}

	/** 계속하기 명령을 전달합니다. */
	public void resume() {
		addKeyListener(keyListener);
		controller.resume();
	}

	/** 재시작 명령을 전달합니다. */
	public void restart() {
		addKeyListener(keyListener);
		controller.restart();
	}

	/** Main화면으로 이동 명령을 전달합니다. */
	public void goMain() {
		controller.goMain();
	}

}
