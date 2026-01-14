package jdbc_application.models;

public class Configurations {
    private int id;
    private String cpu_name;
    private float cpu_ghz;
    private int ram_volume;
    private int disk_volume;

    public Configurations(int id, String cpu_name, float cpu_ghz, int ram_volume, int disk_volume){
        this.id = id;
        this.cpu_name = cpu_name;
        this.cpu_ghz = cpu_ghz;
        this.ram_volume = ram_volume;
        this.disk_volume = disk_volume;
    }

//  Getters
    public int GetId(){
        return id;
    }
    public String GetCpuName(){
        return cpu_name;
    }
    public float GetCpuGhz(){
        return cpu_ghz;
    }
    public int GetRamVolume(){
        return ram_volume;
    }
    public int GetDiskVolume(){
        return disk_volume;
    }

//  Setters
    public void SetCpuName(String cpu_name){
        this.cpu_name = cpu_name;
    }
    public void SetCpuGhz(float cpu_ghz){
        this.cpu_ghz = cpu_ghz;
    }
    public void SetRamVolume(int ram_volume){
        this.ram_volume = ram_volume;
    }
    public void SetDiskVolume(int disk_volume){
        this.disk_volume = disk_volume;
    }
}
