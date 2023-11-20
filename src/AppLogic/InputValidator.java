package AppLogic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputValidator {
    static boolean isPositiveNumber(String input){
        return input.matches("(0|[1-9]\\d*)");
    }

    static boolean isNumberFrom1To10(int input){
        return input > 0 && input <= 10;
    }

    public static boolean isValidDateFormat(String inputDate, DateTimeFormatter formatter) {
        try {
            LocalDate parsedDate = LocalDate.parse(inputDate, formatter);
            // If parsing succeeds, the date is in a valid format
            return true;
        } catch (DateTimeParseException e) {
            // Parsing failed, the date is not in the expected format
            return false;
        }
    }
}
