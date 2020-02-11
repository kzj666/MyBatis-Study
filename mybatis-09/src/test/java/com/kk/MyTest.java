package com.kk;

import com.kk.dao.UserMapper;
import com.kk.pojo.User;
import com.kk.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MyTest {

    //一级缓存
    @Test
    public void test(){

        //在两次一样的查询之间，加入了增删改语句，所以第二次查询虽然与第一次一样，也会刷新缓存
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserById(1);
        System.out.println(user);
        System.out.println("==================================================================");

        //只要有增删改操作，可能会改变原来数据，会刷新缓存
        mapper.updatebyId(new User(2,"kk","111"));

        System.out.println("==================================================================");
        User user2 = mapper.queryUserById(1);
        System.out.println(user2);

        sqlSession.close();

    }

    //手动关闭缓存sqlSession.clearCache();
    @Test
    public void test2(){
        //在两次一样的查询之间，加入了增删改语句，所以第二次查询虽然与第一次一样，也会刷新缓存
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserById(1);
        System.out.println(user);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        //手动关闭缓存
        sqlSession.clearCache();
        User user2 = mapper.queryUserById(1);
        System.out.println(user2);

        sqlSession.close();

    }

//    二级缓存

//- 只要开启了二级缓存，在同一个Mapper下就有效
//- 所有的数据都会先放在一级缓存中；
//- 只有当会话提交，或者关闭的时候，才会提交到二级缓存中！
    @Test
    public void test3(){

        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserById(1);
        System.out.println(user);
        sqlSession.close();

        SqlSession sqlSession2 = MyBatisUtils.getSqlSession();
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.queryUserById(1);
        System.out.println(user2);
        sqlSession2.close();

    }


}
