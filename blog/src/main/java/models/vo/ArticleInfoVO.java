package models.vo;

import models.ArticleInfo;

/**
 * @author FCCC
 * @version 1.0
 * @date 2021/4/9 10:55
 */
public class ArticleInfoVO extends ArticleInfo {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
