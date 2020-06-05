package sol.desk.demo1115.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bicycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bicycleId", nullable = false)
    private Integer bicycleId;

    @Column(name="bicycleModel", nullable = false, length = 30)
    @NotEmpty(message = "자전거 모델명을 입력하세요.")
    private String bicycleModel;

    @Column(name="bicyclePrice", nullable = false)
    @Range(min=1L, message = "자연수 값을 입력해주세요.")
    private int bicyclePrice;

    @Column(name="bicycleRegDate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bicycleRegDate;

    @Column(name="bicycleAddress", nullable = false,length = 30)
    @NotEmpty(message = "자전거 주소를 입력하세요.")
    private String bicycleAddress;

    @Column(name="bicycleLatitude", nullable = false,length = 30)
    private double bicycleLatitude;

    @Column(name="bicycleLongitude", nullable = false,length = 30)
    private double bicycleLongitude;

    @OneToMany(mappedBy = "bicycle")
    private Set<Rent> rent;

}
