package w.model;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

//@Entity
public class Foo extends Dbable {

	private static final long serialVersionUID = -411016648183325987L;

    private String name;

    public Foo() {
        super();
    }
    
    public Foo(final String name) {
        super(0,0);

        this.name = name;
    }
    
    public Foo(final long id, final String name) {
        super(id);

        this.name = name;
    }

    public Foo(final long id, final long version, final String name) {
        super(id,version);

        this.name = name;
    }
    
    // API

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Foo [id=").append(super.toString()).append(" name=").append(name).append("]");
        return builder.toString();
    }
}
