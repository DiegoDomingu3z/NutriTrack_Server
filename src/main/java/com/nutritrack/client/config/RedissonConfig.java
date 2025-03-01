package com.nutritrack.client.config;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Value("${spring.data.redis.password:}")
    private String redisPassword;

    @Value("${redisson.connectionPoolSize:64}")
    private int connectionPoolSize;

    @Value("${redisson.connectionMinimumIdleSize:10}")
    private int connectionMinimumIdleSize;

    @Value("${redisson.idleConnectionTimeout:10000}")
    private int idleConnectionTimeout;

    @Value("${redisson.connectTimeout:10000}")
    private int connectTimeout;

    @Value("${redisson.timeout:3000}")
    private int timeout;

    @Value("${redisson.retryAttempts:3}")
    private int retryAttempts;

    @Value("${redisson.retryInterval:1500}")
    private int retryInterval;

    @Value("${redisson.subscriptionsPerConnection:5}")
    private int subscriptionsPerConnection;

    @Value("${redisson.threads:16}")
    private int threads;

    @Value("${redisson.nettyThreads:32}")
    private int nettyThreads;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();

        // Configure single server mode. (Other modes like cluster or sentinel can be set similarly.)
        SingleServerConfig singleServerConfig = config.useSingleServer();
        String redisAddress = "redis://" + redisHost + ":" + redisPort;
        singleServerConfig.setAddress(redisAddress)
                .setConnectionPoolSize(connectionPoolSize)
                .setConnectionMinimumIdleSize(connectionMinimumIdleSize)
                .setIdleConnectionTimeout(idleConnectionTimeout)
                .setConnectTimeout(connectTimeout)
                .setTimeout(timeout)
                .setRetryAttempts(retryAttempts)
                .setRetryInterval(retryInterval)
                .setSubscriptionsPerConnection(subscriptionsPerConnection);

        if (redisPassword != null && !redisPassword.isEmpty()) {
            singleServerConfig.setPassword(redisPassword);
        }

        // Set additional options
        config.setThreads(threads);
        config.setNettyThreads(nettyThreads);
        // Use JSON codec (recommended for interoperability)
        config.setCodec(new JsonJacksonCodec());

        return Redisson.create(config);
    }
}
