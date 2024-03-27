package project.api.drivers.models;

public class Income {
       private double cost;
       private double revenue;
       private double profit;
       public Income(){}

    public Income(double cost, double revenue, double profit) {
        this.cost = cost;
        this.revenue = revenue;
        this.profit = profit;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue() {

    }

    public double getProfit() {
        return profit;
    }

    public void setProfit() {
        this.profit = this.revenue-this.cost;
    }
}
