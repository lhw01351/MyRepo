package sol.desk.demo1115.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sol.desk.demo1115.models.Notice;
import sol.desk.demo1115.repositories.NoticeRepository;

import java.util.List;


@Service
@Primary
public class NoticeServiceJpa implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Override
    public List<Notice> findAll() {
        return this.noticeRepository.findAll();
    }

    @Override
    public Page<Notice> findAll(Pageable pageable) {
        return this.noticeRepository.findAll(pageable);
    }


    @Override
    public Notice findById(Long id) {
        return this.noticeRepository.findById(id).orElse(null);
    }

    @Override
    public Notice create(Notice notice) {
        return this.noticeRepository.save(notice); //db에 저장
    }

    @Override
    public Notice edit(Notice notice) {
        return this.noticeRepository.save(notice);
    }

    @Override
    public void deleteById(Long id) {
        this.noticeRepository.deleteById(id);

    }
}
