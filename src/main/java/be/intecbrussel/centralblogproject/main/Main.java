package be.intecbrussel.centralblogproject.main;
import be.intecbrussel.centralblogproject.model.Post;
import be.intecbrussel.centralblogproject.service.AuthorServicesImpl;
import be.intecbrussel.centralblogproject.service.VisitorServices;
import be.intecbrussel.centralblogproject.service.VisitorServicesImpl;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        VisitorServicesImpl visitorServices = new VisitorServicesImpl();


        visitorServices.getPostsByAuthor(6);

        for (Post arg : visitorServices.getPostsByAuthor(6)) {
            arg.getTitle();

        }
    }


}

