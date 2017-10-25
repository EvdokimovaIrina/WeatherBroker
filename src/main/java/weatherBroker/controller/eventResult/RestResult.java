package weatherBroker.controller.eventResult;

public class RestResult {
    private EventType eventType;
    private Object mainObject;

    public RestResult(EventType eventType, Object mainObject) {
        this.eventType = eventType;
        this.mainObject = mainObject;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Object getMainObject() {
        return mainObject;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public void setMainObject(Object mainObject) {
        this.mainObject = mainObject;
    }

    @Override
    public boolean equals(Object obj) {
        RestResult restResultObj = (RestResult)obj;
        return (this.eventType == restResultObj.getEventType() & this.mainObject == restResultObj.getMainObject());

    }
}
