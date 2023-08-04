package org.example;

import com.mongodb.client.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.PatternLayout;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;

public class MongoCRUD {
    private static MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    public MongoDatabase database =  mongoClient.getDatabase("conygre_bank");
    public MongoCollection<Document> collection=  database.getCollection("customerData");

    public MongoCRUD() throws IOException {
        FileAppender appender = new FileAppender(new PatternLayout("%r [%t] %p %c %x - %m%n"), "mongodb_logger.log", true, false, 8192);
        BasicConfigurator.configure(appender);
    }

    public Document getDocumentByName(String name) {
        Document query =  new Document();
        query.put("Account Holder", name);
        Document expectedDocument = collection.find(query).first();
        return expectedDocument;
    }

    public void displayAllDocuments() {
        FindIterable<Document> allDocs =  collection.find();
        if (allDocs.first() == null) {
            System.out.println("No accounts are active!");
        }
        System.out.println("The currently active accounts from the database are ");
        System.out.println("===============================================================================");
        for(Document d: allDocs){
            System.out.printf("%s with a balance of Rs.%.2f/- [Account Type: %s]\n", d.get("Account Holder"), d.get("Balance"), d.get("Account Type"));
            System.out.println("===============================================================================");
        }
    }

    public void deleteDocument(String holderName) {
        Document docToBeDeleted = new Document();
        docToBeDeleted.put("Account Holder", holderName);
        collection.deleteOne(docToBeDeleted);
        System.out.println("Successfully deleted the account of " + holderName + ".");

    }

    public void updateDocument(String holderName, double updateBalance, String accountType) {
        Document searchDoc =
                collection.find(new Document("Account Holder",holderName).append("Account Type", accountType)).first();
        if (searchDoc == null) {
            collection.insertOne(new Document("Account Holder",holderName).append("Balance",updateBalance).append("Account Type", accountType));
        }
        else {
            Bson updateValue =  new Document("Balance",updateBalance);
            Bson updateOperation = new Document("$set",updateValue);
            collection.updateOne(searchDoc , updateOperation);
        }
    }

    public void insertDocument(String holderName, double balance, String accType){
        Document doc = new Document();
        doc.put("Account Holder",holderName);
        doc.put("Balance", balance);
        doc.put("Account Type", accType);
        collection.insertOne(doc);
    }








}

