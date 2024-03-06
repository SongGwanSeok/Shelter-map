package shelter.map.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class AddressSevice {

    public static String getGuFromAddress(String address) {
        // 정규식 패턴 정의: 한글로 시작하며 '구' 또는 '군'로 끝남
        Pattern pattern = Pattern.compile("[가-힣]+(구|군)");
        Matcher matcher = pattern.matcher(address);

        // '구' 정보를 찾을 경우 반환, 그렇지 않으면 null 반환
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }
}
