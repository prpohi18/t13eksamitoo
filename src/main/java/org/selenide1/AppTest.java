import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import org.openqa.selenium.By;

public class AppTest {
    public static void main(String[] args){
		open("http://greeny.cs.tlu.ee/~seppcasp/programmeerimise_pohikursus/kodutood/t13eksamitoo/addplayer.php");
		$("#playername").setValue("Aaron Ramsey");
		$("#currentteam").setValue("Arsenal");
		$("#currentnumber").setValue("8");
		$("#submit").click();
    }
}