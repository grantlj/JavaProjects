package com.chess.board;

import java.util.Scanner;

/**
 * @author Grant The Board Class is to save and do something about the chess
 *         board.
 * 
 */

public class Board {

	// maxCol and maxRow defines the size of board.
	public static final int maxCol = 8;
	public static final int maxRow = 8;
	public static Board boardInstance = null;

	// We defines the null blocks here.
	private static char[][] nullRedBlock = { { '！', '！', '！' },
			{ '|', ' ', '|' }, { '|', ' ', '|' }, { '|', ' ', '|' },
			{ '！', '！', '！' } };

	private static char[][] nullWhiteBlock = { { '！', '！', '！' },
			{ '\\', ' ', '/' }, { '|', ' ', '|' }, { '/', ' ', '\\' },
			{ '！', '！', '！' } };

	// The map is what we finally print out.
	private char[][] map = new char[(maxRow + 1) * 5][(maxCol + 1) * 3];

	// chessBoard array saves the state of game.
	private int[][] chessBoard = new int[maxRow][maxCol];

	// Get Block state.
	public int getBlock(char col, int row) {
		return chessBoard[maxRow - row][col - 'A'];
	}

	// Set Block state.
	public void setBlock(char col, int row, int state) {
		chessBoard[maxRow - row][col - 'A'] = state;
	}

	private Board() {
		for (int i = 0; i < maxCol; i++)
			for (int j = 0; j < maxRow; j++)
				chessBoard[i][j] = 0;
		// chessBoard[3][3]=1;
		// chessBoard[4][1]=-1;
	}

	// clear the board and map.
	private void clearBoard() {
		for (int i = 0; i < maxRow; i++)
			for (int j = 0; j < maxCol; j++)
				chessBoard[i][j] = 0;

		for (int i = 0; i <= maxRow * 5; i++)

			for (int j = 0; j <= maxCol * 3; j++)
				map[i][j] = ' ';

	}

	// Set the board to a new turn automaticlly.
	public void setBoardToNewTurn() {
		clearBoard();
		for (int i = 0; i < maxRow; i++)
			for (int j = 0; j < maxCol; j++) {
				if ((i <= 2) && (i + j) % 2 == 1)
					chessBoard[i][j] = 1;
				if ((i >= 5) && (i + j) % 2 == 1)
					chessBoard[i][j] = -1;

			}
	}

	// Print tabs for each map line.
	private void printTabs() {
		System.out.print("              ");
	}

	// Print out the board by map.
	public void printBorad() {
		for (int i = 0; i < maxRow; i++)
			for (int j = 0; j < maxCol; j++) {
				if ((i + j) % 2 == 1 && chessBoard[i][j] == 0)
					// Null block, color is white.
					printWhiteBlockWithNothing(i, j);

				if ((i + j) % 2 == 0 && chessBoard[i][j] == 0)
					// Null block, color is red.
					printRedBlockWithNothing(i, j);

				if ((i + j) % 2 == 1 && chessBoard[i][j] > 0)
					// Blackers, color is white.
					printWhiteBlockWithBlack(i, j);

				if ((i + j) % 2 == 0 && chessBoard[i][j] > 0)
					// Blackers, color is red.
					printRedBlockWithBlack(i, j);

				if ((i + j) % 2 == 1 && chessBoard[i][j] < 0)
					// Whiters, color is white.
					printWhiteBlockWithWhite(i, j);

				if ((i + j) % 2 == 0 && chessBoard[i][j] < 0)
					// Whiters, color is red.
					printRedBlockWithWhite(i, j);
			}

		// System.out.println();

		printTabs();

		for (int i = 0; i < maxCol * 3 + 5; i++)
			System.out.print('=');
		System.out.println();

		for (int i = 0; i <= maxRow * 5; i++) {
			printTabs();
			System.out.print("||");
			for (int j = 0; j <= maxCol * 3; j++)
				System.out.print(map[i][j]);

			System.out.println("||");
		}

		printTabs();
		for (int i = 0; i < maxCol * 3 + 5; i++)
			System.out.print('=');
	}

	// Set block states.
	private void printRedBlockWithWhite(int i2, int j2) {
		// TODO Auto-generated method stub
		nullRedBlock[2][1] = 'W';
		for (int i = 0; i < nullRedBlock.length; i++) {
			for (int j = 0; j < nullRedBlock[1].length; j++)
				map[i2 * 5 + i][j2 * 3 + j] = nullRedBlock[i][j];

		}
		nullRedBlock[2][1] = ' ';

	}

	private void printWhiteBlockWithWhite(int i2, int j2) {
		// TODO Auto-generated method stub
		nullWhiteBlock[2][1] = 'W';
		for (int i = 0; i < nullWhiteBlock.length; i++) {
			for (int j = 0; j < nullWhiteBlock[1].length; j++)
				map[i2 * 5 + i][j2 * 3 + j] = nullWhiteBlock[i][j];
		}
		nullWhiteBlock[2][1] = ' ';

	}

	private void printRedBlockWithBlack(int i2, int j2) {
		// TODO Auto-generated method stub
		nullRedBlock[2][1] = 'B';
		for (int i = 0; i < nullRedBlock.length; i++) {
			for (int j = 0; j < nullRedBlock[1].length; j++)
				map[i2 * 5 + i][j2 * 3 + j] = nullRedBlock[i][j];
		}
		nullRedBlock[2][1] = ' ';

	}

	private void printWhiteBlockWithBlack(int i2, int j2) {

		// TODO Auto-generated method stub
		nullWhiteBlock[2][1] = 'B';
		for (int i = 0; i < nullWhiteBlock.length; i++) {
			for (int j = 0; j < nullWhiteBlock[1].length; j++)
				map[i2 * 5 + i][j2 * 3 + j] = nullWhiteBlock[i][j];
		}
		nullWhiteBlock[2][1] = ' ';

	}

	private void printRedBlockWithNothing(int i2, int j2) {
		// TODO Auto-generated method stub
		for (int i = 0; i < nullRedBlock.length; i++) {
			for (int j = 0; j < nullRedBlock[1].length; j++)
				map[i2 * 5 + i][j2 * 3 + j] = nullRedBlock[i][j];
		}

	}

	private void printWhiteBlockWithNothing(int i2, int j2) {
		// TODO Auto-generated method stub
		for (int i = 0; i < nullWhiteBlock.length; i++) {
			for (int j = 0; j < nullWhiteBlock[1].length; j++)
				map[i2 * 5 + i][j2 * 3 + j] = nullWhiteBlock[i][j];
		}

	}

	// Set board by user's input.
	public void setBoardByUser() {
		// TODO Auto-generated method stub
		clearBoard();
		do {
			System.out
					.println("Set chess position by equation(e.g:A1=W), enter -1 to exit.");
			String str = new Scanner(System.in).nextLine();
			if (str.equals("-1"))
				break;

			char col, color;
			int row;

			try {
				col = str.charAt(0);
				row = str.charAt(1) - '0';
				if (str.charAt(3) == 'W')
					this.setBlock(col, row, -1);
				if (str.charAt(3) == 'B')
					this.setBlock(col, row, 1);
			}

			catch (Exception e) {
				System.out.println("Wrong Input.");
				continue;
			}
		} while (true);

	}

	// Get a board instance.(single-instance mode)
	public static Board getInstance() {
		if (boardInstance == null)

		{
			boardInstance = new Board();
			// boardInstance.setBoardToNewTurn();
		}

		return boardInstance;
	}

}
