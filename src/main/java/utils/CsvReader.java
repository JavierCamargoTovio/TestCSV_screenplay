package utils;

import org.example.test.models.CreateUserDTO;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.util.List;

public class CsvReader {
    public static List<CreateUserDTO> leerUsuarios(String rutaCsv) {
        try {
            return new CsvToBeanBuilder<CreateUserDTO>(new FileReader(rutaCsv))
                    .withType(CreateUserDTO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo CSV", e);
        }
    }
}
