package stringCalculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.Scanner;

@DisplayName("문자열 계산기 테스트")
public class StringCalculatorTest {

    public static final String INPUT_EXPECTED_DIVIDER = "->";
    private static final String STRING_CALCULATOR_TESTCASE_PATH = "src/test/resources/stringCalculatorTestcase/";

    private void calculateTest(String testcasePath) throws Exception {
        String input;
        String expected;
        FileInputStream fileInputStream = new FileInputStream(testcasePath);
        Scanner scanner = new Scanner(fileInputStream);

        while (scanner.hasNext()) {
            String[] raw = scanner.nextLine().split(INPUT_EXPECTED_DIVIDER);
            input = raw[0];
            expected = raw[1];

            Assertions.assertThat(Calculator.calculate(input)).isEqualTo(Double.parseDouble(expected));
        }
        fileInputStream.close();
    }

    @Test
    @DisplayName("콤마 계산 테스트")
    public void commmaCalculateTest() throws Exception {
        calculateTest(STRING_CALCULATOR_TESTCASE_PATH + "commaCalculateTestcase");
    }

    @Test
    @DisplayName("콤마, 콜론 혼용 계산 테스트")
    public void commmaColonCalculateTest() throws Exception {
        calculateTest(STRING_CALCULATOR_TESTCASE_PATH + "commaColonCalculateTestcase");
    }

    @Test
    @DisplayName("사용자 정의 delimiter 혼용 계산 테스트")
    public void customDelimiterCalculateTest() throws Exception {
        calculateTest(STRING_CALCULATOR_TESTCASE_PATH + "customDelimiterCalculateTestcase");
    }

    @Test
    @DisplayName("런타임 익셉션 테스트")
    public void checkValid() throws Exception {
        Assertions.assertThatThrownBy( () -> {
                    calculateTest(STRING_CALCULATOR_TESTCASE_PATH + "invalidInputTestcase");
                }
        ).isInstanceOf(RuntimeException.class);
    }
}
