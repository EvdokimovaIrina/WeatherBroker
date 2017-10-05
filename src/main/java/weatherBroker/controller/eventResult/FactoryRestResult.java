package weatherBroker.controller.eventResult;

import weatherBroker.model.Weather;

public class FactoryRestResult {
    public RestResult getSuccessResult(EventType eventType, Object object) {
        
        return new RestResult(EventType.ERROR, "Ошибка получения данных");
    }
}
