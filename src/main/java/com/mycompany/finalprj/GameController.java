/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.finalprj;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import com.mycompany.finalprj.Move;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Optional;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author kryst
 */
public class GameController implements Initializable {

    // IDs for pieces
    public static final int EMPTY = 0;
    public static final int WHITE = 1;
    public static final int BLACK = 2;
    public static final int WHITE_KING = 1 + 3;
    public static final int BLACK_KING = 2 + 3;

    public static int numWhitePieces = 0;
    public static int numBlackPieces = 0;
    public static int numOfMovesWithoutKill = 0;

    @FXML
    private GridPane gameBoard;

    @FXML
    private Label lblCurrentTurn;

    FileChooser fileChooser = new FileChooser();

    //Move sampleMove = new Move(type, column, row) / Move(type, column, row, killColumn, killRow);
    public static ArrayList<Move> validMoves = new ArrayList<>();

    public static boolean isWhitesTurn = true;

    public static boolean isPieceSelected = false;
    public static int selectedColumn;
    public static int selectedRow;

    public static int piecesBackend[][] = {{0, 2, 0, 2, 0, 2, 0, 2},
    {2, 0, 2, 0, 2, 0, 2, 0},
    {0, 2, 0, 2, 0, 2, 0, 2},
    {0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0},
    {1, 0, 1, 0, 1, 0, 1, 0},
    {0, 1, 0, 1, 0, 1, 0, 1},
    {1, 0, 1, 0, 1, 0, 1, 0}};

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODOs
        fillBoard();
    }

    @FXML
    private void testMove(MouseEvent e) {
        // col index + row index
        //Node source = (Node)e.getSource();
        StackPane sPane = (StackPane) e.getSource();
        Integer colIndex = GridPane.getColumnIndex(sPane);
        Integer rowIndex = GridPane.getRowIndex(sPane);
        //System.out.printf("Mouse clicked cell [%d, %d]%n", colIndex, rowIndex);
        //System.out.println("" + sPane.getChildren());
        if (isPieceSelected) {
            makeMove(colIndex, rowIndex);
        } else {
            selectPiece(piecesBackend[rowIndex][colIndex], colIndex, rowIndex);
        }

    }

    @FXML
    private void buttonPressed(MouseEvent e) throws IOException {
        //System.out.println("Pressed reset button!");
        //System.out.println("White pcs: " + numWhitePieces);
        //System.out.println("Black pcs: " + numBlackPieces);
        clearBoard();
        int freshBoard[][] = {{0, 2, 0, 2, 0, 2, 0, 2},
        {2, 0, 2, 0, 2, 0, 2, 0},
        {0, 2, 0, 2, 0, 2, 0, 2},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {1, 0, 1, 0, 1, 0, 1, 0},
        {0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 0}};
        piecesBackend = freshBoard.clone();
        fillBoard();
        isWhitesTurn = true;
        updateTurnLabel();
    }

    @FXML
    private void loadGame(MouseEvent e) throws IOException {
        Window stage = gameBoard.getScene().getWindow();
        fileChooser.setTitle("Load Game");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Save file (*.txt)", "*.txt"));

        try {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                numOfMovesWithoutKill = 0;
                fileChooser.setInitialDirectory(file.getParentFile());

                Scanner sc = new Scanner(file);
                isWhitesTurn = Boolean.parseBoolean(sc.nextLine());
                updateTurnLabel();
                //System.out.println(sc.nextLine());
                //System.out.println();
                //sc.nextLine();
                //while(sc.hasNextLine()){
                clearBoard();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        piecesBackend[i][j] = Integer.parseInt(sc.nextLine());
                        //System.out.print("" + sc.nextLine());
                    }
                }
                //}
                sc.close();
                fillBoard();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void saveGame(MouseEvent e) throws IOException {
        Window stage = gameBoard.getScene().getWindow();
        fileChooser.setTitle("Save Game");
        fileChooser.setInitialFileName("checkersSave");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Save file (*.txt)", "*.txt"));

        try {
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                fileChooser.setInitialDirectory(file.getParentFile());

                FileWriter writer = new FileWriter(file);
                writer.write(Boolean.toString(isWhitesTurn));
                writer.write("\n");
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        writer.write(Integer.toString(piecesBackend[i][j]));
                        writer.write("\n");
                    }
                    //writer.write("\n");
                }
                writer.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    private void makeMove(int col, int row) {
        Move move = isValidMove(col, row);
        //if(move != null)System.out.println("Move type " + move.getType());
        if (move != null) {
            movePiece(col, row);
            if (move.getType() == 1) {
                removePiece(move.getKillColumn(), move.getKillRow());
                numOfMovesWithoutKill = 0;
            }
            checkAndMakeKing(col, row);
            isPieceSelected = false;
            removeHighlights();
            endTurn();
            validMoves.clear();
        } else {
            //System.out.println("Illegal move");
            isPieceSelected = false;
            removeHighlights();
            validMoves.clear();
        }
    }

    public Move isValidMove(int col, int row) {
        Move move = null;
        Optional<Move> tempMove = validMoves.stream().filter(o -> {
            return o.getTarColumn() == col && o.getTarRow() == row;
        }).findFirst();
        if (tempMove.isPresent()) {
            move = tempMove.get();
        }
        return move;
    }

    private void movePiece(int targetColumn, int targetRow) {
        addPiece(piecesBackend[selectedRow][selectedColumn], targetColumn, targetRow);
        piecesBackend[targetRow][targetColumn] = piecesBackend[selectedRow][selectedColumn];
        removePiece(selectedColumn, selectedRow);
    }

    private void addPiece(int type, int column, int row) {
        if (type == 1 || type == 4) {
            numWhitePieces++;
        } else {
            numBlackPieces++;
        }
        Piece tempPiece = new Piece(type);
        StackPane tarNode = (StackPane) getNodeFromGridPane(gameBoard, column, row);
        tarNode.getChildren().add(tempPiece.getPiece());
    }

    private void removePiece(int column, int row) {
        StackPane tarNode = (StackPane) getNodeFromGridPane(gameBoard, column, row);
        tarNode.getChildren().remove(0);
        if (piecesBackend[row][column] == 1 || piecesBackend[row][column] == 4) {
            numWhitePieces--;
        } else {
            numBlackPieces--;
        }
        piecesBackend[row][column] = EMPTY;
    }

    private void selectPiece(int type, int column, int row) {
        if (!isWhitesTurn && (type == 2 || type == 5)) {
            isPieceSelected = true;
            selectedColumn = column;
            selectedRow = row;
            MoveLogic.checkMoves(type, column, row);
            addHighlights();
        } else if (isWhitesTurn && (type == 1 || type == 4)) {
            isPieceSelected = true;
            selectedColumn = column;
            selectedRow = row;
            MoveLogic.checkMoves(type, column, row);
            addHighlights();
        } else {
            isPieceSelected = false;
        }
    }

    private void checkAndMakeKing(int col, int row) {
        if (row == 0 && piecesBackend[row][col] == 1) {
            removePiece(col, row);
            piecesBackend[row][col] = 4;
            addPiece(piecesBackend[row][col], col, row);
        } else if (row == 7 && piecesBackend[row][col] == 2) {
            removePiece(col, row);
            piecesBackend[row][col] = 5;
            addPiece(piecesBackend[row][col], col, row);
        }
    }

    private void endTurn() {
        if (isWhitesTurn) {
            isWhitesTurn = false;
            updateTurnLabel();
            checkVictory();
        } else {
            isWhitesTurn = true;
            updateTurnLabel();
            checkVictory();
        }
    }

    private void updateTurnLabel() {
        if (isWhitesTurn) {
            lblCurrentTurn.setText("Current turn - Player 1 (White)");
        } else {
            lblCurrentTurn.setText("Current turn - Player 2 (Black)");
        }
    }

    private void checkVictory() {
        if (numBlackPieces == 0) {
            createDialog("Player 1 won!");
        } else if (numWhitePieces == 0) {
            createDialog("Player 2 won!");
        } else {
            if (numOfMovesWithoutKill == 20) {
                createDialog("Game ended in a tie");
            } else {
                numOfMovesWithoutKill++;
            }
        }
    }

    private void createDialog(String message) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Game Over!");
        ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
        dialog.setContentText(message);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }

    private void addHighlights() {
        //Highlight highlight = new Highlight();
        validMoves.forEach(m -> {
            Highlight highlight = new Highlight();
            StackPane tarNode = (StackPane) getNodeFromGridPane(gameBoard, m.getTarColumn(), m.getTarRow());
            tarNode.getChildren().add(highlight.getHighlight());
        });
    }

    private void removeHighlights() {
        validMoves.forEach(m -> {
            StackPane tarNode = (StackPane) getNodeFromGridPane(gameBoard, m.getTarColumn(), m.getTarRow());
            tarNode.getChildren().remove(0);
        });
    }

    private void fillBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (piecesBackend[i][j] != 0) {
                    addPiece(piecesBackend[i][j], j, i);
                }
            }
        }
    }

    private void clearBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (piecesBackend[i][j] != 0) {
                    removePiece(j, i);
                }
            }
        }
    }

}
