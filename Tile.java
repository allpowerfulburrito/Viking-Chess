
package com.Vchess.engine.board;

import com.Vchess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;


/* This is the code for a single tile object.  From this we can build the board.
 * There will be a sub-class for an empty tile, one for an occupied tile, and I think we may
 * need one for the king's throne tile...CHECK WITH STEVE. */

/*Tile is an abstract class, meaning I can't instantiate a "new Tile" object.
But its sub-classes EmptyTile and OccupiedTile are concrete, so we can
instantiate those.
 */
public abstract class Tile {
    /*
    protected can only be accessed by is sub-classes
    final - can only be set once, at construction time.
    Do this in the constructors of the sub-classes as well.
    IMMUTABILITY: we can now create all the valid empty tiles up front and get them whenever
    */
    protected final int tileCoordinate;

    private static final Map<Integer, EmptyTile>EMPTY_TILES = createAllPossibleEmptyTiles();



    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        /* sets the board to be 13x13.  Think this might be a larger scope variable somewhere else.
        * Ask Steve.  Make it a private int in the controller class*/
        int boardDim = 13;

        for (int i = 0; i < boardDim*boardDim; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }
        /* I want an immutable map/container
        * GOTTA GET GUAVA FROM GOOGLE AND INSTALL IT FOR THIS NEXT BIT TO WORK.*/
        return ImmutableMap.copyOf(emptyTileMap);

    }
    //only method a user can use to create a tile - empty tiles from a cache, creating individual Occupied tilse as we go
    public static Tile createTile(final int tileCoordinate, final Piece piece){
        if (piece != null)
            return new OccupiedTile(tileCoordinate, piece);
        else
            return EMPTY_TILES.get(tileCoordinate);
    }

    //Constructor
    private Tile(int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }

    //Define an abstract method here that will be implemented in a sub-class of Tile
    public abstract boolean isTileOccupied();

    //for another class Piece, this will get the piece off a given tile.
    public abstract Piece getPiece();

    //create a concrete sub-class EmptyTile
    public static final class EmptyTile extends Tile {

        EmptyTile(final int coordinate){
            super(coordinate);
        }

        @Override
        public boolean isTileOccupied(){
            return false;
        }

        @Override
        public Piece getPiece(){
            return null;
        }

     }

    //create a concrete sub-class OccupiedTile
     public static final class OccupiedTile extends Tile {

        private final Piece pieceOnTle;

        OccupiedTile(int tileCoordinate, Piece pieceOnTile) {
            super(tileCoordinate);
            this.pieceOnTle = pieceOnTile;

        }

        @Override
         public boolean isTileOccupied() {
            return true;
        }

        @Override
         public Piece getPiece() {
            return this.pieceOnTle;
        }


     }

}
