package SantasFactory.NorthPoleElfs.Service.JsonReadingService;

import SantasFactory.Gifts.GoodKid;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonToJavaConverter {

    /*
    Method that takes json file and by using gson import converts it to java List of goodKids, every
    goodKid contains a list of its own present list.
     */
    public List<GoodKid> getDataFromJSON(String filePath) {
        Gson gson = new Gson();
        Type goodKidListType = new TypeToken<List<GoodKid>>() { // formats list according to goodKid class.
        }.getType();

        List<GoodKid> goodKidsFromFile;

        try {
            Reader reader = new FileReader(filePath);
            goodKidsFromFile = gson.fromJson(reader, goodKidListType);
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't read json file!");
            throw new RuntimeException(e);
        }
        return goodKidsFromFile;
    }

}
