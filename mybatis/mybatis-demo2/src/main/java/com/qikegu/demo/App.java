package com.qikegu.demo;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.qikegu.demo.mapper.UserMapper;

public class App {

    public static void main(String args[]) throws IOException {

        Reader reader = Resources.getResourceAsReader("MybatisConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();
        
        UserMapper mapper = session.getMapper(UserMapper.class); 

        System.out.println("------------ ���ݲ��� -----------");
        // �����û�����
        User user = new User("newUser100");

        // �������û������ݿ�
        mapper.insert(user);
        session.commit();
        System.out.println("���ݲ���ɹ�");

        System.out.println("------------ ��ȡ�û��б� -----------");
        List<User> userList = mapper.getAll();

        for (User u : userList) {
            System.out.println(u.getId());
            System.out.println(u.getName());
        }

        System.out.println("��ȡ�û��б�ɹ�");

        System.out.println("------------ ��ȡ�û����� -----------");
        User user1 = (User) mapper.getById(user.getId());

        System.out.println(user1.getId());
        System.out.println(user1.getName());

        System.out.println("��ȡ�û�����ɹ�");

        System.out.println("------------ �޸��û� -----------");
        user1.setName("userNameUpdated");
        mapper.update(user1);
        session.commit();

        // ��ѯ�޸ĺ���û�����
        User user2 = (User) mapper.getById(user.getId());
        
        System.out.println(user2.getId());
        System.out.println(user2.getName());
        System.out.println("�޸��û��ɹ�");

        System.out.println("------------ ɾ���û� -----------");
        mapper.delete(user.getId());
        System.out.println("ɾ���û��ɹ�");

        session.commit();
        session.close();
    }

}