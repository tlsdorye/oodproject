package model;

import java.awt.*;
import java.util.*;

import controller.Controller;

/**
 * �� Class�� Solo Game Board, ���� Block, ���� Block ���� ������ Ŭ�����Դϴ�. Controller ���� �����
 * �ްų�, �����մϴ�.
 * 
 * @author �Ž���
 *
 */
public class GameBoardSolo implements Runnable {

	/** GameBoard�� Row �� ��Ÿ�� �����Դϴ�. */
	public static final int ROWS = 22;
	/** GameBoard�� Column �� ��Ÿ�� �����Դϴ�. */
	public static final int COLS = 10;
	/** �� Block�� Size�� ��Ÿ�� �����Դϴ�. */
	public static final int BLOCK_SIZE = 30;
	/** Block�� �ִ� ������ ��Ÿ�� �����Դϴ�. */
	public static final int BLOCK_MAX_NUM = 4;
	/** ����� �ްų� ������ Controller Type �����Դϴ�. */
	private Controller controller;
	/** ���� Block�� ������ �����Դϴ�. */
	private Block currentBlock;
	/** ���� Block�� ������ �����Դϴ�. */
	private Block nextBlock;
	/** GameBoard�� ������ 2�����迭�Դϴ�. */
	private int[][] Board;
	/** Board�� ���� �ӽ÷� ������ �����Դϴ�. */
	private int[][] tempBoard;
	/** ���� ���� ȭ�鿡 ǥ������ ���� �Դϴ�. */
	private int[][] NextBlockBoard;
	/** ������ ������ �����Դϴ�. */
	private int score;
	/** Level�� ������ �����Դϴ�. */
	private int level;
	/** Start�� ���¸� ������ �����Դϴ�. */
	private boolean start;
	/** Start Time�� ������ �����Դϴ�. */
	private long startTime;
	/** End Time �� ������ �����Դϴ�. */
	private long endTime;
	/** Play Time �� ������ �����Դϴ�. */
	private double playTime;
	/** Pause�� ������ �ð�, �� Pause�� �ð��� ������ �����Դϴ�. */
	private long startPauseTime, pauseTime;

	/**
	 * GameBoardSolo �� �����մϴ�.
	 * 
	 * @param controller
	 *            - ����� ������ Controller �Դϴ�.
	 */
	public GameBoardSolo(Controller controller) {
		this.controller = controller;
		initGameBoard();
	}

	/** GameBoard �� �ʱ�ȭ �մϴ�. */
	public void initGameBoard() { // ���Ӻ��� �ʱ���� ����
		start = true;
		score = 0;
		level = 1;
		Board = new int[ROWS][COLS];
		tempBoard = new int[ROWS][COLS];
		for (int i = 0; i < Board.length; i++) {
			for (int j = 0; j < Board[i].length; j++) {
				Board[i][j] = -1;
				tempBoard[i][j] = -1;
			}
		}
		setCurrentBlock();
	}

	/** Solo Game �� �����մϴ�. */
	public void startSoloGame() {
		Thread t = new Thread(this);
		t.start();
		setStartTime();
	}

	/**
	 * �������̽� Runnable�� �����ϰ��ִ� ��ü�� ����� thread�� �ۼ��ϸ�, thread�� �⵿�ϸ� , ��ü�� run �޼ҵ尡 �� ����
	 * ���� thread�� �ҷ����ϴ�.
	 */
	@Override
	public void run() {
		while (true) {
			if (start)
				drop();
			setLevel();
			sleep();
		}
	}

	/** Block �� �� ĭ ����Ʈ���ϴ�. */
	public void drop() {
		currentBlock.drop();
	}

	/** Level �� �����մϴ�. */
	public void setLevel() {
		if (level - 1 < score / 50)
			if (level < 10)
				level++;
	}

