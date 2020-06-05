package sol.desk.demo1115.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bicycleRentId", nullable = false)
    private long bicycleRentId;

    //bicycleId 외부키로 갖고오기
    @ManyToOne
    @JoinColumn(name = "bicycleId",unique = false, nullable = false)
    private Bicycle bicycle;

    //customerId 외부키로 갖고오기
    @ManyToOne
    @JoinColumn(name = "customerId",unique = false, nullable = false)
    private Customers customer;

    //대여중인지의 여부
    @Column(name="usingNow")
    private boolean usingNow;

    //대여 시작 일시
    @Column(name="rentDateTime", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date rentDateTime;

    //대여 종료 일시
    @Column(name="returnDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date returnDateTime;

    //대여 기간
    @Column(name="rentPeriod")
    private long rentPeriod;

    //총 대여비용
    @Column(name="rentPrice")
    private int rentPrice;

}
