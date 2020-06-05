package sol.desk.demo1115.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sol.desk.demo1115.models.Inquiry;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    @Query(value = "SELECT id.* FROM inquiry id",nativeQuery = true)
    Inquiry findById();
}

