package view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

/**
 * �� ViewProfilePanel�� Profileȭ���� Panel�� �����ϰ� �����ִ� Ŭ�����Դϴ�.
 * 
 * @author ������
 * 
 */
public class ViewProfilePanel extends JPanel implements IViewPanel {
	/** ViewTotalFrame Type�� ���� �Դϴ� */
	private ViewTotalFrame totalFrame;
	/** JButton Type�� ���� �Դϴ� */
	private JButton backbt;
	/** images�� ����� �̹��� ����� ���� �Դϴ� */
	private ImageIcon profile = new ImageIcon("images/profile.png");
	/** image�� ��ȯ�� ���� �Դϴ� */
	private Image img = profile.getImage();
	/** ��Ʈ ������ ������ ���� �Դϴ� */
	private Color setFontColor = new Color(244, 29, 0);

	/**
	 * ViewProfilePanel�� �����մϴ�.
	 * @param totalFrame - ����� ���޹��� totalFrame �Դϴ�.
	 */
	public ViewProfilePanel(ViewTotalFrame totalFrame) {
		this.setLayout(null);
		this.totalFrame = totalFrame;
		init();
	}

	/** ���� ������Ʈ�� �ڽ��� ����� �׸��ϴ�. */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	/** �ʱⰪ ������ �մϴ�. */
	public void init() {
		addButton();
		addListener();

	}

	/** Button�� �⺻ ������ �����մϴ�. */
	public void addButton() {
		backbt = new JButton("Back");
		backbt.setFont(new Font("Forte", Font.PLAIN, 45));
		backbt.setForeground(setFontColor); // ��ư�� ���ڻ�
		backbt.setBounds(297, 560, 450, 60);
		backbt.setBorderPainted(false);
		backbt.setFocusable(false);
		backbt.setContentAreaFilled(false);
		this.add(backbt);
	}

	/** Button�� MouseListener�� �����մϴ�. */
	public void addListener() {
		backbt.addMouseListener(makeMouseListener());
	}

	/** MouseLister�� �����մϴ�. */
	public MouseListener makeMouseListener() {

		return new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() == backbt) {
					backbt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
			}

			public void mouseExited(MouseEvent e) {
				if (e.getSource() == backbt) {
					backbt.setFont(new Font("Forte", Font.PLAIN, 45));
				}
			}

			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == backbt) {
					showHelpPanel();

				}
			}
		};
	}

	/** HelpPanel�� �����ݴϴ�. */
	public void showHelpPanel() {
		totalFrame.showHelpPanel();
	}

}
