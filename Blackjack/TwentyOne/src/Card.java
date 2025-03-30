public class Card {
    private Rank rank;
    private Sign sign;
    private boolean visible;
    public Card(Rank rank, Sign sign){
        this.rank = rank;
        this.sign = sign;
        visible = false;
    }
    public int getValue(){
        return rank.getValue();
    }
    public Rank getRank(){
        return rank;
    }

    public Sign getSign() {
        return sign;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
