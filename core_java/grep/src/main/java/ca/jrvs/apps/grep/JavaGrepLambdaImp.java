package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.BasicConfigurator;

public class JavaGrepLambdaImp extends JavaGrepImp {

  public static void main(String[] args) {
    if (args.length != 3){
      throw new IllegalArgumentException("USAGE: JavaGrepLambda regex rootPath outFile");
    }

    BasicConfigurator.configure();

    JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);

    try {
      javaGrepLambdaImp.process();
    } catch (IllegalArgumentException e){
      javaGrepLambdaImp.logger.error("Error: Illegal Argument", e);
    } catch (IOException e){
      javaGrepLambdaImp.logger.error("Error: IOException", e);
    } catch (Exception ex) {
      javaGrepLambdaImp.logger.error("Error: Unable to process", ex);
    }
  }

  @Override
  public List<String> readLines(File inputFile) throws IllegalArgumentException {
    try {
      return Files.lines(inputFile.toPath()).collect(Collectors.toList());
    } catch (IOException e){
      throw new IllegalArgumentException(e);
    }
  }

  @Override
  public List<File> listFiles(String rootDir) {
    try {
      return Files.walk(Paths.get(rootDir))
          .filter(p -> !p.toFile().isDirectory())
          .map(p -> p.toFile()).collect(Collectors.toList());
    } catch (IOException e){
      throw new IllegalArgumentException(e);
    }
  }
}
