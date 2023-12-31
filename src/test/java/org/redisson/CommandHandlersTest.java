package org.redisson;

import org.junit.Test;
import org.redisson.client.RedisException;
import org.redisson.Config;

public class CommandHandlersTest extends BaseTest {

    @Test(expected = RedisException.class)
    public void testEncoder() throws InterruptedException {
        Config config = createConfig();
        config.setCodec(new ErrorsCodec());
        
        RedissonClient redisson = Redisson.create(config);
        
        redisson.getBucket("1234").set("1234");
    }
    
    @Test(expected = RedisException.class)
    public void testDecoder() {
        redisson.getBucket("1234").set("1234");
        
        Config config = createConfig();
        config.setCodec(new ErrorsCodec());
        
        RedissonClient redisson = Redisson.create(config);
        
        redisson.getBucket("1234").get();
    }
    
}
