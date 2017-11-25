package view;

import java.awt.event.*;

/**
 * 이 인터페이스는 ViewPanel들을 정의한 interface입니다.
 * 
 * @author 송민석
 *
 */
public interface IViewPanel {

	/** Panel을 초기화 합니다. */
	public void init();

	/** 버튼을 추가합니다. */
	public void addButton();

	/** 리스너를 추가합니다. */
	public void addListener();

	/**
	 * 마우스 리스너를 만듭니다.
	 * 
	 * @return 이 마우스 리스너를 반환합니다.
	 */
	public MouseListener makeMouseListener();
}
