package SantasFactory.NorthPoleElfs.Repository;

import SantasFactory.Gifts.GoodKid;
import SantasFactory.Gifts.Present;
import SantasFactory.NorthPoleElfs.Service.HiernateService.GoodKidHibernateService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GoodKidRepository {
    /*
        Method for saving list of goodKids that, have a list of presents, into database
        by using Hibernate.
     */
    public void saveAll(List<GoodKid> goodKids) {
        Session session = GoodKidHibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        for (GoodKid goodKid : goodKids) {
            for (Present present : goodKid.getPresents()) {
                present.setGoodKid(goodKid);
                session.save(present);
            }
            session.save(goodKid);
        }
        transaction.commit();
        session.close();
        System.out.println("Kids where saved successfully into DB!");
    }

    /*
    Method for adding new goodKid objects to database by using Hibernate.
     */
    public void save(GoodKid goodKid) {
        Session session = GoodKidHibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        for (Present present : goodKid.getPresents()) {
            present.setGoodKid(goodKid);
            session.save(present);
        }
        session.save(goodKid);
        transaction.commit();
        session.close();
        System.out.println(goodKid.getName() + " " + goodKid.getLastName() + " Was added to the list successfully! ");
    }

    /*
        Method for adding present to any goodKid by using his id number from database.
     */
    public void addPresent(Long goodKidId, Present newPresent) {
        Session session = GoodKidHibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        GoodKid goodKid = session.get(GoodKid.class, goodKidId);
        if (goodKid != null) {
            goodKid.getPresents().add(newPresent);
            session.update(goodKid);
            for (Present present : goodKid.getPresents()) {
                present.setGoodKid(goodKid);
                session.save(present);
            }
            transaction.commit();
            System.out.println(newPresent.getName() + " was added to " + goodKid.getName() + " " + goodKid.getLastName() + " present list!");
        } else {
            System.out.println("Person with ID number" + goodKidId + " doesn't exist. ");
        }

        session.close();
    }

    // Method for removing goodKid object from database list, using Hibernate.
    public void removeKid(Long goodKidId) {
        Session session = GoodKidHibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        GoodKid notSoGoodKid = session.get(GoodKid.class, goodKidId);

        if (goodKidId != null) {
            session.delete(notSoGoodKid);
            transaction.commit();
            System.out.println(notSoGoodKid.getName() + " " + notSoGoodKid.getLastName() + " was deleted from good kid's list :(");
        } else {
            System.out.println("Person with ID number" + goodKidId + " doesn't exist. ");
        }
        session.close();
    }



    /*
       neveikia, reikia patvarkyti kai bus laiko. Gali buti kad reikia loopinti per pacius dovanu listus ir taip isimineti, neuztenka remove
    */
    /*
    public void removePresent(Long goodKidId, Long presentId) {
        Session session = GoodKidHibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        GoodKid goodKid = session.get(GoodKid.class, goodKidId);
        Present presentToRemove = session.get(Present.class, presentId);
        if (goodKid != null && presentToRemove != null) {
            if (goodKid.getPresents().contains(presentToRemove)){
                session.delete(presentToRemove);
            }
            session.update(goodKid);
            transaction.commit();
            System.out.println(presentToRemove.getName() + " was successfully removed from " + goodKid.getName() + " " + goodKid.getLastName() + "'s present list!");
        } else {
            System.out.println("Present with ID number " + presentId + " not found in " + goodKid.getName() + " " + goodKid.getLastName() + "'s present list!");
        }
        session.close();
    }*/

}




