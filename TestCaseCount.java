package Count;

import com.opencsv.CSVReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;


class Count {
    static Integer P0[] = new Integer[100000];
    static Integer P1[] = new Integer[100000];
    static Integer P2[] = new Integer[100000];


    static Integer totallength[] = new Integer[100000];

    static String status[] = new String[100000];

    static String priority[] = new String[100000];

    static String name1[] = new String[100000];

    static Integer flag[] = new Integer[100000];
    static Integer len;
    static String useid, pass;
    static int i = 0, j = 0, no = 0;
    static List<String> distinctElements;
    static List<String> list = new ArrayList<String>();
    static ArrayList<Integer> list1 = new ArrayList<Integer>();
    static ArrayList<Integer> list2 = new ArrayList<Integer>();
    static WebDriver driver;
    static String startDate = "2020-01-01";
    public static ArrayList<String> buglist = new ArrayList<String>();
    public static ArrayList<String> prioritylist = new ArrayList<String>();
    public static ArrayList<String> submitterlist = new ArrayList<String>();
    public static ArrayList<String> buglistwithDepri = new ArrayList<String>();
    public static ArrayList<String> prioritylistwithDepri = new ArrayList<String>();
    public static ArrayList<String> submitterlistwithDepri = new ArrayList<String>();
    static String home = System.getProperty("user.home");
    static int count = 0;
    static int count1 = 0;
    static String tem = null;
    static String temp = null;
    static String s;
    static int size11, size12, size13;
    static int total = 0, mon, dat, fromDate, toDate;
    static String url, TestRunUrl;
    static String url1;
    static String mailid, name, sub, mailid1;

    int fl = 0;
    public String pri;
    public String replacepri;

    static String start = "<html>\n" + "<head>\n" + "<style>\n" + "table, th, td {\n" + "  border: 1px solid black;\n" + "  border-collapse: collapse;\n" + "}\n" + "</style>\n" + "</head>\n" + "<body>\n" + "<br> Hi Team, <br><br>Kindly find the output below <br><br>";
    static String htmlMsg = "";

    public static void main(String[] args) throws InterruptedException, MessagingException, IOException {
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println(os);
        Scanner input = new Scanner(System.in);
        //System.out.println("Please provide your name: ");
        //name = input.nextLine();
        System.out.println("Enter the mail id for test rail login: ");
        mailid = input.nextLine();
        //System.out.println("Enter the mail id for report to be sent: ");
        //mailid1 = input.nextLine();
        System.out.println("Enter the password for testrail run: ");
        pass = input.nextLine();
        //System.out.println("Enter the subject ");
        //sub = input.nextLine();
        del();
        Thread.sleep(3000);
        System.out.println("CSV Files deleted");
        {
            choiceTestRun();
        }

    }

    public static void choiceTestRun() throws MessagingException, IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the Entire Testsuite url: ");
        TestRunUrl = input.nextLine();
        System.out.println("TestRun Execution Status Initiated..");
        {
            reportTestRun();
            Count c = new Count();
            c.tabledailywihoutdate();
        }

    }


    public static void reportTestRun() throws NullPointerException, javax.mail.MessagingException {
        for (i = 0; i < 100000; i++) {
            P0[i] = P1[i] = P2[i] = flag[i] = 0;
        }
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        driver.get(TestRunUrl);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys(mailid);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(pass);
        //driver.findElement(By.xpath("//div[@class=\"single-sign-on\"]")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\"button_primary\"]")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\"content-header\"]//*[@class=\"dropdownLink link-tooltip\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportDropdown\"]/ul/li[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns_control\"]/div/div[1]/div/a[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-cases:id\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-cases:title\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-cases:priority_id\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportSubmit\"]")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void tabledailywihoutdate() throws IOException {
        String home = System.getProperty("user.home");
        File dir = new File(home + "/Downloads");
        int fl = 0, count = 0;
        //ArrayList<String> fileContents = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.getName().contains(".csv")) {
                s = file.getName();
                System.out.println(s);
                LineNumberReader lnr = new LineNumberReader(new FileReader(file));
                lnr.skip(Long.MAX_VALUE);
                len = lnr.getLineNumber() ;
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
                        int j = 0;
                        int sum = 0;
                        int sum1 = 0;
                        int sum2 = 0;
                        for (i = 0; i < len-1; i++) {
                            if (alldata.get(j)[2].equals("P0")) {
                                sum++;
                                j++;
                            } else if (alldata.get(j)[2].equals("P1")) {
                                sum1++;
                                j++;
                            } else if (alldata.get(j)[2].equals("P2")) {
                                sum2++;
                                j++;
                            } else {
                                System.out.println("other priorities");
                            }

                        }
                        System.out.println("P0 count is :" +sum);
                        System.out.println("P1 count is :" +sum1);
                        System.out.println("P2 count is :" +sum2);
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
