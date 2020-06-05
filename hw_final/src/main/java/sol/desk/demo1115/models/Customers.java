package sol.desk.demo1115.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="customerId", nullable = false,length = 30,unique = true)
    @NotEmpty(message = "아이디를 입력해주세요")
    public String customerId;

    @Column(length = 60)
    @Length(min=5, message = "비밀번호는 5자 이상으로 입력해주세요")
    @NotEmpty(message = "패스워드를 입력하세요")
    private String customer_login_password;

    @Column(length = 60)
    @Length(min=5, message = "비밀번호는 5자 이상으로 입력해주세요")
    @NotEmpty(message = "확인 패스워드를 입력해주세요.")
    private String customer_login_password_check;

    @Column(name="customer_name", nullable = false,length = 30)
    @Pattern(regexp = "\\S{2,8}", message="이름을 공백없이 2~6자로 입력해주세요.")
    private String customer_name;

    @Email(message="이메일 형식에 맞춰 올바르게 입력해주세요.")
    @Column(name="customer_email", nullable = false,length = 30, unique = true)
    private String customerEmail;

    @Column(name="phone_num",unique = true)
    @NotBlank(message = "전화번호를 작성해주세요.")
    @Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력가능합니다")
    private String phoneNum;

    @Column(name="rental_password", nullable = false)
    @NotEmpty(message = "대여 비밀번호를 입력해주세요.")
    private String rental_password;

    @Column(name="years",nullable=false,length = 4)
    private int years;

    @Column(name="months",nullable=false,length = 2)
    private int months;

    @Column(name="days",nullable=false,length = 2)
    private int days;

    @OneToMany(mappedBy = "customers")
    private Set<Inquiry> inquiry = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<Rent> rent = new HashSet<>();


}
