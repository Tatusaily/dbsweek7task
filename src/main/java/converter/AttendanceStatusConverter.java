// Java
package converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import model.AttendanceStatus;

@Converter(autoApply = true)
public class AttendanceStatusConverter implements AttributeConverter<AttendanceStatus, String> {

    @Override
    public String convertToDatabaseColumn(AttendanceStatus attribute) {
        return switch (attribute) {
            case PRESENT -> "P";
            case ABSENT -> "A";
            case EXCUSED -> "E";
            default -> throw new IllegalArgumentException("Unknown status: " + attribute);
        };
    }

    @Override
    public AttendanceStatus convertToEntityAttribute(String dbData) {
        return switch (dbData) {
            case "P" -> AttendanceStatus.PRESENT;
            case "A" -> AttendanceStatus.ABSENT;
            case "E" -> AttendanceStatus.EXCUSED;
            default -> throw new IllegalArgumentException("Unknown status code: " + dbData);
        };
    }
}