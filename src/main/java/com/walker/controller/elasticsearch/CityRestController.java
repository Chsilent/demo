package com.walker.controller.elasticsearch;

import com.walker.bean.City;
import com.walker.common.utils.ResultInfo;
import com.walker.common.utils.ReturnCodeUtil;
import com.walker.service.elasticsearch.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 城市Controller rest api服务
 *
 * @author Walker
 * @date 2019/1/23 下午4:59
 */
@RestController
@RequestMapping("es/city")
public class CityRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityRestController.class);

    @Autowired
    private CityService cityService;

    /**
     * save操作
     *
     * @param city
     * @return
     */
    @PostMapping("/save")
    public Object insert(City city) {
        ResultInfo resultInfo = new ResultInfo();
        long id = cityService.save(city);
        resultInfo.setData(id);
        return resultInfo;
    }

    /**
     * 查询城市描述
     *
     * @param description
     * @return
     */
    @GetMapping("/description/find")
    public Object findByDescription(@RequestParam("description") String description) {
        ResultInfo resultInfo = new ResultInfo();
        List<City> cityList = cityService.findByDescription(description);
        if (cityList != null && cityList.size() > 0) {
            resultInfo.setData(cityList);
        }
        return resultInfo;
    }

    /**
     * update 操作
     *
     * @param city
     * @return
     */
    @PutMapping("/update")
    public Object update(City city) {
        ResultInfo resultInfo = new ResultInfo();
        long id = cityService.update(city);
        resultInfo.setData(id);
        return resultInfo;
    }

    /**
     * delete 操作
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Object delete(@RequestParam("id") long id) {
        ResultInfo resultInfo = new ResultInfo();
        if (id == 0) {
            resultInfo.setMsg(ReturnCodeUtil.ReturnCode.INVALID_PARAMTER.getMsg());
            resultInfo.setStatus(ReturnCodeUtil.ReturnCode.INVALID_PARAMTER.getStatus());
            return resultInfo;
        }
        cityService.delete(id);
        return resultInfo;
    }

}
