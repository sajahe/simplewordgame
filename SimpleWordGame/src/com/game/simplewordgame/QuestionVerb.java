package com.game.simplewordgame;

public class QuestionVerb {
	  
	private CharSequence question;
	private CharSequence answer;
	private CharSequence pronoun;
	private boolean answered;
	private boolean correct;
	
	
	public QuestionVerb(CharSequence question, CharSequence answer,
			CharSequence pronoun) {
		super();
		this.question = question;
		this.answer = answer;
		this.pronoun = pronoun;
		answered = false;
	}
	public CharSequence getPronoun() {
		return pronoun;
	}
	public CharSequence getQuestion() {
		return question;
	}
	public CharSequence getAnswer() {
		return answer;
	}
	

}
