package com.walker.bean;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 城市实体类
 *
 * @author Walker
 * @date 2019/1/23 下午5:16
 */
@Document(indexName = "province", type = "city", shards = 2)
public class City implements Serializable {

    private static final long serialVersionUID = 9122700392124569472L;

    /**
     * 城市编号
     */
    private Long id;

    /**
     * 城市名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 城市评分
     */
    private Integer score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
