package Count;

import com.opencsv.CSVReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ModuleTestCaseCount {
    public static WebDriver driver;
    public static String mailid, password, url, mailid1;
    public static int totalP0 = 0, totalp0auto = 0, totalP1 = 0, totalp1auto = 0, totalP2 = 0, totalp2auto = 0, totalcase = 0, totalautomatedcase = 0;
    public static int prodautoP0 = 0, prodautoP1 = 0, prodautoP2 = 0, appautop0 = 0, appautop1 = 0, appautop2 = 0, indautop0 = 0, indautop1 = 0, indautop2 = 0, stautop0 = 0, stautop1 = 0, stautop2 = 0, pautop0 = 0, pautop1 = 0, pautop2 = 0;
    public static int rtsautop0 = 0, rtsautop1 = 0, rtsautop2 = 0, psautop0 = 0, psautop1 = 0, psautop2 = 0, boomautop0 = 0, boomautop1 = 0, boomautop2 = 0;
    public static int dautop0 = 0, dautop1 = 0, dautop2 = 0, vsaautop0 = 0, vsaautop1 = 0, vsaautop2 = 0, sccautop0 = 0, sccautop1 = 0, sccautop2 = 0;
    public static int prodtotal = 0, aptotal = 0, indtotal = 0, stowtotal = 0, picktotal = 0, RTStotal = 0, PStotal = 0, Departtotal = 0, VSAtotal = 0, SCCtotal = 0, URLtotal = 0;
    public static int prodP0 = 0, indP0 = 0, appP0 = 0, stP0 = 0, PP0 = 0, RTSP0 = 0, PSP0 = 0, DP0 = 0, SCCP0 = 0, VSAP0 = 0, BoomP0 = 0;
    public static int prodP1 = 0, indP1 = 0, appP1 = 0, stP1 = 0, PP1 = 0, RTSP1 = 0, PSP1 = 0, DP1 = 0, SCCP1 = 0, VSAP1 = 0, BoomP1 = 0;
    public static int prodP2 = 0, indP2 = 0, appP2 = 0, stP2 = 0, PP2 = 0, RTSP2 = 0, PSP2 = 0, DP2 = 0, SCCP2 = 0, VSAP2 = 0, BoomP2 = 0;

    public static int totalprodautomated = 0, totalappauto = 0, totalindauto = 0, totalstowauto = 0, totalpickauto = 0, totalrtsauto = 0, totaldepartauto = 0, totalPSauto = 0, totalSCCauto = 0, totalvsaauto = 0, totalURLauto = 0;
    static String htmlMsg = "";

    public static void main(String[] args) throws InterruptedException, IOException, MessagingException {
        testRailLoginData();
        testSuiteChoose();
        del();
        Thread.sleep(3000);
        System.out.println("CSV file deleted");
        browserOpenAndExport();
        readCSVfile();
        se(htmlMsg);

    }

    public static void testRailLoginData() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the mail id for test rail login: ");
        mailid = input.nextLine();
        System.out.println("Enter the password for login");
        password = input.nextLine();
        System.out.println("Enter the mailId to whom the email to be sent");
        mailid1 = input.nextLine();
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
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys(mailid);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"button_primary\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"content-header\"]//*[@class=\"dropdownLink link-tooltip\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportDropdown\"]/ul/li[5]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns_control\"]/div/div[1]/div/a[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-cases:id\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-cases:title\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-cases:priority_id\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-cases:section_full\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-cases:custom_automated\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportSubmit\"]")).click();

    }


    public static void readCSVfile() throws IOException {

        String home = System.getProperty("user.home");
        File dir = new File(home + "/Downloads");
        for (File file : dir.listFiles()) {
            if (file.getName().contains(".csv")) {
                String s = file.getName();
                System.out.println(s);
                LineNumberReader lnr = new LineNumberReader(new FileReader(file));
                lnr.skip(Long.MAX_VALUE);
                int len = lnr.getLineNumber();
                System.out.println(len); //Add 1 because line index starts at 0
                lnr.close();
                try {
                    FileReader fReader = new FileReader(file);
                    BufferedReader fileBuff = new BufferedReader(fReader);
                    CSVReader csvReader = new CSVReader(fileBuff);
                    while (csvReader.readNext() != null) {
                        List<String[]> alldata = csvReader.readAll();
                        int j = 1;
                        for (int i = 0; i <= len; i++) {
                            if (j < alldata.size()) {
                                if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("Prod") && alldata.get(j)[2].contains("Yes")) {
                                    prodP0++;
                                    prodautoP0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("Prod") && alldata.get(j)[2].contains("No")) {
                                    prodP0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("Prod") && alldata.get(j)[2].contains("Yes")) {
                                    prodP1++;
                                    prodautoP1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("Prod") && alldata.get(j)[2].contains("No")) {
                                    prodP1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("Prod") && alldata.get(j)[2].contains("Yes")) {
                                    prodP2++;
                                    prodautoP2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("Prod") && alldata.get(j)[2].contains("No")) {
                                    prodP2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("APP") && alldata.get(j)[2].contains("Yes")) {
                                    appP0++;
                                    appautop0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("APP") && alldata.get(j)[2].contains("No")) {
                                    appP0++;
                                    j++;
                                }else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("APP") && alldata.get(j)[2].contains("NA") || alldata.get(j)[2].contains("TBD") ) {
                                    appP0++;
                                    j++;
                                }
                                else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("APP") && alldata.get(j)[2].contains("Yes")) {
                                    appP1++;
                                    appautop1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("APP") && alldata.get(j)[2].contains("No")) {
                                    appP1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("APP") && alldata.get(j)[2].contains("NA") || alldata.get(j)[2].contains("TBD") ) {
                                    appP1++;
                                    j++;
                                }
                                else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("APP") && alldata.get(j)[2].contains("Yes")) {
                                    appP2++;
                                    appautop2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("APP") && alldata.get(j)[2].contains("No")) {
                                    appP2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("APP") && alldata.get(j)[2].contains("NA") || alldata.get(j)[2].contains("TBD") ) {
                                    appP2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("Induct") && alldata.get(j)[2].contains("Yes")) {
                                    indP0++;
                                    indautop0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("Induct") && alldata.get(j)[2].contains("No")) {
                                    indP0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("Induct") && alldata.get(j)[2].contains("Yes")) {
                                    indP1++;
                                    indautop1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("Induct") && alldata.get(j)[2].contains("No")) {
                                    indP1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("Induct") && alldata.get(j)[2].contains("Yes")) {
                                    indP2++;
                                    indautop2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("Induct") && alldata.get(j)[2].contains("No")) {
                                    indP2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("Stow") && alldata.get(j)[2].contains("Yes")) {
                                    stP0++;
                                    stautop0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("Stow") && alldata.get(j)[2].contains("No")) {
                                    stP0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("Stow") && alldata.get(j)[2].contains("Yes")) {
                                    stP1++;
                                    stautop1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("Stow") && alldata.get(j)[2].contains("No")) {
                                    stP1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("Stow") && alldata.get(j)[2].contains("Yes")) {
                                    stP2++;
                                    stautop2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("Stow") && alldata.get(j)[2].contains("No")) {
                                    stP2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("Pick") && alldata.get(j)[2].contains("Yes")) {
                                    PP0++;
                                    pautop0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("Pick") && alldata.get(j)[2].contains("No")) {
                                    PP0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("Pick") && alldata.get(j)[2].contains("Yes")) {
                                    PP1++;
                                    pautop1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("Pick") && alldata.get(j)[2].contains("No")) {
                                    PP1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("Pick") && alldata.get(j)[2].contains("Yes")) {
                                    PP2++;
                                    pautop2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("Pick") && alldata.get(j)[2].contains("No")) {
                                    PP2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("RTS") && alldata.get(j)[2].contains("Yes")) {
                                    RTSP0++;
                                    rtsautop0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("RTS") && alldata.get(j)[2].contains("No")) {
                                    RTSP0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("RTS") && alldata.get(j)[2].contains("Yes")) {
                                    RTSP1++;
                                    rtsautop1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("RTS") && alldata.get(j)[2].contains("No")) {
                                    RTSP1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("RTS") && alldata.get(j)[2].contains("Yes")) {
                                    RTSP2++;
                                    rtsautop2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("RTS") && alldata.get(j)[2].contains("No")) {
                                    RTSP2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("Problem solve") && alldata.get(j)[2].contains("Yes")) {
                                    PSP0++;
                                    psautop0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("Problem solve") && alldata.get(j)[2].contains("No")) {
                                    PSP0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("Problem solve") && alldata.get(j)[2].contains("Yes")) {
                                    PSP1++;
                                    psautop1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("Problem solve") && alldata.get(j)[2].contains("No")) {
                                    PSP1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("Problem solve") && alldata.get(j)[2].contains("Yes")) {
                                    PSP2++;
                                    psautop2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("Problem solve") && alldata.get(j)[2].contains("No")) {
                                    PSP2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("Depart") && alldata.get(j)[2].contains("Yes")) {
                                    DP0++;
                                    dautop0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("Depart") && alldata.get(j)[2].contains("No")) {
                                    DP0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("Depart") && alldata.get(j)[2].contains("Yes")) {
                                    DP1++;
                                    dautop1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("Depart") && alldata.get(j)[2].contains("No")) {
                                    DP1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("Depart") && alldata.get(j)[2].contains("Yes")) {
                                    DP2++;
                                    dautop2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("Depart") && alldata.get(j)[2].contains("No")) {
                                    DP2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("Vehicle") && alldata.get(j)[2].contains("Yes")) {
                                    VSAP0++;
                                    vsaautop0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("Vehicle") && alldata.get(j)[2].contains("No")) {
                                    VSAP0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("Vehicle") && alldata.get(j)[2].contains("Yes")) {
                                    VSAP1++;
                                    vsaautop1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("Vehicle") && alldata.get(j)[2].contains("No")) {
                                    VSAP1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("Vehicle") && alldata.get(j)[2].contains("Yes")) {
                                    VSAP2++;
                                    vsaautop2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("Vehicle") && alldata.get(j)[2].contains("No")) {
                                    VSAP2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("Boomerang") && alldata.get(j)[2].contains("Yes")) {
                                    BoomP0++;
                                    boomautop0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("Boomerang") && alldata.get(j)[2].contains("No")) {
                                    BoomP0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("Boomerang") && alldata.get(j)[2].contains("Yes")) {
                                    BoomP1++;
                                    boomautop1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("Boomerang") && alldata.get(j)[2].contains("No")) {
                                    BoomP1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("Boomerang") && alldata.get(j)[2].contains("Yes")) {
                                    BoomP2++;
                                    boomautop2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("Boomerang") && alldata.get(j)[2].contains("No")) {
                                    BoomP2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("SCC New UI") && alldata.get(j)[2].contains("Yes")) {
                                    SCCP0++;
                                    sccautop0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P0") && alldata.get(j)[4].startsWith("SCC New UI") && alldata.get(j)[2].contains("No")) {
                                    SCCP0++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("SCC New UI") && alldata.get(j)[2].contains("Yes")) {
                                    SCCP1++;
                                    sccautop1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P1") && alldata.get(j)[4].startsWith("SCC New UI") && alldata.get(j)[2].contains("No")) {
                                    SCCP1++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("SCC New UI") && alldata.get(j)[2].contains("Yes")) {
                                    SCCP2++;
                                    sccautop2++;
                                    j++;
                                } else if (alldata.get(j)[3].equals("P2") && alldata.get(j)[4].startsWith("SCC New UI") && alldata.get(j)[2].contains("No")) {
                                    SCCP2++;
                                    j++;
                                }
                            } else {
                                System.out.println("Entire file is read");
                            }
                            prodtotal = prodP0 + prodP1 + prodP2;
                            aptotal = appP0 + appP1 + appP2;
                            indtotal = indP0 + indP1 + indP2;
                            stowtotal = stP0 + stP1 + stP2;
                            picktotal = PP0 + PP1 + PP2;
                            RTStotal = RTSP0 + RTSP1 + RTSP2;
                            PStotal = PSP0 + PSP1 + PSP2;
                            Departtotal = DP0 + DP1 + DP2;
                            VSAtotal = VSAP0 + VSAP1 + VSAP2;
                            URLtotal = BoomP0 + BoomP1 + BoomP2;
                            SCCtotal = SCCP0 + SCCP1 + SCCP2;
                            totalP0 = prodP0 + indP0 + appP0 + stP0 + PP0 + RTSP0 + PSP0 + DP0 + SCCP0 + VSAP0 + BoomP0;
                            totalP1 = prodP1 + indP1 + appP1 + stP1 + PP1 + RTSP1 + PSP1 + DP1 + SCCP1 + VSAP1 + BoomP1;
                            totalP2 = prodP2 + indP2 + appP2 + stP2 + PP2 + RTSP2 + PSP2 + DP2 + SCCP2 + VSAP2 + BoomP2;
                            totalp0auto = prodautoP0 + indautop0 + appautop0 + stautop0 + pautop0 + rtsautop0 + dautop0 + psautop0 + sccautop0 + vsaautop0 + boomautop0;
                            totalp1auto = prodautoP1 + indautop1 + appautop1 + stautop1 + pautop1 + rtsautop1 + dautop1 + psautop1 + sccautop1 + vsaautop1 + boomautop1;
                            totalp2auto = prodautoP2 + indautop2 + appautop2 + stautop2 + pautop2 + rtsautop2 + dautop2 + psautop2 + sccautop2 + vsaautop2 + boomautop2;
                            totalcase = totalP0 + totalP1 + totalP2;
                            totalprodautomated = prodautoP0 + prodautoP1 + prodautoP2;
                            totalappauto = appautop0 + appautop1 + appautop2;
                            totalindauto = indautop0 + indautop1 + indautop2;
                            totalstowauto = stautop0 + stautop1 + stautop2;
                            totalpickauto = pautop0 + pautop1 + pautop2;
                            totalrtsauto = rtsautop0 + rtsautop1 + rtsautop2;
                            totaldepartauto = dautop0 + dautop1 + dautop2;
                            totalPSauto = psautop0 + psautop1 + psautop2;
                            totalSCCauto = sccautop0 + sccautop1 + sccautop2;
                            totalvsaauto = vsaautop0 + vsaautop1 + vsaautop2;
                            totalURLauto = boomautop0 + boomautop1 + boomautop2;
                            totalautomatedcase = totalprodautomated + totalappauto + totalindauto + totalstowauto + totalpickauto + totalrtsauto + totaldepartauto + totalPSauto + totalURLauto + totalSCCauto + totalvsaauto;
                        }
                        System.out.println("prod P0 :" + prodP0);
                        System.out.println("Automated Prod P0:" + prodautoP0);
                        System.out.println("prod P1 :" + prodP1);
                        System.out.println("Automated Prod P1:" + prodautoP1);
                        System.out.println("prod P2 :" + prodP2);
                        System.out.println("Automated Prod P2:" + prodautoP2);
                        System.out.println("app P0:" + appP0);
                        System.out.println("Automated App P0:" + appautop0);
                        System.out.println("app P1:" + appP1);
                        System.out.println("Automated App P1:" + appautop1);
                        System.out.println("appP2:" + appP2);
                        System.out.println("Automated App P2:" + appautop2);
                        System.out.println("APP core total:" + aptotal);
                        System.out.println("ind P0 :" + indP0);
                        System.out.println("Automated ind P0:" + indautop0);
                        System.out.println("ind P1 :" + indP1);
                        System.out.println("Automated ind P1:" + appautop0);
                        System.out.println("ind P2 :" + indP2);
                        System.out.println("Automated ind P2:" + appautop0);
                        System.out.println("Induct total:" + indtotal);
                        System.out.println("Stow P0 :" + stP0);
                        System.out.println("Automated Stow P0:" + stautop0);
                        System.out.println("Stow P1 :" + stP1);
                        System.out.println("Automated Stow P1:" + stautop1);
                        System.out.println("Stow P2 :" + stP2);
                        System.out.println("Automated Stow P2:" + stautop2);
                        System.out.println("Stow total:" + stowtotal);
                        System.out.println("Pick and Stage P0 :" + PP0);
                        System.out.println("Automated Pick & Stage P0:" + pautop0);
                        System.out.println("Pick and Stage P1 :" + PP1);
                        System.out.println("Automated Pick & Stage P1:" + pautop1);
                        System.out.println("Pick and Stage P2 :" + PP2);
                        System.out.println("Automated Pick & Stage P2:" + pautop2);
                        System.out.println("Pick and stage total:" + picktotal);
                        System.out.println("RTS P0 :" + RTSP0);
                        System.out.println("Automated RTS P0:" + rtsautop0);
                        System.out.println("RTS P1 :" + RTSP1);
                        System.out.println("Automated RTS P1:" + rtsautop1);
                        System.out.println("RTS P2 :" + RTSP2);
                        System.out.println("Automated RTS P2:" + rtsautop2);
                        System.out.println("RTS total:" + RTStotal);
                        System.out.println("Depart P0 :" + DP0);
                        System.out.println("Automated Depart P0:" + dautop0);
                        System.out.println("Depart P1 :" + DP1);
                        System.out.println("Automated Depart P1:" + dautop1);
                        System.out.println("Depart P2 :" + DP2);
                        System.out.println("Automated Depart P2:" + dautop2);
                        System.out.println("Depart total:" + Departtotal);
                        System.out.println("PS P0 :" + PSP0);
                        System.out.println("Automated PS P0:" + psautop0);
                        System.out.println("PS P1 :" + PSP1);
                        System.out.println("Automated PS P1:" + psautop1);
                        System.out.println("PS P2 :" + PSP2);
                        System.out.println("Automated PS P2:" + psautop2);
                        System.out.println("Problem solve total:" + PStotal);
                        System.out.println("VSA P0 :" + VSAP0);
                        System.out.println("Automated VSA P0:" + vsaautop0);
                        System.out.println("VSA P1 :" + VSAP1);
                        System.out.println("Automated VSA P1:" + vsaautop1);
                        System.out.println("VSA P2 :" + VSAP2);
                        System.out.println("Automated VSA P2:" + vsaautop2);
                        System.out.println("VSA total:" + VSAtotal);
                        System.out.println("SCC P0 :" + SCCP0);
                        System.out.println("Automated SCC P0:" + sccautop0);
                        System.out.println("SCC P1 :" + SCCP1);
                        System.out.println("Automated SCC P1:" + sccautop1);
                        System.out.println("SCC P2 :" + SCCP2);
                        System.out.println("Automated SCC P2:" + sccautop2);
                        System.out.println("SCC total:" + SCCtotal);
                        System.out.println("URL P0 :" + BoomP0);
                        System.out.println("Automated URL P0:" + boomautop0);
                        System.out.println("URL P1 :" + BoomP1);
                        System.out.println("Automated URL P1:" + boomautop1);
                        System.out.println("URL P2 :" + BoomP2);
                        System.out.println("Automated URL P2:" + boomautop2);
                        System.out.println("URL/Boomerang total:" + URLtotal);
                        System.out.println("Total P0 :" + totalP0);
                        System.out.println("Total P1 :" + totalP1);
                        System.out.println("Total P2 :" + totalP2);
                        System.out.println("Total P0 automated :" + totalp0auto);
                        System.out.println("Total P1 automated:" + totalp1auto);
                        System.out.println("Total P2 automated:" + totalp2auto);
                        System.out.println("Total case" + totalcase);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }


    public static void se(String msg) throws MessagingException {

        Properties props = new Properties();

        // this will set host of server- you can change based on your requirement
        props.put("mail.smtp.host", "smtp.amazon.com");

        Session session = Session.getDefaultInstance(props);
        Message message = new MimeMessage(session);

        // Set the from address
        message.setFrom(new InternetAddress(mailid));

        // Recipient's email ID needs to be mentioned.
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailid1));
        message.addRecipient(Message.RecipientType.CC, new InternetAddress("nethhaj@amazon.com"));


        // Set Subject: header field
        message.setSubject("Testcase count");


        msg = "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table, th, td {\n" + "  border: 1px solid black;\n" + "  border-collapse: collapse;\n" + "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" + "<br> Hi Team, <br><br>Kindly find the Testcase count  below <br><br>" +

                "<table style=\"width:80%\">\n" +
                "  <tr bgcolor=\"#25BCF0\">\n" +
                "    <th>Module</th>\n" +
                "    <th>Total P0</th> \n" +
                "    <th>Total P0 Automated</th> \n" +
                "    <th>Total P1</th>\n" +
                "    <th>Total P1 Automated</th>\n" +
                "    <th>Total P2</th>\n" +
                "    <th>Total P2 Automated</th>\n" +
                "    <th>Total cases</th>\n" +
                "    <th>Total automated cases</th>\n" +
                "    </tr>\n" +

                //values

                "    <tr>\n" +
                "    <td align=\"center\"> Prod validation scenario </td>\n" +
                "    <td align=\"center\">" + prodP0 + "</td>\n" +
                "    <td align=\"center\">" + prodautoP0 + "</td>\n" +
                "    <td align=\"center\">" + prodP1 + "</td>\n" +
                "    <td align=\"center\">" + prodautoP1 + "</td>\n" +
                "    <td align=\"center\">" + prodP2 + "</td>\n" +
                "    <td align=\"center\">" + prodautoP2 + "</td>\n" +
                "    <td align=\"center\">" + prodtotal + "</td>\n" +
                "    <td align=\"center\">" + totalprodautomated + "</td>\n" +
                "    </tr>\n" +

                "    <tr>\n" +
                "    <td align=\"center\"> App Core </td>\n" +
                "    <td align=\"center\">" + appP0 + "</td>\n" +
                "    <td align=\"center\">" + appautop0 + "</td>\n" +
                "    <td align=\"center\">" + appP1 + "</td>\n" +
                "    <td align=\"center\">" + appautop1 + "</td>\n" +
                "    <td align=\"center\">" + appP2 + "</td>\n" +
                "    <td align=\"center\">" + appautop2 + "</td>\n" +
                "    <td align=\"center\">" + aptotal + "</td>\n" +
                "    <td align=\"center\">" + totalappauto + "</td>\n" +
                "    </tr>\n" +


                "    <tr>\n" +
                "    <td align=\"center\"> Induct </td>\n" +
                "    <td align=\"center\">" + indP0 + "</td>\n" +
                "    <td align=\"center\">" + indautop0 + "</td>\n" +
                "    <td align=\"center\">" + indP1 + "</td>\n" +
                "    <td align=\"center\">" + indautop1 + "</td>\n" +
                "    <td align=\"center\">" + indP2 + "</td>\n" +
                "    <td align=\"center\">" + indautop2 + "</td>\n" +
                "    <td align=\"center\">" + indtotal + "</td>\n" +
                "    <td align=\"center\">" + totalindauto + "</td>\n" +
                "    </tr>\n" +

                "    <tr>\n" +
                "    <td align=\"center\"> Stow </td>\n" +
                "    <td align=\"center\">" + stP0 + "</td>\n" +
                "    <td align=\"center\">" + stautop0 + "</td>\n" +
                "    <td align=\"center\">" + stP1 + "</td>\n" +
                "    <td align=\"center\">" + stautop1 + "</td>\n" +
                "    <td align=\"center\">" + stP2 + "</td>\n" +
                "    <td align=\"center\">" + stautop2 + "</td>\n" +
                "    <td align=\"center\">" + stowtotal + "</td>\n" +
                "    <td align=\"center\">" + totalstowauto + "</td>\n" +
                "    </tr>\n" +

                "    <tr>\n" +
                "    <td align=\"center\"> Pick and Stage </td>\n" +
                "    <td align=\"center\">" + PP0 + "</td>\n" +
                "    <td align=\"center\">" + pautop0 + "</td>\n" +
                "    <td align=\"center\">" + PP1 + "</td>\n" +
                "    <td align=\"center\">" + pautop1 + "</td>\n" +
                "    <td align=\"center\">" + PP2 + "</td>\n" +
                "    <td align=\"center\">" + pautop2 + "</td>\n" +
                "    <td align=\"center\">" + picktotal + "</td>\n" +
                "    <td align=\"center\">" + totalpickauto + "</td>\n" +
                "    </tr>\n" +

                "    <tr>\n" +
                "    <td align=\"center\"> Receive </td>\n" +
                "    <td align=\"center\">" + RTSP0 + "</td>\n" +
                "    <td align=\"center\">" + rtsautop0 + "</td>\n" +
                "    <td align=\"center\">" + RTSP1 + "</td>\n" +
                "    <td align=\"center\">" + rtsautop1 + "</td>\n" +
                "    <td align=\"center\">" + RTSP2 + "</td>\n" +
                "    <td align=\"center\">" + rtsautop2 + "</td>\n" +
                "    <td align=\"center\">" + RTStotal + "</td>\n" +
                "    <td align=\"center\">" + totalrtsauto + "</td>\n" +
                "    </tr>\n" +

                "    <tr>\n" +
                "    <td align=\"center\"> Depart </td>\n" +
                "    <td align=\"center\">" + DP0 + "</td>\n" +
                "    <td align=\"center\">" + dautop0 + "</td>\n" +
                "    <td align=\"center\">" + DP1 + "</td>\n" +
                "    <td align=\"center\">" + dautop1 + "</td>\n" +
                "    <td align=\"center\">" + DP2 + "</td>\n" +
                "    <td align=\"center\">" + dautop2 + "</td>\n" +
                "    <td align=\"center\">" + Departtotal + "</td>\n" +
                "    <td align=\"center\">" + totaldepartauto + "</td>\n" +
                "    </tr>\n" +

                "    <tr>\n" +
                "    <td align=\"center\"> Problem solve </td>\n" +
                "    <td align=\"center\">" + PSP0 + "</td>\n" +
                "    <td align=\"center\">" + psautop0 + "</td>\n" +
                "    <td align=\"center\">" + PSP1 + "</td>\n" +
                "    <td align=\"center\">" + psautop1 + "</td>\n" +
                "    <td align=\"center\">" + PSP2 + "</td>\n" +
                "    <td align=\"center\">" + psautop2 + "</td>\n" +
                "    <td align=\"center\">" + PStotal + "</td>\n" +
                "    <td align=\"center\">" + totalPSauto + "</td>\n" +
                "    </tr>\n" +

                "    <tr>\n" +
                "    <td align=\"center\"> Boomerang/URL </td>\n" +
                "    <td align=\"center\">" + BoomP0 + "</td>\n" +
                "    <td align=\"center\">" + boomautop0 + "</td>\n" +
                "    <td align=\"center\">" + BoomP1 + "</td>\n" +
                "    <td align=\"center\">" + boomautop1 + "</td>\n" +
                "    <td align=\"center\">" + BoomP2 + "</td>\n" +
                "    <td align=\"center\">" + boomautop2 + "</td>\n" +
                "    <td align=\"center\">" + URLtotal + "</td>\n" +
                "    <td align=\"center\">" + totalURLauto + "</td>\n" +
                "    </tr>\n" +

                "    <tr>\n" +
                "    <td align=\"center\"> SCC </td>\n" +
                "    <td align=\"center\">" + SCCP0 + "</td>\n" +
                "    <td align=\"center\">" + sccautop0 + "</td>\n" +
                "    <td align=\"center\">" + SCCP1 + "</td>\n" +
                "    <td align=\"center\">" + sccautop1 + "</td>\n" +
                "    <td align=\"center\">" + SCCP2 + "</td>\n" +
                "    <td align=\"center\">" + sccautop2 + "</td>\n" +
                "    <td align=\"center\">" + SCCtotal + "</td>\n" +
                "    <td align=\"center\">" + totalSCCauto + "</td>\n" +
                "    </tr>\n" +

                "    <tr>\n" +
                "    <td align=\"center\"> VSA </td>\n" +
                "    <td align=\"center\">" + VSAP0 + "</td>\n" +
                "    <td align=\"center\">" + vsaautop0 + "</td>\n" +
                "    <td align=\"center\">" + VSAP1 + "</td>\n" +
                "    <td align=\"center\">" + vsaautop1 + "</td>\n" +
                "    <td align=\"center\">" + VSAP2 + "</td>\n" +
                "    <td align=\"center\">" + vsaautop2 + "</td>\n" +
                "    <td align=\"center\">" + VSAtotal + "</td>\n" +
                "    <td align=\"center\">" + totalvsaauto + "</td>\n" +
                "    </tr>\n" +

                "    <tr>\n" +
                "    <td align=\"center\">  </td>\n" +
                "    <td align=\"center\">" + totalP0 + "</td>\n" +
                "    <td align=\"center\">" + totalp0auto + "</td>\n" +
                "    <td align=\"center\">" + totalP1 + "</td>\n" +
                "    <td align=\"center\">" + totalp1auto + "</td>\n" +
                "    <td align=\"center\">" + totalP2 + "</td>\n" +
                "    <td align=\"center\">" + totalp2auto + "</td>\n" +
                "    <td align=\"center\">" + totalcase + "</td>\n" +
                "    <td align=\"center\">" + totalautomatedcase + "</td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "<br><br>Thanks\n" +
                "<br>Nethhaji S\n" +
                "</body>\n" +
                "</html>";


        System.out.println(msg);


        message.setContent(msg, "text/html");

        // Send message
        Transport.send(message);
        System.out.println("Sent message successfully....");

    }


    public static void del() {
        File directory = new File("/Users/nethhaj/Downloads");
        for (File file : directory.listFiles()) {
            if (file.getName().contains(".csv")) {
                file.delete();
            }
        }
        System.out.println("CSV Files deleting...");
    }
}