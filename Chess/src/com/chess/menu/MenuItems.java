package com.chess.menu;

import java.util.Scanner;

import com.chess.board.Board;

/**
 * @author Grant
 * MenuItems is the class offering menus and reply menu selecting.
 */
public class MenuItems {
	
//  initialize menu items.
	public static final int itemsCount = 4;
	public static final String[] items = new String[itemsCount];

	static {
		items[0] = "Initial ChessBoard automaticly";
		items[1] = "Initial ChessBoard mannually";
		items[2] = "Print out ChessBoard";
		items[3] = "Exit";
	}
	
//  print out the menu.
	public static void printMenu()

	{
		System.out.println();
		System.out
				.println("===================American Checkers 1.0===================");
		for (int i = 0; i < itemsCount; i++)
			System.out.println(i + 1 + "." + items[i]);
		System.out.println();
		System.out.println("Please enter the number to execute funtions.");
	}

	@SuppressWarnings("resource")
	public static int getSelection() {
		int t;
		do
			t = new Scanner(System.in).nextInt();
		while (!((t >= 1) && (t <= itemsCount)));
		return t;
	}

// reply menu operations.
	public static void doAction(int t) {
		switch (t) {
		case 1:
			Board.getInstance().setBoardToNewTurn();
			break;
		case 2:
			Board.getInstance().setBoardByUser();
			break;
		case 3:
			Board.getInstance().printBorad();
			break;
		case 4:
			System.exit(0);
			break;
		}
	}

}
