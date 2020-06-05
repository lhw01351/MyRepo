package sol.desk.demo1115.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sol.desk.demo1115.models.Notice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class NoticeServiceStub implements NoticeService {

    private List<Notice> notice = new ArrayList<Notice>(){};

    @Override
    public List<Notice> findAll() {
        return this.notice;
    }


    @Override
    public Notice findById(Long id) {
        return this.notice.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst().orElse(null);
    }

    @Override
    public Notice create(Notice notice) {
        notice.setId(this.notice.stream().mapToLong(p -> p.getId()).max().getAsLong() + 1);
        this.notice.add(notice);
        return notice;
    }

    @Override
    public Notice edit(Notice notice) {
        for( int i = 0; i < this.notice.size(); i++){
            if( Objects.equals(this.notice.get(i).getId(), notice.getId())){
                this.notice.set(i, notice);
                return notice;
            }
        }
        throw new RuntimeException("게시물을 찾을 수 없습니다 : " + notice.getId());
    }

    @Override
    public void deleteById(Long id) {
        for( int i = 0; i < this.notice.size(); i++){
            if( Objects.equals(this.notice.get(i).getId(), id)){
                this.notice.remove(i);
            }
        }
        throw new RuntimeException("게시물을 찾을 수 없습니다 : " + id);
    }
    @Override
    public Page<Notice> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }
}
