package pl.sda.mongo;

import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        PostsDAO dao = new PostsDAO();

        Post post = new Post();
        post.setAuthor("Miatek");
        post.setContent("tresc z fufufu");
        post.setDate(new Date());
        dao.insert(post);


//        List<DBObject> searchResult = dao.searchById(new ObjectId("58c059b7fb06ac0530142881"));
//        System.out.println(searchResult.toString());

        dao.removeById(new ObjectId("58c059b7fb06ac0530142881"));
        System.out.println("");

        Post post2 = new Post();
        post2.setAuthor("Swiergol");
        post2.setContent("nie mam pojecia o co chodzi");
        post2.setDate(new Date());
        dao.update(post2, new ObjectId("58c03c69476da6172d7efbaa"));

    }
}
