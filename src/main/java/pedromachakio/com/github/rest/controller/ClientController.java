package pedromachakio.com.github.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/clients")
public class ClientController {

    @RequestMapping(
            value = {"/hello/{name}", "/greetings/{name}"},
            method = RequestMethod.GET

    )
    @ResponseBody
    public String helloClient(@PathVariable("name") String clientName) {
        return String.format("Hello %s ", clientName);
    }
}
