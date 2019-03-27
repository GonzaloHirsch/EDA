package TimerJava;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimerTest {

    @BeforeEach
    void setUp() {
        System.out.println("Test Started");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Finished");
    }

    //---------------------------------------EXCEPTIONS----------------------------------------------------

    void DontStopTimerMethod(){
        Timer timer = new Timer();
        System.out.println(timer);
    }

    void EndWithParameter(long stop){
        Timer timer = new Timer();
        timer.Stop(stop);
        System.out.println(timer);
    }

    void StartWithParameter(long start){
        Timer timer = new Timer(start);
        timer.Stop();
        System.out.println(timer);
    }

    void StopMany(long end){
        Timer timer = new Timer();
        timer.Stop();
        timer.Stop();
        timer.Stop(end);
        System.out.println(timer);
    }

    void AssertExceptionStartEndMethod(long start, long end){
        assertThrows(RuntimeException.class, () -> {
            Timer timer = new Timer(start);
            timer.Stop(end);
            System.out.println(timer);
        });
    }

    void AssertExceptionNotStopMethod(){
        assertThrows(RuntimeException.class, this::DontStopTimerMethod);
    }

    void AssertStopManyMethod(long end){
        assertThrows(RuntimeException.class, () ->{
            StopMany(end);
        });
    }

    void AssertExceptionStartWithParameterMethod(long start){
        assertThrows(RuntimeException.class, () ->{
            StartWithParameter(start);
        });
    }

    void AssertExceptionEndWithParameterMethod(long end){
        assertThrows(RuntimeException.class, () ->{
            EndWithParameter(end);
        });
    }

    //---------------------------------------NOT EXCEPTIONS-------------------------------------------------

    void AssertMillisDurationRangeMethod(long millis, int error){
        Timer timer = new Timer();
        try{
            Thread.sleep(millis);
        } catch (Exception ex){
            fail();
        }
        timer.Stop();
        System.out.println("Duration: " + millis + " Actual Duration: " + timer.GetMillis() + " Error Allowed: " + error);
        assertTrue(millis + error >= timer.GetMillis() && millis - error <= timer.GetMillis() );
    }

    void AssertMillisDurationExactMethod(long start, long end){
        Timer timer = new Timer(start);
        timer.Stop(end);
        System.out.println("Duration: " + (end - start) + " Actual Duration: " + timer.GetMillis());
        assertEquals(end - start, timer.GetMillis());
    }

    //-----------------------------------TESTS-----------------------------------------------------

    @Test
    void AssertMillisDurationRange(){
        AssertMillisDurationRangeMethod(1000, 20);
        AssertMillisDurationRangeMethod(4323, 12);
        AssertMillisDurationRangeMethod(645, 12);
        AssertMillisDurationRangeMethod(1543, 12);
    }

    @Test
    void AssertMillisDurationExact(){
        AssertMillisDurationExactMethod(100, 101);
        AssertMillisDurationExactMethod(1000, 4530);
        AssertMillisDurationExactMethod(234234, 234234434);
        AssertMillisDurationExactMethod(2342, 234234234);
        AssertMillisDurationExactMethod(112, 1231231);
    }

    @Test
    void AssertExceptions(){
        AssertExceptionNotStopMethod();
        AssertExceptionStartEndMethod(-100, 100);
        AssertExceptionStartEndMethod(200, 100);
        AssertExceptionStartWithParameterMethod(999999999999999999L);
        AssertExceptionEndWithParameterMethod(100);
        AssertExceptionEndWithParameterMethod(343434);
        AssertStopManyMethod(999999999999999L);
    }
}