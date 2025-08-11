import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.CustomLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV3 implements BankAccount {
    int balance;
    private final Lock lock = new ReentrantLock();

    public BankAccountV3(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("[BankAccountV3][withdraw] process start.");

        try {
            if (!lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                log("[BankAccountV3][withdraw] other thread has acquired lock.");
                return false;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            log("[BankAccountV3][withdraw] validation start. withdraw amount: " + amount + ", balance: " + balance);

            if (amount > balance) {
                log("[BankAccountV3][withdraw] validation fail. withdraw amount: " + amount + ", balance: " + balance);
                return false;
            }

            log("[BankAccountV3][withdraw] validation success. withdraw amount: " + amount + ", balance: " + balance);
            sleep(1000); // time for transaction to take place.

            balance -= amount;

            log("[BankAccountV3][withdraw] withdraw success. withdraw amount: " + amount + ", balance: " + balance);
        } finally {
            lock.unlock();
        }


        log("[BankAccountV3][withdraw] process end.");

        return true;
    }

    @Override
    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
