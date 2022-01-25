package gradjanibrzogbroda.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserTokenState {

	private String accessToken,username,role;
    private Long expiresIn;

    public UserTokenState() {
    	this.role=null;
    	this.username=null;
        this.accessToken = null;
        this.expiresIn = null;
    }
}
