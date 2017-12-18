package games.CaS;

/**
 * Created by 1 on 21.09.2017.
 * The class implements logic menu of menu.
 */
public enum Command {
    NEW_GAME("Новая игра", "New games"),
    LEADERBOARDS("Список лидеров", "Leaderboards"),
    PROFILES("Профиль", "Profiles"),
    OPTIONS("Настройки", "Options"),
    EXIT("Выход", "Exit");

    private String russian;
    private String english;

    /**
     * The is constructor.
     * @param russian
     * @param english
     */
    Command(String russian, String english) {
        this.russian = russian;
        this.english = english;
    }

    public String getRussian() {
        return russian;
    }

    public String getEnglish() {
        return english;
    }


    public String toSelectTheLanguage(String language) {
        switch (language) {
            case "русский":
                return getRussian();
            case "english":
                return getEnglish();
            default:
                return getEnglish();
        }
    }
}
