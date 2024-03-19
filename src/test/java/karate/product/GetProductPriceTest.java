package karate.product;

import com.intuit.karate.junit5.Karate;
import org.rsvieira.inditextest.InditexTestApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = { InditexTestApplication.class })
@ActiveProfiles("test")
@Sql(scripts = "classpath:data/test-data.sql")
public class GetProductPriceTest {

  @Karate.Test
  Karate testGetProductPrice() {
    return Karate.run("getProductPrice").relativeTo(getClass());
  }
}
