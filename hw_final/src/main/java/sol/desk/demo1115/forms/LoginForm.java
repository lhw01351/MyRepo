package sol.desk.demo1115.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

    @Size(min=2, max=30, message="[2...30]까지 입력가능")
    private String customerId;

    @NotNull
    @Size(min=1, max=50)
    private String customer_login_password;

}
