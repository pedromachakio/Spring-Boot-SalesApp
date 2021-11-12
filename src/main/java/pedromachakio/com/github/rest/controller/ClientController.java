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
            value = {"/hello/{name}", "/greetings/{name}"},  // o mapping pode ter vários paths
            method = RequestMethod.GET
            // consumes {"application/json", "application/xml"} se tivesse um RequestBody nos args do método para o utilizador fornecer json ou xml
            // produces {"application/json", "application/xml"} se fosse para devolver json ou xml (json é default e implicit)
    )
    @ResponseBody // diz que o retorno do método será o body da HTTP response
    public String helloClient(@PathVariable("name") String clientName) {
        return String.format("Hello %s ", clientName); // new java 5+ string interpolation
    }
}
