import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBlocks {

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@Test
	void testReadFile() throws FileNotFoundException, IOException {
		Blocks.readFile("ethereumP1data.txt");
		ArrayList<Blocks> testBlocks = Blocks.getBlocks();
		
		assertEquals(100, testBlocks.size());
		assertEquals(15049330, testBlocks.get(49).getNumber());
		assertEquals("0xcd458d7f11023556cc9058f729831a038cb8df9c", testBlocks.get(80).getMiner());
	}
	
	@Test
	void testBlockDiff() {
		Blocks block1 = new Blocks(100);
		Blocks block2 = new Blocks(50);
		
		assertEquals(50, Blocks.blockDiff(block1, block2));
		assertEquals(-50, Blocks.blockDiff(block2, block1));
	}
	
	@Test
	void testGetBlockByNumber() throws FileNotFoundException, IOException {
		Blocks.readFile("ethereumP1data.txt");
		ArrayList<Blocks> testBlocks = Blocks.getBlocks();
		
		assertEquals(testBlocks.get(0), Blocks.getBlockByNumber(15049308));
		assertEquals(testBlocks.get(49), Blocks.getBlockByNumber(15049330));
		assertNull(Blocks.getBlockByNumber(100000));
	}
	
	@Test
	void testToString() {
		Blocks blockA = new Blocks();
		Blocks blockB = new Blocks(123456);
		Blocks blockC = new Blocks(654321, "0xabcdef");
		System.out.println(blockA);
		assertTrue(outputStreamCaptor.toString().contains("Empty Block"));
		System.out.println(blockB);
		assertTrue(outputStreamCaptor.toString().contains("Block Number: 123456"));
		System.out.println(blockC);
		assertTrue(outputStreamCaptor.toString().contains("Block Number: 654321 Miner Address: 0xabcdef"));
		
	}
	
	@AfterEach
	public void tearDown() {
		System.setOut(standardOut);
	}
	
	@Test
	void testGetters() {
		int number = 22011890;
		String miner = "0xcc2334fa22";
		Blocks testBlock = new Blocks(number, miner);
		assertEquals(number, testBlock.getNumber());
		assertEquals(miner, testBlock.getMiner());
	}
	
	@Test
	void testConstructors() {
		int number = 01234567;
		String miner = "0x89abcdef";
		
		Blocks testCon1 = new Blocks();
		assertEquals(0, testCon1.getNumber());
		assertNull(testCon1.getMiner());
		
		Blocks testCon2 = new Blocks(number);
		assertEquals(number, testCon2.getNumber());
		assertNull(testCon2.getMiner());
		
		Blocks testCon3 = new Blocks(number, miner);
		assertEquals(number, testCon3.getNumber());
		assertEquals(miner, testCon3.getMiner());
	}

}
