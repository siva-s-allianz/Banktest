@FunctionalInterface 
public interface AccountFilter {
    boolean test(BankAccount account);
}
