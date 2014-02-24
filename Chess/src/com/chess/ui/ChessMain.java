package com.chess.ui;

import com.chess.board.Board;
import com.chess.menu.MenuItems;

public class ChessMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = Board.getInstance();
		board.printBorad();

		do {
			MenuItems.printMenu();
			// board.printBorad();

			MenuItems.doAction(MenuItems.getSelection());
		} while (true);
	}

}
