package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {

    private boolean required = false;

    public StringSchema() {
    }

    public BaseSchema required() {
        addCheck("required", input -> input instanceof String && !((String) input).isEmpty());
        changeRequiredStatus();
        return this;
    }

    public StringSchema minLength(int minLength) {
        Predicate<?> validation = input -> input instanceof String && ((String) input).length() >= minLength;
        addCheck("contains", validation);
        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<?> validation = input -> input instanceof String && ((String) input).contains(substring);
        addCheck("contains", validation);
        return this;
    }
}
