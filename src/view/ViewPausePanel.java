package view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;

/**
 * �� ViewPausePanel�� Game���� ���� esc�� ������ �� Pauseȭ���� Panel�� �����ϰ� �����ִ� Ŭ�����Դϴ�.
 * 
 * @author �۹μ�
 * 
 */
public class ViewPausePanel extends JPanel implements IViewPanel {
	/** ViewTotalFrame Type�� ���� �Դϴ� */
	private ViewTotalFrame totalFrame;
	/** JButton Type�� ���� �Դϴ� */
	private JButton resumebt, restartbt, goMainbt;
	/** ��Ʈ ������ ������ ���� �Դϴ� */
	private Color setFontColor = new Color(244, 29, 0);

	/**
	 * ViewPausePanel�� �����մϴ�.
	 * @param totalFrame - ����� ���޹��� totalFrame �Դϴ�.
	 */
	public ViewPausePanel(ViewTotalFrame totalFrame) {
		this.totalFrame = totalFrame;
		init();

	}

	/** �ʱⰪ ������ �մϴ�. */
	public void init() {
		setBackground(new Color(90, 90, 90, 255)); // ����
		addButton();
		addListener();
		setLayout(null);

	}

	/** ���� ������Ʈ�� �ڽ��� ����� �׸��ϴ�. */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		totalFrame.draw(g);
	}

	/** Button�� �⺻ ������ �����մϴ�. */
	public void addButton() {

		resumebt = new JButton("Resume");
		resumebt.setFont(new Font("Forte", Font.PLAIN, 45));
		resumebt.setBounds(297, 200, 450, 60);
		resumebt.setBorderPainted(false);
		resumebt.setForeground(setFontColor);
		resumebt.setFocusable(false);
		resumebt.setContentAreaFilled(false);

		restartbt = new JButton("Restart");
		restartbt.setFont(new Font("Forte", Font.PLAIN, 45));
		restartbt.setBounds(297, 290, 450, 60);
		restartbt.setBorderPainted(false);
		restartbt.setForeground(setFontColor);
		restartbt.setFocusable(false);
		restartbt.setContentAreaFilled(false);

		goMainbt = new JButton("Go Main");
		goMainbt.setFont(new Font("Forte", Font.PLAIN, 45));
		goMainbt.setBounds(297, 380, 450, 60);
		goMainbt.setBorderPainted(false);
		goMainbt.setForeground(setFontColor);
		goMainbt.setFocusable(false);
		goMainbt.setContentAreaFilled(false);

		this.add(resumebt);
		this.add(restartbt);
		this.add(goMainbt);
	}

	/** Button�� MouseListener�� �����մϴ�. */
	public void addListener() {
		resumebt.addMouseListener(makeMouseListener());
		restartbt.addMouseListener(makeMouseListener());
		goMainbt.addMouseListener(makeMouseListener());

	}

	/** MouseLister�� �����մϴ�. */
	public MouseListener makeMouseListener() {
		return new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() == resumebt) {
					resumebt.setFont(new Font("Forte", Font.PLAIN, 55));

				}
				if (e.getSource() == restartbt) {
					restartbt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
				if (e.getSource() == goMainbt) {
					goMainbt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
			}

			public void mouseExited(MouseEvent e) {
				if (e.getSource() == resumebt) {
					resumebt.setFont(new Font("Forte", Font.PLAIN, 45));

				}
				if (e.getSource() == restartbt) {
					restartbt.setFont(new Font("Forte", Font.PLAIN, 45));
				}
				if (e.getSource() == goMainbt) {
					goMainbt.setFont(new Font("Forte", Font.PLAIN, 45));
				}
			}

			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == resumebt) {
					resume();
				}
				if (e.getSource() == restartbt) {
					restart();
				}
				if (e.getSource() == goMainbt) {
					goMain();
				}
			}
		};

	}

	/** ����ϱ� ����� �����մϴ�. */
	public void resume() {
		totalFrame.resume();
	}

	/** ����� ����� �����մϴ�. */
	public void restart() {
		totalFrame.restart();
	}

	/** Mainȭ������ �̵� ����� �����մϴ�. */
	public void goMain() {
		totalFrame.goMain();
	}
}
