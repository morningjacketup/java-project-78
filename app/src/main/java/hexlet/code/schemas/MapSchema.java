package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {
    public MapSchema() {

    }

    public final MapSchema required() {
        addCheck("required", input -> input instanceof Map);
        setRequiredStatus();
        return this;
    }

    public final MapSchema sizeof(int size) {
        Predicate<?> validation = input -> ((Map<?, ?>) input).size() == size;
        addCheck("size", validation);
        return this;

    }

    public final MapSchema shape(Map<String, BaseSchema> schemas) {
        addCheck("shape",
                input -> schemas.entrySet().stream().allMatch(e ->
                    e.getValue().isValid(((Map<?, ?>) input).get(e.getKey())))
        );
        return this;
    }

    @Override
    public final boolean isValidInput(Object input) {
        return input instanceof Map;
    }
}
