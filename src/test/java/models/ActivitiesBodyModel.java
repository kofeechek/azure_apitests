package models;

import lombok.Builder;
import lombok.Data;

@Data
public class ActivitiesBodyModel {
    Integer id;
    String title;
    Integer wrongTitle;
    String dueDate;
    Boolean completed;
   }
