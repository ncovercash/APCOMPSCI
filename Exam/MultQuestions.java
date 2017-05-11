// #2

public class MultQuestions implements StudyQuestions {
	int firstVal, currentSecond;

	public MultQuestions(int first, int second) {
		firstVal = first;
		currentSecond = second;
	}

	public void nextQuestion() {
		currentSecond++;
	}

	public String getQuestion() {
		return ""+firstVal+" TIMES "+currentSecond;
	}



	public static void main(String[] args) {
		MultQuestions test = new MultQuestions(3, 4);
		System.out.println(test.getQuestion());
		System.out.println(test.getQuestion());
		test.nextQuestion();
		System.out.println(test.getQuestion());
	}
}
