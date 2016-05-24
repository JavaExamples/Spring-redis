package com.kee.redis.test;

import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * redis spring ������
 * @author hk
 *
 * 2012-12-22 ����10:40:15
 */
public class TestRedis {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        //�����Ѿ����ú�,����һ��redis�ķ���ӿ�
        RedisService redisService = (RedisService) app.getBean("redisService");

        String ping = redisService.ping();//�����Ƿ����ӳɹ�,���ӳɹ����PONG
        System.out.println(ping);

        //����,���ǿ���redis�������Ƿ�������
        long dbSizeStart = redisService.dbSize();
        System.out.println(dbSizeStart);

        redisService.set("username", "oyhk");//��ֵ(�鿴��Դ����,Ĭ�ϴ��ʱ��30����)
        String username = redisService.get("username");//ȡֵ 
        System.out.println(username);
        redisService.set("username1", "oyhk1", 1);//��ֵ,�����������ݵĴ��ʱ��(��������Ϊ��λ)
        String username1 = redisService.get("username1");
        System.out.println(username1);
        Thread.sleep(2000);//��˯��һ��,��ȥȡ,���ʱ�䳬����,���Ĵ��ʱ��
        String liveUsername1 = redisService.get("username1");
        System.out.println(liveUsername1);//���null

        //�Ƿ����
        boolean exist = redisService.exists("username");
        System.out.println(exist);

        //�鿴keys
        Set<String> keys = redisService.keys("*");//����鿴���е�keys
        System.out.println(keys);//ֻ��username username1(�Ѿ������)

        //ɾ��
        redisService.set("username2", "oyhk2");
        String username2 = redisService.get("username2");
        System.out.println(username2);
        redisService.del("username2");
        String username2_2 = redisService.get("username2");
        System.out.println(username2_2);//���Ϊnull,��ô����ɾ��������

        //dbsize
        long dbSizeEnd = redisService.dbSize();
        System.out.println(dbSizeEnd);

        //���reids��������
        //redisService.flushDB();
    }
}