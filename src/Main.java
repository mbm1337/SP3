import util.*;

public class Main {
    public static void main(String[] args){

        Streaming streaming = new Streaming();
        FileIO io = new FileIO();

        streaming.streamSetup();
        streaming.mainMenu();
    }
}