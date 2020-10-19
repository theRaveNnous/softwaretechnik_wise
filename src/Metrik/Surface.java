package Metrik;

public abstract class Surface {
	protected float _A;
	protected float _U;

	abstract void calculateA();
	abstract void calculateU();
	
	public double getA() {
		calculateA();
		return _A;
	}
	public double getU() {
		calculateU();
		return _U;
	}
	
	
}
