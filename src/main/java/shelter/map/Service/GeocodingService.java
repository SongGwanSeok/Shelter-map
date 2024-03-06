package shelter.map.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shelter.map.Domain.DTO.GeocodingResultDto;
import shelter.map.Domain.DTO.GeocodingResponseDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;

@Service
@Transactional(readOnly = true)
@Slf4j
public class GeocodingService {


    private static final String NAVER_GEOCODING_API_URL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode";
    private final String CLIENT_ID;
    private final String CLIENT_SECRET;

    public GeocodingService(@Value("${map.key})") String key, @Value("${map.X-NCP-APIGW-API-KEY}") String Client_secret) {
        this.CLIENT_ID = key;
        this.CLIENT_SECRET = Client_secret;
    }

    public double[] getGeocoding(String address) throws IOException {
        log.info("address : " +address);
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        String apiUrl = NAVER_GEOCODING_API_URL + "?query=" + encodedAddress;
        log.info("encodingAddress : " + encodedAddress);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(apiUrl);
        httpGet.addHeader("X-NCP-APIGW-API-KEY-ID", CLIENT_ID);
        httpGet.addHeader("X-NCP-APIGW-API-KEY", CLIENT_SECRET);

        HttpResponse response = httpClient.execute(httpGet);
        log.info("response : " + response);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()))) {
                String line;
                StringBuilder result = new StringBuilder();

                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                log.info("result : " + result.toString());

                ObjectMapper objectMapper = new ObjectMapper();
                GeocodingResponseDto geocodingResponse = objectMapper.readValue(result.toString(), GeocodingResponseDto.class);
                log.info("geocodingResponse status : " + geocodingResponse.getStatus());
                log.info("geocodingResponse result length : " + geocodingResponse.getResults().length);
                GeocodingResultDto[] results = geocodingResponse.getResults();
                log.info("results : "+ results);

                if (results != null && results.length > 0) {
                    GeocodingResultDto firstResult = results[0];
                    double latitude = Double.parseDouble(firstResult.getX());
                    double longitude = Double.parseDouble(firstResult.getY());
                    log.info("latitude : "+ latitude);
                    log.info("longitude : "+ longitude);
                    return new double[] { latitude, longitude };
                }

            }
        }
        log.info("return null");
        return null;
    }
}

