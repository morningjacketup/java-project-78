package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    public NumberSchema() {

    }

    public final NumberSchema required() {
        addCheck("required", input -> input instanceof Integer);
        setRequiredStatus();
        return this;
    }

    public final NumberSchema positive() {
        Predicate<?> validation = input -> (Integer) input > 0;
        addCheck("positive", validation);
        return this;
    }

    public final NumberSchema range(int start, int end) {
        Predicate<?> validation = input -> (Integer) input <= end
                && (Integer) input >= start;
        addCheck("range", validation);
        return this;
    }

    @Override
    public final boolean isValidInput(Object input) {
        return input instanceof Integer;
    }
}
