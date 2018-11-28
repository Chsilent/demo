package com.walker.repository;

import com.walker.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Walker
 * @date 2018/11/12 下午2:24
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
