import static util.CustomLogger.log;
import static util.ThreadUtils.sleep;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccountV1(1000);

        Thread t1 = new Thread(new WithdrawTask(bankAccount, 800), "t1");
        Thread t2 = new Thread(new WithdrawTask(bankAccount, 800), "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log("account balance: " + bankAccount.getBalance());

    }
}
