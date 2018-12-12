package Games.CaS;

/**
 * Created by 1 on 21.09.2017.
 * The class implements logic menu of menu.
 */
public enum Command {
    NEW_GAME("Новая игра", "New Games"),
    LEADER_BOARDS("Список лидеров", "Leaderboards"),
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

    public String toSelectTheLanguage(String language) {
        switch (language) {
            case "русский":
                return russian;
            case "english":
                return english;
            default:
                return english;
        }
    }

    public String getName(Controller controller) {
        return this.toSelectTheLanguage(controller.getLanguage()).toUpperCase();
    }
}
