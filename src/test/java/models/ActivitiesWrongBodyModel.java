package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActivitiesWrongBodyModel {
    Integer id;
    String title;
    String dueDate;
    String completed;
}
