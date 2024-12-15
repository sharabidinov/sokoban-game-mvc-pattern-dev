import java.util.HashMap;
import java.util.Map;

public class ThemeDatabase {
    private Map<String , PlayerTheme>themes;
    public ThemeDatabase(){
        themes = new HashMap<String, PlayerTheme>();
        createTheme();
    }
    public PlayerTheme getPlayerTheme(String themeType) {
        return themes.get(themeType);
    }
    private void createTheme() {
        themes.put("Girl", new GirlTheme());
        themes.put("Boy", new BoyTheme());
        themes.put("Erkin" , new ErkinTheme());
    }
}
