/********************************
 * Carlos Martinez
********************************/
package inermerationsEnum;

public enum Grade {
	 A(new Range(90,100))
	,B(new Range(80,89))
	,C(new Range(70,79))
	,D(new Range(60,69))
	,F(new Range(0,59));
	
	private Range range;
	
	private Grade(Range range) {
		this.range = range;
	}
	
	public int getUpperBound() {
		return range.getUpperBound();
	}
	
	public int getLowerBound() {
		return range.getLowerBound();
	}
	
	@Override
	public String toString() {
		return super.toString() + "[" + getLowerBound() + ","
				 + getUpperBound() + "]";
	}
	
}