	/** Thread�� ���� ������ ������� �����մϴ�. */
	private void sleep() { // ���̵� speed ����
		int speed = 0;
		if (level == 1)
			speed = 1000;
		if (level == 2)
			speed = 810;
		if (level == 3)
			speed = 720;
		if (level == 4)
			speed = 630;
		if (level == 5)
			speed = 540;
		if (level == 6)
			speed = 450;
		if (level == 7)
			speed = 360;
		if (level == 8)
			speed = 270;
		if (level == 9)
			speed = 180;
		if (level == 10)
			speed = 90;
		try {
			Thread.sleep(speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Block �� �������� �����մϴ�.
	 * 
	 * @return �������� ������ Block �Դϴ�.
	 */
	public Block createRandomBlock() {
		Random r = new Random();
		Block block = null;
		int rNum = r.nextInt(7);
		switch (rNum) {
		case 0:
			block = new BlockS(this);
			break;
		case 1:
			block = new BlockZ(this);
			break;
		case 2:
			block = new BlockI(this);
			break;
		case 3:
			block = new BlockL(this);
			break;
		case 4:
			block = new BlockJ(this);
			break;
		case 5:
			block = new BlockT(this);
			break;
		case 6:
			block = new BlockO(this);
			break;
		default:
			block = new BlockS(this);
			break;
		}
		return block;
	}

	/** ���� Block�� �����մϴ�. */
	public void setNextBlock() {
		nextBlock = createRandomBlock();
		NextBlockBoard = new int[BLOCK_MAX_NUM][BLOCK_MAX_NUM];
		for (int i = 0; i < nextBlock.coord.length; i++)
			NextBlockBoard[nextBlock.coord[i].getX() + 2][nextBlock.coord[i].getY() + 1] = 2;
	}

	/** ���� Block�� �����մϴ�. */
	public void setCurrentBlock() {
		if (nextBlock == null)
			setNextBlock();
		currentBlock = nextBlock;
		setNextBlock();
	}

	/** Block���� ȸ������� �����ϴ�. */
	public void spin() {
		currentBlock.performSpin();
	}

	/** Block���� ���� �̵������ �����ϴ�. */
	public void moveLeft() {
		currentBlock.moveLeft();
	}

	/** Block ���� ������ �̵������ �����ϴ�. */
	public void moveRight() {
		currentBlock.moveRight();
	}

	/** Block���� �Ʒ��� �̵������ �����ϴ�. */
	public void moveDown() {
		currentBlock.moveDown();
	}

	/** Block���� �ٷζ���Ʈ���� ����� �����ϴ�. */
	public void fastDown() {
		currentBlock.fastDown();
	}

	/** Controller�� update �޼ҵ带 �����մϴ�. */
	public void update() {
		controller.update();
	}

	/** Game �� Pause �մϴ�. */
	public void pause() {
		start = false;
		startPauseTime = System.nanoTime();
	}

	/** Game�� �簳�մϴ�. */
	public void resume() {
		start = true;
		pauseTime = System.nanoTime() - startPauseTime;
	}

	/** Game�� ������մϴ�. */
	public void restart() {
		initGameBoard();
		setStartTime();
	}

	/**
	 * GameBoard ȭ���� �׸��ϴ�.
	 * 
	 * @param g
	 *            - Controller���� ���޹��� Graphcis g �Դϴ�.
	 */
	public void draw(Graphics g) {
		drawBoard(g);
		drawNextBlock(g);
		drawScore(g);
		drawLevel(g);
		drawPlayTime(g);
	}

	/**
	 * GameBoard�� Board �κ��� �׸��ϴ�.
	 * 
	 * @param g
	 *            - draw���� ���޹��� Graphics g �Դϴ�.
	 */
	public void drawBoard(Graphics g) {

		for (int i = 2; i < Board.length; i++) {
			for (int j = 0; j < Board[i].length; j++) {
				int color = Board[i][j];
				switch (color) {
				case 0:
					g.setColor(new Color(224, 102, 245));
					break;
				case 1:
					g.setColor(new Color(244, 217, 245));
					break;
				case 2:
					g.setColor(new Color(244,36,51));
					break;
				case 3:
					g.setColor(new Color(36, 244, 0));
					break;
				case 4:
					g.setColor(new Color(0, 92, 244));
					break;
				case 5:
					g.setColor(new Color(0, 244, 235));
					break;
				case 6:
					g.setColor(new Color(245, 180, 0));
					break;

				default:
					if (j % 2 == 0)
						g.setColor(new Color(66, 66, 66, 80));
					else
						g.setColor(new Color(66, 66, 66, 140));
					break;
				}

				g.fillRoundRect(370 + (j) * BLOCK_SIZE, 10 + (i - 2) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, 5, 5);
				g.setColor(new Color(66, 66, 66, 180));
				g.drawRoundRect(370 + (j) * BLOCK_SIZE, 10 + (i - 2) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, 5, 5);
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(6));
				g2.drawRoundRect(365, 5, BLOCK_SIZE * 10 + 10, BLOCK_SIZE * 20 + 10, 5, 5);
				g2.setStroke(new BasicStroke(2));
			}
		}
	}

	/**
	 * GameBoard�� ���� Block�� �׸��ϴ�.
	 * 
	 * @param g
	 *            - draw���� ���޹��� Graphics g �Դϴ�.
	 */
	public void drawNextBlock(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("SVN-Block", Font.BOLD, 30));
		g2.drawString("Next", 490 + 10 * BLOCK_SIZE, 65);
		for (int i = 0; i < NextBlockBoard.length; i++) {
			for (int j = 0; j < NextBlockBoard[i].length; j++) {
				int position = NextBlockBoard[i][j];
				if (position == 2) {
					g2.setColor(nextBlock.getColor());
				} else {
					g2.setColor(new Color(66, 66, 66, 100));
				}
				g2.fillRoundRect(490 + 10 * BLOCK_SIZE + j * BLOCK_SIZE, 70 + i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, 5,
						5);
				g2.setColor(new Color(66, 66, 66, 180));
				g2.drawRoundRect(490 + 10 * BLOCK_SIZE + j * BLOCK_SIZE, 70 + i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, 5,
						5);
			}
		}
	}

	/**
	 * GameBoard�� Score�� �׸��ϴ�.
	 * 
	 * @param g
	 *            - draw���� ���޹��� Graphics g �Դϴ�.
	 */
	public void drawScore(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("SVN-Block", Font.BOLD, 30));
		g2.drawString("Score", 490 + 10 * BLOCK_SIZE, 245);
		g2.setColor(new Color(66, 66, 66, 210));
		g2.drawRoundRect(490 + 10 * BLOCK_SIZE, 250, BLOCK_SIZE * 4, BLOCK_SIZE * 1, 5, 5);
		g2.setFont(new Font("Digital-7", Font.LAYOUT_RIGHT_TO_LEFT, 30));
		g2.setColor(Color.GREEN);
		g2.drawString(score + "", 490 + 10 * BLOCK_SIZE, 275);
	}

