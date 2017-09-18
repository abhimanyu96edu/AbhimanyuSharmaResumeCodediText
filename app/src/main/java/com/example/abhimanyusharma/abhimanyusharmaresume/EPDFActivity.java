package com.example.abhimanyusharma.abhimanyusharmaresume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class EPDFActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epdf);

        tv = (TextView) findViewById(R.id.tv);

        requestPermissions();

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/A_PDF");
        myDir.mkdirs();

        String FILE = Environment.getExternalStorageDirectory().toString() + "/A_PDF/Abhimanyu_Sharma_Resume.pdf";

// Create New Blank Document
        Document document = new Document(PageSize.A4, 10, 5, 20, 10); //left, right, top, bottom

        // Create Pdf Writer for Writting into New Created Document
        try {

            PdfWriter.getInstance(document, new FileOutputStream(FILE));

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();

        try {
            addMetaData(document);
            addTitle(document);
            addContent(document);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


// Close Document after writting all content
        document.close();

        tv.setText("RESUME PDF Successfully Created at: " + FILE);

        Toast.makeText(getApplicationContext(), "Your Resume Is Saved At Location: \n" + FILE, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "Thank You for Using Rinc. Resume by RESUME INC.", Toast.LENGTH_SHORT).show();

    }

    private void addMetaData(Document document) {

        document.addTitle("Resume Inc.");
        document.addSubject("Resume Inc.");
        document.addKeywords("Personal, Education, Skills, Work, Resume, Technical, Achievements");
        document.addAuthor("Abhimanyu Sharma");
        document.addCreator("Abhimanyu Sharma");
    }

    private void addTitle(Document document) throws DocumentException, IOException {

        document.newPage();

        BaseColor myColor1 = new BaseColor(1, 161, 161);
        BaseColor myColor2 = new BaseColor(95, 96, 108);

        //Font nameFont = new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD, myColor1);
        //Font descFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, myColor2);
//_____________________________________________________


        PdfPTable tH = new PdfPTable(3);
        //PdfPTable tH = new PdfPTable(2);
        tH.setWidthPercentage(100.0f);
        tH.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        Paragraph row1 = new Paragraph();
        row1.setFont(new Font(Font.FontFamily.HELVETICA, 25, Font.BOLD, myColor1));
        row1.setAlignment(Element.ALIGN_CENTER);
        row1.setLeading(15, 0);
        row1.add("ABHIMANYU SHARMA");
        //row1.add(u_name);

        Paragraph row2 = new Paragraph();
        row2.setFont(new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, myColor2));
        row2.setAlignment(Element.ALIGN_CENTER);
        row2.add("\nLooking forward to work as Software & Mobile Application Developer and to contribute efficiently my skills and abilities for the growth of the organization while enhancing my skills & knowledge\n ");

        //Paragraph row4 = new Paragraph();
        //row4.setFont(new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, myColor2));
        //row4.setAlignment(Element.ALIGN_CENTER);
        //row4.add("B.Tech 4th Year, Computer Science Engineering, \nGalgotias College of Engineering & Technology\n \n ");


        PdfPCell row_1 = new PdfPCell(row1);
        PdfPCell row_2 = new PdfPCell(row2);
        //PdfPCell row_4 = new PdfPCell(row4);

        row_1.setBorder(Rectangle.NO_BORDER);
        row_2.setBorder(Rectangle.NO_BORDER);
        //row_4.setBorder(Rectangle.NO_BORDER);

        //Add table Rows
        PdfPTable th = new PdfPTable(1);
        th.setWidthPercentage(100.0f);

        th.addCell(row_1);
        //th.addCell(row_4);//for spaced line
        th.addCell(row_2);

        InputStream inputStream = getAssets().open("logo.png");
        //PUT THIS IMAGE IN ASSETS FOLDER OTHERWISE ERROR MIGHT APPEAR LIKE:  ExceptionConverter: java.io.IOException: The document has no pages.

        Bitmap bmp = BitmapFactory.decodeStream(inputStream);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        //picture.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Image logoImage = Image.getInstance(stream.toByteArray());
        logoImage.scalePercent(60);
        logoImage.scaleAbsolute(40, 40);


        tH.setWidths(new int[]{8, 1, 2});
        tH.addCell(th);
        tH.addCell(new Phrase(" "));
        tH.addCell(logoImage);

        document.add(tH);

        //document.add(addEmptyLine(1));
        document.add(addColorLineEmptyTableGray(1));

    }

    private void addContent(Document document) throws DocumentException, IOException {

        //document.newPage();
        BaseColor myColor1 = new BaseColor(2, 177, 178);//GREENY
        BaseColor myColor2 = new BaseColor(95, 96, 108);//GRAY

        Font normalFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, myColor2);
        Font normalBoldFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, myColor2);
        Font smallFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD, BaseColor.GRAY);
        //Font smallBoldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD, BaseColor.DARK_GRAY);

        Paragraph emailBar = new Paragraph();
        emailBar.setFont(new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, myColor1));
        emailBar.setAlignment(Element.ALIGN_CENTER);
        emailBar.add("abhimanyusharma96.edu@gmail.com");

        Paragraph numberBar = new Paragraph();
        numberBar.setFont(new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, myColor1));
        numberBar.setAlignment(Element.ALIGN_CENTER);
        numberBar.add("+91-7042218117");

        Paragraph portfolioBar = new Paragraph();
        portfolioBar.setFont(new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, myColor1));
        portfolioBar.setAlignment(Element.ALIGN_CENTER);
        portfolioBar.add("abhimanyu96edu.github.io");

        Paragraph addressBar = new Paragraph();
        addressBar.setFont(new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, myColor1));
        addressBar.setAlignment(Element.ALIGN_CENTER);
        addressBar.add("linkedin.com/in/abhimanyu-sharma-0ba68b112/\n ");

        PdfPCell c1 = new PdfPCell(emailBar);
        PdfPCell c2 = new PdfPCell(numberBar);
        PdfPCell c3 = new PdfPCell(portfolioBar);
        PdfPCell c4 = new PdfPCell(addressBar);

        c1.setBorder(Rectangle.NO_BORDER);
        c2.setBorder(Rectangle.NO_BORDER);
        c3.setBorder(Rectangle.NO_BORDER);
        c4.setBorder(Rectangle.NO_BORDER);

        //Add table Rows
        PdfPTable tbar = new PdfPTable(4);
        tbar.setWidthPercentage(100.0f);
        tbar.setWidths(new int[]{6, 3, 5, 8});

        tbar.addCell(c1);
        tbar.addCell(c2);
        tbar.addCell(c3);
        tbar.addCell(c4);

        //document.add(tbar);

        Paragraph bar = new Paragraph();
        bar.setFont(new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, new BaseColor(19, 150, 150)));
        bar.setAlignment(Element.ALIGN_CENTER);
        bar.add("abhimanyusharma96.edu@gmail.com | +91-7042218117 | abhimanyu96edu.github.io | linkedin.com/in/abhimanyu-sharma-0ba68b112/\n ");

        document.add(bar);

        document.add(addColorLineEmptyTableGray(1));
        //document.add(addEmptyLine(1));

