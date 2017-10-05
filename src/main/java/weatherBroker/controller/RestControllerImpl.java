package weatherBroker.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import weatherBroker.model.QueryWeather;
import weatherBroker.model.Weather;
import weatherBroker.service.WeatherService;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
    public RestResult getWeather(@RequestParam(value = "city") String city) {
        String url = "https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in " +
                "(select woeid from geo.places(1) where text =\"" + city + "\")&format=json";
        String url2 = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Saratov%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        String urlTesr;

        RestTemplate restTemplate = new RestTemplate();


        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode;
        QueryWeather weather;
        try {
            jsonNode = restTemplate.getForObject(url, JsonNode.class).get("query");
            weather = mapper.readValue(jsonNode.traverse(),QueryWeather.class);

        } catch (Exception e) {
            return new RestResult(EventType.ERROR, e);
        }

        return new RestResult(EventType.WEATHER, weather);
    }
}
