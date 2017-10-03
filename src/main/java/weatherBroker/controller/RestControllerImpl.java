package weatherBroker.controller;

import org.apache.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import weatherBroker.controller.eventResult.EventType;
import weatherBroker.controller.eventResult.FactoryRestResult;
import weatherBroker.controller.eventResult.RestResult;
import weatherBroker.model.Weather;
import weatherBroker.service.WeatherService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController("restController")
@RequestMapping(value = "/rest")
public class RestControllerImpl {

    @Resource(name = "weatherService")
    private WeatherService weatherService;
    @Resource(name = "factoryRestResult")
    private FactoryRestResult factoryRestResult;

    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public RestResult getWeather(@RequestParam(value="city") String city){
        final String url = "http://graph.facebook.com/pivotalsoftware";
        final String urlTesr = "https://htmlweb.ru/service/api.php?bic=043469751&json&jsonp=parseResponse";

        RestTemplate restTemplate = new RestTemplate();
        //ResponseEntity responseEntity
        String resp = restTemplate.getForObject("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Saratov%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys", String.class);

      /*try {
        final String uri = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Saratov%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
       //   HttpEntity entit = new HttpEntity();

       // JSONObject jsonObject = new JSONObject(new RestTemplate().getForObject("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Saratov%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys", String.class));
        String result = restTemplate.getForObject(uri, String.class);

    } catch (HttpClientErrorException e) {
        System.out.println(e.getStatusCode());
        System.out.println(e.getResponseBodyAsString());
    }

       *//* try {
            return factoryRestResult.getSuccessResult(EventType.CONTACT,contactService.addContact(contactName));
        } catch (Exception e) {
            return factoryRestResult.getFailResult(e);
        }*/
       return new RestResult(EventType.WEATHER,new Object());
    }
}
