package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class ValidatorTests {
    private final Validator v = new Validator();

    @Test
    void testStringValidation() {
        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.required();

        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isFalse();

        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();
    }

    @Test
    void testNumberValidation() {
        NumberSchema schema = v.number();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.positive().isValid(null)).isTrue();

        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(50)).isTrue();
        assertThat(schema.isValid("50")).isFalse();
        assertThat(schema.isValid(-50)).isFalse();
        assertThat(schema.isValid(0)).isFalse();

        schema.range(50, 100);

        assertThat(schema.isValid(49)).isFalse();
        assertThat(schema.isValid(60)).isTrue();
        assertThat(schema.isValid(99)).isTrue();
        assertThat(schema.isValid(101)).isFalse();
    }

    @Test
    void testMapValidation() {
        MapSchema schema = v.map();

        assertThat(schema.isValid(null));

        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap())).isTrue();
        Map<String, String> heroes = new HashMap<>();
        heroes.put("Night Elf", "Illidan");
        assertThat(schema.isValid(heroes)).isTrue();

        schema.sizeof(2);

        assertThat(schema.isValid(heroes)).isFalse();
        heroes.put("Undead", "Arthas");
        assertThat(schema.isValid(heroes)).isTrue();
    }

    @Test
    void testMapShapeValidation() {
        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("level", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> hero1 = new HashMap<>();
        hero1.put("name", "Axe");
        hero1.put("level", 30);
        assertThat(schema.isValid(hero1)).isTrue();

        Map<String, Object> hero2 = new HashMap<>();
        hero2.put("name", "Lina");
        hero2.put("level", null);
        assertThat(schema.isValid(hero2)).isTrue();

        Map<String, Object> hero3 = new HashMap<>();
        hero3.put("name", "");
        hero3.put("level", null);
        assertThat(schema.isValid(hero3)).isFalse();

        Map<String, Object> hero4 = new HashMap<>();
        hero4.put("name", "Chaos Knight");
        hero4.put("level", -5);
        assertThat(schema.isValid(hero4)).isFalse();

    }
}
