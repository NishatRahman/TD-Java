package enums;

import lombok.Getter;

@Getter
public enum PageNumber {
    FIRST_CARD(1),
    SECOND_CARD(2),
    THIRD_CARD(3);

    private final int number;

    PageNumber(int number) {
        this.number = number;
    }
}
