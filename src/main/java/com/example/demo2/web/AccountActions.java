package com.example.demo2.web;

import com.mongodb.client.*;
import org.bson.Document;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class AccountActions implements Closeable {
    private final MongoClient mongoClient;
    private final MongoCollection<Document> userCollection;
    private final MongoDatabase db;
    private final Document doc;
    private final String nameFromServlet;
    private final String passFromServlet;
    private User user;

    public AccountActions (String name, String pass) {
        mongoClient = MongoDB.getMongoClient();
        //connection to collection user in DB progress_pulse_db
        db = mongoClient.getDatabase("progress_pulse_db");
        userCollection = db.getCollection("users");


        //SELECT * FROM <database> WHERE name = name, password = pass
        FindIterable<Document> findIterable = userCollection.find(and(eq("name", name)
                , eq("password", pass)));
        doc = findIterable.first();
        this.nameFromServlet = name;
        this.passFromServlet = pass;
    }

    //returns true if account successfully logged
    public boolean loginIntoAccount () {
        System.out.println("doc.toString()");
        if (!isEmpty()) {
            try {
                user  = new User(db, doc);
                /*user.showNotes();
                System.out.println(doc.getObjectId("_id").toString());
                System.out.println(doc);*/
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else return false;
        return true;
    }

    //returns true if account successfully created
    public boolean createNewAccount () {

        if (!isEmpty()) {
            return false;
            //account exists, can not be created
        } else {
            Map<String, String> newAccountMap = new HashMap<>();
            newAccountMap.put("name", nameFromServlet);
            newAccountMap.put("password", passFromServlet);

            //INSERT INTO <database> name, password VALUES (nameFromServlet, passFromServlet)
            userCollection.insertOne(new Document(newAccountMap));
        }
        return true;
    }

    //return true if account does not exist
    public boolean isEmpty() {
        return doc==null;
    }

    //returns true if account successfully deleted
    public boolean deleteAccount () {
        if (isEmpty()) {
            return false;
        } else {
            System.out.println("DROP is not working yet");
        }
        return true;
    }

    @Override
    public void close() throws IOException {
        mongoClient.close();
    }
}
