package graphs.helper;

public class Artist extends Person {

    private String name;

    public Artist() {
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println(name);
        this.name = name == null || name.equals(" ") ? "NA" : name;
    }
}
