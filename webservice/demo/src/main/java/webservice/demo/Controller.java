package webservice.demo;


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
        return "ss";
    }

    @RequestMapping(value = "/register.php", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public String Register(@RequestParam(value="username") String username, @RequestParam(value="password")String Password) {

        return "username:"+username+" Password: "+Password;
    }
    @RequestMapping(value = "/getToken.php", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public double GetToken(@RequestParam(value="username") String username, @RequestParam(value="password")String Password) {

        return Math.random();
    }

}
