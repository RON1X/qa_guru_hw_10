package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class LoginTests extends TestBase {
    @ValueSource(strings = {
            "#userName",
            "#password"
    })
    @DisplayName("Тест с дата провайдером @ValueSource")
    @ParameterizedTest()
    @Tag("login")
    void enableInputField(String id) {
        open("/login");
        $(id).isEnabled();
    }

    @CsvSource(value = {
            "4dm1n, 12345678",
            "email@gmail.com, fdiYVDye323ujH"
    })
    @DisplayName("Тест с дата провайдером @CsvSource")
    @ParameterizedTest()
    @Tag("login")
    void invalidUsernameOrPasswordTest(String login, String password) {
        open("/login");
        $("#userName").setValue(login);
        $("#password").setValue(password);
        $("#login").click();
        $("#output").shouldHave(text("Invalid username or password!"));
    }

    static Stream<Arguments> navigationPanelTest() {
        return Stream.of(
                Arguments.of("Elements", List.of("Text Box", "Check Box", "Radio Button", "Web Tables", "Buttons", "Links", "Broken Links - Images", "Upload and Download", "Dynamic Properties")),
                Arguments.of("Forms", List.of("Practice Form"))
        );
    }

    @MethodSource
    @DisplayName("Тест с дата провайдером @MethodSource")
    @ParameterizedTest
    @Tag("navigation_panel")
    void navigationPanelTest(String groupHeader, List<String> labels) {
        open("/login");
        $(".accordion").$(byText(groupHeader)).click();
        $$(".menu-list li").filter(visible).shouldHave(CollectionCondition.texts(labels));
    }
}
