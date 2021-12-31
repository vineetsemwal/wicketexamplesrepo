package com.mycompany;

import org.apache.wicket.core.util.resource.locator.caching.FileSystemResourceStreamReference;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.FileSystemResource;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyFileSystemResourceReference extends ResourceReference {

    public MyFileSystemResourceReference(String name){
        super(name);
    }

    @Override
    public IResource getResource() {
        System.out.println("inside getResource in MyFileSystemResourceReference");
        URI uri=URI.create("file:///home/vineet/Pictures/appimages/"+getName());
        Path path= Paths.get(uri);
        FileSystemResource resource=new FileSystemResource(path);
        return resource;
    }
}
