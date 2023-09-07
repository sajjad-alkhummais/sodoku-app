package sodoku.buildlogic;

import sodoku.computationlogic.GameLogic;
import sodoku.persistence.LocalStorageImpl;
import sodoku.problemdomain.IStorage;
import sodoku.problemdomain.SudokuGame;
import sodoku.userinterface.IUserInterfaceContract;
import sodoku.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {

    public static void build(IUserInterfaceContract.View userInterface)
            throws IOException{


        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try {

            initialState = storage.getGameData();

        } catch (IOException e){

            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic =
                new ControlLogic(storage, userInterface);

        userInterface.setListener(uiLogic);
        if(storage.getGameData() == null) System.out.println("Here");

        userInterface.updateBoard(initialState);

    }
}
