package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoaderTest {
	private static int i = 0;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		i = FileUtilities.countLinesOfAFile("./Resources/TestInput/2007_sample.tsv");
		
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public final void testLoad() {
		assertEquals("test if load() works ok", 120, i);
	}

}
