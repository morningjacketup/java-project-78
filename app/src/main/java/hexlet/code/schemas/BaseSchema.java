package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {
    private final Map<String, Predicate> checks = new HashMap<>();
    private boolean required = false;

    public void changeRequiredStatus() {
        required = true;
    }

    public final boolean isRequired() {
        return required;
    }

    protected final void addCheck(String name, Predicate validate) {
        checks.put(name, validate);
    }

    public final boolean isValid(Object input) {
        return checks.values().stream().allMatch(check -> check.test(input));
    }


}
