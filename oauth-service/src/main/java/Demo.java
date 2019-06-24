import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.Arrays;

public class Demo {

    public static File report = new File("D:\\max\\dms\\report.txt");

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\max\\dms");
        report.delete();
        report.createNewFile();
        try(Writer reportWriter = new BufferedWriter(new FileWriter(report)))
        {

            System.out.println(file);

            recursiveFile(file,reportWriter);

            report.createNewFile();

        }
    }

    public static void recursiveFile(File root, Writer reportWriter) throws IOException {
        if (root != null) {
            File[] files = root.listFiles();
            if (files != null) {
                for (File file : files) {
                    recursiveFile(file, reportWriter);
                }
            }
            if (root.isFile() && FilenameUtils.isExtension(root.getName(), Arrays.asList("java", "yml", "yaml", "gitignore", "sh"))) {


                try (Reader fileReader = new BufferedReader(new FileReader(root))) {

                    reportWriter.write(root.getAbsolutePath().replace("D:\\max", "") + " :\n");
                    int c;
                    while ((c = fileReader.read()) != -1) {
                        reportWriter.write(c);
                    }
                    reportWriter.write("\n\n");
                    reportWriter.flush();
                }
            }
        }
    }

}
