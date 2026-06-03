package response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserShortResponse {

    private Long id;
    private String fullName;
    private String email;
    private String groupName;
    private String role;
    private String status;
}