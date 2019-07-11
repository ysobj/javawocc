package javawocc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InstructionSetListTest {
	@Test
	void test() {
		InstructionSetList list = new InstructionSetList();
		list.add(Instruction.iload_1);
		list.add(Instruction.iconst_1);
		list.add(Instruction.iadd);
		list.add(Instruction.istore_1);
		list.add(Instruction.iload_1);
		list.add(Instruction.ireturn);
		byte[] expected = new byte[] { //
				0x1b, //
				0x4, //
				0x60, //
				0x3c, //
				0x1b, //
				(byte) 0xac//
		};
		assertArrayEquals(expected, list.assemble());
	}
}
