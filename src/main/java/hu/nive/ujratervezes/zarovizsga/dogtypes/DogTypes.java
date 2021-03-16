package hu.nive.ujratervezes.zarovizsga.dogtypes;

import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class DogTypes {

    private MariaDbDataSource dataSource;

    public DogTypes(MariaDbDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<String> getDogsByCountry(String country) {
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT name FROM dog_types WHERE country = ?")
        ) {
            stmt.setString(1, country.toUpperCase());

            List<String> result = getStringListFromPreparedStatement(stmt);
            result.sort(Collator.getInstance(new Locale("hu", "HU")));
            return result;

        } catch (SQLException se) {
            throw new IllegalStateException("Cannot connect", se);
        }
    }

    private List<String> getStringListFromPreparedStatement(PreparedStatement stmt) {
        List<String> result = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name").toLowerCase();
                result.add(name);
            }
        } catch (SQLException se) {
            throw new IllegalStateException("Cannot select", se);
        }
        return result;
    }
}
