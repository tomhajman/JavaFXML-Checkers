/*
    Normal move (dependant on white/black)
    king move
    kill move

 */
package com.mycompany.finalprj;

import static com.mycompany.finalprj.GameController.piecesBackend;

/**
 *
 * @author kryst
 */
public class MoveLogic {

    public static void checkMoves(int type, int col, int row) {
        switch (type) {
            case 1:
                checkWhitePieceMoves(col, row);
                break;
            case 2:
                checkBlackPieceMoves(col, row);
                break;
            case 4:
                checkKingMoves(type, col, row);
                break;
            case 5:
                checkKingMoves(type, col, row);
                break;
            default:
                break;
        }
    }

    private static void checkBlackPieceMoves(int col, int row) {
        try {
            if (piecesBackend[row + 1][col - 1] == 0) {
                GameController.validMoves.add(new Move(0, col - 1, row + 1));
            } else if ((piecesBackend[row + 1][col - 1] == 1 || piecesBackend[row + 1][col - 1] == 4)
                    && piecesBackend[row + 2][col - 2] == 0) {
                GameController.validMoves.add(new Move(1, col - 2, row + 2, col - 1, row + 1));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        
        try{
            if (piecesBackend[row + 1][col + 1] == 0) {
                GameController.validMoves.add(new Move(0, col + 1, row + 1));
            } else if ((piecesBackend[row + 1][col + 1] == 1 || piecesBackend[row + 1][col + 1] == 4)
                    && piecesBackend[row + 2][col + 2] == 0) {
                GameController.validMoves.add(new Move(1, col + 2, row + 2, col + 1, row + 1));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        
        try {
            if ((piecesBackend[row - 1][col - 1] == 1 || piecesBackend[row - 1][col - 1] == 4)
                    && piecesBackend[row - 2][col - 2] == 0) {
                GameController.validMoves.add(new Move(1, col - 2, row - 2, col - 1, row - 1));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        
        try {
            if ((piecesBackend[row - 1][col + 1] == 1 || piecesBackend[row - 1][col + 1] == 4)
                    && piecesBackend[row - 2][col + 2] == 0) {
                GameController.validMoves.add(new Move(1, col + 2, row - 2, col + 1, row - 1));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    private static void checkWhitePieceMoves(int col, int row) {
        try {
            if (piecesBackend[row - 1][col - 1] == 0) {
                GameController.validMoves.add(new Move(0, col - 1, row - 1));
            } else if ((piecesBackend[row - 1][col - 1] == 2 || piecesBackend[row - 1][col - 1] == 5)
                    && piecesBackend[row - 2][col - 2] == 0) {
                GameController.validMoves.add(new Move(1, col - 2, row - 2, col - 1, row - 1));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            if (piecesBackend[row - 1][col + 1] == 0) {
                GameController.validMoves.add(new Move(0, col + 1, row - 1));
            } else if ((piecesBackend[row - 1][col + 1] == 2 || piecesBackend[row - 1][col + 1] == 5)
                    && piecesBackend[row - 2][col + 2] == 0) {
                GameController.validMoves.add(new Move(1, col + 2, row - 2, col + 1, row - 1));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        
        try {
            if ((piecesBackend[row + 1][col + 1] == 2 || piecesBackend[row + 1][col + 1] == 5)
                    && piecesBackend[row + 2][col + 2] == 0) {
                GameController.validMoves.add(new Move(1, col + 2, row + 2, col + 1, row + 1));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        
        try {
            if ((piecesBackend[row + 1][col - 1] == 2 || piecesBackend[row + 1][col - 1] == 5)
                    && piecesBackend[row + 2][col - 2] == 0) {
                GameController.validMoves.add(new Move(1, col - 2, row + 2, col - 1, row + 1));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
     }

    private static void checkKingMoves(int type, int col, int row) {
        checkTopLeftMoves(type, col, row);
        checkTopRightMoves(type, col, row);
        checkBottomLeftMoves(type, col, row);
        checkBottomRightMoves(type, col, row);
        
    }
    
    private static void checkTopLeftMoves(int type, int col, int row) {
        // row-- col--
        int pieceColor = (type == 4 ? 1 : 2);

        try{
        while(col > 0 && row > 0){
            row--;
            col--;
            if(piecesBackend[row][col] == 0) {
                GameController.validMoves.add(new Move(0, col, row));
            } else if(piecesBackend[row][col] == pieceColor || piecesBackend[row][col] == pieceColor+3){
                break;
            } else {
                if(piecesBackend [row-1][col-1] == 0) {
                    GameController.validMoves.add(new Move(1, col-1, row-1, col, row));
                    break;
                }
                else break;
            }
        }
        } catch (IndexOutOfBoundsException e) {
             System.out.println(e);
        }
    }
    
    private static void checkTopRightMoves(int type, int col, int row) {
        // row-- col++
        int pieceColor = (type == 4 ? 1 : 2);

        try{
        while(col < GameController.BOARD_WIDTH && row > 0){
            row--;
            col++;
            if(piecesBackend[row][col] == 0) {
                GameController.validMoves.add(new Move(0, col, row));
            } else if(piecesBackend[row][col] == pieceColor || piecesBackend[row][col] == pieceColor+3){
                break;
            } else {
                if(piecesBackend [row-1][col+1] == 0) {
                    GameController.validMoves.add(new Move(1, col+1, row-1, col, row));
                    break;
                }
                else break;
            }
        }
        } catch (IndexOutOfBoundsException e) {
             System.out.println(e);
        }
    }
    
    private static void checkBottomLeftMoves(int type, int col, int row){
        // row++ col--
        int pieceColor = (type == 4 ? 1 : 2);

        try{
        while(col > 0 && row < GameController.BOARD_HEIGHT){
            row++;
            col--;
            if(piecesBackend[row][col] == 0) {
                GameController.validMoves.add(new Move(0, col, row));
            } else if(piecesBackend[row][col] == pieceColor || piecesBackend[row][col] == pieceColor+3){
                break;
            } else {
                if(piecesBackend [row+1][col-1] == 0) {
                    GameController.validMoves.add(new Move(1, col-1, row+1, col, row));
                    break;
                }
                else break;
            }
        }
        } catch (IndexOutOfBoundsException e) {
             System.out.println(e);
        }
    }
    
    private static void checkBottomRightMoves(int type, int col, int row){
        // row++ col++
        int pieceColor = (type == 4 ? 1 : 2);

        try{
        while(col < GameController.BOARD_WIDTH && row < GameController.BOARD_HEIGHT){
            row++;
            col++;
            if(piecesBackend[row][col] == 0) {
                GameController.validMoves.add(new Move(0, col, row));
            } else if(piecesBackend[row][col] == pieceColor || piecesBackend[row][col] == pieceColor+3){
                break;
            } else {
                if(piecesBackend [row+1][col+1] == 0) {
                    GameController.validMoves.add(new Move(1, col+1, row+1, col, row));
                    break;
                }
                else break;
            }
        }
        } catch (IndexOutOfBoundsException e) {
             System.out.println(e);
        }
    }

}
