package ratelimit;

import java.io.Serializable;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public class Ratelimit implements Serializable{

    private AtomicInteger remaining;

    private final Instant expiredAt;

    private final long period;

    public Ratelimit(int limit, long period) {
        this.remaining = new AtomicInteger(limit);
        this.expiredAt = Instant.now().plusSeconds(period);
        this.period = period;
    }


    public void decrease(){
        remaining.getAndDecrement();
    }

    public boolean reached(){
        return this.remaining.get() < 0;
    }

    public boolean expired(){
        return Instant.now().isAfter(expiredAt);
    }
}
