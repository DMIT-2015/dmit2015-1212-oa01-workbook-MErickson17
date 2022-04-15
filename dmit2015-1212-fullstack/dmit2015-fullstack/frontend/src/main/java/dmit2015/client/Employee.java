package dmit2015.client;

import lombok.Data;

@Data
public class Employee {
// data class, this is NOT an entity and does not map to a table
    private Long id;
    private String name;
    private String role;

}