import java.io.*;

public class CsvSortController {

    public CsvSortController(String csvFileName) throws IOException {

        String line;
        int counter = 0;
        try{
            // полчучаем количество строк в файле
            LineNumberReader reader = new LineNumberReader(new FileReader(csvFileName));
            reader.skip(Integer.MAX_VALUE);
            counter = reader.getLineNumber();
            reader.close();

            String[] arr = new String[counter];
            counter = counter - 1;

            // Считываем строки из файла и заполняем массив строк
            BufferedReader br = new BufferedReader(new FileReader(csvFileName));

            while((line = br.readLine()) != null) {
                arr[counter] = line;
                counter--;
            }
            br.close();

            // сортируем полученный массив
            sort(arr, 0, arr.length - 1);
            // генерируем отсортированный csv
            generateCSV(arr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sort(String[] arr, int startIndex, int endIndex) {
        int startMarker = startIndex;
        int endMarker = endIndex;
        int pivot = Integer.valueOf(arr[(startMarker + endMarker) / 2].split(";")[0]);
        do {
            // Двигаем начальный маркер слева направо пока элемент меньше, чем pivot
            while (Integer.valueOf(arr[startMarker].split(";")[0]) < pivot) {
                startMarker++;
            }
            // Двигаем конечный маркер, пока элемент больше, чем pivot
            while (Integer.valueOf(arr[endMarker].split(";")[0]) > pivot) {
                endMarker--;
            }
            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (startMarker <= endMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (startMarker < endMarker) {
                    String tmp = arr[startMarker];
                    arr[startMarker] = arr[endMarker];
                    arr[endMarker] = tmp;
                }
                // Сдвигаем маркеры, чтобы получить новые границы
                startMarker++;
                endMarker--;
            }
        } while (startMarker <= endMarker);

        // Выполняем рекурсивно для частей
        if (startMarker < endIndex) {
            sort(arr, startMarker, endIndex);
        }
        if (startIndex < endMarker) {
            sort(arr, startIndex, endMarker);
        }
    }

    private void generateCSV(String[] arr) throws FileNotFoundException {

        File file = new File("temp/sorted.csv");
        file.getParentFile().mkdirs();
        PrintWriter pw = new PrintWriter(file);

        for (int i = 0; i < arr.length; i++) {
            pw.write(arr[i] + "\n");
        }
        pw.close();
    }
}
