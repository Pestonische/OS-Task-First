package by.fpmi.os.main;

import org.apache.commons.lang3.RandomStringUtils;
import java.util.*;

public class RandomStringsGenerator {

    public static final int STRING_LEN = 1;

    public List<String>generate(int listCount){
        List<String>randomStrings = new ArrayList<>();
        for(int i = 0; i < listCount; i++){
            String randomString = RandomStringUtils.randomAlphabetic(STRING_LEN);
            randomStrings.add(randomString);
        }
        return randomStrings;
    }

}
