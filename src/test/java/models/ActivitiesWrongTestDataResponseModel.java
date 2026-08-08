package models;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ActivitiesWrongTestDataResponseModel {

    String type;
    String title;
    Integer status;
    String traceId;
    Map<String, List<String>> errors;
}
