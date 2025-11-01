package org.fintech.paytech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SampleUnitTestClass {

    Calculator calculatorTest = new Calculator();

    @Test
    public void test_add(){

        // given
        int firstNumber = 5;
        int secondNumber = 10;
        int expectedResult = 15;
        int notExpectedResult = 16;

        // when
        int actualResult = calculatorTest.add(firstNumber, secondNumber);

        // then
        Assertions.assertEquals(expectedResult, actualResult);
        Assertions.assertNotEquals(notExpectedResult, actualResult);
        Assertions.assertEquals(notExpectedResult, actualResult, "The test failed because the expected result is not equal to the actual result");
    }

    class Calculator{
        int add(int a, int b){
            return a + b;
        }
    }
}
