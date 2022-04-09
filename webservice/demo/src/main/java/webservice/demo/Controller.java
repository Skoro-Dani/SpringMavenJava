package webservice.demo;

import java.sql.*;

import java.io.*;
//import java.security.*;

//import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String INDEX() {
        try {
            return LeggiDaFile("index.txt");
        } catch (IOException e) {
            e.printStackTrace();
            return "errore di lettura";
        }

    }

    @RequestMapping(value = "/register.php", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public String Register(@RequestParam(value = "username") String username1,
            @RequestParam(value = "password") String Password1) {

        try {
            Statement stmt;

            String sql_select ="INSERT INTO utente(username,password) VALUES(?,?)";
            System.out.println(sql_select);
            String ris = "giusto";

            Connection conn = DBconnection.createNewDBconnection();

            PreparedStatement sql = conn.prepareStatement(sql_select);
            sql.setString(1, username1);
            sql.setString(2, Password1);
            sql.executeUpdate();
            sql.close();
            //stmt.execute(sql_select);

            
            return ris;
        } catch (Exception e) {
            return e.toString();
        }

    }

    @RequestMapping(value = "/getToken.php", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public double GetToken(@RequestParam(value = "username") String username,
            @RequestParam(value = "password") String Password) {

        return Math.random();
    }

    public String LeggiDaFile(String nomeFile) throws FileNotFoundException, IOException {
        // variabile di appoggio
        File file = new File(nomeFile);
        String RIS = "";
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = br.readLine();
        while (line != null) {
            RIS += line;
            RIS += "\n";
            line = br.readLine();
        }
        br.close();
        return RIS;
    }

    public void ScriviSuFileURL(String NomeFile) throws IOException {

        File f = new File(NomeFile);
        FileWriter fw = new FileWriter(f);

        fw.write("ciao");
        fw.flush();
        fw.close();

    }

}
