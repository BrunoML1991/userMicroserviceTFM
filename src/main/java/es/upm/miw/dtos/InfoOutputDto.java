package es.upm.miw.dtos;

public class InfoOutputDto {

    private String name;

    private String version;

    private String date;

    public InfoOutputDto() {
        // empty  framework
    }

    public InfoOutputDto(String name, String version, String date) {
        this.name = name;
        this.version = version;
        this.date = date;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InfoOutputDto{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
