package com.example.demo2.web;

import com.mongodb.*;
import com.mongodb.client.*;

import java.io.Closeable;
import java.io.IOException;

public class MongoDB implements Closeable {
    private static MongoClient mongoClient;
    public static MongoClient getMongoClient () {
        ConnectionString connectionString = new ConnectionString(
                "mongodb+srv://EdisoltH:Ehm8993524@cluster0.mvr7t7h.mongodb.net/?retryWrites=true&w=majority"
        );

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        mongoClient = MongoClients.create(settings);
        return mongoClient;
    }

    @Override
    public void close() throws IOException {
        mongoClient.close();
    }
}

