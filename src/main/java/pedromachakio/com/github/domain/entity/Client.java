package pedromachakio.com.github.domain.entity;

public class Client {

    private Integer id;
    private String name;

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public Client(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                " id = " + id +
                ", name = '" + name + '\'' +
                '}';
    }
}
