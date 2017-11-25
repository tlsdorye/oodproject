package model;

import java.awt.*;
import java.util.*;

import controller.Controller;

/**
 * 이 Class는 Solo Game Board, 현재 Block, 다음 Block 등이 구현된 클래스입니다. Controller 에게 명령을
 * 받거나, 전달합니다.
 * 
 * @author 신승현
 *
 */
public class GameBoardSolo implements Runnable {

	/** GameBoard의 Row 를 나타낼 변수입니다. */
	public static final int ROWS = 22;
	/** GameBoard의 Column 을 나타낼 변수입니다. */
	public static final int COLS = 10;
	/** 한 Block의 Size를 나타낼 변수입니다. */
	public static final int BLOCK_SIZE = 30;
	/** Block의 최대 개수를 나타낼 변수입니다. */
	public static final int BLOCK_MAX_NUM = 4;
	/** 명령을 받거나 전달할 Controller Type 변수입니다. */
	private Controller controller;
	/** 현재 Block을 저장할 변수입니다. */
	private Block currentBlock;
	/** 다음 Block을 저장할 변수입니다. */
	private Block nextBlock;
	/** GameBoard를 저장할 2차원배열입니다. */
	private int[][] Board;
	/** Board의 값을 임시로 저장할 변수입니다. */
	private int[][] tempBoard;
	/** 다음 블럭을 화면에 표시해줄 영역 입니다. */
	private int[][] NextBlockBoard;
	/** 점수를 저장할 변수입니다. */
	private int score;
	/** Level을 저장할 변수입니다. */
	private int level;
	/** Start의 상태를 저장할 변수입니다. */
	private boolean start;
	/** Start Time을 저장할 변수입니다. */
	private long startTime;
	/** End Time 을 저장할 변수입니다. */
	private long endTime;
	/** Play Time 을 저장할 변수입니다. */
	private double playTime;
	/** Pause를 시작한 시간, 총 Pause된 시간을 저장할 변수입니다. */
	private long startPauseTime, pauseTime;

	/**
	 * GameBoardSolo 를 생성합니다.
	 * 
	 * @param controller
	 *            - 명령을 전달할 Controller 입니다.
	 */
	public GameBoardSolo(Controller controller) {
		this.controller = controller;
		initGameBoard();
	}

