package sol.desk.demo1115.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "customers",unique = false)
    private Customers customers;

    @Column(name = "title", nullable=false, length = 50)
    private String title;

    @Column(name = "content", nullable=false, length = 5000)
    private String content;

    @Column(name="regDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date regDate;

    @Column(name="updateDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;

    @OneToMany(mappedBy = "customer")
    private Set<Rent> rent;

}