// MAIN PAGE START_________________________________________________________


        //BIGGEST TABLE
        PdfPTable bigT = new PdfPTable(2);
        bigT.setWidthPercentage(100.0f);
        bigT.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        //TABLE 1 FOR BIGGEST TABLE
        PdfPTable bigT1 = new PdfPTable(1);
        bigT1.setWidthPercentage(100.0f);
        bigT1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        //TABLE 2 FOR BIGGEST TABLE
        PdfPTable bigT2 = new PdfPTable(1);
        bigT2.setWidthPercentage(100.0f);
        bigT2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);


        //________________________________________________________________________
        //PROJECT EXPERIENCE

        //bigT1.addCell(addColorLineEmptyTable(1));
        //bigT1.addCell(addEmptyLine(1));
        bigT1.addCell(addHeadTitle("Projects"));
        //bigT1.addCell(addEmptyLine(1));


        //Add table Rows
        PdfPTable pebar = new PdfPTable(1);
        pebar.setWidthPercentage(100.0f);
        pebar.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        Paragraph pe21 = new Paragraph();
        pe21.setFont(normalBoldFont);
        Anchor anchor21= new Anchor("\nSentiment Detector/Analyzer  " + "(4th year project - Ongoing)", normalBoldFont);
        anchor21.setReference("https://github.com/abhimanyu96edu?tab=repositories");
        pe21.add(anchor21);
        pe21.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe22 = new Paragraph();
        pe22.setFont(normalFont);
        pe22.add("Improving Sentiment Analysis by first performing Audio Analysis & then applying NLP on extracted information and providing a valid sentiment value\n");
        pe22.setAlignment(Element.ALIGN_LEFT);


        Paragraph pe1 = new Paragraph();
        pe1.setFont(normalBoldFont);
        Anchor anchor1= new Anchor("\nRinc. Resume App    " + "(JULY 2017)", normalBoldFont);
        anchor1.setReference("https://play.google.com/store/apps/details?id=com.abhimanyusharma.resume&hl=en");
        pe1.add(anchor1);
        pe1.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe2 = new Paragraph();
        pe2.setFont(normalFont);
        pe2.add("Worked with Active Android for complex database management, iText library for PDF, Material Design UI & created a totally Offline Application, 300+ downloads\n");
        pe2.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe3 = new Paragraph();
        Anchor anchor2= new Anchor("\nBit Math    " + "(MAY 2017)", normalBoldFont);
        anchor2.setReference("https://drive.google.com/open?id=0Bwv1sAEhO8I4RWp6cHprNzN1Um8");
        pe3.add(anchor2);
        pe3.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe4 = new Paragraph();
        pe4.setFont(normalFont);
        pe4.add("Worked with animations and interactive layouts, concepts of probability are applied to generate random numbers with increasing difficulty\n");
        pe4.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe5 = new Paragraph();
        Anchor anchor3= new Anchor("\nCustomer Sentiment Analyzer App    " + "(APRIL 2017)", normalBoldFont);
        anchor3.setReference("https://github.com/abhimanyu96edu/CustomerSentimentAnalyser");
        pe5.add(anchor3);
        pe5.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe6 = new Paragraph();
        pe6.setFont(normalFont);
        pe6.add("Worked with Natural Language Processing Algorithm & IBM Watson APIs, App performs Real-Time Sentiment Analysis by first " +
                "performing Audio Analysis & then applying NLP on extracted information for providing a valid sentiment\n");
        pe6.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe7 = new Paragraph();
        Anchor anchor4= new Anchor("\nWeather Forecast App    " + "(DEC 2017)", normalBoldFont);
        anchor4.setReference("https://github.com/abhimanyu96edu/WeatherForecast");
        pe7.add(anchor4);
        pe7.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe8 = new Paragraph();
        pe8.setFont(normalFont);
        pe8.add("Used OWM POST APIs and parsed JSON response for fetching weather data along with 5 days forecast\n");
        pe8.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe9 = new Paragraph();
        Anchor anchor5= new Anchor("\nExpense Manager App    " + "(JULY 2016)", normalBoldFont);
        anchor5.setReference("https://github.com/abhimanyu96edu/Expense_Manager");
        pe9.add(anchor5);
        pe9.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe10 = new Paragraph();
        pe10.setFont(normalFont);
        pe10.add("Worked with SQL for complex database management, developed interactive & user friendly UI\n");
        pe10.setAlignment(Element.ALIGN_LEFT);


        pebar.addCell(pe21);
        pebar.addCell(pe22);

        pebar.addCell(pe1);
        pebar.addCell(pe2);

        pebar.addCell(pe3);
        pebar.addCell(pe4);

        pebar.addCell(pe5);
        pebar.addCell(pe6);

        pebar.addCell(pe7);
        pebar.addCell(pe8);

        pebar.addCell(pe9);
        pebar.addCell(pe10);


        PdfPTable pexTable = new PdfPTable(2);//use autocomplete()
        pexTable.setWidthPercentage(100.0f);
        pexTable.setWidths(new int[]{3, 2});
        pexTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        Paragraph pex1 = new Paragraph();
        pex1.setFont(normalFont);
        pex1.add("•  Real Time Text Scanner\n");
        pex1.add("•  Optical Character Recognition\n");
        pex1.add("•  Bluetooth Printing\n");
        pex1.add("•  Student Result System\n");
        pex1.setAlignment(Element.ALIGN_LEFT);

        Paragraph pex2 = new Paragraph();
        pex2.setFont(normalFont);
        pex2.add("•  Group Chat\n");
        pex2.add("•  Call Blocking \n");
        pex2.add("•  Volley for APIs\n");
        pex2.setAlignment(Element.ALIGN_LEFT);

        pexTable.addCell(pex1);
        pexTable.addCell(pex2);

        pexTable.completeRow();

        //pebar.addCell(pexTable);
        bigT1.addCell(pebar);

        Font moreProjectFont = new Font(Font.FontFamily.HELVETICA, 8, Font.UNDERLINE | Font.BOLD, new BaseColor(95, 96, 108));
        Paragraph pmLink = new Paragraph();
        pmLink.setFont(moreProjectFont);
        pmLink.add("\nOther Important Projects are:\n ");
        pmLink.setAlignment(Element.ALIGN_LEFT);

        bigT1.addCell(pmLink);

        bigT1.addCell(pexTable);

