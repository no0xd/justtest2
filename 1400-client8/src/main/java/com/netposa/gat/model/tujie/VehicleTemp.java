package com.netposa.gat.model.tujie;


import lombok.Data;

@Data
public class VehicleTemp {
	private Long jgsj;
	private String hphm;
	private String hpys;
	private String hpzl;
	private String cllx;
	private String clpp;
	private String clnk;
	private String csys;
	private ylzd2 ylzd2;
	private String vehicleFeatureImage1;

	@Data
	public static class ylzd2{
		private Vehicle Vehicle;
		@Data
		public static class Vehicle{
			private Pos Pos;
			@Data
			public static class Pos{
				private int Bottom;
				private int Top;
				private int Right;
				private int Left;
			}
		}

	}

}
