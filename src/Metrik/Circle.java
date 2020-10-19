package Metrik;

public class Circle extends Surface{
		int r;

		public Circle(int r) {
			this.r = r;
		}

		@Override
		void calculateA() {
			// TODO Auto-generated method stub
			_A = (float)(Math.PI * r * r);
		}

		@Override
		void calculateU() {
			// TODO Auto-generated method stub
			_U = (float)(2 * Math.PI * r);
		}

	}

