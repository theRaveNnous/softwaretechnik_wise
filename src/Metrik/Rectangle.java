package Metrik;

public class Rectangle extends Surface{
	int a;
	int b;

	public Rectangle(int a, int b) {
		this.a = a;
		this.b = b;

		calculateA();
		calculateU();
	}

	@Override
	void calculateA() {
		// TODO Auto-generated method stub
		_A = a * b;
	}

	@Override
	void calculateU() {
		// TODO Auto-generated method stub
		_U = 2 * (a +b);
	}


}
