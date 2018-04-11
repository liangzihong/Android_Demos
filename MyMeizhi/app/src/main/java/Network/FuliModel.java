package Network;

import java.util.List;

/**
 * Created by Liang Zihong on 2018/4/4.
 */

public class FuliModel {

    private boolean error;
    private List<Result> results;


    public FuliModel() {
    }


    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }


    public class Result
    {
        private String url;
        private String publishedAt;

        public Result() {
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }
    }
}
