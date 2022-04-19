package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) {
    if (args.length != 3){
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    BasicConfigurator.configure();

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (IOException e){
      javaGrepImp.logger.error("Error: IOException", e);
    } catch (IllegalArgumentException e){
      javaGrepImp.logger.error("Error: Illegal Argument", e);
    } catch (Exception ex) {
      javaGrepImp.logger.error("Error: Unable to process", ex);
    }
  }

  public void process() throws IOException, IllegalArgumentException {
    List<String> matchedLines = new ArrayList<>();
    List<File> files = listFiles(this.rootPath);
    for (File file : files){
      List<String> lines = readLines(file);
      for (String line : lines){
        if (containsPattern(line)){
          matchedLines.add(line);
        }
      }
    }
    writeToFile(matchedLines);
  }

  @Override
  public List<File> listFiles(String rootDir) throws IOException {
    List<File> files = new ArrayList<>();
    File cur = new File(rootDir);
    Queue<File> q= new LinkedList<>();
    q.add(cur);

    while (!q.isEmpty()){
      cur = q.poll();
      if (cur.isDirectory()){
        this.addFilesToQ(q, cur);
      } else {
        files.add(cur);
      }
    }
    return files;
  }

  private void addFilesToQ(Queue<File> q, File cur) {
    File[] subDirectories = cur.listFiles();
    for (File subDir : subDirectories){
      q.add(subDir);
    }
  }

  @Override
  public List<String> readLines(File inputFile) throws IllegalArgumentException, IOException {
    List<String> lines = new ArrayList<>();
    try(BufferedReader br = new BufferedReader(new FileReader(inputFile))){
      String line;
      while ((line = br.readLine()) != null){
        lines.add(line);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException(e);
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
    return lines;
  }

  @Override
  public boolean containsPattern(String line) {
    Pattern pattern = Pattern.compile(this.regex);
    Matcher matcher = pattern.matcher(line);

    return matcher.find();
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    OutputStream stream = new FileOutputStream(this.outFile);
    OutputStreamWriter outWriter = new OutputStreamWriter(stream);

    for (String line : lines){
      outWriter.write(line);
    }
    outWriter.close();
  }

  @Override
  public String getRootPath() {
    return this.rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getRegex() {
    return this.regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return this.outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}
