package javawocc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JavawoccCompilerTest {

	@Test
	void test() {
		JavawoccCompiler compiler = new JavawoccCompiler();
		byte[] compiled = compiler.compile("a=3; if(a==3){return 1;}else{return 2;}");
		assertNotNull(compiled);
	}

}
