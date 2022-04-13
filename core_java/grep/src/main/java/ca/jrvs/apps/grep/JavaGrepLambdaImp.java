package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepLambdaImp extends JavaGrepImp {

  public static void main(String[] args) {
    if (args.length != 3){
      throw new IllegalArgumentException("USAGE: JavaGrepLambda regex rootPath outFile");
    }

    JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);

    try {
      javaGrepLambdaImp.process();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public List<String> readLines(File inputFile) throws IllegalArgumentException, IOException {
    return Files.newBufferedReader(inputFile.toPath(), Charset.forName("ISO-8859-1")).lines().collect(
        Collectors.toList());
  }

  @Override
  public List<File> listFiles(String rootDir) throws IOException {
    return Files.walk(Paths.get(rootDir))
        .filter(p -> !p.toFile().isDirectory())
        .map(p -> p.toFile()).collect(Collectors.toList());
  }
}
