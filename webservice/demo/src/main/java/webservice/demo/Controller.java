package webservice.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
//import java.security.*;

import org.json.JSONObject;
//import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    /*
     * @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
     * public String index() {
     * 
     * return "{'success':true,'result':'ciao'}";
     * }
     */

    @GetMapping(value = "/register.php", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String Register(@RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password) {
        try {
            if (MethodDB.Instance().Register(username, password))
                return "{'status':'ok','result':'utente registrato'}";
            else
                return "{'status':'error','message':'utente gia registrato'}";
        } catch (SQLException e) {
            return "{'status':'error','message':'utente gia registrato'}";
        }
    }

    @GetMapping(value = "/getToken.php", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String GetToken(@RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password) {
        try {
            int id = MethodDB.Instance().GetToken(username, password);
            if (id >= 0) {
                String token = RW.AToken();
                if (MethodDB.Instance().SetToken(id, token))
                    return "{'status':'ok','result':{'token':'" + token + "'}}";
                else
                    return "{'status':'error','message':'username o passowrd errati'}";
            } else
                return "{'status':'error','message':'username o passowrd errati'}";
        } catch (SQLException e) {
            return "{'status':'error','message':'" + e.getMessage() + "'}";
        }
    }

    @GetMapping(value = "/setString.php", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String SetString(@RequestParam(name = "token") String token,
            @RequestParam(name = "key") String key, @RequestParam(name = "string") String string) {
        try {
            if (MethodDB.Instance().SetString(MethodDB.Instance().GetUtente(token).getInt("Id"), key, string)) {
                return "{'status':'ok','result':{'stringa inserira'}}";
            } else if (MethodDB.Instance().SetStringWhere(MethodDB.Instance().GetUtente(token).getInt("Id"), key, string))
                return "{'status':'error','message':'Stringa aggiornata'}";
            else
                return "{'status':'error','message':'token non valido'}";
        } catch (SQLException e) {
            return "{'status':'error','message':'" + e.getMessage() + "'}";
        }
    }

    @GetMapping(value = "/getString.php", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String GetString(@RequestParam(name = "token") String token,
            @RequestParam(name = "key") String key) {
        try {
            ResultSet rs = MethodDB.Instance().GetString(MethodDB.Instance().GetUtente(token).getInt("Id"), key);
            if (rs != null) {
                return "{'status':'ok','result':{'key':'"+key+"','string':'" + rs.getString("testo") + "'}}";
            } else
                return "{'status':'error','message':'key non trovata'}";
        } catch (SQLException e) {
            return "{'status':'error','message':'key non trovata'}";
        }
    }

    @GetMapping(value = "/deleteString.php", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String DeleteString(@RequestParam(name = "token") String token,
            @RequestParam(name = "key") String key) {
        try {
            if (MethodDB.Instance().DeleteString(MethodDB.Instance().GetUtente(token).getInt("Id"), key)) {
                return "{'status':'ok','result':{'testo':'stringa rimossa'}}";
            } else
                return "{'status':'error','message':'key non trovata'}";
        } catch (SQLException e) {
            return "{'status':'error','message':'key non trovata'}";
        }
    }

    @GetMapping(value = "/getKeys.php", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String GetKeys(@RequestParam(name = "token") String token) {
        try {
            ResultSet rs = MethodDB.Instance().GetKeys(MethodDB.Instance().GetUtente(token).getInt("Id"));
            if (rs != null) {
                List<String> keys = new ArrayList<String>();
                keys.add(rs.getString("chiave"));
                while (rs.next())
                    keys.add(rs.getString("chiave"));
                JSONObject ris = new JSONObject();
                ris.put("status", "ok");
                ris.put("result", keys);
                return ris.toString();
            } else
                return "{'status':'error','message':'token non valido'}";
        } catch (SQLException e) {
            return "{'status':'error','message':'token non valido'}";
        }
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String INDEX() {
        try {
            return RW.LeggiDaFile("index.txt");
        } catch (IOException e) {
            e.printStackTrace();
            return "errore di lettura";
        }

    }

}
