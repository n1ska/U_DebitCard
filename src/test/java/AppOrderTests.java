import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.ExactText;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppOrderTests {

    @Test
    void successTask1Test() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Нина");
        form.$("[data-test-id=phone] input").setValue("+71234567890");
        form.$("[data-test-id=agreement]").click();
        form.$(".form-field>button").click();
        $("[data-test-id=order-success]").shouldHave(new ExactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void checkMandatoryNameTask2Test() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=phone] input").setValue("+712345678901");
        form.$("[data-test-id=agreement]").click();
        form.$(".form-field>button").click();
        $(".input_invalid[data-test-id=name]").shouldBe(Condition.visible);
        $(".input_invalid[data-test-id=name] .input__sub").shouldHave(new ExactText("Поле обязательно для заполнения"));
    }

    @Test
    void checkMandatoryPhoneTask2Test() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Нина");
        form.$("[data-test-id=agreement]").click();
        form.$(".form-field>button").click();
        $(".input_invalid[data-test-id=phone]").shouldBe(Condition.visible);
        $(".input_invalid[data-test-id=phone] .input__sub").shouldHave(new ExactText("Поле обязательно для заполнения"));
    }

    @Test
    void checkValidationNameTask2Test() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("nina");
        form.$("[data-test-id=phone] input").setValue("+712345678901");
        form.$("[data-test-id=agreement]").click();
        form.$(".form-field>button").click();
        $(".input_invalid[data-test-id=name]").shouldBe(Condition.visible);
        $(".input_invalid[data-test-id=name] .input__sub").shouldHave(new ExactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void checkValidationTelephoneTask2Test() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Нина");
        form.$("[data-test-id=phone] input").setValue("abcdefg");
        form.$("[data-test-id=agreement]").click();
        form.$(".form-field>button").click();
        $(".input_invalid[data-test-id=phone]").shouldBe(Condition.visible);
        $(".input_invalid[data-test-id=phone] .input__sub").shouldHave(new ExactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void checkValidationTelephoneWithoutFirstPlusTask2Test() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Нина");
        form.$("[data-test-id=phone] input").setValue("712345678901");
        form.$("[data-test-id=agreement]").click();
        form.$(".form-field>button").click();
        $(".input_invalid[data-test-id=phone]").shouldBe(Condition.visible);
        $(".input_invalid[data-test-id=phone] .input__sub").shouldHave(new ExactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void checkValidationTelephoneMore11SymbolsTask2Test() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Нина");
        form.$("[data-test-id=phone] input").setValue("+123456789012");
        form.$("[data-test-id=agreement]").click();
        form.$(".form-field>button").click();
        $(".input_invalid[data-test-id=phone]").shouldBe(Condition.visible);
        $(".input_invalid[data-test-id=phone] .input__sub").shouldHave(new ExactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void checkValidationAgreemenTask2tTest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Нина");
        form.$("[data-test-id=phone] input").setValue("+71234567890");
        form.$(".form-field>button").click();
        $(".input_invalid[data-test-id=agreement]").shouldBe(Condition.visible);
    }
}
