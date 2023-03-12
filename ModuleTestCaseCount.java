package Count;

import com.opencsv.CSVReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

public class ModuleTestCaseCount {
    public static File dir = new File("/Users/nethhaj/Downloads/utr_qa_regression_suite.csv");
    public static Integer P0[] = new Integer[100000];
    public static Integer P1[] = new Integer[100000];
    public static Integer P2[] = new Integer[100000];
    public static String Appcore[] = new String[100000];
    public static WebDriver driver;
    public static String mailid, password, url;
    public static String s;
    public static int len;
    public static String alldata;

    public static void main(String[] args) throws InterruptedException, IOException {
        testRailLoginData();
        testSuiteChoose();
        del();
        Thread.sleep(3000);
        System.out.println("CSV file deleted");
        browserOpenAndExport();
        readCSVfile();

    }

    public static void testRailLoginData() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the mail id for test rail login: ");
        mailid = input.nextLine();
        System.out.println("Enter the password for login");
        password = input.nextLine();
    }

    public static void testSuiteChoose() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the Entire Testsuite url: ");
        url = input.nextLine();
    }

    public static void browserOpenAndExport() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        DesiredCapabilities cp = new DesiredCapabilities();
        cp.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(cp);
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        driver.get(url);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys(mailid);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"button_primary\"]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"content-header\"]//*[@class=\"dropdownLink link-tooltip\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportDropdown\"]/ul/li[5]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns_control\"]/div/div[1]/div/a[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-cases:id\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-cases:title\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-cases:priority_id\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-cases:section_full\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportSubmit\"]")).click();

    }


    public static void readCSVfile() throws IOException {

        String home = System.getProperty("user.home");
        File dir = new File(home + "/Downloads");
        //int fl = 0, count = 0;
        //ArrayList<String> fileContents = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.getName().contains(".csv")) {
                s = file.getName();
                System.out.println(s);
                LineNumberReader lnr = new LineNumberReader(new FileReader(file));
                lnr.skip(Long.MAX_VALUE);
                len = lnr.getLineNumber();
                System.out.println(len); //Add 1 because line index starts at 0
                lnr.close();
                String[] nextRecord;
                try {
                    FileReader fReader = new FileReader(file);
                    BufferedReader fileBuff = new BufferedReader(fReader);
                    CSVReader csvReader = new CSVReader(fileBuff);
                    while ((nextRecord = csvReader.readNext()) != null) {
                        // String temp = nextRecord[2];
                        // System.out.println(temp);
                        List<String[]> alldata = csvReader.readAll();
                        int j = 1;
                        int totalP0 = 0;
                        int totalP1 = 0;
                        int totalP2 = 0;
                        int totalcase = 0;
                        int aptotal = 0, indtotal = 0, stowtotal = 0, picktotal = 0, RTStotal = 0, PStotal = 0, Departtotal = 0, VSAtotal = 0, SCCtotal = 0, URLtotal = 0;
                        int prodP0 = 0, indP0 = 0, appP0 = 0, stP0 = 0, PP0 = 0, RTSP0 = 0, PSP0 = 0, DP0 = 0, SCCP0 = 0, VSAP0 = 0, BoomP0 = 0;
                        int prodP1 = 0, indP1 = 0, appP1 = 0, stP1 = 0, PP1 = 0, RTSP1 = 0, PSP1 = 0, DP1 = 0, SCCP1 = 0, VSAP1 = 0, BoomP1 = 0;
                        int prodP2 = 0, indP2 = 0, appP2 = 0, stP2 = 0, PP2 = 0, RTSP2 = 0, PSP2 = 0, DP2 = 0, SCCP2 = 0, VSAP2 = 0, BoomP2 = 0;
                        for (int i = 0; i <= len; i++) {
                            if (j < alldata.size()) {
                                if (alldata.get(j)[2].equals("P0") && alldata.get(j)[3].startsWith("Prod")) {
                                    prodP0++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P1") && alldata.get(j)[3].startsWith("Prod")) {
                                    prodP1++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P2") && alldata.get(j)[3].startsWith("Prod")) {
                                    prodP2++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P0") && alldata.get(j)[3].startsWith("APP")) {
                                    appP0++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P1") && alldata.get(j)[3].startsWith("APP")) {
                                    appP1++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P2") && alldata.get(j)[3].startsWith("APP")) {
                                    appP2++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P0") && alldata.get(j)[3].startsWith("Induct")) {
                                    indP0++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P1") && alldata.get(j)[3].startsWith("Induct")) {
                                    indP1++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P2") && alldata.get(j)[3].startsWith("Induct")) {
                                    indP2++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P0") && alldata.get(j)[3].startsWith("Stow")) {
                                    stP0++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P1") && alldata.get(j)[3].startsWith("Stow")) {
                                    stP1++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P2") && alldata.get(j)[3].startsWith("Stow")) {
                                    stP2++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P0") && alldata.get(j)[3].startsWith("Pick")) {
                                    PP0++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P1") && alldata.get(j)[3].startsWith("Pick")) {
                                    PP1++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P2") && alldata.get(j)[3].startsWith("Pick")) {
                                    PP2++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P0") && alldata.get(j)[3].startsWith("RTS")) {
                                    RTSP0++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P1") && alldata.get(j)[3].startsWith("RTS")) {
                                    RTSP1++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P2") && alldata.get(j)[3].startsWith("RTS")) {
                                    RTSP2++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P0") && alldata.get(j)[3].startsWith("Problem")) {
                                    PSP0++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P1") && alldata.get(j)[3].startsWith("Problem")) {
                                    PSP1++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P2") && alldata.get(j)[3].startsWith("Problem")) {
                                    PSP2++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P0") && alldata.get(j)[3].startsWith("Depart")) {
                                    DP0++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P1") && alldata.get(j)[3].startsWith("Depart")) {
                                    DP1++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P2") && alldata.get(j)[3].startsWith("Depart")) {
                                    DP2++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P0") && alldata.get(j)[3].startsWith("Vehicle")) {
                                    VSAP0++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P1") && alldata.get(j)[3].startsWith("Vehicle")) {
                                    VSAP1++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P2") && alldata.get(j)[3].startsWith("Vehicle")) {
                                    VSAP2++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P0") && alldata.get(j)[3].startsWith("Boomerang")) {
                                    BoomP0++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P1") && alldata.get(j)[3].startsWith("Boomerang")) {
                                    BoomP1++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P2") && alldata.get(j)[3].startsWith("Boomerang")) {
                                    BoomP2++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P0") && alldata.get(j)[3].startsWith("SCC New UI")) {
                                    SCCP0++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P1") && alldata.get(j)[3].startsWith("SCC New UI")) {
                                    SCCP1++;
                                    j++;
                                } else if (alldata.get(j)[2].equals("P2") && alldata.get(j)[3].startsWith("SCC New UI")) {
                                    SCCP2++;
                                    j++;
                                }
                            } else {
                                System.out.println("Entire file is read");
                            }
                            aptotal=appP0+appP1+appP2;
                            indtotal=indP0+indP1+indP2;
                            stowtotal=stP0+stP1+stP2;
                            picktotal=PP0+PP1+PP2;
                            RTStotal=RTSP0+RTSP1+RTSP2;
                            PStotal=PSP0+PSP1+PSP2;
                            Departtotal=DP0+DP1+DP2;
                            VSAtotal=VSAP0+VSAP1+VSAP2;
                            URLtotal=BoomP0+BoomP1+BoomP2;
                            SCCtotal=SCCP0+SCCP1+SCCP2;
                            totalP0 = prodP0 + indP0 + appP0 + stP0 + PP0 + RTSP0 + PSP0 + DP0 + SCCP0 + VSAP0 + BoomP0;
                            totalP1 = prodP1 + indP1 + appP1 + stP1 + PP1 + RTSP1 + PSP1 + DP1 + SCCP1 + VSAP1 + BoomP1;
                            totalP2 = prodP2 + indP2 + appP2 + stP2 + PP2 + RTSP2 + PSP2 + DP2 + SCCP2 + VSAP2 + BoomP2;
                            totalcase = totalP0 + totalP1 + totalP2;
                        }
                        System.out.println("prod P0 :" + prodP0);
                        System.out.println("prod P1 :" + prodP1);
                        System.out.println("prod P2 :" + prodP2);
                        System.out.println("app P0:" + appP0);
                        System.out.println("app P1:" + appP1);
                        System.out.println("appP2:" + appP2);
                        System.out.println("APP core total:" + aptotal);
                        System.out.println("ind P0 :" + indP0);
                        System.out.println("ind P1 :" + indP1);
                        System.out.println("ind P2 :" + indP2);
                        System.out.println("Indutc total:" + indtotal);
                        System.out.println("Stow P0 :" + stP0);
                        System.out.println("Stow P1 :" + stP1);
                        System.out.println("Stow P2 :" + stP2);
                        System.out.println("Stow total:" + stowtotal);
                        System.out.println("Pick and Stage P0 :" + PP0);
                        System.out.println("Pick and Stage P1 :" + PP1);
                        System.out.println("Pick and Stage P2 :" + PP2);
                        System.out.println("Pick and stage total:" + picktotal);
                        System.out.println("RTS P0 :" + RTSP0);
                        System.out.println("RTS P1 :" + RTSP1);
                        System.out.println("RTS P2 :" + RTSP2);
                        System.out.println("RTS total:" + RTStotal);
                        System.out.println("Depart P0 :" + DP0);
                        System.out.println("Depart P1 :" + DP1);
                        System.out.println("Depart P2 :" + DP2);
                        System.out.println("Depart total:" + Departtotal);
                        System.out.println("PS P0 :" + PSP0);
                        System.out.println("PS P1 :" + PSP1);
                        System.out.println("PS P2 :" + PSP2);
                        System.out.println("Problem solve total:" + PStotal);
                        System.out.println("VSA P0 :" + VSAP0);
                        System.out.println("VSA P1 :" + VSAP1);
                        System.out.println("VSA P2 :" + VSAP2);
                        System.out.println("VSA total:" + VSAtotal);
                        System.out.println("SCC P0 :" + SCCP0);
                        System.out.println("SCC P1 :" + SCCP1);
                        System.out.println("SCC P2 :" + SCCP2);
                        System.out.println("SCC total:" + SCCtotal);
                        System.out.println("URL P0 :" + BoomP0);
                        System.out.println("URL P1 :" + BoomP1);
                        System.out.println("URL P2 :" + BoomP2);
                        System.out.println("URL/Boomerang total:" + URLtotal);
                        System.out.println("Total P0 :" + totalP0);
                        System.out.println("Total P1 :" + totalP1);
                        System.out.println("Total P2 :" + totalP2);
                        System.out.println("Total case" + totalcase);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }


    public static void del() {
        //String home = System.getProperty("user.home");
        File directory = new File("/Users/nethhaj/Downloads");
        for (File file : directory.listFiles()) {
            if (file.getName().contains(".csv")) {
                file.delete();
            }
        }
        System.out.println("CSV Files deleting...");
    }
}