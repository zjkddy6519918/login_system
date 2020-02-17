package com.kemp.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {

    private  static JedisPool jedisPool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(10);
        jedisPool = new JedisPool(config, "localhost",6379);
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    public static void close(Jedis jedis){
        try{
            if (jedis != null){
                jedis.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
