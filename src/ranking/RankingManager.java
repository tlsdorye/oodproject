package ranking;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.ViewAIRankingPanel;
import view.ViewRankingRegisterPanel;
import view.ViewSoloRankingPanel;

/**
 * �� Class�� ��ŷ�� ����°� ����� ����ϴ� Class �Դϴ�.
 * 
 * @author ������
 *
 */
public class RankingManager{
	
	/** */
	private BufferedWriter bw;
	/** */
	File soloFile = new File("SoloRanking.txt");
	/** */
	File AIFile = new File("AIRanking.txt");
	
	/** */
	private int num1; //soloRankingData�� ���� ���� �� �̿��ϴ� count
	/** */
	private int num2; //AIRankingData�� ���� ���� �� �̿��ϴ� count
	/** */
	private RankingData[] soloRankingData;
	/** */
	private RankingData[] AIRankingData;
	/** */
	private ViewSoloRankingPanel viewSoloRanking;
	/** */
	private ViewAIRankingPanel viewAIRanking;
	/** */
	private ViewRankingRegisterPanel viewRankingRegister;
	/** */
	private JPanel secFrmPanel;
	/** */
	private JLabel secFrmLabel;
	/** */
	private JButton okbt;
	
	/** */
	public RankingManager() {
		soloRankingData = new RankingData[10];
		AIRankingData =  new RankingData[10];
		viewRankingRegister = new ViewRankingRegisterPanel(this);
//		viewAIRanking = new ViewAIRankingPanel();
	}

	/**
	 * 
	 * @param data
	 */
	public void fillArray(RankingData[] data) {
		for(int i = 0 ; i < data.length ; i++) {
			soloRankingData[i] = new RankingData();
		}
	}
	
	/**
	 * 
	 * @param name
	 * @param score
	 * @throws IOException
	 */
	public void registerSolo (String name, int score) throws IOException {
		//�̸��� ������ �迭�� ��� (+ ����)
	
		if(num1 < 10){
			soloRankingData[num1++] = new RankingData(name, score);
		} 
		else if(num1 == 10) {
			
			if(score > soloRankingData[9].score) {
				soloRankingData[9] = new RankingData(name, score);
			} else {
				new RankingSecFrm();
			}	
		}
		
		soloRankingSorting(soloRankingData);
		saveTXTinSoloRankingData();
	}

	/**
	 * 
	 * @param name
	 * @param score
	 * @throws IOException
	 */
	public void registerAI (String name, int score) throws IOException {
		
		if(num2 < 10){
			AIRankingData[num2++] = new RankingData(name, score);
		} 
		else if(num2 == 10) { //���� ����� ���� ���� ���� ���ؼ� ���
			
			if(score > AIRankingData[9].score) {
				AIRankingData[9] = new RankingData(name, score);
			} else {
				new RankingSecFrm();
			}	
		}
		
		AIRankingSorting(AIRankingData);
		saveTXTinAIRankingData();
	}
	
	/**
	 * 
	 * @param data
	 * @throws IOException
	 */
	public void soloRankingSorting(RankingData[] data) throws IOException {
		
		for(int i = 0 ; i < 9 ; i++)
		{
			for(int j = 1 ; j < 10-i ; j++)
			{
				if(data[j-1].score < data[j].score)
				{
					int tmpScore = data[j-1].score;
					String tmpName = data[j-1].name;
					data[j-1].score = data[j].score;
					data[j-1].name = data[j].name;
					data[j].score = tmpScore;
					data[j].name = tmpName;
				}
			}
		}
		saveTXTinSoloRankingData();
	}
	
	/**
	 * 
	 * @param data
	 * @throws IOException
	 */
	public void AIRankingSorting(RankingData[] data) throws IOException {
		
		for(int i = 0 ; i < 9 ; i++)
		{
			for(int j = 1 ; j < 10-i ; j++)
			{
				if(data[j-1].score < data[j].score)
				{
					int tmpScore = data[j-1].score;
					String tmpName = data[j-1].name;
					data[j-1].score = data[j].score;
					data[j-1].name = data[j].name;
					data[j].score = tmpScore;
					data[j].name = tmpName;
				}
			}
		}
		saveTXTinAIRankingData();
	}

	/**
	 * 
	 */
	public void showSoloRanking() {
		viewSoloRanking = new ViewSoloRankingPanel(soloRankingData);
	}

	/**
	 * 
	 */
	public void showAIRanking() {
//		viewAIRanking = new ViewAIRankingPanel(AIRankingData);
	}

	/** �ʱ�ȭ�ϴ� �޼ҵ� */
	public void resetSoloData (RankingData[] data) throws IOException {
		num1 = 0;
		
		for(int i = 0 ; i < data.length ; i++){
			data[i] = new RankingData();
		}
		
		this.soloRankingData = data;
		resetTXT(0);
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	public void resetAIData () throws IOException {
		num2 = 0;
		
		for(int i = 0 ; i < AIRankingData.length ; i++){
			AIRankingData[i] = new RankingData();
		}
		resetTXT(1);
	}
	
	/**
	 * 
	 * @param resetNum
	 * @throws IOException
	 */
	public void resetTXT(int resetNum) throws IOException {
		if(resetNum == 0){ //soloRankingData �ʱ�ȭ
			soloFile.delete();
		}
		else if(resetNum == 1){ //AIRankingData �ʱ�ȭ
			AIFile.delete();
		}
		else
			System.out.println("reset Number error");
	}
	
	/** �ֹ߼� �ȵǰ� txt�� �������ִ� �޼ҵ�  */
	public void saveTXTinSoloRankingData() throws IOException{
		
		if (!soloFile.exists()) {
			try {
				soloFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			for(int i = 0 ; i < soloRankingData.length ; i++){
				
				String str = soloRankingData[i].name + " " + soloRankingData[i].score;
				try {
					bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(soloFile)));
					bw.write(str);
					bw.newLine();
					bw.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	public void saveTXTinAIRankingData() throws IOException{
		
		if (!AIFile.exists()){
			try {
				AIFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			for(int i = 0 ; i < AIRankingData.length ; i++){
				
				String str = (char)(i+1)+" "+AIRankingData[i].name + " " + AIRankingData[i].score;
				try {
					bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(AIFile)));
					bw.write(str);
					bw.newLine();
					bw.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	 * @author ������
	 *
	 */
	class RankingSecFrm extends JFrame{
		
		RankingSecFrm(){
			setTitle("�˸�!");
			setSize(350, 150);
			secFrmPanel = new JPanel();
			secFrmLabel = new JLabel("�̸��� ����� �� �����ϴ�.");
			okbt = new JButton("ok");

			secFrmLabel.setFont(new Font("SVN-Block", Font.PLAIN, 16)); 
			secFrmPanel.add(secFrmLabel, JLabel.CENTER);
			okbt.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getSource() == okbt){
						ok();
					}
				}
				
			});
			
			this.add(secFrmPanel, BorderLayout.CENTER);
			this.add(okbt, BorderLayout.SOUTH);
			
			setLocationRelativeTo(null);
			setVisible(true);
		}
		
		public void ok(){
			this.dispose();
		}
	}
}