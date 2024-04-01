package project.api.drivers.models;

import java.util.Map;

public class Income {
    private String  Id;
    private double cost;
    private double revenue;
    private double profit;
    public Income(){}

    public Income(double cost, double revenue, double profit) {
        this.cost = cost;
        this.revenue = revenue;
        this.profit = profit;
    }
    public Income (double distance , double costExtend , double consumption ,
                   double volume ,double mass, Map<Double,Integer> listTicketCost  ){
        this.cost= set_Cost(distance,costExtend , consumption);
        this.revenue =set_Revenue(distance, volume, mass,listTicketCost  );
        this.profit=set_profit();

    }
    public double set_Cost(double distance , double costExtend , double consumption){
        return (distance/100) * consumption +costExtend;
    }
    public double set_Revenue ( double distance ,double volume ,double mass
            , Map<Double ,Integer> listTicketCost ){
        double Revenue = 0;
        if( listTicketCost !=null){
            double ticketCost = 0;
            for(Map.Entry<Double,Integer> entry :  listTicketCost.entrySet()){
                double key = entry.getKey();
                double value = entry.getValue();
                Revenue += key*value;
            }
        }
        double mass_2=0;
        if(volume!=0){
            mass_2 = (volume/5000 +mass)/2;
        }
        else {
            mass_2 = mass;
        }
        mass_2=mass_2-100;
        Revenue +=15000;
        if(mass_2>0){
            Revenue +=(mass_2/100)*5000*(distance/500);
        }
        return Revenue;



    }
    public double set_profit(){
        return this.revenue-this.cost;
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

    public void setRevenue(double revenue) {
        this.revenue=revenue;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
