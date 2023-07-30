import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.ExactText;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppOrderTests {

    @Test
    void SuccessTest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Нина");
        form.$("[data-test-id=phone] input").setValue("+71234567890");
        form.$("[data-test-id=agreement]").click();
        form.$(".form-field>button").click();
        $("[data-test-id=order-success]").shouldHave(new ExactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void CheckValidationNameTest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("nina");
        form.$(".form-field>button").click();
        assertTrue($(".input_invalid[data-test-id=name]").exists());
    }

    @Test
    void CheckValidationTelephoneTest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Нина");
        form.$("[data-test-id=phone] input").setValue("+712345678901");
        form.$(".form-field>button").click();
        assertTrue($(".input_invalid[data-test-id=phone]").exists());
    }

    @Test
    void CheckValidationAgreementTest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Нина");
        form.$("[data-test-id=phone] input").setValue("+71234567890");
        form.$(".form-field>button").click();
        assertTrue($(".input_invalid[data-test-id=agreement]").exists());
    }
}
