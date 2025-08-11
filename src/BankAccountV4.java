import java.util.concurrent.atomic.AtomicInteger;

import static util.CustomLogger.log;

public class BankAccountV4 implements BankAccount {
    private final AtomicInteger balance;

    public BankAccountV4(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public boolean withdraw(int amount) {
        log("[BankAccountV4][withdraw] process start.");

        while (true) {
            int current = balance.get();
            if (amount > current) {
                log("[BankAccountV4][withdraw] withdraw fail. withdraw amount: " + amount + ", balance: " + balance);
                return false;
            }

            if (balance.compareAndSet(current, current - amount)) {
                log("[BankAccountV4][withdraw] withdraw success. withdraw amount: " + amount + ", balance: " + balance);
                log("[BankAccountV4][withdraw] process end.");
                return true;
            }
        }
    }

    @Override
    public int getBalance() {
        return balance.get();
    }
}
