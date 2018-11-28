package com.walker.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.walker.bean.User;
import com.walker.bean.UserEntity;
import com.walker.common.constant.Constant;
import com.walker.common.redis.XredisTemplate;
import com.walker.common.utils.XJsonTools;
import com.walker.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * user service
 *
 * @author Walker
 * @date 2018/10/18 下午10:32
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private XredisTemplate xredisTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询所有的User
     *
     * @return
     */
    public List<User> getAllUsers() {
        return userDAO.selectAllUsers();
    }

    /**
     * 测试springboot redis
     *
     * @return
     */
    public List<User> testRedis() {
        List<User> userList = XJsonTools.toList(xredisTemplate.getValue(Constant.REDIS_SB_USER), User.class);
        if (userList == null || userList.size() == 0) {
            userList = userDAO.selectAllUsers();
            xredisTemplate.setKeyValue(Constant.REDIS_SB_USER, userList);
        }
        return userList;
    }

    /**
     * mongodb:创建用户
     *
     * @param user
     */
    public void save(UserEntity user) {
        mongoTemplate.save(user);
    }

    /**
     * mongodb:根据用户姓名查找对应的用户
     *
     * @param name
     * @return
     */
    public UserEntity queryByName(String name) {
        Query query = new Query(Criteria.where("userName").is(name));
        UserEntity user = mongoTemplate.findOne(query, UserEntity.class);
        return user;
    }

    /**
     * mongodb:更新用户
     *
     * @param user
     * @return
     */
    public int update(UserEntity user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update().set("userName", user.getUserName()).set("password", user.getPassword()).set("age", user.getAge());
        //更新结果集的第一条数据
        UpdateResult result = mongoTemplate.updateFirst(query, update, UserEntity.class);
        //更新结果集的全部数据
        //mongoTemplate.updateMulti(query, update, UserEntity.class);
        if (result != null) {
            return (int) result.getModifiedCount();
        } else {
            return 0;
        }
    }

    /**
     * mongodb:根据id删除用户
     *
     * @param id
     */
    public int delete(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        //删除的结果集 获取被删除的行数
        DeleteResult result = mongoTemplate.remove(query, UserEntity.class);
        if (result != null) {
            return (int) result.getDeletedCount();
        } else {
            return 0;
        }
    }
}
