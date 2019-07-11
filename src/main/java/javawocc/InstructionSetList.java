package javawocc;

public class InstructionSetList {
	protected byte[] isl;
	protected int index;

	public InstructionSetList() {
		this.isl = new byte[5];
	}

	public void add(Instruction inst) {
		if (index >= isl.length) {
			byte[] tmp = new byte[isl.length + 1];
			System.arraycopy(this.isl, 0, tmp, 0, this.isl.length);
			this.isl = tmp;
		}
		this.isl[index++] = (byte) inst.getOpcode();
	}

	public byte[] assemble() {
		return isl;
	}
}
