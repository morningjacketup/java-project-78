package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {

    private boolean required = false;

    public StringSchema() {
    }

    public final StringSchema required() {
        addCheck("required", input -> input instanceof String && !((String) input).isEmpty());
        changeRequiredStatus();
        return this;
    }

    public final StringSchema minLength(int minLength) {
        Predicate<?> validation = input -> input instanceof String && ((String) input).length() >= minLength;
        addCheck("minLength", validation);
        return this;
    }

    public final StringSchema contains(String substring) {
        Predicate<?> validation = input -> input instanceof String && ((String) input).contains(substring);
        addCheck("contains", validation);
        return this;
    }
}
