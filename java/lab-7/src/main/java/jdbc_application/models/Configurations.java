package jdbc_application.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;

public class Configurations {

    private int id;

    @NotBlank(message = "Cpu name can't be null")
    @Size(min = 2, max = 100, message = "Cpu required lenght is from 2 to 100")
    private String cpu_name;

    @DecimalMin(value = "0.0", message = "Cpu ghz can't be less then zero")
    @DecimalMax("10.0")
    @NotNull
    private float cpu_ghz;

    @NotNull
    @Min(0)
    private int ram_volume;

    @NotNull
    @Min(0)
    private int disk_volume;

    @NotNull
    @Min(0)
    private double price;

    public Configurations()
    {

    }
    public Configurations(String cpu_name, float cpu_ghz, int ram_volume, int disk_volume, double price){
        this.cpu_name = cpu_name;
        this.cpu_ghz = cpu_ghz;
        this.ram_volume = ram_volume;
        this.disk_volume = disk_volume;
        this.price = price;
    }
    public Configurations(int id, String cpu_name, float cpu_ghz, int ram_volume, int disk_volume, double price){
        this.id = id;
        this.cpu_name = cpu_name;
        this.cpu_ghz = cpu_ghz;
        this.ram_volume = ram_volume;
        this.disk_volume = disk_volume;
        this.price = price;
    }

//  Getters
    public int getId(){
        return id;
    }
    public String getCpuName(){
        return cpu_name;
    }
    public float getCpuGhz(){
        return cpu_ghz;
    }
    public int getRamVolume(){
        return ram_volume;
    }
    public int getDiskVolume(){
        return disk_volume;
    }
    public double getPrice()
    {
        return price;
    }

//  Setters
    public void setCpuName(String cpu_name){
        this.cpu_name = cpu_name;
    }
    public void setCpuGhz(float cpu_ghz){
        this.cpu_ghz = cpu_ghz;
    }
    public void setRamVolume(int ram_volume){
        this.ram_volume = ram_volume;
    }
    public void setDiskVolume(int disk_volume){
        this.disk_volume = disk_volume;
    }
    public void setPrice(double price){
        this.price = price;
    }
}
