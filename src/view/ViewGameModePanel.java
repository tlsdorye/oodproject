package view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

/**
 * �� ViewGameModePanel�� GameModeȭ���� Panel�� �����ϰ� �����ִ� Ŭ�����Դϴ�.
 * @author ������
 */
public class ViewGameModePanel extends JPanel implements IViewPanel {
	/** ViewTotalFrame Type�� ���� �Դϴ� */
	private ViewTotalFrame totalFrame;
	/** JButton Type�� ���� �Դϴ� */
	private JButton soloGamebt, ZPGamebt, AIGamebt, backbt;
	/** images�� ����� �̹��� ����� ���� �Դϴ� */
	private ImageIcon image = new ImageIcon("images/Basic.png");
	/** image�� ��ȯ�� ���� �Դϴ� */
	private Image im = image.getImage();
	/** ��Ʈ ������ ������ ���� �Դϴ� */
	private Color setFontColor = new Color(244, 88, 232);

	/**
	 * ViewGameModePanel�� �����մϴ�.
	 * @param totalFrame - ����� ���޹��� totalFrame �Դϴ�.
	 */
	public ViewGameModePanel(ViewTotalFrame totalFrame) {
		this.totalFrame = totalFrame;
		init();
	}

	/** �ʱⰪ ������ �մϴ�. */
	@Override
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
	@Override
	public void addButton() {

		soloGamebt = new JButton("Solo Game");
		soloGamebt.setFont(new Font("Forte", Font.PLAIN, 45));
		soloGamebt.setBounds(297, 290, 450, 60);
		soloGamebt.setBorderPainted(false);
		soloGamebt.setForeground(setFontColor);
		soloGamebt.setFocusable(false);
		soloGamebt.setContentAreaFilled(false);

		ZPGamebt = new JButton("2P Game");
		ZPGamebt.setFont(new Font("Forte", Font.PLAIN, 45));
		ZPGamebt.setBounds(297, 380, 450, 60);
		ZPGamebt.setBorderPainted(false);
		ZPGamebt.setForeground(setFontColor);
		ZPGamebt.setFocusable(false);
		ZPGamebt.setContentAreaFilled(false);

		AIGamebt = new JButton("AI Game");
		AIGamebt.setFont(new Font("Forte", Font.PLAIN, 45));
		AIGamebt.setBounds(297, 470, 450, 60);
		AIGamebt.setBorderPainted(false);
		AIGamebt.setForeground(setFontColor);
		AIGamebt.setFocusable(false);
		AIGamebt.setContentAreaFilled(false);

		backbt = new JButton("Back");
		backbt.setFont(new Font("Forte", Font.PLAIN, 45));
		backbt.setBounds(297, 560, 450, 60);
		backbt.setBorderPainted(false);
		backbt.setForeground(setFontColor);
		backbt.setFocusable(false);
		backbt.setContentAreaFilled(false);

		this.add(soloGamebt);
		this.add(ZPGamebt);
		this.add(AIGamebt);
		this.add(backbt);
	}

	/** Button�� MouseListener�� �����մϴ�. */
	@Override
	public void addListener() {
		soloGamebt.addMouseListener(makeMouseListener());
		ZPGamebt.addMouseListener(makeMouseListener());
		AIGamebt.addMouseListener(makeMouseListener());
		backbt.addMouseListener(makeMouseListener());
	}

	/** MouseLister�� �����մϴ�. */
	@Override
	public MouseListener makeMouseListener() {
		return new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() == soloGamebt) {
					soloGamebt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
				if (e.getSource() == ZPGamebt) {
					ZPGamebt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
				if (e.getSource() == AIGamebt) {
					AIGamebt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
				if (e.getSource() == backbt) {
					backbt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
			}

			public void mouseExited(MouseEvent e) {
				if (e.getSource() == soloGamebt) {
					soloGamebt.setFont(new Font("Forte", Font.PLAIN, 45));
				}
				if (e.getSource() == ZPGamebt) {
					ZPGamebt.setFont(new Font("Forte", Font.PLAIN, 45));
				}
				if (e.getSource() == AIGamebt) {
					AIGamebt.setFont(new Font("Forte", Font.PLAIN, 45));
				}
				if (e.getSource() == backbt) {
					backbt.setFont(new Font("Forte", Font.PLAIN, 45));
				}
			}

			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == soloGamebt) {
					totalFrame.soloGameStart();
				}
				if (e.getSource() == ZPGamebt) {
					show2PGamePanel();
				}
				if (e.getSource() == AIGamebt) {
					showAIGamePanel();
				}
				if (e.getSource() == backbt) {
					showMainPanel();
				}
			}

		};
	}

	/** SoloGamePanel�� �����ݴϴ�. */
	public void showSoloGamePanel() {
		totalFrame.showSoloGamePanel();
	}

	/** 2PGamePanel�� �����ݴϴ�. */
	public void show2PGamePanel() {
		totalFrame.show2PGamePanel();
	}

	/** AIGamePanel�� �����ݴϴ�. */
	public void showAIGamePanel() {
		totalFrame.showAIGamePanel();
	}

	/** MainPanel�� �����ݴϴ�. */
	public void showMainPanel() {
		totalFrame.showMainPanel();
	}
}
