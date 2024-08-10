package app.ticketService;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class generates unique ticket ids.
 */
public class TicketIdGenerator {
    private static final AtomicLong id = new AtomicLong(0);
    private static TicketIdGenerator instance;
    private final ReentrantLock lock = new ReentrantLock();

    private TicketIdGenerator() {}

    public static TicketIdGenerator getInstance() {
        if (instance == null) {
            synchronized (TicketIdGenerator.class) {
                instance = new TicketIdGenerator();
            }
        }
        return instance;
    }

    public long getId() {
        lock.lock();
        try {
            return id.addAndGet(1L);
        } finally {
            lock.unlock();
        }
    }
}

