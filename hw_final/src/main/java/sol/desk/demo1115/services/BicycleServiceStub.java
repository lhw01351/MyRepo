package sol.desk.demo1115.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sol.desk.demo1115.models.Bicycle;
import sol.desk.demo1115.repositories.BicycleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BicycleServiceStub implements BicycleService {

    private List<Bicycle> bicycle = new ArrayList<Bicycle>();

    @Autowired
    private BicycleRepository bicycleRepository;

    @Override
    public List<Bicycle> findAll() {
        return this.bicycle;
    }

    @Override
    public Page<Bicycle> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Bicycle> find10cycles() {
        //한 페이지에  10개를 출력
        return this.bicycle.stream().sorted( (a,b) ->
                b.getBicycleRegDate().compareTo(a.getBicycleRegDate()) ).limit(10).collect(Collectors.toList());
    }

    @Override
    public Bicycle addBicycle(Bicycle bicycleInfo) {
        bicycleInfo.setBicycleId(this.bicycle.stream().mapToInt(p -> p.getBicycleId()).max().getAsInt() + 1);
        this.bicycle.add(bicycleInfo);
        return bicycleInfo;
    }

    @Override
    public void deleteByBicycleId(int bicycleId) {
        for( int i = 0; i < this.bicycle.size(); i++){
            if( Objects.equals(this.bicycle.get(i).getBicycleId(), bicycleId)){
                this.bicycle.remove(i);
            }
        }
        throw new RuntimeException("다음 번호의 자전거를 찾을 수 없습니다.: " + bicycleId);
    }

    @Override
    public Bicycle findByBicycleId(Long bicycleId) {
        return this.bicycle.stream().filter(p -> Objects.equals(p.getBicycleId(), bicycleId)).findFirst().orElse(null);
    }

}
