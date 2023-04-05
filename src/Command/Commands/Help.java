package Command.Commands;

import Command.*;
import DataStructure.CollectionManager;
import DataStructure.Response;

/**
 * Class for the help command. Print list of commands
 */
public class Help extends Command_abstract implements CommandResponse {
    private String output;

    public Help(){
    }
    @Override
    public void execute() {
        output = "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n"+
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "insert_at index {element} : добавить новый элемент в заданную позицию\n" +
                "remove_greater id : удалить из коллекции все элементы, у которого id больше заданного\n" +
                "remove_lower id : удалить из коллекции все элементы, id которых меньше, чем заданный\n" +
                "sum_of_impact_speed` : вывести сумму значений поля impactSpeed для всех элементов коллекции\n" +
                "count_greater_than_car car : вывести количество элементов, значение поля car которых больше заданного\n" +
                "filter_starts_with_soundtrack_name soundtrackName : вывести элементы, значение поля soundtrackName которых начинается с заданной подстроки\n";
    }
    @Override
    public Response getResponse() {
        return new Response("help", output);
    }
}
