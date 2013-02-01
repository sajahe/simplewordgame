package com.game.simplewordgame;

public class QuestionVerb {
	  
	private String question;
	private String answer;
	private String wrongAnswer;
	public String getWrongAnswer() {
		return wrongAnswer;
	}
	public void setWrongAnswer(String wrongAnswer) {
		this.wrongAnswer = wrongAnswer;
	}
	public boolean isAnswered() {
		return answered;
	}
	public void setAnswered(boolean answered) {
		this.answered = answered;
	}
	public boolean isCorrect() {
		return correct;
	}
	private String pronoun;
	private boolean answered;
	private boolean correct;
	
	
	/**
	 * This class is for asking questions in the verb quiz.
	 * @param question = the infinitive of the verb 
	 * @param answer = correctly conjugated verb
	 * @param pronoun = pronoun that indicates the personal tense of the conjugation 
	 */
	public QuestionVerb(String question, String answer,
			String pronoun) {
		super();
		this.question = question;
		this.answer = answer;
		this.pronoun = pronoun;
		answered = false;
	}
	public String getPronoun() {
		return pronoun;
	}
	public String getQuestion() {
		return question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setCorrect(boolean b){
		correct = b;
	}
	

}
