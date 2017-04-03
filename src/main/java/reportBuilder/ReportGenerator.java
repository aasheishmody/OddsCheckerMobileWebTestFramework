package reportBuilder;

import net.masterthought.cucumber.ReportBuilder;
import org.picocontainer.classname.ClassName;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ReportGenerator {
    private static Logger logger = Logger.getLogger(ClassName.class.getName());

    public static void main(String[] args) throws Throwable {
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.mergeReports();
    }

    private void mergeReports() throws Exception {
        logger.info("Merging reports ...");
        File reportOutputDirectory = new File((System.getProperty("user.dir") + "/target/cucumber-html-report"));
        List<String> list = Arrays.asList(reportOutputDirectory.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".json");
            }
        }));
        List<String> jsonReportFiles = new ArrayList<>();
        for (int i = 1; i <= list.size(); i++) {
            jsonReportFiles.add((System.getProperty("user.dir") + "/target/cucumber-html-report/" + i + ".json"));
        }
        ReportBuilder reportBuilder = new ReportBuilder(jsonReportFiles, reportOutputDirectory, "", "", "Test Report", false, false, true, true, false, "", false);
        logger.info("Generating report ...");
        try {
            reportBuilder.generateReports();
            logger.info("Report has been generated");
        } catch (Throwable exception) {
            exception.printStackTrace();
            logger.severe(exception.getMessage());
        }
    }
}