/*****/ //ADD PROJECT LINK

        Font projectLinkFont = new Font(Font.FontFamily.HELVETICA, 8, Font.UNDERLINE | Font.BOLD, new BaseColor(95, 96, 108));
        Paragraph prLink = new Paragraph();
        prLink.setFont(projectLinkFont);
        prLink.add("\nAll of the Projects are available on GitHub: ");
        prLink.setAlignment(Element.ALIGN_LEFT);


        Font projectLinkFont1 = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(19, 150, 150));
        Paragraph prLink1 = new Paragraph();
        prLink1.setFont(projectLinkFont1);
        prLink1.add("\nhttps://github.com/abhimanyu96edu?tab=repositories");
        prLink1.setAlignment(Element.ALIGN_LEFT);

        bigT1.addCell(prLink);
        bigT1.addCell(prLink1);

/*****/

        //Position of Responsibilty

        bigT1.addCell(addEmptyLine(1));
        bigT1.addCell(addHeadTitle("Positions Of Responsibility"));
        //bigT2.addCell(addEmptyLine(1));

        PdfPTable porTable = new PdfPTable(1);//use autocomplete()
        porTable.setWidthPercentage(100.0f);
        porTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        Paragraph por1 = new Paragraph();
        por1.setFont(normalFont);
        por1.add("\n•  Speaker & Coordinator of Android Workshop, Mobile Quiz,\n   Enigma-2, Crptox at Galgotias College\n   (2015-2017)\n");
        por1.add("\n•  Team Leader and Event Coordinator of Japanese\n   Anime Cosplay event “Animatsuri”, Rendezvous IIT-Delhi\n   (2015)\n");
        por1.add("\n•  Coordinator/Member LOOP Programming Club, FRAG\n   Gaming Club, Extreme Club, Sports Club, Enthiran Club\n   (2014-2017)\n");
        por1.setAlignment(Element.ALIGN_LEFT);

        porTable.addCell(por1);

        porTable.completeRow();

        bigT1.addCell(porTable);

        //________________________________________________________________________
        //WORK EXPERIENCE

        bigT2.addCell(addHeadTitle("Experience"));
        //bigT2.addCell(addEmptyLine(1));


        PdfPTable wbar = new PdfPTable(1);
        wbar.setWidthPercentage(100.0f);
        wbar.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        Paragraph prWork1 = new Paragraph();
        prWork1.setFont(normalBoldFont);
        prWork1.add("\nSoftware Engineer Intern at SHOPVIEW");
        prWork1.setAlignment(Element.ALIGN_LEFT);

        Paragraph pr2 = new Paragraph();
        pr2.setFont(normalBoldFont);
        pr2.add("(JUN 2017 \u2014 AUGUST 2017)");
        pr2.setAlignment(Element.ALIGN_LEFT);

        Paragraph pr3 = new Paragraph();
        pr3.setFont(normalFont);
        pr3.add("•  Worked on Volley for GET and POST APIs, Database\n   Management using Active Android, Optical Character\n   Recognition and JSON response parsing\n");
        pr3.add("•  Built different ShopView Application Modules\n");
        pr3.setAlignment(Element.ALIGN_LEFT);

        Paragraph prWork4 = new Paragraph();
        prWork4.setFont(normalBoldFont);
        prWork4.add("\nTechnical Intern at PAPSWAP");
        prWork4.setAlignment(Element.ALIGN_LEFT);

        Paragraph pr5 = new Paragraph();
        pr5.setFont(normalBoldFont);
        pr5.add("(FEBRUARY 2017 \u2014 APRIL 2017)");
        pr5.setAlignment(Element.ALIGN_LEFT);

        Paragraph pr6 = new Paragraph();
        pr6.setFont(normalFont);
        pr6.add("•  Worked on PAPSWAP Android Application\n");
        pr6.add("•  Generated Content and User Traffic\n");
        pr6.add("•  Learnt Email & Social Marketing and Promotions\n");
        pr6.setAlignment(Element.ALIGN_LEFT);

        Paragraph prWork7 = new Paragraph();
        prWork7.setFont(normalBoldFont);
        prWork7.add("\nAndroid Development Trainee, Summer Training at HEWLETT PACKARD ENTERPRISE");
        prWork7.setAlignment(Element.ALIGN_LEFT);

        Paragraph pr8 = new Paragraph();
        pr8.setFont(normalBoldFont);
        pr8.add("(JUN 2016 \u2014 JULY 2016)");
        pr8.setAlignment(Element.ALIGN_LEFT);

        Paragraph pr9 = new Paragraph();
        pr9.setFont(normalFont);
        pr9.add("•  Learnt basics of Android Application Development\n");
        pr9.add("•  Awarded A+ Grade in Android Test at Hewlett Packard\n");
        pr9.setAlignment(Element.ALIGN_LEFT);

        Paragraph prWork10 = new Paragraph();
        prWork10.setFont(normalBoldFont);
        prWork10.add("\nTechnical Content Management Intern at ETHICALHACKX");
        prWork10.setAlignment(Element.ALIGN_LEFT);

        Paragraph pr11 = new Paragraph();
        pr11.setFont(normalBoldFont);
        pr11.add("(OCT 2015 \u2014 NOV 2015)");
        pr11.setAlignment(Element.ALIGN_LEFT);

        Paragraph pr12 = new Paragraph();
        pr12.setFont(normalFont);
        pr12.add("•  Learnt Website Creation & Management on Wordpress\n");
        pr12.add("•  Created Technical Content for the website\n  ");
        pr12.setAlignment(Element.ALIGN_LEFT);

        //Add table Rows

        wbar.addCell(prWork1);
        wbar.addCell(pr2);
        wbar.addCell(pr3);

        //wbar.addCell(new Phrase(" "));

        wbar.addCell(prWork4);
        wbar.addCell(pr5);
        wbar.addCell(pr6);

        wbar.addCell(prWork7);
        wbar.addCell(pr8);
        wbar.addCell(pr9);

        wbar.addCell(prWork10);
        wbar.addCell(pr11);
        wbar.addCell(pr12);

        //wbar.completeRow();
        bigT2.addCell(wbar);


        //________________________________________________________________________
        //EDUCATION
        //bigT2.addCell(addEmptyLine(1));
        bigT2.addCell(addHeadTitle("Educational Qualification"));
        //bigT2.addCell(addEmptyLine(1));


        PdfPTable ebar = new PdfPTable(1);
        ebar.setWidthPercentage(100.0f);
        ebar.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        //Generate Contents
        Paragraph e1 = new Paragraph();
        e1.setFont(normalFont);
        e1.add("\n\u2022  Bachelor's Degree in Computer Science and Engineering |\n   Dr. APJ Abdul Kalam Technical University (State Govt.) |\n   Galgotias College of Engineering & Technology\n ");
        e1.add("  Grad 2018 (expected) | 71.45% (till 6th sem)\n");
        e1.setAlignment(Element.ALIGN_LEFT);

        Paragraph e2 = new Paragraph();
        e2.setFont(normalFont);
        e2.add("\u2022  Secondary School from CBSE\n");
        e2.add("   Grad 2014 | 78.00% \n");
        e2.setAlignment(Element.ALIGN_LEFT);

        Paragraph e3 = new Paragraph();
        e3.setFont(normalFont);
        e3.add("\u2022  High School from CBSE\n");
        e3.add("   Grad 2012 | CGPA: 9.4/10 - A1\n  ");
        e3.setAlignment(Element.ALIGN_LEFT);

        ebar.addCell(e1);
        ebar.addCell(e2);
        ebar.addCell(e3);

        bigT2.addCell(ebar);

        //________________________________________________________________________
        //SKILLS

        //bigT2.addCell(addEmptyLine(1));
        bigT2.addCell(addHeadTitle("Skills and Competencies"));
        //bigT2.addCell(addEmptyLine(1));


        PdfPTable sbar = new PdfPTable(2);
        sbar.setWidthPercentage(100.0f);
        sbar.setWidths(new int[]{1, 1});
        sbar.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        Paragraph prSkills = new Paragraph();
        prSkills.setFont(normalFont);
        prSkills.add("\n•  Android App Development\n");
        prSkills.add("•  Java Programming\n");
        prSkills.add("•  C/C++ Programming\n");
        prSkills.add("•  HTML and CSS\n");
        prSkills.add("•  Firebase/Oracle 11g \n");
        prSkills.setAlignment(Element.ALIGN_LEFT);


        Paragraph prs1 = new Paragraph();
        prs1.setFont(normalFont);

        prs1.add("\n•  Data Structures\n");
        prs1.add("•  Operating System\n");
        prs1.add("•  DBMS\n");
        prs1.add("•  Volley\n");
        prs1.add("•  Android Studio\n");
        prs1.setAlignment(Element.ALIGN_LEFT);

        sbar.addCell(prSkills);
        sbar.addCell(prs1);

        sbar.completeRow();

        bigT2.addCell(sbar);

        //________________________________________________________________________
        //ACHIEVEMENTSjh

        //bigT2.addCell(addColorLineEmptyTable(1));
        //bigT2.addCell(addEmptyLine(1));
        bigT2.addCell(addHeadTitle("Achievements"));
        //bigT2.addCell(addEmptyLine(1));

        PdfPTable abar = new PdfPTable(1);
        abar.setWidthPercentage(100.0f);
        abar.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        Paragraph prAchievements = new Paragraph();
        prAchievements.setFont(normalFont);
        prAchievements.add("\n•  2017  WINNER, D/Encryption Event, Technical Fest\n");
        prAchievements.add("•  2017  Awarded BEST Interviewer at College Job Fair\n");
        prAchievements.add("•  2017  Semi-Finalist in Zonal Badminton Competition\n");
        prAchievements.add("•  2016  WINNER, CODE HUMMER event, Technical Fest\n");
        prAchievements.add("•  2015  2nd place, CODE HUMMER event, Technical Fest\n");
        prAchievements.setAlignment(Element.ALIGN_LEFT);
        abar.addCell(prAchievements);
        //}
        bigT2.addCell(abar);


        bigT.setSplitLate(false);
        bigT1.setSplitLate(false);
        bigT2.setSplitLate(false);

        bigT.addCell(bigT1);
        bigT.addCell(bigT2);

        document.add(bigT);

                   /*____________________*/
        document.newPage();
                   /*____________________*/
    }


