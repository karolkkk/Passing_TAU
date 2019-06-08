package REST.servlets;

/**
 * Created by Owner on 08/06/2019.
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConcertController {

    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello World";
    }

}