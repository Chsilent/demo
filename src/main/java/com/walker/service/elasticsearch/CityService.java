package com.walker.service.elasticsearch;

import com.walker.bean.City;
import com.walker.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市 ES service
 *
 * @author Walker
 * @date 2019/1/23 下午5:36
 */
@Service
public class CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityService.class);

    // 分页参数
    private static final Integer pageNumber = 0;
    private static final Integer pageSize = 10;
    Pageable pageable = PageRequest.of(pageNumber, pageSize);

    @Autowired
    private CityRepository cityRepository;

    /**
     * save 操作
     *
     * @param city
     * @return
     */
    public long save(City city) {
        City result = cityRepository.save(city);
        return result.getId();
    }

    /**
     * 根据description查询操作
     *
     * @param description
     * @return
     */
    public List<City> findByDescription(String description) {
        return cityRepository.findByDescription(description, pageable).getContent();
    }

    /**
     * update 操作
     *
     * @param city
     * @return
     */
    public long update(City city) {
        City result = cityRepository.save(city);
        return result.getId();
    }

    /**
     * delete 操作
     *
     * @param id
     */
    public void delete(long id) {
        cityRepository.deleteById(id);
    }

}
