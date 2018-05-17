package it.dan.model;

public class Item {
    private String articleId;
    private String name;
    private Integer price;

    public String getArticleId(){
        return articleId;
    }

    public void setArticleId(String articleId){
        this.articleId = articleId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Integer getPrice(){
        return price;
    }

    public void setPrice(Integer price){
        this.price = price;
    }

    @Override
    public String toString(){
        return "Article: " + articleId + "\t\t" + "Product name: \"" + name + "\"\t\t" + "Price: $" + price;
    }
}