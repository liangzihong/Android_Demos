package Models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Liang Zihong on 2018/3/28.
 */

public interface APIInterface {

    @GET("/users/{username}")
    Call<User> getUser(@Path("username") String name);

}
