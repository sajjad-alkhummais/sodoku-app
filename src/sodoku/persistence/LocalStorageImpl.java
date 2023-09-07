package sodoku.persistence;

import sodoku.problemdomain.IStorage;
import sodoku.problemdomain.SudokuGame;

import java.io.*;

public class LocalStorageImpl implements IStorage {

    //this file is variable is like a URL, it is not the file itself it is like a path,
    //system.getProperty will retrieve the oprating system home directory and then the child
    //we will store a file with a the name: gamedata.txt, we will store and retrieve the
    // game data from this file.
    private static File GAME_DATA = new File(
            System.getProperty("user.home"),"gamedata.txt"
    );


    //this method writes data into the txt file
    @Override
    public void updateGameData(SudokuGame game) throws IOException {

        try{
            FileOutputStream fileOutputStream = new FileOutputStream(GAME_DATA);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(game);
            objectOutputStream.close();



        } catch (IOException e){

            throw new IOException("Unable to access Game Data");
        }

    }
    //this method retrieves from data into the txt file

    @Override
    public SudokuGame getGameData() throws IOException {


        FileInputStream fileInputStream = new FileInputStream(GAME_DATA);
        ObjectInputStream  objectInputStream = new ObjectInputStream(fileInputStream);
        SudokuGame gameState;
        try{

            gameState = (SudokuGame) objectInputStream.readObject();
            objectInputStream.close();

        } catch (ClassNotFoundException e){
            throw new IOException("File Not Found");

        }

        return gameState;
    }
}
