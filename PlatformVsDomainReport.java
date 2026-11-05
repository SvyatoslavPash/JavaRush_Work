import java.util.List;

enum StepType {
    PLATFORM,
    DOMAIN
}

// Один шаг отчёта: текстовое название и тип слоя, к которому он относится.
record StartupStep(String name, StepType type) {
}

public class PlatformVsDomainReport {

    // Неизменяемый сценарий старта: платформа и домен в одном списке, но с разными типами.
    // List.of(...) делает список фиксированным: это важно, чтобы набор шагов не менялся во время выполнения.
    private static final List<StartupStep> STEPS = List.of(
            new StartupStep("согласование зависимостей", StepType.PLATFORM),
            new StartupStep("загрузка настроек", StepType.PLATFORM),
            new StartupStep("подготовка логов", StepType.PLATFORM),
            new StartupStep("открытие порта", StepType.PLATFORM),
            new StartupStep("регистрация HTTP-обработчиков", StepType.PLATFORM),
            new StartupStep("загрузка каталога курсов", StepType.DOMAIN),
            new StartupStep("показ списка курсов", StepType.DOMAIN)
    );

    public static void main(String[] args) {
        // Сначала выводим платформенные шаги, затем доменную часть — так лучше видна граница между слоями.
        printSection("PLATFORM LAYER", StepType.PLATFORM);
        System.out.println();
        printSection("DOMAIN LOGIC", StepType.DOMAIN);
    }

    // Фильтруем шаги по типу, чтобы граница слоёв была видна прямо в выводе.
    // Здесь не создаём отдельные списки — просто читаем один общий сценарий и печатаем нужные элементы.
    private static void printSection(String title, StepType type) {
        System.out.println(title);
        for (StartupStep step : STEPS) {
            if (step.type() == type) {
                System.out.println("- " + step.name());
            }
        }
    }
}
