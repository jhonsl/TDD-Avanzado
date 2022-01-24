import static org.junit.jupiter.api.Assertions.*;

import org.testng.annotations.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class CalculatorTest {
    public Calculator calculator = new Calculator();

    //Un string vacio, debe retornar 0
    @Test
    public void emptyStringTest() throws Exception {
        assertThat(calculator.add(""), is(0));
    }

    //Un solo numero retornara dicho valor 
    @Test
    public void singleNumberReturnItsValueTest() throws Exception {
        assertThat(calculator.add("2"), is(2));
        assertThat(calculator.add("1"), is(1));
    }

    //Dos numeros delimitados por coma, retornara la suma de ellos
    @Test
    public void twoNumbersCommaDelimitedReturnsTheSumTest() throws Exception {
        assertThat(calculator.add("0,2"), is(2));
        assertThat(calculator.add("1,1"), is(2));
        assertThat(calculator.add("1,2"), is(3));
    }

    //Dos numeros delimitados por un salto de linea, retornara la suma de ellos
    @Test
    public void sameThatBeforeButNewLineDelimitedTest() throws Exception {
        assertThat(calculator.add("0\n2"), is(2));
        assertThat(calculator.add("1\n1"), is(2));
        assertThat(calculator.add("1\n2"), is(3));
    }

    //Tres numeros delimitados por lo que sea, retornara la suma de ellos
    @Test
    public void sameThatBeforeButAllDelimitersWorksTest() throws Exception {
        assertThat(calculator.add("1\n2,3"), is(6));
        assertThat(calculator.add("1,2\n4"), is(7));
    }

    //Los numeros negativos retornaran una excepcion
    @Test(expectedExceptions =  Exception.class)
    public void negativeNumbersThrowsAnExceptionTest() throws Exception {
        calculator.add("-1,-1");
    }

    //Los numeros mayores a 1000 seran ignorados
    @Test
    public void greaterThan1000AreIgnoredTest() throws Exception {
        assertThat(calculator.add("2,1000"), is(1002));
        assertThat(calculator.add("2,1001"), is(2));
    }

    //Un caracter en la primera posicion puede ser usado como delimitador
    @Test
    public void singleCharDelimiterTest() throws Exception {
        assertThat(calculator.add("#2#1000"), is(1002));
    }

    //Varios caracteres de seguidos en la primera posicion pueden ser usados como delimitadores 
    @Test
    public void pluralCharactersDelimiterOneOSomeTest() throws Exception {
        assertThat(calculator.add("[###]1[###]3"), is(4));
        assertThat(calculator.add("[###]5[###]7[###]"), is(12));
    }
}