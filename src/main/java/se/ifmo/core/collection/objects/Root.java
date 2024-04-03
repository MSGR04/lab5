package se.ifmo.core.collection.objects;

import se.ifmo.core.collection.Resource;

// for json parsing
public class Root {
    private Resource resource;

    public Root(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
