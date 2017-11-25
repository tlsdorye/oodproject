package controller;

import java.awt.Graphics;

import model.GameBoardSolo;
import model.GameBoardZP;
import view.ViewTotalFrame;

/**
 * �� Controller Ŭ������ GameBoard Class �� ViewTotalFrame Class �� ������ ����մϴ�.
 * ViewTotalFrame ��ü�� GameBoard ��ü�� ���� ����� Controller ��ü���� �����ϰų� ���޹޽��ϴ�.
 * 
 * @author �Ž���
 */
public class Controller {

	/** ����� ���� TotalFrame Type �� �����Դϴ�. */
	private ViewTotalFrame totalFrame;
	/** ����� ������ GameBoardSolo Type �� �����Դϴ�. */
	private GameBoardSolo soloGameBoard;
	/** ����� ������ ZPGameBoard Type �� �����Դϴ�. */
	private GameBoardZP ZPGameBoard;
	/** GameMode �� ��Ÿ�� Int Type �����Դϴ�. */
	public int gameMode;

	/** ViewTotalFrame�� �����Ǵ� Controller �� �����մϴ�. */
	public Controller() {
		gameMode = 0;
		totalFrame = new ViewTotalFrame(this);
	}

	/** Controller �� ����������� �ʱ�ȭ �մϴ�. */
	public void init() {
		soloGameBoard = new GameBoardSolo(this);

	}

	/** Solo Game�� �����մϴ�. */
	public void startSoloGame() {
		init();
		gameMode = 1;
		soloGameBoard.startSoloGame();
		totalFrame.showSoloGamePanel();
	}

	/** 2P Game �� �����մϴ�. */
	public void start2PGame() {

	}

	/** AI Game �� �����մϴ�. */
	public void startAIGame() {

	}

	/**
	 * soloGameBoard �� draw �޼ҵ带 �����մϴ�.
	 * 
	 * @param g
	 *            - totalFrame���� ���� �Ű����� �Դϴ�. �� Graphics g�� GameBoard�� draw �޼ҵ忡
	 *            �����մϴ�.
	 */
	public void draw(Graphics g) {
		soloGameBoard.draw(g);
	}

	/** totalFrame �� update �޼ҵ带 �����մϴ�. */
	public void update() {
		totalFrame.update();
	}

	/** ȸ�� ����� �����մϴ�. */
	public void spin() {
		soloGameBoard.spin();
		update();
	}

	/** �����̵� ����� �����մϴ�. */
	public void moveLeft() {
		soloGameBoard.moveLeft();
		update();
	}

	/** ������ �̵� ����� �����մϴ�. */
	public void moveRight() {
		soloGameBoard.moveRight();
		update();
	}

	/** �Ʒ��̵� ����� �����մϴ�. */
	public void moveDown() {
		soloGameBoard.moveDown();
		update();
	}

	/** �ٷγ��� ����� �����մϴ�. */
	public void fastDown() {
		soloGameBoard.fastDown();
		update();
	}

	/** Game �� �Ͻ����� �մϴ�. */
	public void pause() {
		soloGameBoard.pause();
		totalFrame.showPausePanel();
	}

	/** Game �� �簳 �մϴ�. */
	public void resume() {
		soloGameBoard.resume();
		totalFrame.showSoloGamePanel();
		update();
	}

	/** Game �� ����� �մϴ�. */
	public void restart() {
		soloGameBoard.restart();
		totalFrame.showSoloGamePanel();
		update();
	}

	/** �������� ���ư��ϴ�. */
	public void goMain() {
		totalFrame.showMainPanel();
	}

	/** SoloGame Over�ϸ� ȣ��˴ϴ�. */
	public void soloGameOver() {
		totalFrame.soloGameOver();
	}

}
