package dawid.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates getters, setters, toString, equals, and hashCode
@AllArgsConstructor // Generates a constructor with all fields
@NoArgsConstructor  // Generates a no-arguments constructor
public class EventMesage {
    private String eventType;
    private String timestamp;
    private String payload;
}
