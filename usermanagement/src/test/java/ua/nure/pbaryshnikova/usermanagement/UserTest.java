package ua.nure.pbaryshnikova.usermanagement;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class UserTest{

    private static final Long ID = 1L;
    private static final String MY_FIRST_NAME = "Polina";
    private static final String MY_LAST_NAME  = "Baryshnikova";
    private static Date myDate;
    private User user;
    private Calendar calendar;

    @Before
    public void setUp() throws Exception {
        myDate = new SimpleDateFormat("dd-MM-yyyy").parse("27-01-1999");
        user = new User(ID,MY_FIRST_NAME, MY_LAST_NAME, myDate);
        calendar = Calendar.getInstance();
    }

    @Test
    //testAgeNow
    public void test1() {
        int ageExpected = 19;

        calendar.set(1999,calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

        user.setDateOfBirth(calendar.getTime());

        int ageActual = user.getAge();

        assertEquals(ageExpected, ageActual);
    }

    @Test
    //testAgeBirthdayOneDayAfterToday
    public void test2() {
        int ageExpected = 18;

        calendar.set(1999,calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH + 1));

        user.setDateOfBirth(calendar.getTime());

        int ageActual = user.getAge();
        assertEquals(ageExpected, ageActual);
    }

    @Test
    //testAgeOneMonthAheadFromCurrentMonth
    public void test3() {
        int ageExpected = 18;

        calendar.set(1999,calendar.get(Calendar.MONTH),26);
        calendar.add(Calendar.MONTH, 1);

        user.setDateOfBirth(calendar.getTime());

        int ageActual = user.getAge();
        assertEquals(ageExpected, ageActual);
    }

    @Test
    //testAgeOneYearAheadFromCurrentMonth
    public void test4() {
        int ageExpected = 17;

        calendar.set(1999,calendar.get(Calendar.MONTH),26);
        calendar.add(Calendar.YEAR, 1);

        user.setDateOfBirth(calendar.getTime());

        int ageActual = user.getAge();
        assertEquals(ageExpected, ageActual);
    }

    @Test
    //testNewbornAge
    public void test5() {
        int ageExpected = 0;

        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

        user.setDateOfBirth(calendar.getTime());

        int ageActual = user.getAge();
        assertEquals(ageExpected, ageActual);
    }


    @Test
    //testFullName
    public void test6() {
        String resultExpected = MY_LAST_NAME + ", " + MY_FIRST_NAME;
        assertEquals(resultExpected,user.getFullName());
    }
}