package assignment4;
/** DictionaryTester *****************************************
 * Class to test the Dictionary Data Structure.
 * Tests all the Dictionary Methods
 * Uses the JUnit framework
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: rpm953, hjw396
 * @author Ronald Macmaster, Horng-Bin Justin Wei 
 * @version 1.01 2/27/2016
 ************************************************************/

import static org.junit.Assert.*;

import org.junit.*;

public class DictionaryTester{

	public Dictionary dict1;
	public Dictionary dict2;

	public static final int word_count = 5757;

	@Before
	public void SetUp(){
		dict1 = new Dictionary("A4words.dat");
		dict2 = new Dictionary("A4-words.txt");
	}

	@Test
	/** Tests the size of each dictionary obj */
	public void testGetCapactiy(){
		Dictionary dictnull = new Dictionary("test/graph.csv");
		Dictionary dictsingle = new Dictionary("test/Untitled1");
		assertEquals("failed dictnull capactity", 0, dictnull.getCapacity());
		assertEquals("failed dictsingle capactity", 1, dictsingle.getCapacity());
		assertEquals("failed dict1 capactity", word_count, dict1.getCapacity());
		assertEquals("failed dict2 capactity", word_count, dict2.getCapacity());
	}

	@Test
	/** Tests getting a word by index from the dictionary */
	public void testGetWord(){
		// null tests
		Dictionary dictnull = new Dictionary("test/graph.csv");
		assertEquals("failed dictnull getword", null, dictnull.getWord(123));
		assertEquals("failed dictnull getword", null, dictnull.getWord(0));

		// single tests
		Dictionary dictsingle = new Dictionary("test/Untitled1");
		assertEquals("failed dictsingle getword", new Word("onwor"), dictsingle.getWord(0));
		assertEquals("failed dictsingle getword", null, dictsingle.getWord(-1));
		assertEquals("failed dictsingle getword", null, dictsingle.getWord(1));

		/** dict1 tests */
		// regular usecase
		assertEquals("failed dict1 getword", new Word("lauds"), dict1.getWord(2735));
		assertEquals("failed dict1 getword", new Word("minas"), dict1.getWord(3097));
		assertEquals("failed dict1 getword", new Word("fagot"), dict1.getWord(1621));

		// left bound
		assertEquals("failed dict1 getword", new Word("aargh"), dict1.getWord(0));
		assertEquals("failed dict1 getword", new Word("abaca"), dict1.getWord(1));
		assertEquals("failed dict1 getword", null, dict1.getWord(-1));

		// right bound
		assertEquals("failed dict1 getword", new Word("zooms"), dict1.getWord(5755));
		assertEquals("failed dict1 getword", new Word("zowie"), dict1.getWord(5756));
		assertEquals("failed dict1 getword", null, dict1.getWord(5757));

		/** dict2 tests */
		// regular usecase
		assertEquals("failed dict2 getword", new Word("lauds"), dict2.getWord(2735));
		assertEquals("failed dict2 getword", new Word("minas"), dict2.getWord(3097));
		assertEquals("failed dict2 getword", new Word("fagot"), dict2.getWord(1621));

		// left bound
		assertEquals("failed dict2 getword", new Word("aargh"), dict2.getWord(0));
		assertEquals("failed dict2 getword", new Word("abaca"), dict2.getWord(1));
		assertEquals("failed dict2 getword", null, dict2.getWord(-1));

		// right bound
		assertEquals("failed dict2 getword", new Word("zooms"), dict2.getWord(5755));
		assertEquals("failed dict2 getword", new Word("zowie"), dict2.getWord(5756));
		assertEquals("failed dict2 getword", null, dict2.getWord(5757));
	}

	@Test
	/** Tests word check for existence in the dictionary */
	public void testContainsWord(){
		// TODO: check ContainsWord Method
	}
}
