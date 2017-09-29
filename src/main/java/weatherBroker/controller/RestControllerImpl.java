package weatherBroker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import weatherBroker.controller.eventResult.RestResult;

@RestController("restController")
@RequestMapping(value = "/rest")
public class RestControllerImpl {

    @Resource(name = "contactServise")
    private ContactService contactService;
    @Resource(name = "groupServise")
    private GroupService groupService;
    @Resource(name = "userServise")
    private UserService userService;
    @Resource(name = "factoryRestResult")
    private FactoryRestResult factoryRestResult;

    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    public RestResult getWeather(@RequestParam(value="city") String city){
        try {
            return factoryRestResult.getSuccessResult(EventType.CONTACT,contactService.addContact(contactName));
        } catch (DaoException e) {
            return factoryRestResult.getFailResult(e);
        }
    }
}
