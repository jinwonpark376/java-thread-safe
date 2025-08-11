import static util.CustomLogger.log;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        BankAccount bankAccount = new BankAccountV1(1000); // thread unsafe
//        BankAccount bankAccount = new BankAccountV2(1000); // synchronized
        BankAccount bankAccount = new BankAccountV3(1000); // lock

        Thread t1 = new Thread(new WithdrawTask(bankAccount, 800), "t1");
        Thread t2 = new Thread(new WithdrawTask(bankAccount, 800), "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log("account balance: " + bankAccount.getBalance());

    }
}
