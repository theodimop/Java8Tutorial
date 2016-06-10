package com.theo.TransformToJDK8.patterns.executerRoundMethod;

import java.util.function.Consumer;

/**
 * Created by dj_di_000 on 8/6/2016.
 */

        /*WHAT GOES WRONG HERE
    * 1) Developers may forget to invoke close on the resource instance
    * 2) An exception in operations will prevent close to be executed
    *
    * -- Better Second thought (Not implemented)
    * Use try / Finally, but the 1 is still there even bigger and our code becomes
     * difficult to read, with other words becomes verbose
    *
    *--Use AutoCloseable interface
    * Much more for a programmer to forget....
    *
    * --Use Consumer Interface with lambda expression!
    *
    */


class Resource_v1{
    public Resource_v1() {System.out.println("Creating...");}
    public void operationA(){System.out.println("Operation A");}
    public void operationB(){System.out.println("Operation B");}
    //this was an wrong implementation, because the object was not cleaned
    public void finalize(){System.out.println("Garbage Collection calls this method");}
    //name the method close() to release the resource
    public void close(){System.out.println("External Resource Cleaned Up!");}
}

class Resource_v2 implements AutoCloseable{
    public Resource_v2() {System.out.println("Creating resource2...");}
    public void operationA(){System.out.println("Operation of resource 2 A");}
    public void operationB(){System.out.println("Operation of resource 2  B");}
    //Method of the AutoCloseable interface
    public void close(){System.out.println("External Resource Cleaned Up!");}
}


class Resource {
    private Resource() {System.out.println("Creating resource with Pattern...");}
    public void operationA(){System.out.println("Operation  A");}
    public void operationB(){System.out.println("Operation  B");}
    private void close(){System.out.println("External Resource Cleaned Up!");}
    //Create an static method with enforce you to use the resource wisely
    public static void use(Consumer<Resource> block){
        Resource resource=new Resource();
        try{
            block.accept(resource);
        }finally {
            resource.close();
        }
    }
}
public class Sample {



    public static void main(String args[]) {

        //Implementation before a secong thought
        Resource_v1 resource = new Resource_v1();

        resource.operationA();
        resource.operationB();
        resource.close();

        //Implementation with the second thought
        //Verbose code
        //This implementation is called in jdk 7
        // ARM-Automatic Resource Management
        try(Resource_v2 res2= new Resource_v2()) {
            res2.operationA();
            res2.operationB();
        }
        //res2.close will be invoked when the block of statements if try ends!


        //Pattern Implementation,Consumer takes place
        //Forget about invoking close...
        Resource.use(res -> {
            res.operationA();
            res.operationB();
        });
    }
}


