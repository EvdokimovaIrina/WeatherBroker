package weatherBroker.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import weatherBroker.controller.eventResult.EventType;
import weatherBroker.controller.eventResult.FactoryRestResult;
import weatherBroker.controller.eventResult.RestResult;
import weatherBroker.service.WeatherService;

import javax.annotation.Resource;

@RestController("restController")
@RequestMapping(value = "/rest")
public class RestControllerImpl {

    @Resource(name = "contactServise")
    private WeatherService weatherService;
       @Resource(name = "factoryRestResult")
    private FactoryRestResult factoryRestResult;

    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    public RestResult getWeather(@RequestParam(value="city") String city){

        final String uri = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Saratov%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

       /* try {
            return factoryRestResult.getSuccessResult(EventType.CONTACT,contactService.addContact(contactName));
        } catch (Exception e) {
            return factoryRestResult.getFailResult(e);
        }*/
       return new RestResult(EventType.WEATHER,new Object());
    }
}
