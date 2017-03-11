package net.yourzjf.corejava.myUtils;

import java.io.Serializable;

/**
 * default unit is byte, default value is 0.00d
 * 
 * @author Jeffrey.Zh
 * @version 1.0
 */
public class DiskSpaceWithUnit implements Comparable<DiskSpaceWithUnit>, Serializable {

	private static final long serialVersionUID = 326736782467300376L;

	private double space = 0.00d;
	private DiskSpaceUnit unit = DiskSpaceUnit.B;

	public DiskSpaceWithUnit() {
	}

	public DiskSpaceWithUnit(double space) {
		this.space = space;
	}

	public DiskSpaceWithUnit(double space, DiskSpaceUnit unit) {
		if (unit == null)
			throw new IllegalArgumentException("unit can not null.");
		this.space = space;
		this.unit = unit;
	}

	/**
	 * 方法提供转换为可读性较好的单位形态展现存储数据空间大小，范围为1.0~1023.#，单位为B、KB、MB、GB、TB、PB、EB，进制为1024
	 * 
	 * @param persist
	 *            保留小数位数
	 * @return 带单位的存储空间大小值，超出保留位数的部分将被舍弃，非精确数值
	 */
	public DiskSpaceWithUnit humanReadableVal(int persist) {
		DiskSpaceUnit currUnit = unit;
		double currSpace = space;
		do {
			if (currSpace < 1.0d) {
				if (DiskSpaceUnit.B.equals(unit))
					return this;
				currUnit = currUnit.previous();
			} else if (currSpace >= 1024.0d) {
				if (DiskSpaceUnit.EB.equals(unit))
					return this;
				currUnit = currUnit.next();
			}
			currSpace = currUnit.convert(space, unit, persist);
		} while (currSpace >= 1024.0d || currSpace < 1.0d);
		return new DiskSpaceWithUnit(currSpace, currUnit);
	}

	/**
	 * 方法将返回一个新的带单位的存储数据空间对象，超出保留小数后的部分将被舍弃或者补零
	 * 
	 * @param unit
	 *            需要转换的新的单位
	 * @param persist
	 *            保留小数位数
	 * @return 带单位的存储空间大小值，超出保留位数的部分将被舍弃，非精确数值
	 */
	public DiskSpaceWithUnit valueAs(DiskSpaceUnit unit, int persist) {
		if (unit == null) {
			try {
				return (DiskSpaceWithUnit) this.clone();
			} catch (CloneNotSupportedException e) {
				throw new RuntimeException(e);
			}
		}
		double value = unit.convert(this.space, this.unit, persist);
		return new DiskSpaceWithUnit(value, unit);
	}

	/**
	 * 带单位的加运算，返回结果单位为(B)
	 * 
	 * @param b
	 *            被加数
	 * @return 运算结果，单位为B
	 */
	public DiskSpaceWithUnit increase(DiskSpaceWithUnit b) {
		DiskSpaceWithUnit t = new DiskSpaceWithUnit();
		t.setUnit(DiskSpaceUnit.B);
		t.setSpace(unit.toB(space) + b.unit.toB(b.getSpace()));
		return t;
	}

	/**
	 * 带单位的减运算，返回结果单位为(B)
	 * 
	 * @param b
	 *            被减数
	 * @return 运算结果，单位为B
	 */
	public DiskSpaceWithUnit subtract(DiskSpaceWithUnit b) {
		DiskSpaceWithUnit t = new DiskSpaceWithUnit();
		t.setUnit(DiskSpaceUnit.B);
		t.setSpace(unit.toB(space) - b.unit.toB(b.getSpace()));
		return t;
	}

	@Override
	public int compareTo(DiskSpaceWithUnit o) {
		if (this.unit.toB(space) > o.unit.toB(o.space))
			return 1;
		else if (this.unit.toB(space) < o.unit.toB(o.space))
			return -1;
		return 0;
	}

	public double getSpace() {
		return space;
	}

	public void setSpace(double space) {
		this.space = space;
	}

	public DiskSpaceUnit getUnit() {
		return unit;
	}

	public void setUnit(DiskSpaceUnit unit) {
		if (unit == null)
			throw new IllegalArgumentException("unit can not null.");
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "DiskSpaceWithUnit [space=" + space + ", unit=" + unit + "]";
	}

}
