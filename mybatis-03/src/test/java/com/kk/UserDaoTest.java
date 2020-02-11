package com.kk;

import com.kk.dao.UserMapper;
import com.kk.pojo.User;
import com.kk.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class UserDaoTest {
    @Test
    public void test(){
        //第一步：获得SqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        try{
            //方式一：getMapper
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUserById(1);

            System.out.println(user);

        }
        finally {
            //关闭SqlSession
            sqlSession.close();
        }
    }

}
