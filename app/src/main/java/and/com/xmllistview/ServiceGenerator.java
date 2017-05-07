package and.com.xmllistview;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by pavan on 6/5/17.
 */
public class ServiceGenerator {

    static final String BASE_URL = "http://vogella.com/";

    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create()).build();

}
