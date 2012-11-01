package um2.websemantique.ontoligie.utils;

public class Response {
	boolean returned;
	String containts;

	public void setContaints(String containts) {
		this.containts = containts;
	}

	public String getContaints() {
		return containts;
	}

	public boolean isOK() {
		return returned;
	}

	public void setOK(boolean returned) {
		this.returned = returned;
	}

}
