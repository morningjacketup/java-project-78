package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {

    public StringSchema() {
    }

    public final StringSchema required() {
        addCheck("required", input -> input instanceof String && !((String) input).isEmpty());
        setRequiredStatus();
        return this;
    }

    public final StringSchema minLength(int minLength) {
        Predicate<?> validation = input -> ((String) input).length() >= minLength;
        addCheck("minLength", validation);
        return this;
    }

    public final StringSchema contains(String substring) {
        Predicate<?> validation = input -> ((String) input).contains(substring);
        addCheck("contains", validation);
        return this;
    }

    @Override
    public final boolean isValidInput(Object input) {
        return input instanceof String;
    }
}
