import java.util.*;

public class Parser extends AbstractParser {
	// your code goes below
	private int curPosition;
	private Set<String> start_rem_follow_set;
	private Set<String> term_rem_follow_set;

	public Parser() {
		start_rem_follow_set = new HashSet<String>();
		start_rem_follow_set.add("$");
		start_rem_follow_set.add(")");

		term_rem_follow_set = new HashSet<String>();
		term_rem_follow_set.add("$");
		term_rem_follow_set.add("+");
		term_rem_follow_set.add("-");
		term_rem_follow_set.add(")");
	}

	public float parseStart() {
		this.curPosition = 0;
		float result = start();
		acceptTerminal("$");
		return result;
	}

	private float start() {
		float term_evaluation = term();
		return start_rem(term_evaluation);
	}

	private float start_rem(float term_evaluation) {
		float cur_result = 0;
		switch (curTok) {
			case "+":
				acceptTerminal("+");
				cur_result = term_evaluation + term();
				return start_rem(cur_result);
			case "-":
				acceptTerminal("-");
				cur_result = term_evaluation - term();
				return start_rem(cur_result);
			default:
				if (start_rem_follow_set.contains(curTok))
					return term_evaluation;
				
				System.out.println("Invalid token '" + curTok + "' expected any of \"+, -, ), $\" at position " + curPosition);
				return -1;	
		}
	}

	private float term() {
		float cur_result = factor();
		return term_rem(cur_result);
	}

	private float term_rem(float term_evaluation) {
		switch (curTok) {
			case "*":
				acceptTerminal("*");

				float cur_result = term_evaluation * factor();
				return term_rem(cur_result);
			default:
				if (term_rem_follow_set.contains(curTok))
					return term_evaluation;

				System.out.println("Invalid token '" + curTok + "' expected any of \"*, +, -, ), $\" at position " + curPosition);
				return -1;	
		}
	}

	private float factor() {
		switch (curTok) {
			case "(":
				acceptTerminal("(");
				float cur_result = start();
				acceptTerminal(")");
				return cur_result;
			default:
				return float_production(); // this function checks if the current token is a <float> or not
		}
	}

	private float float_production() {
		float val = -1;

		try {
			val = Float.parseFloat(curTok);
		} catch (Exception e) {
			System.out.println("Invalid <float> terminal '" + curTok + "' at position " + curPosition);
		}

		moveToNextToken();
		return val;
	}

	private void acceptTerminal(String token) {
		if (!curTok.equals(token))
			System.out.println("Expected token: " + token + " at position " + curPosition + ", found: " + curTok);

		moveToNextToken();
	}

	private boolean isFloat(String token) {
		try {
			Float.parseFloat(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void moveToNextToken() {
		this.curTok = lex.getNextToken();
		this.curPosition++;
	}
}

/*
	Task 0: (We mainly avoided the left-recursion in rules 1 and 2)
		<start> ::= <term> <start_rem>
		<start_rem> ::= "+" <term> <start_rem> | "-" <term> <start_rem> | λ
		<term> ::= <factor> <term_rem>
		<term_rem> ::= "*" <factor> <term_rem> | λ
		<factor> ::= "(" <start> ")" | <float>
*/

/*
	Valid Test Cases:
		( ( ( 5 ) ) ) = 5
		( ( ( 3.3 + 2.5 * 5.25 ) ) ) = 16.425
		( ( ( ( 4 ) ) ) ) = 4
		( ( ( ( 4 * 2 ) ) ) ) = 8
		( ( 4 + 3 ) * 2 ) = 14
		( 4 * 2 + 3 ) = 11 
		( 4 + 3 * 2 ) = 10
		( 4 * 2 + 2 * 5 ) = 18
		( 4 + 2 * 2 + 3 ) = 11
		( ( 4 + 2 ) * ( 2 + 3 ) ) = 30

	Invalid Test Cases:
		5 + 5 +
		5 + 5 + ( * )
		* + 5 + 5
		+ 5
		5 * 5 - 8 + abc
		234 + z5 + (
		111 + 1 + ( )
		( 5
		5 )
		( 5 + z )
		( 5z )
*/