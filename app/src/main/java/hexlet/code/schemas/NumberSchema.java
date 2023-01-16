package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    public NumberSchema() {

    }

    public final NumberSchema required() {
        addCheck("required", input -> input instanceof Integer);
        changeRequiredStatus();
        return this;
    }

    public final NumberSchema positive() {
        Predicate<?> validation = input -> {
            if (isRequired() || input instanceof Integer) {
                return input instanceof Integer && (Integer) input > 0;
            }
            return true;
        };
        addCheck("positive", validation);
        return this;
    }

    public final NumberSchema range(int start, int end) {
        Predicate<?> validation = input -> input instanceof Integer
                && (Integer) input <= end
                && (Integer) input >= start;
        addCheck("range", validation);
        return this;
    }
}
