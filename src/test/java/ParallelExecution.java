import org.testng.annotations.Test;

public class ParallelExecution {
@Test
	public static void test1() {
System.out.println("test1--1");
	}

@Test
public static void test12() {
	
	System.out.println("test--12");
}

@Test
public static void test13() {
	System.out.println("test--13");
}

}
