package bubble.test.ex07;

public interface Moveable {

	public abstract void left();
	void right();
	void up();
	// 인터페이스 추가 기능 default 사용해보기
	// 인터페이스에 모든 메소드는 추상메소드이여야 한다.
	// 기능의 확장 --> 단, default 메소드를 제외하고
	default void down() {};
	// 마지막에 default 는 세미클론을 추가해야 한다.
	
}
