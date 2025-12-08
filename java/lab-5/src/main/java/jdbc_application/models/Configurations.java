package jdbc_application.models;

public class Configurations {
    private int id;
    private String cpu_name;
    private float cpu_ghz;
    private int ram_volume;
    private int disk_volume;

    public Configurations()
    {

    }

    public Configurations(int id, String cpu_name, float cpu_ghz, int ram_volume, int disk_volume){
        this.id = id;
        this.cpu_name = cpu_name;
        this.cpu_ghz = cpu_ghz;
        this.ram_volume = ram_volume;
        this.disk_volume = disk_volume;
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
}
