package pl.sda.mongo;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Database Access Object
 */
public class PostsDAO {
    public WriteResult insert(Post post) {
        DBCollection posts = getDbCollection();

        BasicDBObject newPost = new BasicDBObject();
        newPost.put("author", post.getAuthor());
        newPost.put("content", post.getContent());
        newPost.put("date", post.getDate());

        return posts.insert(newPost);

    }

    public List<DBObject> searchById(ObjectId id) {
        DBCollection posts = getDbCollection();
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);

        DBCursor postCursor = posts.find(query);
        List<DBObject> result = new ArrayList<>();
        while (postCursor.hasNext()) {
            DBObject post = postCursor.next();
            result.add(post);
        }
        return result;
    }

    public DBObject removeById(ObjectId id) {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);

        DBCollection posts = getDbCollection();
       return posts.findAndRemove(query);

    }

    private DBCollection getDbCollection() {
        Mongo mongo = new Mongo("localhost", 27017);
        return mongo.getDB("SDATest").getCollection("posts");

    }

    public WriteResult update(Post post, ObjectId id) {
        DBCollection posts = getDbCollection(); //pobieranie kolekcji
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);

        BasicDBObject updatePost = new BasicDBObject();
        updatePost.put("author", post.getAuthor());
        updatePost.put("content", post.getContent());
        updatePost.put("date", post.getDate());

        return posts.update(query, updatePost);
    }
}
