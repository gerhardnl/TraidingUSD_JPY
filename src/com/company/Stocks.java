package com.company;

public class Stocks {
    private double prosentigeTracker = 1.0002;
    private double openPrice;
    private double closePositionPrice;
    private boolean buy;
    private double amount = 2000;


    public Stocks(double prosentigeTracker, double openPrice, double closePositionPrice, boolean buy, double amount) {
        this.prosentigeTracker = prosentigeTracker;
        this.openPrice = openPrice;
        this.closePositionPrice = closePositionPrice;
        this.buy = buy;
        this.amount = amount;
    }

    public Stocks() {

    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isBuy() {
        return buy;
    }

    public double getProsentigeTracker() {
        return prosentigeTracker;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public double getClosePositionPrice() {
        return closePositionPrice;
    }


    public void setProsentigeTracker(double prosentigeTracker) {
        this.prosentigeTracker = prosentigeTracker;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public void setClosePositionPrice(double closePositionPrice) {
        this.closePositionPrice = closePositionPrice;
    }

}
