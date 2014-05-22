package laba06;

public class ParallelMultiplyTwoMatrix extends Thread {
	private Matrix firstMatrix;
	private Matrix secondMatrix;
	private Matrix resultMatrix;
	private int thread;
	private static volatile int i = 0;

	public ParallelMultiplyTwoMatrix(Matrix firstMatrix, Matrix secondMatrix,
			Matrix resultMatrix, int thread) {

		this.firstMatrix = firstMatrix;
		this.secondMatrix = secondMatrix;
		this.resultMatrix = resultMatrix;
		this.thread = thread;

	}

	public void run() {

		for (; i < firstMatrix.getHeight(); i++) {
			System.out.println(Thread.currentThread().getName() + " : i = " + i);
			for (int j = 0; j < secondMatrix.getWeigth(); j++) {
				double sum = 0.0;
				for (int k = 0; k < firstMatrix.getWeigth(); k++) {
					sum += firstMatrix.getValue(i, k)* secondMatrix.getValue(k, j);
				}
				resultMatrix.setValue(i, j, sum);
			}
		}
	}

	public static Matrix parallelMultiplyTwoMatrix(Matrix firstMatrix, Matrix secondMatrix) throws InterruptedException {

		if (firstMatrix.getWeigth() != secondMatrix.getHeight()) {
			throw new IllegalArgumentException("Wrong matrix dimension");
		}

		Runtime runtime = Runtime.getRuntime();
		int numberOfThreads = runtime.availableProcessors();
		Thread[] threads = new Thread[numberOfThreads];

		Matrix resultMatrix = new Matrix(firstMatrix.getHeight(),
				secondMatrix.getWeigth());

		for (int i = 1; i <= numberOfThreads; i++) {
			threads[i - 1] = new ParallelMultiplyTwoMatrix(firstMatrix,
					secondMatrix, resultMatrix, i);
			threads[i - 1].start();
		}
		for (int i = 0; i < threads.length; i++) {

			threads[i].join();
		}
		return resultMatrix;
	}
}