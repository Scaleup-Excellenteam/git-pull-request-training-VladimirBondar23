public class Bank {
    private int store;
    public Bank(){
        store = 2025;
    }
    public Bank(int store){
        this.store = store;
    }
    public int getStore() {
        return store;
    }
    public void setStore(int store) {
        this.store = store;
    }
    public boolean isEmpty(){
        return store == 0;
    }
    public void removeCash(int Cash){
        store -= Cash;
    }
    public void addCash(int Cash){
        store += Cash;
    }
}
