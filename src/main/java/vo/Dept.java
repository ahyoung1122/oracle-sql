package vo; 
//value object의 줄임말이다. 데이터를 나타내는 객체를 의미
//DTO라고 부르기도 한다.(Data transfer Object)데이터 전달용
//Domain (컬럼 속성값의 범위) ->테이블 속성의 범위 
//VO > DTO , Domain (큰 VO 안에 포함되어있다는 느낌으로 보면 된다 크게 구별할 필요는 없다.)

public class Dept {
	public int deptNo;
	public String dname;
	public String loc;
}
