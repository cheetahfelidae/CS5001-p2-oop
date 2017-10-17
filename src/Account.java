/**
 * This class is used for collecting the player's coins
 */
public class Account {
    private int coin_balance;
    private int earned_coins = 0;

    public Account(int coin_balance) {
        this.coin_balance = coin_balance;
    }

    public void set_coin_balance(int coin_balance) {
        this.coin_balance = coin_balance;
    }

    /**
     * Get the current number of earned coins.
     *
     * @return
     */
    public int get_coin_balance() {
        return coin_balance;
    }
}