//_____________________________________________________________________________________________//

    public Paragraph addEmptyLine(int lines) {

        int i;
        //Add Empty Line
        Paragraph emptyLines = new Paragraph();

        for (i = 0; i < lines; i++) {
            emptyLines.add(new Paragraph(" "));
        }
        return emptyLines;
    }

    public PdfPTable addColorLineEmptyTable(int lines) {
        int i;

        // Create Table into Document with
        PdfPTable myTable = new PdfPTable(lines);
        for (i = 0; i < lines; i++) {

            myTable.setWidthPercentage(100.0f);
            PdfPCell myCell = new PdfPCell(new Paragraph(""));
            myCell.setBorder(Rectangle.BOTTOM);
            myCell.setBorderColor(new BaseColor(2, 177, 178));

            // Add Cell into Table
            myTable.addCell(myCell);
        }
        return myTable;
    }

    public void requestPermissions() {
        Log.d("RESULT", "----------------------------------requestPermissions: ");
        ActivityCompat.requestPermissions(EPDFActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }


    private Paragraph addHeadTitle(String s) {

        BaseColor myColor2 = new BaseColor(2, 177, 178);//GREENY
        Font allHeadingFont = new Font(Font.FontFamily.HELVETICA, 14, Font.UNDERLINE | Font.BOLD, myColor2);
        Paragraph prHead = new Paragraph();
        prHead.setFont(allHeadingFont);
        prHead.add(s);

        prHead.setAlignment(Element.ALIGN_LEFT);

        return prHead;
    }

    public PdfPTable addColorLineEmptyTableGray(int lines) {
        int i;

        // Create Table into Document with
        PdfPTable myTable = new PdfPTable(lines);
        for (i = 0; i < lines; i++) {

            myTable.setWidthPercentage(100.0f);
            PdfPCell myCell = new PdfPCell(new Paragraph(""));
            myCell.setBorder(Rectangle.BOTTOM);
            myCell.setBorderColor(new BaseColor(95, 96, 108));

            // Add Cell into Table
            myTable.addCell(myCell);
        }
        return myTable;
    }

}
//ADDING SPACING BETWEEN LINES: paragraph.setLeading(fixed, multiplied);
//table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);