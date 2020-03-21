package com.zxtt.newsapp.service.user;

import com.zxtt.newsapp.commons.entity.User;
import com.zxtt.newsapp.commons.impl.BaseCrudServiceImpl;
import com.zxtt.newsapp.commons.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户
 */
@Service
public class UserServiceImpl extends BaseCrudServiceImpl<User, UserMapper>
        implements UserService {

}
