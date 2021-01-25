package com.netposa.gat.model.person;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
* @author netposa
* @Description 人员数据 GAT 1400.3-数据库技术 表A.8  人员对象特征属性 页数：25
**/
@Data
public class PersonStruc implements Serializable{

    private static final long serialVersionUID = 3991053342839578154L;

    @JsonProperty("PersonListObject")
    private PersonObject PersonListObject;

    @Data
    public static class PersonObject{

        @JsonProperty("PersonObject")
        private List<Person> PersonObject;
    }
}
