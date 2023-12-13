package SantasFactory.NorthPoleElfs.Service.PDF;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;

public class DbToPDF {
    /*
        This method, fromDatabaseTableToPDF, generates a PDF document from a database table named "goodKid."
        It establishes a connection to the database, retrieves data from the "goodKid" table,
        and creates a PDF document containing the table data. The PDF includes a table structure with six columns:
        ID, Name, Lastname, Age, PhoneNumber, and Address. Each row in the table corresponds to a record in the "goodKid" table.
        The resulting PDF is saved as "fromDatabase.pdf" in the project directory. In case of any exceptions
        during the process, such as SQL errors or document handling issues, the method prints the stack trace for debugging purposes.
     */
    public void goodKidListFromDB() {

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            String sql = "SELECT * FROM goodkid";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("goodKid.pdf"));
            document.open();

            PdfPTable table = new PdfPTable(6);
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

            table.addCell(new PdfPCell(new Phrase("ID", boldFont)));
            table.addCell(new PdfPCell(new Phrase("Name", boldFont)));
            table.addCell(new PdfPCell(new Phrase("Lastname", boldFont)));
            table.addCell(new PdfPCell(new Phrase("Age", boldFont)));
            table.addCell(new PdfPCell(new Phrase("PhoneNumber", boldFont)));
            table.addCell(new PdfPCell(new Phrase("Address", boldFont)));

            while (resultSet.next()) {
                table.addCell(resultSet.getString("id"));
                table.addCell(resultSet.getString("name"));
                table.addCell(resultSet.getString("lastname"));
                table.addCell(resultSet.getString("age"));
                table.addCell(resultSet.getString("phonenumber"));
                table.addCell(resultSet.getString("address"));
            }

            document.add(table);

            document.close();
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException | DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*Method takes presents name and id number and joins it wit goodkid name, lastname and id number of a kid that the present belongs to*/
    public void listOfKidsWithTherePresents(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            String sql = "SELECT goodkid.id AS goodkid_id, goodkid.name, goodkid.lastname, " +
                    "present.id AS present_id, present.name AS present_name FROM goodkid " +
                    "JOIN present ON goodkid.id = present.goodkid_id";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("presentsAndKids.pdf"));
            document.open();

            PdfPTable table = new PdfPTable(5);
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

            table.addCell(new PdfPCell(new Phrase("Present ID", boldFont)));
            table.addCell(new PdfPCell(new Phrase("Present Name", boldFont)));
            table.addCell(new PdfPCell(new Phrase("Persons Name", boldFont)));
            table.addCell(new PdfPCell(new Phrase("Lastname", boldFont)));
            table.addCell(new PdfPCell(new Phrase("GoodKid ID", boldFont)));

            while (resultSet.next()) {
                table.addCell(resultSet.getString("present_id"));
                table.addCell(resultSet.getString("present_name"));
                table.addCell(resultSet.getString("name"));
                table.addCell(resultSet.getString("lastname"));
                table.addCell(resultSet.getString("goodkid_id"));
            }

            document.add(table);

            document.close();
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException | DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
