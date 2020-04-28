package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OpenPositons extends Stocks {
    public ArrayList<Stocks> stocks;
    private double currentPrice;
    private double funds = 500000.00;

    public OpenPositons() {
        super();
        this.stocks = new ArrayList<>();
    }

    public double getFunds() {
        return funds;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void buyPosition() {
        if (stocks.isEmpty() || (currentPrice < (stocks.get(stocks.size()-1).getOpenPrice()/getProsentigeTracker()))) {
            super.setOpenPrice(currentPrice);
            super.setClosePositionPrice(currentPrice * getProsentigeTracker());
            funds = funds - (super.getAmount()*currentPrice);
            System.out.println("the stock was opened as BUY at " + super.getOpenPrice() + " and will be closed at " + super.getClosePositionPrice());
            saveStock(getOpenPrice(), getClosePositionPrice(), true);
        }
    }

    public void sellPositon() {
        if (stocks.isEmpty() || (currentPrice > (stocks.get(stocks.size()-1).getOpenPrice())*getProsentigeTracker())) {
            super.setOpenPrice(currentPrice);
            super.setClosePositionPrice(currentPrice / getProsentigeTracker());
            funds = funds - (super.getAmount()*currentPrice);
            System.out.println("the stock was opened as SELL at " + super.getOpenPrice() + " and will be closed at " + super.getClosePositionPrice());
            saveStock(getOpenPrice(), getClosePositionPrice(), false);
        }
    }


    public void closeBuyPosition() {
        for (int i = 0; i < stocks.size(); i++) {
            if (currentPrice > stocks.get(i).getClosePositionPrice()) {
                funds = funds + (getAmount() * ((getOpenPrice() - getClosePositionPrice()) + getOpenPrice())) ;
                System.out.println("stock was closed at " + stocks.get(i).getClosePositionPrice());
                stocks.remove(i);
            }
        }
    }


    public void closeSellPosition() {
        for (int i = 0; i < stocks.size(); i++) {
            if (currentPrice < stocks.get(i).getClosePositionPrice()) {
                funds = funds + (getAmount() * (getClosePositionPrice()));
                System.out.println("stock was closed at " + stocks.get(i).getClosePositionPrice());
                stocks.remove(i);
            }
        }
    }

    public void saveStock(double openPosition, double closePosition, boolean buy) {
        this.stocks.add(new Stocks(1.2, openPosition, closePosition, buy, 2000));
    }


}


