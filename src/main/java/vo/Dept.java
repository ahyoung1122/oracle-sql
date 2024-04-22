package vo; 
//value object의 줄임말이다. 데이터를 나타내는 객체를 의미
//DTO라고 부르기도 한다.(Data transfer Object)데이터 전달용
//Domain (컬럼 속성값의 범위) ->테이블 속성의 범위 
//VO > DTO , Domain (큰 VO 안에 포함되어있다는 느낌으로 보면 된다 크게 구별할 필요는 없다.)

//public에서 private로 바뀌면 문제가 생긴다.
//->dao에서 먼저 오류가 생긴다. ->d.setDeptNo 이런식으로 set으로 변경해주어야 한다.
public class Dept {
	private int deptNo;
	private String dname;
	private String loc;
	
	
	public int getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	
}
