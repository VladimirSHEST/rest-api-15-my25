package homeWork;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)


@Data
public class ResponseModel {
    private String name;
    private String job;


}
