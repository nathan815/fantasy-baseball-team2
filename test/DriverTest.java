import static org.junit.Assert.*;

import org.junit.Test;

public class DriverTest {

//    @Test
//    public void testSplitFunction() {
//        String str = "IDRAFT ODRAFT OVERALL";
//        String[] arr = {"IDRAFT", "ODRAFT", "OVERALL"};
//        assertEquals(arr, Driver.userSplit(str));
//    }

    @Test
    public void testValidInput() {
        String str = "IDRAFT";
        assertEquals("IDRAFT", Driver.order(str));
    }

    @Test
    public void testInvalidInput() {
        String str = "ABCDE";
        assertEquals("", Driver.order(str));
    }

    @Test
    public void testUserSplitODRAFT(){
        String[] arr = {"ODRAFT", "Cruz, N", "A"};
        assertArrayEquals(arr, Driver.userSplit("ODRAFT \"Cruz, N\" A"));
    }

    @Test
    public void testUserSplitIDRAFT(){
        String[] arr = {"IDRAFT", "Gray"};
        assertArrayEquals(arr, Driver.userSplit("IDRAFT \"Gray\""));
    }

    @Test
    public void testUserSplitSaveRestore(){
        String[] arr = {"SAVE", "book"};
        assertArrayEquals(arr, Driver.userSplit("SAVE book"));
        String[] arr1 = {"RESTORE", "book1"};
        assertArrayEquals(arr1, Driver.userSplit("RESTORE book1"));

    }

}
