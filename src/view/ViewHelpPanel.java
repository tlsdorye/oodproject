package view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

/**
 * 이 ViewHelpPanel 클래스는 Help화면의 Panel을 구성하고 보여주는 클래스입니다.
 * @author 이은경
 */
public class ViewHelpPanel extends JPanel {
	/** ViewTotalFrame Type의 변수 입니다. */
	private ViewTotalFrame totalFrame;
	/** JButton Type의 변수 입니다. */
	private JButton controlMethodbt, profilebt, backbt;
	/** images에 저장된 이미지 사용할 변수 입니다. */
	private ImageIcon image = new ImageIcon("images/Basic.png");
	/** image를 반환할 변수 입니다. */
	private Image im = image.getImage();
	/** 폰트 색상을 지정할 변수 입니다. */
	private Color setFontColor = new Color(39, 244, 82);

	/** 
	 * ViewHelpPanel을 생성합니다.
	 * @param totalFrame - 명령을 전달받을 totalFrame 입니다.
	 */
	public ViewHelpPanel(ViewTotalFrame totalFrame) {
		this.totalFrame = totalFrame;
		init();
	}

	/** 초기값 설정을 합니다. */
	public void init() {
		addButton();
		addListener();
		setLayout(null);
	}

	/** 스윙 컴포넌트가 자신의 모양을 그립니다. */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(im, 0, 0, getWidth(), getHeight(), this);
	}

	/** Button을 추가합니다. */
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

	/** Button에 MouseListener를 추가합니다. */
	public void addListener() {
		controlMethodbt.addMouseListener(makeListener());
		profilebt.addMouseListener(makeListener());
		backbt.addMouseListener(makeListener());
	}

	/**
	 * MouseLister를 만듭니다.
	 * @return 이 MouseListener 를 반환합니다.
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

	/** KeyGuidePanel을 보여줍니다. */
	public void showKeyGuide() {
		totalFrame.showControlMethodPanel();
	}

	/** ProfilePanel을 보여줍니다. */
	public void showProfile() {
		totalFrame.showProfile();
	}

	/** MainPanel을 보여줍니다. */
	public void showMainPanel() {
		totalFrame.showMainPanel();
	}
}
