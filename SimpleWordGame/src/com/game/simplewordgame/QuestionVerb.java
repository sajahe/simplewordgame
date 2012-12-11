package com.game.simplewordgame;

public class QuestionVerb {
	  
	private String question;
	private String answer;
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
