package and.com.xmllistview.Interface;

import and.com.xmllistview.POJO.RSSFeed;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by pavan on 5/5/17.
 */
public interface GoogleClient {

    @GET("article.rss")
    Call<RSSFeed> loadRSSFeed();
}
