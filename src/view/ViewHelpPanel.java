package view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

/**
 * �� ViewHelpPanel Ŭ������ Helpȭ���� Panel�� �����ϰ� �����ִ� Ŭ�����Դϴ�.
 * @author ������
 */
public class ViewHelpPanel extends JPanel {
	/** ViewTotalFrame Type�� ���� �Դϴ�. */
	private ViewTotalFrame totalFrame;
	/** JButton Type�� ���� �Դϴ�. */
	private JButton controlMethodbt, profilebt, backbt;
	/** images�� ����� �̹��� ����� ���� �Դϴ�. */
	private ImageIcon image = new ImageIcon("images/Basic.png");
	/** image�� ��ȯ�� ���� �Դϴ�. */
	private Image im = image.getImage();
	/** ��Ʈ ������ ������ ���� �Դϴ�. */
	private Color setFontColor = new Color(39, 244, 82);

	/** 
	 * ViewHelpPanel�� �����մϴ�.
	 * @param totalFrame - ����� ���޹��� totalFrame �Դϴ�.
	 */
	public ViewHelpPanel(ViewTotalFrame totalFrame) {
		this.totalFrame = totalFrame;
		init();
	}

	/** �ʱⰪ ������ �մϴ�. */
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

	/** Button�� �߰��մϴ�. */
	public void addButton() {

		controlMethodbt = new JButton("Control Method");
		controlMethodbt.setFont(new Font("Forte", Font.PLAIN, 45));
		controlMethodbt.setBounds(297, 290, 450, 60);
		controlMethodbt.setBorderPainted(false);
		controlMethodbt.setForeground(setFontColor);
		controlMethodbt.setFocusable(false);
		controlMethodbt.setContentAreaFilled(false);

		profilebt = new JButton("Profile");
		profilebt.setFont(new Font("Forte", Font.PLAIN, 45));
		profilebt.setBounds(297, 380, 450, 60);
		profilebt.setBorderPainted(false);
		profilebt.setForeground(setFontColor);
		profilebt.setFocusable(false);
		profilebt.setContentAreaFilled(false);

		backbt = new JButton("Back");
		backbt.setFont(new Font("Forte", Font.PLAIN, 45));
		backbt.setBounds(297, 470, 450, 60);
		backbt.setBorderPainted(false);
		backbt.setForeground(setFontColor);
		backbt.setFocusable(false);
		backbt.setContentAreaFilled(false);

		this.add(controlMethodbt);
		this.add(profilebt);
		this.add(backbt);
	}

	/** Button�� MouseListener�� �߰��մϴ�. */
	public void addListener() {
		controlMethodbt.addMouseListener(makeListener());
		profilebt.addMouseListener(makeListener());
		backbt.addMouseListener(makeListener());
	}

	/**
	 * MouseLister�� ����ϴ�.
	 * @return �� MouseListener �� ��ȯ�մϴ�.
	 */
	public MouseListener makeListener() {
		return new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() == controlMethodbt) {
					controlMethodbt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
				if (e.getSource() == profilebt) {
					profilebt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
				if (e.getSource() == backbt) {
					backbt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
			}

			public void mouseExited(MouseEvent e) {
				if (e.getSource() == controlMethodbt) {
					controlMethodbt.setFont(new Font("Forte", Font.PLAIN, 45));
				}
				if (e.getSource() == profilebt) {
					profilebt.setFont(new Font("Forte", Font.PLAIN, 45));
				}
				if (e.getSource() == backbt) {
					backbt.setFont(new Font("Forte", Font.PLAIN, 45));
				}
			}

			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == controlMethodbt) {
					showKeyGuide();
				}
				if (e.getSource() == profilebt) {
					showProfile();
				}
				if (e.getSource() == backbt) {
					showMainPanel();
				}
			}

		};
	}

	/** KeyGuidePanel�� �����ݴϴ�. */
	public void showKeyGuide() {
		totalFrame.showControlMethodPanel();
	}

	/** ProfilePanel�� �����ݴϴ�. */
	public void showProfile() {
		totalFrame.showProfile();
	}

	/** MainPanel�� �����ݴϴ�. */
	public void showMainPanel() {
		totalFrame.showMainPanel();
	}
}
