package models;

import lombok.Data;

@Data
public class ActivitiesResponseModel {
    Integer id;
    String title;
    String dueDate;
    Boolean completed;
}
