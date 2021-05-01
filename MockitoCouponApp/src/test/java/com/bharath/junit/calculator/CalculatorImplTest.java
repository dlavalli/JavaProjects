package com.bharath.junit.calculator	;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CalculatorImplTest {

	private int num1;
	private int num2;
	private int expectedResult;

	public CalculatorImplTest(int num1, int num2, int result) {

		this.num1 = num1;
		this.num2 = num2;
		this.expectedResult = result;

	}

	// An example of parameterized Junit sample using a static method
	// @Parameters is what caused the multiple iterations on the provided data sampels
	@Parameters
	public static Collection<Integer[]> data() {
		// Convert a 2 dimensional array to a list of 1 dimensional array
		// The valules match the order of the parameters of the constructor
		return Arrays.asList(new Integer[][] { { -1, 2, 1 }, { 1, 2, 3}, { 6, 7, 13 } });
	}

	@Test
	public void addShouldReturnAResult() {
		Calculator c = new CalculatorImpl();
		int result = c.add(num1, num2);   		// num1, num2 comes from data()
		assertEquals(expectedResult, result);	// expectedResult comes from @Parameters annotation
	}

}
