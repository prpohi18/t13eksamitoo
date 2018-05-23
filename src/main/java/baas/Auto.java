package baas;
import javax.persistence.*;

@Entity
@Table(name="autod")
public class Auto{
    @Id
    public String nrmark;
	public String algus;
	public String lopp;
	public Long loppjs;
	public int mitu;
}