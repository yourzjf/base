package net.yourzjf.corejava.myUtils;

/**
 * 数据存储单位转换
 * @author Jeffrey.Zh
 * @version 1.0
 */
public enum DiskSpaceUnit {
	/**
	 * 存储空间计算的基本单位，1B即1byte.
	 */
	B {
		public double toB(double v) { return persistCut(v, 0); }
		public double toKB(double v, int persist) { return persistCut(v / (C1 / C0), persist); }
		public double toMB(double v, int persist) { return persistCut(v / (C2 / C0), persist); }
		public double toGB(double v, int persist) { return persistCut(v / (C3 / C0), persist); }
		public double toTB(double v, int persist) { return persistCut(v / (C4 / C0), persist); }
		public double toPB(double v, int persist) { return persistCut(v / (C5 / C0), persist); }
		public double toEB(double v, int persist) { return persistCut(v / (C6 / C0), persist); }
		public double convert(double v, DiskSpaceUnit unit, int persist){ return unit.toB(v); }
		public DiskSpaceUnit next() { return KB; }
	},
	/**
	 * 存储空间计算的基本单位，1KB=2^10B.
	 */
	KB {
		public double toB(double v) { return persistCut(v * (C1 / C0), 0); }
		public double toKB(double v, int persist) { return persistCut(v, persist); }
		public double toMB(double v, int persist) { return persistCut(v / (C2 / C1), persist); }
		public double toGB(double v, int persist) { return persistCut(v / (C3 / C1), persist); }
		public double toTB(double v, int persist) { return persistCut(v / (C4 / C1), persist); }
		public double toPB(double v, int persist) { return persistCut(v / (C5 / C1), persist); }
		public double toEB(double v, int persist) { return persistCut(v / (C6 / C1), persist); }
		public double convert(double v, DiskSpaceUnit unit, int persist){ return unit.toKB(v, persist); }
		public DiskSpaceUnit previous() { return B; }
		public DiskSpaceUnit next() { return MB; }
	},
	/**
	 * 存储空间计算的基本单位，1MB=2^20B.
	 */
	MB {
		public double toB(double v) { return persistCut(v * (C2 / C0), 0); }
		public double toKB(double v, int persist) { return persistCut(v * (C2 / C1), persist); }
		public double toMB(double v, int persist) { return persistCut(v, persist); }
		public double toGB(double v, int persist) { return persistCut(v / (C3 / C2), persist); }
		public double toTB(double v, int persist) { return persistCut(v / (C4 / C2), persist); }
		public double toPB(double v, int persist) { return persistCut(v / (C5 / C2), persist); }
		public double toEB(double v, int persist) { return persistCut(v / (C6 / C2), persist); }
		public double convert(double v, DiskSpaceUnit unit, int persist){ return unit.toMB(v, persist); }
		public DiskSpaceUnit previous() { return KB; }
		public DiskSpaceUnit next() { return GB; }
	},
	/**
	 * 存储空间计算的基本单位，1GB=2^30B.
	 */
	GB {
		public double toB(double v) { return persistCut(v * (C3 / C0), 0); }
		public double toKB(double v, int persist) { return persistCut(v * (C3 / C1), persist); }
		public double toMB(double v, int persist) { return persistCut(v * (C3 / C2), persist); }
		public double toGB(double v, int persist) { return persistCut(v, persist); }
		public double toTB(double v, int persist) { return persistCut(v / (C4 / C3), persist); }
		public double toPB(double v, int persist) { return persistCut(v / (C5 / C3), persist); }
		public double toEB(double v, int persist) { return persistCut(v / (C6 / C3), persist); }
		public double convert(double v, DiskSpaceUnit unit, int persist){ return unit.toGB(v, persist); }
		public DiskSpaceUnit previous() { return MB; }
		public DiskSpaceUnit next() { return TB; }
	},
	/**
	 * 存储空间计算的基本单位，1TB=2^40B.
	 */
	TB {
		public double toB(double v) { return persistCut(v * (C4 / C0), 0); }
		public double toKB(double v, int persist) { return persistCut(v * (C4 / C1), persist); }
		public double toMB(double v, int persist) { return persistCut(v * (C4 / C2), persist); }
		public double toGB(double v, int persist) { return persistCut(v * (C4 / C3), persist); }
		public double toTB(double v, int persist) { return persistCut(v, persist); }
		public double toPB(double v, int persist) { return persistCut(v / (C5 / C4), persist); }
		public double toEB(double v, int persist) { return persistCut(v / (C6 / C4), persist); }
		public double convert(double v, DiskSpaceUnit unit, int persist){ return unit.toTB(v, persist); }
		public DiskSpaceUnit previous() { return GB; }
		public DiskSpaceUnit next() { return PB; }
	},
	/**
	 * 存储空间计算的基本单位，1PB=2^50B.
	 */
	PB {
		public double toB(double v) { return persistCut(v * (C5 / C0), 0); }
		public double toKB(double v, int persist) { return persistCut(v * (C5 / C1), persist); }
		public double toMB(double v, int persist) { return persistCut(v * (C5 / C2), persist); }
		public double toGB(double v, int persist) { return persistCut(v * (C5 / C3), persist); }
		public double toTB(double v, int persist) { return persistCut(v * (C5 / C4), persist); }
		public double toPB(double v, int persist) { return persistCut(v, persist); }
		public double toEB(double v, int persist) { return persistCut(v / (C6 / C5), persist); }
		public double convert(double v, DiskSpaceUnit unit, int persist){ return unit.toPB(v, persist); }
		public DiskSpaceUnit previous() { return TB; }
		public DiskSpaceUnit next() { return EB; }
	},
	/**
	 * 存储空间计算的基本单位，1EB=2^60B.
	 */
	EB {
		public double toB(double v) { return persistCut(v * (C6 / C0), 0); }
		public double toKB(double v, int persist) { return persistCut(v * (C6 / C1), persist); }
		public double toMB(double v, int persist) { return persistCut(v * (C6 / C2), persist); }
		public double toGB(double v, int persist) { return persistCut(v * (C6 / C3), persist); }
		public double toTB(double v, int persist) { return persistCut(v * (C6 / C4), persist); }
		public double toPB(double v, int persist) { return persistCut(v * (C6 / C5), persist); }
		public double toEB(double v, int persist) { return persistCut(v, persist); }
		public double convert(double v, DiskSpaceUnit unit, int persist){ return unit.toEB(v, persist); }
		public DiskSpaceUnit previous() { return PB; }
	};

