package org.algorithm.snowflake;

/**
 * 雪花算法 分布式环境 唯一ID
 *
 * <pre>
 *     SnowFlake算法是Twitter设计的一个可以在分布式系统中生成唯一的ID的算法，它可以满足Twitter每秒上万条消息ID分配的请求，这些消息ID是唯一的且有大致的递增顺序。
 *
 *     SnowFlake算法产生的ID是一个64位的整型，结构如下（每一部分用“-”符号分隔）：
 *     0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 *
 *      1位标识部分，在java中由于long的最高位是符号位，正数是0，负数是1，一般生成的ID为正数，所以为0；
 *
 *      41位时间戳部分，这个是毫秒级的时间，一般实现上不会存储当前的时间戳，而是时间戳的差值（当前时间-固定的开始时间），这样可以使产生的ID从更小值开始；41位的时间戳可以使用69年，(1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69年；
 *
 *      10位节点部分，Twitter实现中使用前5位作为数据中心标识，后5位作为机器标识，可以部署1024个节点；
 *
 *      12位序列号部分，支持同一毫秒内同一个节点可以生成4096个ID；
 *
 *      SnowFlake算法生成的ID大致上是按照时间递增的，用在分布式系统中时，需要注意数据中心标识和机器标识必须唯一，这样就能保证每个节点生成的ID都是唯一的。
 *      或许我们不一定都需要像上面那样使用5位作为数据中心标识，5位作为机器标识，可以根据我们业务的需要，灵活分配节点部分，
 *      如：若不需要数据中心，完全可以使用全部10位作为机器标识；若数据中心不多，也可以只使用3位作为数据中心，7位作为机器标识。
 *
 *
 * </pre>
 *
 * @author CYX
 * @create 2019-06-02-11:26
 */
public class SnowflakeIdGenerator {

    /**
     * 起始的时间戳
     */
    private static final long STARTTIME = System.currentTimeMillis();

    /**
     * 机器ID所占的位数
     */
    private static final long WORKDERIDBITS = 5L;

    /**
     * 数据标识id所占的位数
     */
    private static final long DATACENTERIDBITS = 5L;

    /**
     * 支持的最大机器id(十进制)，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     * -1L 左移 5位 (worker id 所占位数) 即 5位二进制所能获得的最大十进制数 - 31
     */
    private static final long MAXWORKERID = -1L ^ (-1L << WORKDERIDBITS);

    /**
     * 支持的最大数据标识id - 31
     */
    private static final long MAXDATACENTERID = -1L ^ (-1L << DATACENTERIDBITS);

    /**
     * 序列在id中占的位数
     */
    private static final long SEQUENCEBITS = 12L;

    /**
     * 机器ID 左移位数 - 12 (即末 sequence 所占用的位数)
     */
    private static final long WORKERIDMOVEBITS = SEQUENCEBITS;

    /**
     * 数据标识id 左移位数 - 17(12+5)
     */
    private static final long DATACENTERIDMOVEBITS = SEQUENCEBITS + WORKDERIDBITS;

    /**
     * 时间截向 左移位数 - 22(5+5+12)
     */
    private static final long TIMESTAMPMOVEBITS = SEQUENCEBITS + WORKDERIDBITS + DATACENTERIDBITS;

    /**
     * 生成序列的掩码(12位所对应的最大整数值)，这里为4095 (0b111111111111=0xfff=4095)
     */
    private static final long SEQUENCEMASK = -1L ^ (-1L << WORKERIDMOVEBITS);

    /**
     * 工作机器ID(0~31)
     */
    private long workerId;
    /**
     * 数据中心ID(0~31)
     */
    private long dataCenterId;
    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;
    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     *
     * @param workerId     工作ID (0~31)
     * @param dataCenterId 数据中心ID (0~31)
     */
    public SnowflakeIdGenerator(long workerId, long dataCenterId) {
        if (workerId > MAXWORKERID || workerId < 0) {
            throw new IllegalArgumentException(String.format("Worker Id can't be greater than %d or less than 0", MAXWORKERID));
        }
        if (dataCenterId > MAXDATACENTERID || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("DataCenter Id can't be greater than %d or less than 0", MAXDATACENTERID));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 线程安全的获得下一个 ID 的方法
     *
     * @return
     */
    public synchronized long nextId() {

        long timestamp = currentTime();

        //如果当前时间小于上一次ID生成的时间戳: 说明系统时钟回退过 - 这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCEMASK;
            //毫秒内序列溢出 即 序列 > 4095
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = blockTillNextMillis(lastTimestamp);
            }
        } else {
            //时间戳改变，毫秒内序列重置
            sequence = 0L;
        }
        //上次生成ID的时间截
        lastTimestamp = timestamp;
        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - STARTTIME) << TIMESTAMPMOVEBITS)
                | (dataCenterId << DATACENTERIDMOVEBITS)
                | (workerId << WORKERIDMOVEBITS)
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒 即 直到获得新的时间戳
     *
     * @param lastTimestamp
     * @return
     */
    protected long blockTillNextMillis(long lastTimestamp) {
        long timestamp = currentTime();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTime();
        }
        return timestamp;
    }

    /**
     * 获得以毫秒为单位的当前时间
     *
     * @return
     */
    protected long currentTime() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {

        SnowflakeIdGenerator idWorker = new SnowflakeIdGenerator(0, 0);
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            System.out.println(Long.toBinaryString(id));
            System.out.println(id);
        }

    }

}
