package resources;

public enum APIResources {
	volumesAPI("/volumes");
	
	private String resource;
	
	APIResources(String resource) {
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}
}
