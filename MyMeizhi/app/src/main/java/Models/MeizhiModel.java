package Models;

/**
 * Created by Liang Zihong on 2018/4/3.
 */

public class MeizhiModel {

    private String url;
    private String text;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MeizhiModel(String url, String text) {
        this.url = url;
        this.text = text;
    }
}
