package HashClass;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestHash {

	@Test
	public void testMD5(){
		assertEquals("3184e6d8bfd0cbbcdc412675ecc5f927", Hash.md5("Vladislav"));
	}
	
	@Test
	public void testSha1(){
		assertEquals("0ede07bad314be2826e33d60fa412c481af2bd09", Hash.sha1("Vladislav"));
	}
}
