package view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

/**
 * �� ViewKeyGuidePanel Ŭ������ KeyGuideȭ���� Panel�� �����ϰ� �����ִ� Ŭ�����Դϴ�. 
 * @author ������
 */
public class ViewKeyGuidePanel extends JPanel implements IViewPanel {
	/** ViewTotalFrame Type�� ���� �Դϴ�. */
	private ViewTotalFrame totalFrame;
	/** JLabel Type�� ���� �Դϴ�. */
	private JLabel viewKeyGuide;
	/** JButton Type�� ���� �Դϴ�. */
	private JButton backbt;
	/** images�� ����� �̹��� ����� ���� �Դϴ�. */
	private ImageIcon image = new ImageIcon("images/Ű����.png");
	/** image�� ��ȯ�� ���� �Դϴ�. */
	private Image im = image.getImage();
	/** ��Ʈ ������ ������ ���� �Դϴ�. */
	private Color setFontColor = new Color(244, 29, 0);

	/**
	 * ViewKeyGuidePanel�� �����մϴ�.
	 * @param totalFrame - ����� ���޹��� totalFrame �Դϴ�.
	 */
	public ViewKeyGuidePanel(ViewTotalFrame totalFrame) {
		this.totalFrame = totalFrame;
		viewKeyGuide = new JLabel("");
		backbt = new JButton("back");
		init();
	}

	/** �ʱⰪ ������ �մϴ�. */
	@Override
	public void init() {
		addLabel();
		addButton();
		addListener();
		setLayout(null);
	}

	/** ���� ������Ʈ�� �ڽ��� ����� �׸��ϴ�. */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(im, 0, 0, getWidth(), getHeight(), this);
	}

	/** Label�� �߰��մϴ�. */
	public void addLabel() {
		this.add(viewKeyGuide);
	}

	/** Button�� �⺻ ������ �����մϴ�. */
	@Override
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
	@Override
	public void addListener() {
		backbt.addMouseListener(makeMouseListener());
	}

	/** MouseLister�� �����մϴ�. */
	@Override
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