import java.util.Scanner;

public class HumanStateAutomaton {

    enum State {
        CALM, SADNESS, ANGER, JOY
    }

    private State currentState;

    // Матрица переходов состояний
    private final int[][] transitionMatrix = {
            { 4, 3, 3, 4 }, // CALM
            { 1, 3, 2, 1 }, // SADNESS
            { 2, 3, 3, 1 }, // ANGER
            { 4, 2, 3, 4 } // JOY
    };

    // Сообщения для каждого перехода
    private final String[][] transitionMessages = {
            { "Ура! Поставлю в вазу.", "Заявлю в полицию! Как они посмели!", "Заявлю в полицию! Как они посмели!", "И все таки чудесный день!" }, // CALM
            { "Какой приятный день)", "Заявлю в полицию! Как они посмели!", "Заявлю в полицию, куда еще хуже?", "Какой приятный день)" }, // SADNESS
            { "Не все так плохо, как казалось...", "Заявлю в полицию! Как они посмели!", "Заявлю в полицию! Как они посмели!", "Не все так плохо, как казалось..." }, // ANGER
            { "Ура! Поставлю в вазу.", "Заявлю в полицию…Такой день испортили…", "Заявлю в полицию! Как они посмели!", "Лучше быть не может!" } // JOY
    };

    public HumanStateAutomaton() {
        this.currentState = State.CALM; // Начальное состояние по умолчанию
    }

    public void transition(int event) {
        int current = currentState.ordinal();
        int next = transitionMatrix[current][event - 1]; // event - 1 для нумерации с 0

        if (next != 0) {
            currentState = State.values()[next - 1]; // next - 1 для нумерации с 0
            System.out.println(transitionMessages[current][event - 1]); // event - 1 для нумерации с 0
        } else {
            System.out.println("Событие не приводит к переходу состояния.");
        }
    }

    public State getCurrentState() {
        return currentState;
    }

    public void chooseInitialState() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите начальное состояние:");
        System.out.println("1. Спокойствие (CALM)");
        System.out.println("2. Грусть (SADNESS)");
        System.out.println("3. Гнев (ANGER)");
        System.out.println("4. Радость (JOY)");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера ввода

        switch (choice) {
            case 1:
                currentState = State.CALM;
                break;
            case 2:
                currentState = State.SADNESS;
                break;
            case 3:
                currentState = State.ANGER;
                break;
            case 4:
                currentState = State.JOY;
                break;
            default:
                System.out.println("Неверный выбор.");
                chooseInitialState(); // Повторный запрос
        }
    }

    public void chooseEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер события:");
        System.out.println("1. Подарили цветок");
        System.out.println("2. Украли сумку");
        System.out.println("3. Напали в подъезде");
        System.out.println("4. Прогулка в парке");

        int event = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера ввода
        transition(event);
    }

    public static void main(String[] args) {
        HumanStateAutomaton automaton = new HumanStateAutomaton();

        automaton.chooseInitialState(); // Выбор начального состояния
        System.out.println("Текущее состояние: " + automaton.getCurrentState());

        // Ввод события пользователем
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер события (1-4) или 'конец' для завершения: n " +
                "\n1. Подарили цветок" +
                "\n2. Украли сумку" +
                "\n3. Напали в подъезде" +
                "\n4. Прогулка в парке");

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("конец")) {
                break;
            }

            try {
                int event = Integer.parseInt(input);
                if (event >= 1 && event <= 4) {
                    automaton.transition(event);
                    System.out.println("Текущее состояние: " + automaton.getCurrentState() +
                            "\nВведите номер события (1-4) или 'конец' для завершения:" +
                            "\n1. Подарили цветок" +
                            "\n2. Украли сумку" +
                            "\n3. Напали в подъезде" +
                            "\n4. Прогулка в парке");
                } else {
                    System.out.println("Введите число от 1 до 4 или 'конец'.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите число от 1 до 4 или 'конец'.");
            }
        }
        System.out.println("Программа завершена.");
    }
}
