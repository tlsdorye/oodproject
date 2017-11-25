package ranking;

import java.io.*;
import java.util.*;

/**
 * �� Class�� RankingData�� �����մϴ�.
 * 
 * @author ������
 *
 */
public class RankingData{
	/** ������ ������ ���� �Դϴ�. */
	int score;
	/** �̸��� ������ ���� �Դϴ�. */
	String name;

	/** RankingData �� �����մϴ�. */
	public RankingData() {
		this.score = 0;
		this.name = "";
	}
	
	/**
	 * RankingData �� �����մϴ�.
	 * @param name - �� �̸��� �����ϴ�.
	 * @param score - �� ������ �����ϴ�.
	 */
	public RankingData(String name, int score) {
		this.score = score;
		this.name = name;
	}

}