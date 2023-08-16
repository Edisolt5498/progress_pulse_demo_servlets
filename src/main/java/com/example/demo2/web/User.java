package com.example.demo2.web;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class User {
    private final Document doc;
    private final String userId;
    private final MongoDatabase db;

    public User (MongoDatabase db, Document doc) throws Exception {
        this.db = db;
        this.doc = doc;
        userId = doc.getObjectId("_id").toString();
    }

    public void createNote (String topic, String noteName, String categoryName) {
        MongoCollection<Document> noteCollection = db.getCollection("notes");

        //INSERT INTO <database> :
        noteCollection.insertOne(Document.parse(
                "{\n" +
                "   userId: \""    + userId + "\",\n" +
                "   noteName: \""  + noteName + "\",\n" +
                "   noteBody: {\n" +
                "              topic: \""        + topic + "\",\n" +
                "              categoryName: \"" + categoryName + "\",\n" +
                "              time: \""         + "some time" + "\",\n" +
                "            }\n" +
                "}"));
    }
    public boolean showAll () {
        MongoCollection<Document> noteCollection = db.getCollection("notes");
        //SELECT  * FROM <database> WHERE userId = userId
        FindIterable<Document> findIterable = noteCollection.find(eq("userId", userId));
        MongoCursor<Document> cursor = findIterable.cursor();

        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
        cursor.close();
        return true;
    }
}
