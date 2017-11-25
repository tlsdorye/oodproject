package view;

import java.awt.event.*;

/**
 * �� �������̽��� ViewPanel���� ������ interface�Դϴ�.
 * 
 * @author �۹μ�
 *
 */
public interface IViewPanel {

	/** Panel�� �ʱ�ȭ �մϴ�. */
	public void init();

	/** ��ư�� �߰��մϴ�. */
	public void addButton();

	/** �����ʸ� �߰��մϴ�. */
	public void addListener();

	/**
	 * ���콺 �����ʸ� ����ϴ�.
	 * 
	 * @return �� ���콺 �����ʸ� ��ȯ�մϴ�.
	 */
	public MouseListener makeMouseListener();
}
