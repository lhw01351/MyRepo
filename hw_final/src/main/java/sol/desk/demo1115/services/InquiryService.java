package sol.desk.demo1115.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sol.desk.demo1115.models.Inquiry;

import java.util.List;

public interface InquiryService {

    List<Inquiry> findAll();
    Page<Inquiry> findAll(Pageable pageable);
    Inquiry findById(Long id);
    Inquiry create(Inquiry inquiry);
    Inquiry edit(Inquiry inquiry);
    void deleteById(Long id);
}
