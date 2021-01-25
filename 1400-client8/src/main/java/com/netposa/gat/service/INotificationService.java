package com.netposa.gat.service;

import com.netposa.gat.model.face.FaceStruc;
import com.netposa.gat.model.motor.MotorVehicleStruc;
import com.netposa.gat.model.nomotor.NonMotorVehicleStruc;
import com.netposa.gat.model.notification.SubscribeNotification;
import com.netposa.gat.model.person.PersonStruc;
import com.netposa.gat.model.subscribe.Subscribe;

public interface INotificationService {

	public boolean NotificationFaceData(FaceStruc faceStruc,Subscribe subscribe);
	
	public boolean NotificationPersonData(PersonStruc personStruc,Subscribe subscribe);
	
	public boolean NotificationVehicleData(MotorVehicleStruc vehicleStruc,Subscribe subscribe);
	
	public boolean NotificationNomotorData(NonMotorVehicleStruc noMotorStruc,Subscribe subscribe);
	
	public String sendNotificationData(SubscribeNotification notification, Subscribe subscribe) throws Exception;


}
