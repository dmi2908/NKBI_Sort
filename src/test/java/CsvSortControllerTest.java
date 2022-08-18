import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class CsvSortControllerTest {
    @Test
    public void CsvSortControllerMainTest() throws IOException {
        PrintWriter writer = new PrintWriter(new File("source.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("453");
        sb.append(';');
        sb.append("fgdgdgd");
        sb.append(';');
        sb.append("28.06.2022");
        sb.append(';');
        sb.append("23423aa4");
        sb.append('\n');

        sb.append("375");
        sb.append(';');
        sb.append("fgdgdgd");
        sb.append(';');
        sb.append("25.06.2022");
        sb.append(';');
        sb.append("23555aa4");
        sb.append('\n');

        writer.write(sb.toString());
        writer.close();

        Main.main(new String[]{"source.csv"});
        BufferedReader br = new BufferedReader(new FileReader("temp/sorted.csv"));
        Assert.assertEquals(br.readLine().split(";")[0], "375");
        Assert.assertEquals(br.readLine().split(";")[0], "453");
        br.close();
        File f = new File("temp/sorted.csv");
        f.delete();
        File f1 = new File("temp");
        f1.delete();
    }
}
