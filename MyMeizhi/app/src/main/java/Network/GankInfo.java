package Network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Liang Zihong on 2018/4/4.
 */

public interface GankInfo {

    @GET("data/福利/{count}/{page}")
    Call<FuliModel> getFuliModel(@Path("count") String count,@Path("page") String page);
}

