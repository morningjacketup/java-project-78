package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final Map<String, Predicate> checks = new HashMap<>();
    private boolean required = false;

    public final boolean isRequired() {
        return required;
    }

    public final void setRequired(boolean requiredStatus) {
        this.required = requiredStatus;
    }

    public final void setRequiredStatusTrue() {
        required = true;
    }
    protected final void addCheck(String name, Predicate validate) {
        checks.put(name, validate);
    }

    public final boolean isValid(Object input) {
        if (input == null) {
            return !required;
        }
        if (!isValidInput(input)) {
            return false;
        }
        return checks.values().stream().allMatch(check -> check.test(input));
    }

    public abstract boolean isValidInput(Object input);
}
