import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import org.openqa.selenium.By;

public class AppTest {
    public static void main(String[] args){
		open("http://greeny.cs.tlu.ee/~niinsimo/veebiprogrammeerimine/proovieksam/index.php");
		$("#carmark").setValue("49");
		$("#sparepart").setValue("7");
		$("#wish").setValue("buy");
		$("#comment").setValue("test");
		$("#submit").click();
		
		$("#result").shouldHave(text("Sinule sobivad kuulutused puuduvad")); 

		$("#carmark").setValue("49");
		$("#sparepart").setValue("7");
		$("#wish").setValue("sell");
		$("#comment").setValue("test");
		$("#submit").click();
		
		$("#result").shouldHave(text("Sinule sobivad kuulutused: ")); 
      
    }
}