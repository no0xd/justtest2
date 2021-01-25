package com.netposa.gat.model.face;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
* @author netposa
* @Description 人脸数据 GAT 1400.3-数据库技术 表A.9  人脸对象特征属性 页数：29
* @return
**/
@Data
public class FaceStruc {

    @JsonProperty("FaceListObject")
    private FaceObject FaceListObject;

    public static class FaceObject{
        @JsonProperty("FaceObject")
        private List<Face> FaceObject;

		public List<Face> getFaceObject() {
			return FaceObject;
		}

		public void setFaceObject(List<Face> faceObject) {
			FaceObject = faceObject;
		}
        
    }
}
