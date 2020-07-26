#在与redis交互的时候，需要与交互放写入redis中的key，value的序列化方式保持统一

#java代码配置方式

pom配置：
依赖springboot的redis依赖
<dependency>
    <groupId>org.redisson</groupId>
    <artifactId>redisson-spring-data-21</artifactId>
    <version>3.13.1</version>
</dependency>

代码配置
    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private String port;

    @Value("${redis.password}")
    private String password;

    private int database = 7;

    @Bean(name = "redissonClient")
    public RedissonClient buildRedissonClient() {
        Config config = new Config();
        config.setCodec(new StringCodec())  # 关键代码默认使用的是 string的序列化方式等同于 StringRedisTemplate
                .useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setPassword(password)
                .setDatabase(database); # 可以动态构建实例  RedissonClient 支持
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
#代码使用
String key = date + BURIED_POINT_200092;
List<String> list = redissonClient.getList(key);

#对 Jaskson序列化的方式 可以动态制定
List<String> list = redissonClient.getList(key,new JsonJacksonCodec());

# 以上也可实现动态切换 redis-db 的功能


# 参考地址 https://blog.csdn.net/ntotl/article/details/80515995

# 队列操作说明
add()	增加一个元素	如果队列已满，则抛出一个IIIegaISlabEepeplian异常
remove()	移除并返回队列头部的元素	如果队列为空，则抛出一个NoSuchElementException异常
element()	返回队列头部的元素	如果队列为空，则抛出一个NoSuchElementException异常
offer()	添加一个元素并返回true	如果队列已满，则返回false
poll()	移除并返问队列头部的元素	如果队列为空，则返回null
peek()	返回队列头部的元素	如果队列为空，则返回null
put()	添加一个元素	如果队列满，则阻塞
take()	移除并返回队列头部的元素	如果队列为空，则阻塞
————————————————
版权声明：本文为CSDN博主「MelodyHub」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/weixin_43438052/java/article/details/106473476





