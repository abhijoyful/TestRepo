package com.rps;

import java.util.Arrays;
import java.util.Random;

public class MainApp {

	private class abc {
		
	}
	
	public static void main(String[] args) {
		GameFigures computerChoice = runComputerChoice();
		GameFigures computerChoice2 = runComputerChoice();
		System.out.println(computerChoice);
		System.out.println(computerChoice2);
		String result = findResult(computerChoice, computerChoice2);
		System.out.println(result);
		
		
		int j = 010;
		int k = 07;
		System.out.println(j);
		System.out.println(k);
		
//		do {
//			i++;
//		} while (i<0);
	}

	private static String findResult(GameFigures computerChoice, GameFigures computerChoice2) {
		String result = null;
		if (computerChoice == computerChoice2) {
			result = "TIE";
		}
		else {
			if (computerChoice == GameFigures.ROCK) {
				if (computerChoice2 == GameFigures.SCISSOR) {
					result = "Computer 1 wins";
				}
				else {
					result = "Computer 2 wins";
				}
			}
			else if (computerChoice == GameFigures.PAPER) {
				if (computerChoice2 == GameFigures.ROCK) {
					result = "Computer 1 wins";
				}
				else {
					result = "Computer 2 wins";
				}
			}
			else { // 1 - Scissor
				if (computerChoice2 == GameFigures.PAPER) {
					result = "Computer 1 wins";
				}
				else {
					result = "Computer 2 wins";
				}
			}
			
		}
		return result;
	}

	private static GameFigures runComputerChoice() {
		GameFigures computerChoice = null;;
		Random ran = new Random();
		int num = ran.nextInt(3);
		computerChoice = Arrays.stream(GameFigures.values()).filter(x -> x.ordinal() == num).findFirst().get();
		return computerChoice;
	}

}
