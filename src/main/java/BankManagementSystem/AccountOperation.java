@FunctionalInterface
public interface AccountOperation {
    /** Performs an operation on the supplied bank account. */
    void perform(BankAccount account);
}
