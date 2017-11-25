package view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

/**
 * 이 ViewGameModePanel은 GameMode화면의 Panel을 구성하고 보여주는 클래스입니다.
 * @author 이은경
 */
public class ViewGameModePanel extends JPanel implements IViewPanel {
	/** ViewTotalFrame Type의 변수 입니다 */
	private ViewTotalFrame totalFrame;
	/** JButton Type의 변수 입니다 */
	private JButton soloGamebt, ZPGamebt, AIGamebt, backbt;
	/** images에 저장된 이미지 사용할 변수 입니다 */
	private ImageIcon image = new ImageIcon("images/Basic.png");
	/** image를 반환할 변수 입니다 */
	private Image im = image.getImage();
	/** 폰트 색상을 지정할 변수 입니다 */
	private Color setFontColor = new Color(244, 88, 232);

	/**
	 * ViewGameModePanel을 생성합니다.
	 * @param totalFrame - 명령을 전달받을 totalFrame 입니다.
	 */
	public ViewGameModePanel(ViewTotalFrame totalFrame) {
		this.totalFrame = totalFrame;
		init();
	}

	/** 초기값 설정을 합니다. */
	@Override
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

	/** Button의 기본 설정을 세팅합니다. */
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

	/** Button에 MouseListener를 삽입합니다. */
	@Override
	public void addListener() {
		soloGamebt.addMouseListener(makeMouseListener());
		ZPGamebt.addMouseListener(makeMouseListener());
		AIGamebt.addMouseListener(makeMouseListener());
		backbt.addMouseListener(makeMouseListener());
	}

	/** MouseLister를 구현합니다. */
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

	/** SoloGamePanel을 보여줍니다. */
	public void showSoloGamePanel() {
		totalFrame.showSoloGamePanel();
	}

	/** 2PGamePanel을 보여줍니다. */
	public void show2PGamePanel() {
		totalFrame.show2PGamePanel();
	}

	/** AIGamePanel을 보여줍니다. */
	public void showAIGamePanel() {
		totalFrame.showAIGamePanel();
	}

	/** MainPanel을 보여줍니다. */
	public void showMainPanel() {
		totalFrame.showMainPanel();
	}
}
