package sol.desk.demo1115.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sol.desk.demo1115.config.WebSecurityConfig;
import sol.desk.demo1115.models.Inquiry;
import sol.desk.demo1115.repositories.InquiryRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@Primary
public class InquiryServiceJpa implements InquiryService{


    @Autowired
    private InquiryRepository inquiryRepository;

    @Override
    public List<Inquiry> findAll() {
        return this.inquiryRepository.findAll();
    }

    @Override
    public Page<Inquiry> findAll(Pageable pageable) {
        return this.inquiryRepository.findAll(pageable);
    }

    @Override
    public Inquiry findById(Long id) {
        return this.inquiryRepository.findById(id).orElse(null);
    }

    @Override
    public Inquiry create(Inquiry inquiry) {
        return this.inquiryRepository.save(inquiry); //db에 저장
    }

    @Override
    public Inquiry edit(Inquiry inquiry) {
        return this.inquiryRepository.save(inquiry);
    }

    @Override
    public void deleteById(Long id) {
        this.inquiryRepository.deleteById(id);

    }
}
