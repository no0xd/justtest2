package com.netposa.gat.service;

import java.util.List;

import com.netposa.gat.model.face.FaceStruc;
import com.netposa.gat.model.motor.MotorVehicleStruc;
import com.netposa.gat.model.nomotor.NonMotorVehicleStruc;
import com.netposa.gat.model.person.PersonStruc;
import com.netposa.gat.model.regist.RegisterContext;

public interface IPushDataService {
	
	public boolean pushFaceData(List<FaceStruc> faceStrucss,RegisterContext registContext);
	
	public boolean pushPersonData(List<PersonStruc> personStrucs,RegisterContext registContext);
	
	public boolean pushVehicleData(List<MotorVehicleStruc> vehicleStrucs,RegisterContext registContext);
	
	public boolean pushNonMotorData(List<NonMotorVehicleStruc> nonMotorStrucs,RegisterContext registContext);
	
}
