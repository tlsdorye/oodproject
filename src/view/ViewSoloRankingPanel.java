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
 * �� ViewSoloRankingPanel Ŭ������ SoloRankingȭ���� Panel�� �����ϰ� �����ִ� Ŭ�����Դϴ�.
 * @author ������
 */
public class ViewSoloRankingPanel extends JPanel {

	/** ViewTotalFrame Type�� ���� �Դϴ�. */
	private ViewTotalFrame totalFrame;
	/** RankingManager Type�� ���� �Դϴ�. */
	private RankingManager rankingManager;
	/** RankingData[] Type�� ���� �Դϴ�. */
	private RankingData[] soloRankingData;
	/** JPanel Type�� ���� �Դϴ�. */
	private JPanel panel1, panel2, panel3, secFrmPanel1, secFrmPanel2;
	/** JLabel Type�� ���� �Դϴ�. */
	private JLabel namelabel, secFrmLabel;
	/** JTextArea Type�� ���� �Դϴ�. */
	private JTextArea list;
	/** JButton Type�� ���� �Դϴ�. */
	private JButton resetbt, backbt, yesbt, nobt;
	/** images�� ����� �̹��� ����� ���� �Դϴ�. */
	private ImageIcon image = new ImageIcon("images/black.png");
	/** image�� ��ȯ�� ���� �Դϴ�. */
	private Image im = image.getImage();

	/**
	 *  ViewSoloRankingpanel�� �����մϴ�. 
	 * @param totalFrame - ����� ���� ���� totalFrame �Դϴ�.
	 */
	public ViewSoloRankingPanel(ViewTotalFrame totalFrame) {

		this.totalFrame = totalFrame;
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		namelabel = new JLabel("score��ŷ");
		list = new JTextArea(5, 40); // txt������ �о�;� ��
		resetbt = new JButton("reset");
		backbt = new JButton("back");
		yesbt = new JButton("Yes");
		nobt = new JButton("No");
		rankingManager = new RankingManager();
		soloRankingData = new RankingData[10];

		init1();
	}

	/**
	 * ViewSoloRankingPanel�� �����մϴ�.
	 * @param soloRankingData - ����� ���޹��� soloRankingData �Դϴ�.
	 */
	public ViewSoloRankingPanel(RankingData[] soloRankingData) {
		this.soloRankingData = soloRankingData;
		init2();
	}

	/** �ʱⰪ ������ �մϴ�. */
	public void init1() {
		setLayout(new BorderLayout());
		addContents();
		addListener();
		setVisible(true);
	}

	/** ��ŷ �����͸� ��Ÿ���ϴ�. */
	public void init2() {
		// ��ŷ�����͸� ��Ÿ���� ��
	}

	/**
	 *  ���� ������Ʈ�� �ڽ��� ����� �׸��ϴ�.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(im, 0, 0, getWidth(), getHeight(), this);
	}

	/** Contents�� �⺻ ������ �����մϴ�. */
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

	/** Button�� MouseListener�� �����մϴ�. */
	public void addListener() {
		backbt.addMouseListener(makeListener());
		resetbt.addMouseListener(makeListener());
		yesbt.addMouseListener(makeListener());
		nobt.addMouseListener(makeListener());
	}

	/** MouseLister�� �����մϴ�. 
	 * @return MouseEvent�� ��ȯ�մϴ�. 
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

	/** RankingPanel�� �����ݴϴ�. */
	public void showRankingPanel() {
		totalFrame.showRankingPanel();
	}

	/** */
	class SecFrm extends JFrame {
		SecFrm() {
			setTitle("���!");
			setSize(350, 150);
			secFrmPanel1 = new JPanel();
			secFrmPanel2 = new JPanel();
			secFrmLabel = new JLabel("���� �ʱ�ȭ�Ͻðڽ��ϱ�?");

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