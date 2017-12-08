package pl.sdacademy.animals.bear;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import pl.sdacademy.animals.time.Clock;

import static org.junit.jupiter.api.Assertions.*;

class BearTest {
    @Test
    void BearShouldBeAliveImmediatelyAfterCreation() {
        //given:
        int weight = 3;
        Bear bear = new BlackBear(weight);

        //when:
        boolean result = bear.isAlive();

        //then:
        assertTrue(result);
    }

    @Test
    void BearShouldBeAliveIfItHasEatenWithin10Days() {
        //given:
        int weight = 3;
        Bear bear = new BlackBear(weight);
        bear.eat();

        //when:
        boolean result = bear.isAlive();

        //then:
        assertTrue(result);
    }

    @Test
    void BearShouldNotBeAliveIfItHasEatenWithinMoreThan10Days() {
        //given:
        int weight = 3;
        Clock clock = new TestClock();
        Bear bear = new BlackBear(weight, clock);
        bear.eat();

        //when:
        boolean result = bear.isAlive();

        //then:
        assertTrue(result == false);
    }

    class TestClock implements Clock {

        private DateTime time = DateTime.now();

        @Override
        public DateTime getCurrentTime() {
            return time;
        }

        public void changeTimeByDays(int days) {
            time = time.plusDays(days);
        }
    }

    @Test
    void BearShouldBeAliveAfterEat() {
        //given:
        int weight = 3;
        Bear bear = new BlackBear(weight);

        //when:
        bear.eat();

        //then:
        bear.isAlive();
    }

    @Test
    void BearShouldGainedByMealWeight() {
        //given:
        int weight = 3;
        int weightOfMeal = 3;
        Bear bear = new BlackBear(weight);

        //when:
        bear.eat(weightOfMeal);

        //then:
        assertTrue(bear.getWeight() == weight + weightOfMeal);
    }

}