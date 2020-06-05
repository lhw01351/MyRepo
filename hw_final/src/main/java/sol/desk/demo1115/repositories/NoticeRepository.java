package sol.desk.demo1115.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sol.desk.demo1115.models.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Long> {

    @Query(value = "SELECT n.* FROM notice n",nativeQuery = true)
    Notice findById();
}


