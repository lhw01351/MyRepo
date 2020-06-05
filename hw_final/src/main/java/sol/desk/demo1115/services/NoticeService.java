package sol.desk.demo1115.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sol.desk.demo1115.models.Notice;

import java.util.List;

public interface NoticeService {

    List<Notice> findAll();
    Page<Notice> findAll(Pageable pageable);
    Notice findById(Long id);
    Notice create(Notice notice);
    Notice edit(Notice notice);
    void deleteById(Long id);
}


