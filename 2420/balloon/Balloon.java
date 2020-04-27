package balloon;

public class Balloon {
	private String type;
	private Size size;
	
	public Balloon(String type, Size size) {
		
		this.type = type;
		this.size = size;
	}
	
	@Override
	public String toString() {
		return this.type + " " + this.size;
	}

	public String getType() {
		return type;
	}

	public Size getSize() {
		return size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Balloon other = (Balloon) obj;
		if (size != other.size)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}