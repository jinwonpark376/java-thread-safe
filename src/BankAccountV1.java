import static util.CustomLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV1 implements BankAccount {
    int balance;

    public BankAccountV1(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("[BankAccountV1][withdraw] process start.");

        log("[BankAccountV1][withdraw] validation start. withdraw amount: " + amount + ", balance: " + balance);

        if (amount > balance) {
            log("[BankAccountV1][withdraw] validation fail. withdraw amount: " + amount + ", balance: " + balance);
            return false;
        }

        log("[BankAccountV1][withdraw] validation success. withdraw amount: " + amount + ", balance: " + balance);
        sleep(1000); // time for transaction to take place.

        balance -= amount;
        log("[BankAccountV1][withdraw] withdraw success. withdraw amount: " + amount + ", balance: " + balance);

        log("[BankAccountV1][withdraw] process end.");

        return true;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
