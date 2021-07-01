package dreamteam.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CacheSecondLevelTest {

    SessionFactory sessionFactory;

    @BeforeEach
    public void setUp() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void testCache() {
//        Cache cache = sessionFactory.getCache();
//        cache.evictAllRegions();

        Session s1 = sessionFactory.openSession();
        s1.beginTransaction();
        Query q = s1.createQuery("from User");
        q.setCacheable(true);
        q.list();
        s1.getTransaction().commit();
        s1.close();

        Session s2 = sessionFactory.openSession();
        s2.beginTransaction();
        Query q2 = s2.createQuery("from User");
        q2.setCacheable(true);
        q2.list();
        s2.getTransaction().commit();
        s2.close();

        Statistics s = sessionFactory.getStatistics();
        System.out.println(s);
        System.out.println("put:" + s.getSecondLevelCachePutCount());
        System.out.println("hit:" + s.getSecondLevelCacheHitCount());
        System.out.println("miss:" + s.getSecondLevelCacheMissCount());
    }
}