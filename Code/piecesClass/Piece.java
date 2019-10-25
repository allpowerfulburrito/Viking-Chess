package com.Vchess.engine.pieces;

import com.Vchess.engine.board.Board;
import com.Vchess.engine.board.Move;

import java.util.List;

//make this class abstract, then all the different pieces will be sub-classes of it.
public abstract class Piece {
    //every piece has a piece position
    protected final int piecePosition;
    protected final  Alliance pieceAlliance; //Alliance will be an enum - sorta like a class

    Piece(final int piecePosition, final Alliance pieceAlliance){
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
    }

    /* For each piece create a list of legal moves when its that piece's turn.
    * All the different pieces will overwrite this method.  So it will always be Rook's moves
    * unless the player chooses to roll the dice and get different moves.
    *       *so this method will have to be called when a dice is rolled as well.
    *       *it will also have to be called if the player just decides to move in the typical rook way.
    * */
    public abstract List<Move> calculateLegalMoves(final Board board);



}