	/** GameBoard 를 초기화 합니다. */
	public void initGameBoard() { // 게임보드 초기상태 설정
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

	/** Solo Game 을 시작합니다. */
	public void startSoloGame() {
		Thread t = new Thread(this);
		t.start();
		setStartTime();
	}

	/**
	 * 인터페이스 Runnable를 구현하고있는 객체를 사용해 thread를 작성하면, thread를 기동하면 , 객체의 run 메소드가 그 개별
	 * 실행 thread로 불려갑니다.
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

	/** Block 이 한 칸 떨어트립니다. */
	public void drop() {
		currentBlock.drop();
	}

	/** Level 을 조정합니다. */
	public void setLevel() {
		if (level - 1 < score / 50)
			if (level < 10)
				level++;
	}

	/** Thread가 몇초 단위로 실행될지 설정합니다. */
	private void sleep() { // 난이도 speed 설정
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
	 * Block 을 랜덤으로 생성합니다.
	 * 
	 * @return 랜덤으로 생성된 Block 입니다.
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

	/** 다음 Block을 설정합니다. */
	public void setNextBlock() {
		nextBlock = createRandomBlock();
		NextBlockBoard = new int[BLOCK_MAX_NUM][BLOCK_MAX_NUM];
		for (int i = 0; i < nextBlock.coord.length; i++)
			NextBlockBoard[nextBlock.coord[i].getX() + 2][nextBlock.coord[i].getY() + 1] = 2;
	}

	/** 현재 Block을 설정합니다. */
	public void setCurrentBlock() {
		if (nextBlock == null)
			setNextBlock();
		currentBlock = nextBlock;
		setNextBlock();
	}

	/** Block에게 회전명령을 내립니다. */
	public void spin() {
		currentBlock.performSpin();
	}

	/** Block에게 왼쪽 이동명령을 내립니다. */
	public void moveLeft() {
		currentBlock.moveLeft();
	}

	/** Block 에게 오른쪽 이동명령을 내립니다. */
	public void moveRight() {
		currentBlock.moveRight();
	}

	/** Block에게 아래로 이동명령을 내립니다. */
	public void moveDown() {
		currentBlock.moveDown();
	}

	/** Block에게 바로떨어트리는 명령을 내립니다. */
	public void fastDown() {
		currentBlock.fastDown();
	}

	/** Controller의 update 메소드를 실행합니다. */
	public void update() {
		controller.update();
	}

	/** Game 을 Pause 합니다. */
	public void pause() {
		start = false;
		startPauseTime = System.nanoTime();
	}

	/** Game을 재개합니다. */
	public void resume() {
		start = true;
		pauseTime = System.nanoTime() - startPauseTime;
	}

	/** Game을 재시작합니다. */
	public void restart() {
		initGameBoard();
		setStartTime();
	}

	/**
	 * GameBoard 화면을 그립니다.
	 * 
	 * @param g
	 *            - Controller에게 전달받은 Graphcis g 입니다.
	 */
	public void draw(Graphics g) {
		drawBoard(g);
		drawNextBlock(g);
		drawScore(g);
		drawLevel(g);
		drawPlayTime(g);
	}

	/**
	 * GameBoard의 Board 부분을 그립니다.
	 * 
	 * @param g
	 *            - draw에게 전달받은 Graphics g 입니다.
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
	 * GameBoard의 다음 Block을 그립니다.
	 * 
	 * @param g
	 *            - draw에게 전달받은 Graphics g 입니다.
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
	 * GameBoard의 Score을 그립니다.
	 * 
	 * @param g
	 *            - draw에게 전달받은 Graphics g 입니다.
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
	 * GameBoard의 Level을 그립니다.
	 * 
	 * @param g
	 *            - draw에게 전달받은 Graphics g 입니다.
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
	 * GameBoard의 PlayTime을 그립니다.
	 * 
	 * @param g
	 *            - draw에게 전달받은 Graphics g 입니다.
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
		g2.drawString(Double.toString(playTime) + "초", 490 + 10 * BLOCK_SIZE, 462);
	}

	/** Start Time 을 설정합니다. */
	public void setStartTime() {
		startTime = System.nanoTime();
		pauseTime = 0;
	}

	/** GameBoard의 AI Ranking 을 그립니다. */
	public void drawAIRanking(Graphics g) {

	}

	/** Block을 Board에 고정시키고, 다음 Block을 설정합니다. */
	public void fixedAndSetNextBlock() {
		clear();
		for (int i = 0; i < Board.length; i++)
			for (int j = 0; j < Board[i].length; j++)
				tempBoard[i][j] = Board[i][j];
		if (isGameOver())
			GameOver();
		setCurrentBlock();
	}

	/** Game Over시 호출됩니다. */
	public void GameOver() {
		start = false;
		controller.soloGameOver();
	}

	/**
	 * Game Over 됬는지 확인합니다.
	 * 
	 * @return Game Over 가 됬다면 true를 , 아니라면 false 를 반환합니다.
	 */
	public boolean isGameOver() {
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < Board[i].length; j++)
				if (Board[i][j] != -1)
					return true;
		return false;
	}

	/**
	 * Board위의 Block위치를 바꿉니다.
	 * 
	 * @param position
	 *            - Block의 위치입니다.
	 * @param value
	 *            - Block의 종류 입니다.
	 */
	public void changePoint(Point position, int value) {
		Board[position.getX()][position.getY()] = value;
	}

	/**
	 * Block이 충돌하는지 확인합니다.
	 * 
	 * @param position
	 *            - Block 의 위치입니다.
	 * @return - 충돌한다면 true를, 충돌하지않는다면 false 를 리턴합니다.
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
	 * 회전할 때 충돌하는지 확인합니다.
	 * 
	 * @param position
	 *            - Block 의 위치입니다.
	 * @return 회전할 때 충돌한다면 true, 충돌하지 않는다면 false 를 리턴합니다.
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

	/** tempBoard에 저장된 원래의 Board값을 되돌립니다. */
	public void revertMatrix() {
		for (int i = 0; i < Board.length; i++)
			for (int j = 0; j < Board[i].length; j++)
				Board[i][j] = tempBoard[i][j];
	}

	/** 위에서부터 완성된Line을 삭제하고, 블럭들을 아래로 내립니다. */
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
	 * Line을 삭제합니다.
	 * 
	 * @param line
	 *            - 삭제할 line의 위치 입니다.
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
	 * Line이 완성되었는지 확인합니다.
	 * 
	 * @param line
	 *            - 완성되었는지 확인할 line 입니다.
	 * @return Line이 완성되었다면 true, 아니라면 false를 리턴합니다.
	 */
	public boolean isFullRow(int line) {
		for (int i = 0; i < Board[line].length; i++)
			if (Board[line][i] == -1)
				return false;
		return true;
	}

}