	/**
	 * GameBoard�� Level�� �׸��ϴ�.
	 * 
	 * @param g
	 *            - draw���� ���޹��� Graphics g �Դϴ�.
	 */
	public void drawLevel(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("SVN-Block", Font.BOLD, 30));
		g2.drawString("Level", 490 + 10 * BLOCK_SIZE, 340);
		g2.setColor(new Color(66, 66, 66, 180));
		g2.drawRoundRect(490 + 10 * BLOCK_SIZE, 345, BLOCK_SIZE * 4, BLOCK_SIZE * 1, 5, 5);
		g2.setFont(new Font("Digital-7", Font.LAYOUT_RIGHT_TO_LEFT, 30));
		g2.setColor(Color.GREEN);
		g2.drawString(level + "", 490 + 10 * BLOCK_SIZE, 370);
	}

	/**
	 * GameBoard�� PlayTime�� �׸��ϴ�.
	 * 
	 * @param g
	 *            - draw���� ���޹��� Graphics g �Դϴ�.
	 */
	public void drawPlayTime(Graphics g) {
		endTime = System.nanoTime();
		playTime = (endTime - startTime - pauseTime) / 1000000000;
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("SVN-Block", Font.BOLD, 30));
		g2.drawString("PlayTime", 490 + 10 * BLOCK_SIZE, 430);
		g2.setColor(new Color(66, 66, 66, 180));
		g2.drawRoundRect(490 + 10 * BLOCK_SIZE, 436, BLOCK_SIZE * 4, BLOCK_SIZE * 1, 5, 5);
		g2.setFont(new Font("Digital-7", Font.LAYOUT_RIGHT_TO_LEFT, 30));
		g2.setColor(Color.GREEN);
		g2.drawString(Double.toString(playTime) + "��", 490 + 10 * BLOCK_SIZE, 462);
	}

	/** Start Time �� �����մϴ�. */
	public void setStartTime() {
		startTime = System.nanoTime();
		pauseTime = 0;
	}

	/** GameBoard�� AI Ranking �� �׸��ϴ�. */
	public void drawAIRanking(Graphics g) {

	}

	/** Block�� Board�� ������Ű��, ���� Block�� �����մϴ�. */
	public void fixedAndSetNextBlock() {
		clear();
		for (int i = 0; i < Board.length; i++)
			for (int j = 0; j < Board[i].length; j++)
				tempBoard[i][j] = Board[i][j];
		if (isGameOver())
			GameOver();
		setCurrentBlock();
	}

	/** Game Over�� ȣ��˴ϴ�. */
	public void GameOver() {
		start = false;
		controller.soloGameOver();
	}

	/**
	 * Game Over ����� Ȯ���մϴ�.
	 * 
	 * @return Game Over �� ��ٸ� true�� , �ƴ϶�� false �� ��ȯ�մϴ�.
	 */
	public boolean isGameOver() {
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < Board[i].length; j++)
				if (Board[i][j] != -1)
					return true;
		return false;
	}

	/**
	 * Board���� Block��ġ�� �ٲߴϴ�.
	 * 
	 * @param position
	 *            - Block�� ��ġ�Դϴ�.
	 * @param value
	 *            - Block�� ���� �Դϴ�.
	 */
	public void changePoint(Point position, int value) {
		Board[position.getX()][position.getY()] = value;
	}

	/**
	 * Block�� �浹�ϴ��� Ȯ���մϴ�.
	 * 
	 * @param position
	 *            - Block �� ��ġ�Դϴ�.
	 * @return - �浹�Ѵٸ� true��, �浹�����ʴ´ٸ� false �� �����մϴ�.
	 */
	public boolean isCollision(Point position) {
		if (position.getX() > ROWS - 1)
			return true;
		if (position.getY() < 0)
			return true;
		if (position.getY() > COLS - 1)
			return true;
		if (Board[position.getX()][position.getY()] != -1)
			return true;
		return false;
	}

	/**
	 * ȸ���� �� �浹�ϴ��� Ȯ���մϴ�.
	 * 
	 * @param position
	 *            - Block �� ��ġ�Դϴ�.
	 * @return ȸ���� �� �浹�Ѵٸ� true, �浹���� �ʴ´ٸ� false �� �����մϴ�.
	 */
	public boolean isCollistionSpin(Point position) {
		if (position.getX() > ROWS - 1)
			return true;
		if (position.getX() < 0)
			return true;
		if (position.getY() < 0)
			return true;
		if (position.getY() > COLS - 1)
			return true;
		if (Board[position.getX()][position.getY()] != -1)
			return true;
		return false;
	}

	/** tempBoard�� ����� ������ Board���� �ǵ����ϴ�. */
	public void revertMatrix() {
		for (int i = 0; i < Board.length; i++)
			for (int j = 0; j < Board[i].length; j++)
				Board[i][j] = tempBoard[i][j];
	}

	/** ���������� �ϼ���Line�� �����ϰ�, ������ �Ʒ��� �����ϴ�. */
	public void clear() {
		for (int i = 0; i < Board.length; i++) {
			if (isFullRow(i)) {
				deleteLine(i);
				score += 10;
			}
		}
		update();
	}

	/**
	 * Line�� �����մϴ�.
	 * 
	 * @param line
	 *            - ������ line�� ��ġ �Դϴ�.
	 */
	public void deleteLine(int line) {
		int[][] temp = new int[line][COLS];
		for (int i = 0; i < line; i++)
			for (int j = 0; j < Board[i].length; j++)
				temp[i][j] = Board[i][j];
		/////////////////////////////////////////////
		for (int i = 0; i < temp.length; i++)
			for (int j = 0; j < temp[i].length; j++)
				Board[i + 1][j] = temp[i][j];
	}

	/**
	 * Line�� �ϼ��Ǿ����� Ȯ���մϴ�.
	 * 
	 * @param line
	 *            - �ϼ��Ǿ����� Ȯ���� line �Դϴ�.
	 * @return Line�� �ϼ��Ǿ��ٸ� true, �ƴ϶�� false�� �����մϴ�.
	 */
	public boolean isFullRow(int line) {
		for (int i = 0; i < Board[line].length; i++)
			if (Board[line][i] == -1)
				return false;
		return true;
	}

}
