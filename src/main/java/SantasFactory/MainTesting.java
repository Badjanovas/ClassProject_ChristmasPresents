package SantasFactory;

import SantasFactory.Gifts.GoodKid;
import SantasFactory.Gifts.GoodKidFactory;
import SantasFactory.Gifts.Present;
import SantasFactory.NorthPoleElfs.Interaction.InteractionWithDatabaseJDBC;
import SantasFactory.NorthPoleElfs.Repository.GoodKidRepository;
import SantasFactory.NorthPoleElfs.Service.JsonReadingService.JsonToJavaConverter;
import SantasFactory.NorthPoleElfs.Service.PDF.DbToPDF;

import java.sql.SQLException;
import java.util.List;

public class MainTesting {
    public static void main(String[] args) throws SQLException {
        GoodKid ben = GoodKidFactory.createBen();
        GoodKid sara = GoodKidFactory.createSara();
        GoodKid alex = GoodKidFactory.createAlex();

        List<GoodKid> goodKids = new JsonToJavaConverter().getDataFromJSON("src/main/resources/SantasFavorites.json");

        GoodKidRepository goodKidRepository = new GoodKidRepository();
        goodKidRepository.saveAll(goodKids);
        goodKidRepository.removeKid(3L);
        goodKidRepository.save(sara);
        goodKidRepository.save(ben);
        goodKidRepository.save(alex);
        goodKidRepository.removeKid(6L);
        goodKidRepository.addPresent(9L, new Present("computer", 800, "Gaming computer", 3.3, "Black"));

        InteractionWithDatabaseJDBC interaction = new InteractionWithDatabaseJDBC();
        interaction.fetchMostExpensivePresent();
        //interaction.fetchPresentsByGivenColor("Black");
       // interaction.fetchPresentsHeavierThanGivenWeight(7);
       // interaction.fetchYoungestReceiver();
       // interaction.fetchReceiverByLastName("Williams");
       // interaction.fetchAllReceiversAddresses();

        DbToPDF dbToPDF = new DbToPDF();
        //dbToPDF.goodKidListFromDB();
        // dbToPDF.listOfKidsWithTherePresents();

    }
}
