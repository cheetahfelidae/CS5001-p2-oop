/**
 * This class is used for collecting coins when killing an enemy.
 */
public class Account {
    private int coin_balance;

    /**
     * The number of coins needs to be initialised.
     *
     * @param coin_balance the number of coins.
     */
    public Account(int coin_balance) {
        this.coin_balance = coin_balance;
    }

    /**
     * Update the number of earned coins.
     *
     * @param coin_balance the number of coins.
     */
    public void setCoinBalance(int coin_balance) {
        this.coin_balance = coin_balance;
    }

    /**
     * Get the current number of earned coins.
     *
     * @return the number of coins.
     */
    public int getCoinBalance() {
        return coin_balance;
    }
}
