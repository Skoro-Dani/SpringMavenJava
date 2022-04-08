package webservice.demo;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		DBconnection.createNewDBconnection();

		Statement stmt;
		ResultSet results;

		String sql_select = "Select * From utente";

		try (Connection conn = DBconnection.createNewDBconnection()) {

			stmt = (java.beans.Statement) conn.createStatement();
			results = ((java.sql.Statement) stmt).executeQuery(sql_select);

			List<utente> studentsList = new ArrayList<utente>();

			while (results.next()) {

				utente stdObject = new utente();

				stdObject.setID(results.getString("ID"));
				stdObject.setpassword(results.getString("username"));
				stdObject.setusername(results.getString("password"));

				studentsList.add(stdObject);
			}

			ObjectMapper mapper = new ObjectMapper();
			String JSONOutput = mapper.writeValueAsString(studentsList);
			System.out.println(JSONOutput);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
