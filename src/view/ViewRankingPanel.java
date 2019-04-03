package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 * 이 ViewRankingPanel 클래스는 Ranking화면의 Panel을 구성하고 보여주는 클래스입니다.
 * 
 * @author 이은경
 * 
 */
public class ViewRankingPanel extends JPanel implements IViewPanel {
	/** ViewTotalFrame Type의 변수 입니다. */
	private ViewTotalFrame totalFrame;
	/** JButton Type의 변수 입니다. */
	private JButton soloRankingbt, AIRankingbt, backbt;
	/** images에 저장된 이미지 사용할 변수 입니다. */
	private ImageIcon image = new ImageIcon("images/Basic.png");
	/** image를 반환할 변수 입니다. */
	private Image im = image.getImage();
	/** 폰트 색상을 지정할 변수 입니다. */
	private Color setFontColor = new Color(41, 230, 244);

	/**
	 * ViewRankingPanel을 생성합니다.
	 * @param totalFrame - 명령을 전달받을 totalFrame 입니다.
	 */
	public ViewRankingPanel(ViewTotalFrame totalFrame) {
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

	/** Button의 기본 설정을 세팅합니다. */
	public void addButton() {
		soloRankingbt = new JButton("Solo Ranking");
		soloRankingbt.setFont(new Font("Forte", Font.PLAIN, 45));
		soloRankingbt.setBounds(297, 290, 450, 60);
		soloRankingbt.setBorderPainted(false);
		soloRankingbt.setForeground(setFontColor);
		soloRankingbt.setFocusable(false);
		soloRankingbt.setContentAreaFilled(false);

		AIRankingbt = new JButton("AI Ranking");
		AIRankingbt.setFont(new Font("Forte", Font.PLAIN, 45));
		AIRankingbt.setBounds(297, 380, 450, 60);
		AIRankingbt.setBorderPainted(false);
		AIRankingbt.setForeground(setFontColor);
		AIRankingbt.setFocusable(false);
		AIRankingbt.setContentAreaFilled(false);

		backbt = new JButton("back");
		backbt.setFont(new Font("Forte", Font.PLAIN, 45));
		backbt.setBounds(297, 470, 450, 60);
		backbt.setBorderPainted(false);
		backbt.setForeground(setFontColor);
		backbt.setFocusable(false);
		backbt.setContentAreaFilled(false);

		this.add(soloRankingbt);
		this.add(AIRankingbt);
		this.add(backbt);
	}

	/** Button에 MouseListener를 삽입합니다. */
	public void addListener() {
		soloRankingbt.addMouseListener(makeMouseListener());
		AIRankingbt.addMouseListener(makeMouseListener());
		backbt.addMouseListener(makeMouseListener());
	}

	/**
	 * MouseLister를 구현합니다.
	 */
	public MouseListener makeMouseListener() {
		return new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() == soloRankingbt) {
					soloRankingbt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
				if (e.getSource() == AIRankingbt) {
					AIRankingbt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
				if (e.getSource() == backbt) {
					backbt.setFont(new Font("Forte", Font.PLAIN, 55));
				}
			}

			public void mouseExited(MouseEvent e) {
				if (e.getSource() == soloRankingbt) {
					soloRankingbt.setFont(new Font("Forte", Font.PLAIN, 45));
				}
				if (e.getSource() == AIRankingbt) {
					AIRankingbt.setFont(new Font("Forte", Font.PLAIN, 45));
				}
				if (e.getSource() == backbt) {
					backbt.setFont(new Font("Forte", Font.PLAIN, 45));
				}

			}

			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == soloRankingbt) {
					showSoloRankingPanel();
				}
				if (e.getSource() == AIRankingbt) {
					showAIRankingPanel();
				}
				if (e.getSource() == backbt) {
					showMainPanel();
				}

			}
		};

	}

	/** SoloRankingPanel을 보여줍니다. */
	public void showSoloRankingPanel() {
		totalFrame.showSoloRankingPanel();
	}

	/** AIRankingPanel을 보여줍니다. */
	public void showAIRankingPanel() {
		totalFrame.showAIRankingPanel();
	}

	/** MainPanel을 보여줍니다. */
	public void showMainPanel() {
		totalFrame.showMainPanel();
	}
}