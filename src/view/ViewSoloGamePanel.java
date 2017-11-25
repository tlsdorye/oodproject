package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * �� ViewSoloGamePanel Ŭ������ SoloGameȭ���� Panel�� �����ϰ� �����ִ� Ŭ�����Դϴ�.
 * @author �۹μ�
 */
public class ViewSoloGamePanel extends JPanel {

	/** ViewTotalFrame Type�� ���� �Դϴ�. */
	private ViewTotalFrame totalFrame;

	/**
	 * ViewSoloGamePanel�� �����մϴ�.
	 * @param totalFrame - ����� ���޹��� totalFrame �Դϴ�.
	 */
	public ViewSoloGamePanel(ViewTotalFrame totalFrame) {
		this.totalFrame = totalFrame;
		init();
	}

	/** �ʱⰪ ������ �մϴ�. */
	public void init() {
		setLayout(null);
		setBackground(new Color(66, 66, 66, 160));
		setVisible(true);
	}

	/** ���� ������Ʈ�� �ڽ��� ����� �׸��ϴ�. */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		totalFrame.draw(g);
	}

	/** ȭ���� repaint �մϴ�. */
	public void update() {
		repaint();
	}

	/** Game�� �������� GameOver �޽����� ǥ�� �մϴ�. */
	public void gameOver() {
		Graphics2D g2 = (Graphics2D) getGraphics();
		g2.setFont(new Font("Chiller", Font.BOLD, 110));
		g2.setColor(new Color(250, 0, 0, 250));
		g2.drawString("Game Over", 320, 240);

	}
}
