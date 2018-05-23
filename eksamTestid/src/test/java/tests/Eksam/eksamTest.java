package tests.Eksam;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class eksamTest {
    
     @Test
     public void ShouldSucceed(){
         open("http://localhost/laulusõnad.html");
         $("#addButton").click();
         $("#nameModal").setValue("Test");
         $("#artistModal").setValue("Test");
         $("#lyricsModal").setValue("Test");
         $("#addLyrics").click();
         $("#songSelect").click();
         $("#songSelect").selectOption("Test");
         $("#showLyrics").click();
         $("#lyrics").shouldHave(text("Test"));
     }

}
