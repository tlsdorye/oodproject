package view;

import ranking.RankingData;
import ranking.RankingManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

/**
 * 이 ViewSoloRankingPanel 클래스는 SoloRanking화면의 Panel을 구성하고 보여주는 클래스입니다.
 * @author 이은경
 */
public class ViewSoloRankingPanel extends JPanel {

	/** ViewTotalFrame Type의 변수 입니다. */
	private ViewTotalFrame totalFrame;
	/** RankingManager Type의 변수 입니다. */
	private RankingManager rankingManager;
	/** RankingData[] Type의 변수 입니다. */
	private RankingData[] soloRankingData;
	/** JPanel Type의 변수 입니다. */
	private JPanel panel1, panel2, panel3, secFrmPanel1, secFrmPanel2;
	/** JLabel Type의 변수 입니다. */
	private JLabel namelabel, secFrmLabel;
	/** JTextArea Type의 변수 입니다. */
	private JTextArea list;
	/** JButton Type의 변수 입니다. */
	private JButton resetbt, backbt, yesbt, nobt;
	/** images에 저장된 이미지 사용할 변수 입니다. */
	private ImageIcon image = new ImageIcon("images/black.png");
	/** image를 반환할 변수 입니다. */
	private Image im = image.getImage();

	/**
	 *  ViewSoloRankingpanel을 생성합니다. 
	 * @param totalFrame - 명령을 전달 받을 totalFrame 입니다.
	 */
	public ViewSoloRankingPanel(ViewTotalFrame totalFrame) {

		this.totalFrame = totalFrame;
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		namelabel = new JLabel("score랭킹");
		list = new JTextArea(5, 40); // txt파일을 읽어와야 함
		resetbt = new JButton("reset");
		backbt = new JButton("back");
		yesbt = new JButton("Yes");
		nobt = new JButton("No");
		rankingManager = new RankingManager();
		soloRankingData = new RankingData[10];

		init1();
	}

	/**
	 * ViewSoloRankingPanel을 생성합니다.
	 * @param soloRankingData - 명령을 전달받을 soloRankingData 입니다.
	 */
	public ViewSoloRankingPanel(RankingData[] soloRankingData) {
		this.soloRankingData = soloRankingData;
		init2();
	}

	/** 초기값 설정을 합니다. */
	public void init1() {
		setLayout(new BorderLayout());
		addContents();
		addListener();
		setVisible(true);
	}

	/** 랭킹 데이터를 나타냅니다. */
	public void init2() {
		// 랭킹데이터를 나타내야 함
	}

	/**
	 *  스윙 컴포넌트가 자신의 모양을 그립니다.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(im, 0, 0, getWidth(), getHeight(), this);
	}

	/** Contents의 기본 설정을 세팅합니다. */
	public void addContents() {
		namelabel.setFont(new Font("SVN-Block", Font.PLAIN, 40));
		namelabel.setForeground(Color.WHITE);
		resetbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
		backbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
		list.setRows(300);

		panel1.setBackground(Color.black);
		panel2.setBackground(Color.black);
		panel3.setBackground(Color.black);

		panel1.add(namelabel, JLabel.CENTER);
		panel2.add(new JScrollPane(list));
		panel3.add(resetbt);
		panel3.add(Box.createHorizontalStrut(50));
		panel3.add(backbt);

		this.add(panel1, BorderLayout.NORTH);
		this.add(panel2, BorderLayout.CENTER);
		this.add(panel3, BorderLayout.SOUTH);
	}

	/** Button에 MouseListener를 삽입합니다. */
	public void addListener() {
		backbt.addMouseListener(makeListener());
		resetbt.addMouseListener(makeListener());
		yesbt.addMouseListener(makeListener());
		nobt.addMouseListener(makeListener());
	}

	/** MouseLister를 구현합니다. 
	 * @return MouseEvent를 반환합니다. 
	 */
	public MouseListener makeListener() {
		return new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if (e.getSource() == resetbt) {
					resetbt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
				}
				if (e.getSource() == backbt) {
					backbt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
				}
				if (e.getSource() == yesbt) {
					yesbt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
				}
				if (e.getSource() == nobt) {
					nobt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
				}
			}

			public void mouseExited(MouseEvent e) {
				if (e.getSource() == resetbt) {
					resetbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
				}
				if (e.getSource() == backbt) {
					backbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
				}
				if (e.getSource() == yesbt) {
					yesbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
				}
				if (e.getSource() == nobt) {
					nobt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
				}
			}

			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == resetbt) {
					new SecFrm();
				}
				if (e.getSource() == backbt) {
					showRankingPanel();
				}
			}
		};
	}

	/** RankingPanel을 보여줍니다. */
	public void showRankingPanel() {
		totalFrame.showRankingPanel();
	}

	/** */
	class SecFrm extends JFrame {
		SecFrm() {
			setTitle("경고!");
			setSize(350, 150);
			secFrmPanel1 = new JPanel();
			secFrmPanel2 = new JPanel();
			secFrmLabel = new JLabel("정말 초기화하시겠습니까?");

			yesbt.addActionListener(makeListener2());
			nobt.addActionListener(makeListener2());

			secFrmLabel.setFont(new Font("SVN-Block", Font.PLAIN, 16));

			secFrmPanel1.add(secFrmLabel, JLabel.CENTER);
			secFrmPanel2.add(yesbt);
			secFrmPanel2.add(Box.createHorizontalStrut(20));
			secFrmPanel2.add(nobt);

			this.add(secFrmPanel1, BorderLayout.CENTER);
			this.add(secFrmPanel2, BorderLayout.SOUTH);

			setLocationRelativeTo(null);
			setVisible(true);
		}

		/** */
		public ActionListener makeListener2() {
			return new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == yesbt) {
						ok();
						try {
							rankingManager.resetSoloData(soloRankingData);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if (e.getSource() == nobt) {
						ok();
					}
				}
			};

		}

		/** */
		public void ok() {
			this.dispose();
		}
	}
}