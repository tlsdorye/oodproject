package view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

/**
 * 이 ViewKeyGuidePanel 클래스는 KeyGuide화면의 Panel을 구성하고 보여주는 클래스입니다. 
 * @author 이은경
 */
public class ViewKeyGuidePanel extends JPanel implements IViewPanel {
	/** ViewTotalFrame Type의 변수 입니다. */
	private ViewTotalFrame totalFrame;
	/** JLabel Type의 변수 입니다. */
	private JLabel viewKeyGuide;
	/** JButton Type의 변수 입니다. */
	private JButton backbt;
	/** images에 저장된 이미지 사용할 변수 입니다. */
	private ImageIcon image = new ImageIcon("images/키설정.png");
	/** image를 반환할 변수 입니다. */
	private Image im = image.getImage();
	/** 폰트 색상을 지정할 변수 입니다. */
	private Color setFontColor = new Color(244, 29, 0);

	/**
	 * ViewKeyGuidePanel을 생성합니다.
	 * @param totalFrame - 명령을 전달받을 totalFrame 입니다.
	 */
	public ViewKeyGuidePanel(ViewTotalFrame totalFrame) {
		this.totalFrame = totalFrame;
		viewKeyGuide = new JLabel("");
		backbt = new JButton("back");
		init();
	}

	/** 초기값 설정을 합니다. */
	@Override
	public void init() {
		addLabel();
		addButton();
		addListener();
		setLayout(null);
	}

	/** 스윙 컴포넌트가 자신의 모양을 그립니다. */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(im, 0, 0, getWidth(), getHeight(), this);
	}

	/** Label를 추가합니다. */
	public void addLabel() {
		this.add(viewKeyGuide);
	}

	/** Button의 기본 설정을 세팅합니다. */
	@Override
	public void addButton() {
		backbt = new JButton("Back");
		backbt.setFont(new Font("Forte", Font.PLAIN, 45));
		backbt.setForeground(setFontColor); // 버튼의 글자색
		backbt.setBounds(297, 560, 450, 60);
		backbt.setBorderPainted(false);
		backbt.setFocusable(false);
		backbt.setContentAreaFilled(false);
		this.add(backbt);
	}

	/** Button에 MouseListener를 삽입합니다. */
	@Override
	public void addListener() {
		backbt.addMouseListener(makeMouseListener());
	}

	/** MouseLister를 구현합니다. */
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

	/** HelpPanel을 보여줍니다. */
	public void showHelpPanel() {
		totalFrame.showHelpPanel();
	}
}