	private static double persistCut(double v, int persist) {
		return ((long) (v * Math.pow(10, persist))) / Math.pow(10, persist);
	}

	static final double C0 = 1.0d;
	static final double C1 = C0 * 1024;
	static final double C2 = C1 * 1024;
	static final double C3 = C2 * 1024;
	static final double C4 = C3 * 1024;
	static final double C5 = C4 * 1024;
	static final double C6 = C5 * 1024;

	public abstract double toB(double v);
	public abstract double toKB(double v, int persist);
	public abstract double toMB(double v, int persist);
	public abstract double toGB(double v, int persist);
	public abstract double toTB(double v, int persist);
	public abstract double toPB(double v, int persist);
	public abstract double toEB(double v, int persist);

	/**
	 * 基于当前单位的单位换算
	 * 
	 * @param v
	 *            数值
	 * @param vunit
	 *            原单位
	 * @param persist
	 *            保留小数位数
	 * @return 使用新单位计量的新数值
	 */
	public abstract double convert(double v, DiskSpaceUnit vunit, int persist);

	/**
	 * 上一个计数单位，最小单位为{@link #B}
	 * @return 上一个计数单位
	 */
	public DiskSpaceUnit previous() {
		throw new RuntimeException("previous disk space unit is not found");
	};

	/**
	 * 下一个计数单位，最大单位为{@link #EB}
	 * @return 下一个计数单位
	 */
	public DiskSpaceUnit next() {
		throw new RuntimeException("next disk space unit is not found");
	};
}