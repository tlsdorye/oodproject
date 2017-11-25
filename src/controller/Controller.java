package controller;

import java.awt.Graphics;

import model.GameBoardSolo;
import model.GameBoardZP;
import view.ViewTotalFrame;

/**
 * 이 Controller 클래스는 GameBoard Class 와 ViewTotalFrame Class 의 연결을 담당합니다.
 * ViewTotalFrame 객체와 GameBoard 객체는 각자 명령을 Controller 객체에게 전달하거나 전달받습니다.
 * 
 * @author 신승현
 */
public class Controller {

	/** 명령을 받을 TotalFrame Type 의 변수입니다. */
	private ViewTotalFrame totalFrame;
	/** 명령을 전달할 GameBoardSolo Type 의 변수입니다. */
	private GameBoardSolo soloGameBoard;
	/** 명령을 전달할 ZPGameBoard Type 의 변수입니다. */
	private GameBoardZP ZPGameBoard;
	/** GameMode 를 나타낼 Int Type 변수입니다. */
	public int gameMode;

	/** ViewTotalFrame이 생성되는 Controller 를 생성합니다. */
	public Controller() {
		gameMode = 0;
		totalFrame = new ViewTotalFrame(this);
	}

	/** Controller 의 멤버변수들을 초기화 합니다. */
	public void init() {
		soloGameBoard = new GameBoardSolo(this);

	}

	/** Solo Game을 시작합니다. */
	public void startSoloGame() {
		init();
		gameMode = 1;
		soloGameBoard.startSoloGame();
		totalFrame.showSoloGamePanel();
	}

	/** 2P Game 을 시작합니다. */
	public void start2PGame() {

	}

	/** AI Game 을 시작합니다. */
	public void startAIGame() {

	}

	/**
	 * soloGameBoard 의 draw 메소드를 실행합니다.
	 * 
	 * @param g
	 *            - totalFrame에서 받은 매개변수 입니다. 이 Graphics g를 GameBoard의 draw 메소드에
	 *            전달합니다.
	 */
	public void draw(Graphics g) {
		soloGameBoard.draw(g);
	}

	/** totalFrame 의 update 메소드를 실행합니다. */
	public void update() {
		totalFrame.update();
	}

	/** 회전 명령을 전달합니다. */
	public void spin() {
		soloGameBoard.spin();
		update();
	}

	/** 왼쪽이동 명령을 전달합니다. */
	public void moveLeft() {
		soloGameBoard.moveLeft();
		update();
	}

	/** 오른쪽 이동 명령을 전달합니다. */
	public void moveRight() {
		soloGameBoard.moveRight();
		update();
	}

	/** 아래이동 명령을 전달합니다. */
	public void moveDown() {
		soloGameBoard.moveDown();
		update();
	}

	/** 바로내림 명령을 전달합니다. */
	public void fastDown() {
		soloGameBoard.fastDown();
		update();
	}

	/** Game 을 일시정지 합니다. */
	public void pause() {
		soloGameBoard.pause();
		totalFrame.showPausePanel();
	}

	/** Game 을 재개 합니다. */
	public void resume() {
		soloGameBoard.resume();
		totalFrame.showSoloGamePanel();
		update();
	}

	/** Game 을 재시작 합니다. */
	public void restart() {
		soloGameBoard.restart();
		totalFrame.showSoloGamePanel();
		update();
	}

	/** 메인으로 돌아갑니다. */
	public void goMain() {
		totalFrame.showMainPanel();
	}

	/** SoloGame Over하면 호출됩니다. */
	public void soloGameOver() {
		totalFrame.soloGameOver();
	}

}
