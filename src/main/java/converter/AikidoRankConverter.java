// Java
package converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AikidoRankConverter implements AttributeConverter<String, Integer> {

    @Override
    public Integer convertToDatabaseColumn(String attribute) {
        switch (attribute) {
            case "White Belt":
                return 1;
            case "Yellow Belt":
                return 2;
            case "Green Belt":
                return 3;
            case "Brown Belt":
                return 4;
            case "Black Belt":
                return 5;
            default:
                throw new IllegalArgumentException("Unknown rank: " + attribute);
        }
    }

    @Override
    public String convertToEntityAttribute(Integer dbData) {
        switch (dbData) {
            case 1:
                return "White Belt";
            case 2:
                return "Yellow Belt";
            case 3:
                return "Green Belt";
            case 4:
                return "Brown Belt";
            case 5:
                return "Black Belt";
            default:
                throw new IllegalArgumentException("Unknown rank ID: " + dbData);
        }
    }
}