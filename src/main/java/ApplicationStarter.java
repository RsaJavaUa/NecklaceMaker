import services.ConsoleUserApi;

public class ApplicationStarter {
    public static void main(String[] args) {
        ConsoleUserApi consoleUserApi = new ConsoleUserApi();
        consoleUserApi.makeNecklace();
    }
}
