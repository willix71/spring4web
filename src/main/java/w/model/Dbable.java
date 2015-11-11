package w.model;

import java.io.Serializable;

public class Dbable implements Serializable {

	private static final long serialVersionUID = 8714119585997395380L;
	
	private long id;

	private long version;
	
	public Dbable() {
		this.id = -1;
		this.version = -1;
	}

	public Dbable(long id) {
		this(id,0);
	}
	
	public Dbable(long id, long version) {
		this.id = id;
		this.version = version;
	}

	public long getId() {
	    return id;
	}

	public void setId(final long id) {
	    this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dbable other = (Dbable) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + "." + version;
	}
	
}
