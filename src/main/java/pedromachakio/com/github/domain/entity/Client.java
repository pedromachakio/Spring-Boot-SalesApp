package pedromachakio.com.github.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "CLIENT")
// anotação opcional, desde que o nome da classe corresponda ao nome na tabela, which it should in the first place
public class Client {

    @Id // defines primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    //https://stackoverflow.com/questions/20603638/what-is-the-use-of-annotations-id-and-generatedvaluestrategy-generationtype

    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", length = 100)
    // anotação opcional, desde que o nome da classe corresponda ao nome na tabela, which it should in the first place
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
