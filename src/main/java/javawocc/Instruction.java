package javawocc;

public enum Instruction {
	iload(0x15), iload_0(0x1a), iload_1(0x1b), iload_2(0x1c), iload_3(0x1d), //
	istore(0x36), istore_0(0x3b), istore_1(0x3c), istore_2(0x3d), istore_3(0x3e), //
	ifeq(0x99), ifnull(0xc6), iflt(0x9b), ifle(0x9e), ifne(0x9a), ifnonnull(0xc7), ifgt(0x9d), ifge(0x9c), //
	ireturn(0xac), //
	iconst_1(0x4), iadd(0x60);

	private int opcode;

	private Instruction(int opcode) {
		this.opcode = opcode;
	}

	public int getOpcode() {
		return this.opcode;
	}
}
