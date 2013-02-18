package com.game.simplewordgame;

public class Tense {
	public static final int PRESENT =0;
	public static final int IMPERFECT =1;
	public static final int PASSE_COMPOSE =2;
	public static final int FUTURE =3;
	public static final int CONDITIONAL =4;
	
	public static String getTense(int i) {
		switch (i) {
		case 0:

			return "present indicative";
		case 1:

			return "imperfect";

		case 2:

			return "passé composé";

		case 3:

			return "future";
		case 4:

			return "conditional";
		case 5:
			return "";

		default:
			return "";
		}
	}
}
