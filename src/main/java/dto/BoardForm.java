package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardForm {
    private String userName;
    private String password;
    private String title;
    private String content;
}
