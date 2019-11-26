import static org.junit.Assert.*;

import org.junit.Test;

public class DriverTest {

    @Test
    public void testSplitFunction() {
        String str = "IDRAFT ODRAFT OVERALL";
        String[] arr = {"IDRAFT", "ODRAFT", "OVERALL"};
        assertEquals(arr, Driver.userSplit(str));
    }

    @Test
    public void testValidInput() {
        String str = "IDRAFT";
        assertEquals("IDRAFT", Driver.order(str));
    }

    @Test
    public void testInvalidInput() {
        String str = "ABCDE";
        assertEquals("Invalid Input", Driver.order(str));
    }
}
