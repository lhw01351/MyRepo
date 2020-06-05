package sol.desk.demo1115.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sol.desk.demo1115.models.Customers;
import sol.desk.demo1115.models.Inquiry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InquiryServiceStub implements InquiryService{

    private List<Inquiry> inquiry = new ArrayList<Inquiry>(){};


    @Override
    public List<Inquiry> findAll() {
        return this.inquiry;
    }

    @Override
    public Inquiry findById(Long id) {
        return this.inquiry.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst().orElse(null);
    }

    @Override
    public Inquiry create(Inquiry inquiry) {
        // Calculate next post ID
        inquiry.setId(this.inquiry.stream().mapToLong(p -> p.getId()).max().getAsLong() + 1);
        this.inquiry.add(inquiry);
        return inquiry;
    }

    @Override
    public Inquiry edit(Inquiry inquiry) {
        for( int i = 0; i < this.inquiry.size(); i++){
            if( Objects.equals(this.inquiry.get(i).getId(), inquiry.getId())){
                this.inquiry.set(i, inquiry);
                return inquiry;
            }
        }
        throw new RuntimeException("게시물을 찾을 수 없습니다 : " + inquiry.getId());
    }

    @Override
    public void deleteById(Long id) {
        for( int i = 0; i < this.inquiry.size(); i++){
            if( Objects.equals(this.inquiry.get(i).getId(), id)){
                this.inquiry.remove(i);
            }
        }
        throw new RuntimeException("게시물을 찾을 수 없습니다 : " + id);
    }
    @Override
    public Page<Inquiry> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }
}
