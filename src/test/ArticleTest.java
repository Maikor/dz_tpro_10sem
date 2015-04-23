import com.springapp.article.Article;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by mkorshun on 4/23/2015.
 */
public class ArticleTest {

    @Test
    public void sayHello(){

        Article article = new Article();
        article.setId(3);
        Assert.assertEquals(3, article.getId());

    }
}
