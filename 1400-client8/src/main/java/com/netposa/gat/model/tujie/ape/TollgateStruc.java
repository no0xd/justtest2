package com.netposa.gat.model.tujie.ape;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hjc
 * Date: 2018-04-24
 * Time: 16:54
 */
@Data
public class TollgateStruc {
    private TollgateStruc.TollgateObject TollgateListObject;

    @Data
    public static class TollgateObject{

        private List<Tollgate> TollgateObject;
    }
}
