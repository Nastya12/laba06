package laba06;

public class Main {
	@SuppressWarnings("static-access")
	public static void main(String[] args){
		Matrix mtx1 = null, mtx2 = null,result = null;
		mtx1.random(3,3);
		mtx2.random(3,3);
		@SuppressWarnings("unused")
		ParallelMultiplyTwoMatrix res=new ParallelMultiplyTwoMatrix(mtx1,mtx2, result, 0);
	}
}
