package jdbc_application.models;

import java.sql.Time;
import java.util.Date;

public class Orders {
    private int id;
    private int user_id;
    private int config_id;
    private float total;
    private Date order_date;
    private Time order_time;

    public Orders(int id, int user_id, int config_id, float total, Date order_date, Time order_time){
        this.id = id;
        this.user_id = user_id;
        this.config_id = config_id;
        this.total = total;
        this.order_date = order_date;
        this.order_time = order_time;
    }

//  getters
    public int GetId(){
        return id;
    }
    public int GetUserId(){
        return user_id;
    }
    public int GetConfigId(){
        return config_id;
    }
    public float GetTotal(){
        return total;
    }
    public Date GetOrderDate(){
        return order_date;
    }
    public Time GetOrderTime(){
        return order_time;
    }

//  setters
    public void SetUserId(int user_id){
        this.user_id = user_id;
    }
    public void SetConfigId(int config_id){
        this.config_id = config_id;
    }
    public void SetTotal(float total){
        this.total = total;
    }
    public void SetOrderDate(Date order_date){
        this.order_date = order_date;
    }
    public void SetOrderTime(Time order_time){
        this.order_time = order_time;
    }
}
