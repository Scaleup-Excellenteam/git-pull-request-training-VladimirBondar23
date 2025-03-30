public class Player {
    private Bank Total;
    private int Sum;
    private Card Card1;
    private Card Card2;
    private int currentDebt;

    public Player(){
        Total = new Bank(405);
        Sum = 0;
        Card1 = null;
        Card2 = null;
        currentDebt = 0;
    }
    public Player (Bank bank){
        this();
        Total = bank;
    }
    public Bank getTotal() {
        return Total;
    }

    public int countSum(){
        return Card2.getValue() + Card1.getValue();
    }
    public int getSum() {
        return Sum;
    }

    public void setSum(int sum) {
        Sum = sum;
    }

    public Card getCard1() {
        return Card1;
    }

    public void setCard1(Card card1) {
        Card1 = card1;
    }

    public Card getCard2() {
        return Card2;
    }

    public void setCard2(Card card2) {
        Card2 = card2;
    }

    public int getCurrentDebt() {
        return currentDebt;
    }

    public void setCurrentDebt(int currentDebt) {
        this.currentDebt = currentDebt;
    }
}
