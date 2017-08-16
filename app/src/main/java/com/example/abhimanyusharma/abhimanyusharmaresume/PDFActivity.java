package com.example.abhimanyusharma.abhimanyusharmaresume;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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

public class PDFActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        tv = (TextView) findViewById(R.id.tv);

        requestPermissions();

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/A_PDF");
        myDir.mkdirs();

        String FILE = Environment.getExternalStorageDirectory().toString() + "/A_PDF/Abhimanyu_Sharma_Resume.pdf";

// Create New Blank Document
        Document document = new Document(PageSize.A4);

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
        tH.setWidthPercentage(100.0f);
        tH.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        Paragraph row1 = new Paragraph();
        row1.setFont(new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD, myColor1));
        row1.setAlignment(Element.ALIGN_CENTER);
        row1.setLeading(15, 0);
        row1.add("ABHIMANYU SHARMA");
        //row1.add(u_name);

        Paragraph row2 = new Paragraph();
        row2.setFont(new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, myColor2));
        row2.setAlignment(Element.ALIGN_CENTER);
        row2.add("To pursue an advanced development career oriented towards Mobile Application and Software Development and to contribute efficiently " +
                "my skills and abilities for the growth of the organization along with enhancing my skills, knowledge and abilities by practical application.");

        PdfPCell row_1 = new PdfPCell(row1);
        PdfPCell row_2 = new PdfPCell(row2);

        row_1.setBorder(Rectangle.NO_BORDER);
        row_2.setBorder(Rectangle.NO_BORDER);

        //Add table Rows
        PdfPTable th = new PdfPTable(1);
        th.setWidthPercentage(100.0f);

        th.addCell(row_1);
        th.addCell(row_2);

        InputStream inputStream = getAssets().open("logo.png");
        //PUT THIS IMAGE IN ASSETS FOLDER OTHERWISE ERROR MIGHT APPEAR LIKE:  ExceptionConverter: java.io.IOException: The document has no pages.

        Bitmap bmp = BitmapFactory.decodeStream(inputStream);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        //picture.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Image logoImage = Image.getInstance(stream.toByteArray());
        logoImage.scalePercent(60);
        logoImage.scaleAbsolute(80, 80);


        tH.setWidths(new int[]{7, 1, 2});
        tH.addCell(th);
        tH.addCell(new Phrase(" "));
        tH.addCell(logoImage);

        document.add(tH);

        document.add(addEmptyLine(1));
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
        emailBar.setFont(new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, myColor1));
        emailBar.setAlignment(Element.ALIGN_CENTER);
        emailBar.add("abhimanyusharma96.edu@gmail.com");

        Paragraph numberBar = new Paragraph();
        numberBar.setFont(new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, myColor1));
        numberBar.setAlignment(Element.ALIGN_CENTER);
        numberBar.add("+91-7042218117");

        Paragraph portfolioBar = new Paragraph();
        portfolioBar.setFont(new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, myColor1));
        portfolioBar.setAlignment(Element.ALIGN_CENTER);
        portfolioBar.add("abhimanyu96edu.github.io");

        Paragraph addressBar = new Paragraph();
        addressBar.setFont(new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, myColor1));
        addressBar.setAlignment(Element.ALIGN_CENTER);
        addressBar.add("Room-204, Boys Hostel, Galgotias campus 1, Greater Noida");

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
        tbar.setWidths(new int[]{4, 2, 3, 5});

        tbar.addCell(c1);
        tbar.addCell(c2);
        tbar.addCell(c3);
        tbar.addCell(c4);

        document.add(tbar);

        document.add(addColorLineEmptyTableGray(1));
        document.add(addEmptyLine(1));

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
        //WORK EXPERIENCE

        bigT1.addCell(addHeadTitle("Work Experience"));
        bigT1.addCell(addEmptyLine(1));


        PdfPTable wbar = new PdfPTable(1);
        wbar.setWidthPercentage(100.0f);
        wbar.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        Paragraph prWork1 = new Paragraph();
        prWork1.setFont(normalBoldFont);
        prWork1.add("1. Chief Technical Officer, Working at Resume Inc.");
        prWork1.setAlignment(Element.ALIGN_LEFT);

        Paragraph pr2 = new Paragraph();
        pr2.setFont(smallFont);
        pr2.add("(JUN 2017 \u2014 CURRENT)");
        pr2.setAlignment(Element.ALIGN_LEFT);

        Paragraph pr3 = new Paragraph();
        pr3.setFont(normalFont);
        pr3.add("Worked on Scientific and Technical issues of Resume Inc.");
        pr3.setAlignment(Element.ALIGN_LEFT);

        Paragraph prWork4 = new Paragraph();
        prWork4.setFont(normalBoldFont);
        prWork4.add("2. Chief Technical Officer, Working at Resume Inc.");
        prWork4.setAlignment(Element.ALIGN_LEFT);

        Paragraph pr5 = new Paragraph();
        pr5.setFont(smallFont);
        pr5.add("(JUN 2017 \u2014 CURRENT)");
        pr5.setAlignment(Element.ALIGN_LEFT);

        Paragraph pr6 = new Paragraph();
        pr6.setFont(normalFont);
        pr6.add("Worked on Scientific and Technical issues of Resume Inc.");
        pr6.setAlignment(Element.ALIGN_LEFT);

        Paragraph prWork7 = new Paragraph();
        prWork7.setFont(normalBoldFont);
        prWork7.add("3. Chief Technical Officer, Working at Resume Inc.");
        prWork7.setAlignment(Element.ALIGN_LEFT);

        Paragraph pr8 = new Paragraph();
        pr8.setFont(smallFont);
        pr8.add("(JUN 2017 \u2014 CURRENT)");
        pr8.setAlignment(Element.ALIGN_LEFT);

        Paragraph pr9 = new Paragraph();
        pr9.setFont(normalFont);
        pr9.add("Worked on Scientific and Technical issues of Resume Inc.");
        pr9.setAlignment(Element.ALIGN_LEFT);

        Paragraph prWork10 = new Paragraph();
        prWork10.setFont(normalBoldFont);
        prWork10.add("4. Chief Technical Officer, Working at Resume Inc.");
        prWork10.setAlignment(Element.ALIGN_LEFT);

        Paragraph pr11 = new Paragraph();
        pr11.setFont(smallFont);
        pr11.add("(JUN 2017 \u2014 CURRENT)");
        pr11.setAlignment(Element.ALIGN_LEFT);

        Paragraph pr12 = new Paragraph();
        pr12.setFont(normalFont);
        pr12.add("Worked on Scientific and Technical issues of Resume Inc.");
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
        bigT1.addCell(wbar);

        //________________________________________________________________________
        //PROJECT EXPERIENCE

        //bigT1.addCell(addColorLineEmptyTable(1));
        bigT1.addCell(addEmptyLine(1));
        bigT1.addCell(addHeadTitle("Project Experience"));
        bigT1.addCell(addEmptyLine(1));


        //Add table Rows
        PdfPTable pebar = new PdfPTable(1);
        pebar.setWidthPercentage(100.0f);
        pebar.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        Paragraph pe1 = new Paragraph();
        pe1.setFont(normalBoldFont);
        pe1.add("1. Abhimanyu Sharma Resume Coded    " + "(JULY - 2017)");
        pe1.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe2 = new Paragraph();
        pe2.setFont(smallFont);
        pe2.add("https://github.com/abhimanyu96edu/AbhimanyuSharmaResume");
        pe2.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe3 = new Paragraph();
        pe3.setFont(normalFont);
        pe3.add("Worked on Scientific and Technical issues of Resume Inc. and created Resume Production Android App");
        pe3.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe4 = new Paragraph();
        pe4.setFont(normalBoldFont);
        pe4.add("2. Resume Inc.    " + "(JULY - 2017)");
        pe4.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe5 = new Paragraph();
        pe5.setFont(smallFont);
        pe5.add("https://github.com/abhimanyu96edu/ResumeInc.");
        pe5.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe6 = new Paragraph();
        pe6.setFont(normalFont);
        pe6.add("Worked on Scientific and Technical issues of Resume Inc. and created Resume Production Android App");
        pe6.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe7 = new Paragraph();
        pe7.setFont(normalBoldFont);
        pe7.add("3. Abhimanyu Sharma Resume    " + "(JULY - 2017)");
        pe7.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe8 = new Paragraph();
        pe8.setFont(smallFont);
        pe8.add("https://github.com/abhimanyu96edu/AbhimanyuSharmaResume");
        pe8.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe9 = new Paragraph();
        pe9.setFont(normalFont);
        pe9.add("Worked on Scientific and Technical issues of Resume Inc. and created Resume Production Android App");
        pe9.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe10 = new Paragraph();
        pe10.setFont(normalBoldFont);
        pe10.add("4. Resume Inc.    " + "(JULY - 2017)");
        pe10.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe11 = new Paragraph();
        pe11.setFont(smallFont);
        pe11.add("https://github.com/abhimanyu96edu/ResumeInc.");
        pe11.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe12 = new Paragraph();
        pe12.setFont(normalFont);
        pe12.add("Worked on Scientific and Technical issues of Resume Inc. and created Resume Production Android App");
        pe12.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe13 = new Paragraph();
        pe13.setFont(normalBoldFont);
        pe13.add("5. Resume Inc.    " + "(JULY - 2017)");
        pe13.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe14 = new Paragraph();
        pe14.setFont(smallFont);
        pe14.add("https://github.com/abhimanyu96edu/ResumeInc.");
        pe14.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe15 = new Paragraph();
        pe15.setFont(normalFont);
        pe15.add("Worked on Scientific and Technical issues of Resume Inc. and created Resume Production Android App");
        pe15.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe16 = new Paragraph();
        pe16.setFont(normalBoldFont);
        pe16.add("6. Resume Inc.    " + "(JULY - 2017)");
        pe16.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe17 = new Paragraph();
        pe17.setFont(smallFont);
        pe17.add("https://github.com/abhimanyu96edu/ResumeInc.");
        pe17.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe18 = new Paragraph();
        pe18.setFont(normalFont);
        pe18.add("Worked on Scientific and Technical issues of Resume Inc. and created Resume Production Android App");
        pe18.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe19 = new Paragraph();
        pe19.setFont(normalBoldFont);
        pe19.add("7. Resume Inc.    " + "(JULY - 2017)");
        pe19.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe20 = new Paragraph();
        pe20.setFont(smallFont);
        pe20.add("https://github.com/abhimanyu96edu/ResumeInc.");
        pe20.setAlignment(Element.ALIGN_LEFT);

        Paragraph pe21 = new Paragraph();
        pe21.setFont(normalFont);
        pe21.add("Worked on Scientific and Technical issues of Resume Inc. and created Resume Production Android App");
        pe21.setAlignment(Element.ALIGN_LEFT);

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
        pebar.addCell(pe11);
        pebar.addCell(pe12);

        pebar.addCell(pe13);
        pebar.addCell(pe14);
        pebar.addCell(pe15);

        pebar.addCell(pe16);
        pebar.addCell(pe17);
        pebar.addCell(pe18);

        pebar.addCell(pe19);
        pebar.addCell(pe20);
        pebar.addCell(pe21);


        bigT1.addCell(pebar);



        //________________________________________________________________________
        //EDUCATION

        bigT2.addCell(addHeadTitle("Education Qualification"));
        bigT2.addCell(addEmptyLine(1));


        PdfPTable ebar = new PdfPTable(1);
        ebar.setWidthPercentage(100.0f);
        ebar.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        //Generate Contents
        Paragraph e1 = new Paragraph();
        e1.setFont(normalFont);
        e1.add("\u2022 B.Tech from AKTU University (State Government)\n");
        e1.add(" (2018)" + "\n");
        e1.add(" Scored 73% (till 6th sem)\n");
        e1.setAlignment(Element.ALIGN_LEFT);

        Paragraph e2 = new Paragraph();
        e2.setFont(normalFont);
        e2.add("\u2022 Secondary School from CBSE\n");
        e2.add(" (2014)" + "\n");
        e2.add(" Scored 78% \n");
        e2.setAlignment(Element.ALIGN_LEFT);

        Paragraph e3 = new Paragraph();
        e3.setFont(normalFont);
        e3.add("\u2022 High School from CBSE\n");
        e3.add(" (2012)" + "\n");
        e3.add(" Scored 9.4 - A1 (CGPA)\n");
        e3.setAlignment(Element.ALIGN_LEFT);

        ebar.addCell(e1);
        ebar.addCell(e2);
        ebar.addCell(e3);

        bigT2.addCell(ebar);

        //________________________________________________________________________
        //SKILLS

        bigT2.addCell(addEmptyLine(1));
        bigT2.addCell(addHeadTitle("Skills and Competences"));
        bigT2.addCell(addEmptyLine(1));


        PdfPTable sbar = new PdfPTable(1);
        sbar.setWidthPercentage(100.0f);
        sbar.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        Paragraph prSkills = new Paragraph();
        prSkills.setFont(normalFont);
        prSkills.add("•  Android App Development\n");
        prSkills.add("•  Java Programming\n");
        prSkills.add("•  Algorithms & Software Development\n");
        prSkills.add("•  C/C++ Programming");
        prSkills.add("•  Android App Development\n");
        prSkills.add("•  Java Programming\n");
        prSkills.add("•  Algorithms & Software Development\n");
        prSkills.add("•  C/C++ Programming");
        prSkills.add("•  Android App Development\n");
        prSkills.add("•  Java Programming\n");
        prSkills.add("•  Algorithms & Software Development\n");
        prSkills.add("•  C/C++ Programming");
        prSkills.setAlignment(Element.ALIGN_LEFT);

        sbar.addCell(prSkills);

        //sbar.completeRow();
        bigT2.addCell(sbar);

        //________________________________________________________________________
        //ACHIEVEMENTSjh

        //bigT2.addCell(addColorLineEmptyTable(1));
        bigT2.addCell(addEmptyLine(1));
        bigT2.addCell(addHeadTitle("Achievements"));
        bigT2.addCell(addEmptyLine(1));

        PdfPTable abar = new PdfPTable(1);
        abar.setWidthPercentage(100.0f);
        abar.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        Paragraph prAchievements = new Paragraph();
        prAchievements.setFont(normalFont);
        prAchievements.add("\u2022   CTO at Resume Inc.\n");
        prAchievements.add("\u2022   Full Stack Developer at Resume Inc.\n");
        prAchievements.add("\u2022   CTO at Resume Inc.\n");
        prAchievements.add("\u2022   Full Stack Developer at Resume Inc.\n");
        prAchievements.add("\u2022   CTO at Resume Inc.\n");
        prAchievements.add("\u2022   Full Stack Developer at Resume Inc.\n");
        prAchievements.setAlignment(Element.ALIGN_LEFT);
        abar.addCell(prAchievements);
        //}
        bigT2.addCell(abar);


        //Position of Responsibilty

        bigT2.addCell(addEmptyLine(1));
        bigT2.addCell(addHeadTitle("Position Of Responsibilities"));
        bigT2.addCell(addEmptyLine(1));

        PdfPTable porTable = new PdfPTable(2);//use autocomplete()
        porTable.setWidthPercentage(100.0f);
        porTable.setWidths(new int[]{1, 1});
        porTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        Paragraph por1 = new Paragraph();
        por1.setFont(normalFont);
        por1.add("•  Chief Technical Officer \n");
        por1.add("•  Chief Executive Officer \n");
        por1.add("•  Management Head \n");
        por1.add("•  Full Stack Development Manager \n");
        por1.add("•  Marketing & Promotions Manager \n");
        por1.add("•  Chief Technical Officer \n");
        por1.add("•  Chief Executive Officer \n");
        por1.add("•  Management Head \n");
        por1.add("•  Full Stack Development Manager \n");
        por1.add("•  Marketing & Promotions Manager \n");
        por1.add("•  Chief Technical Officer \n");
        por1.add("•  Chief Executive Officer \n");
        por1.add("•  Full Stack Development Manager \n");
        por1.add("•  Marketing & Promotions Manager \n");
        por1.setAlignment(Element.ALIGN_LEFT);

        Paragraph por2 = new Paragraph();
        por2.setFont(normalFont);
        por2.add("•  Chief Technical Officer \n");
        por2.add("•  Chief Executive Officer \n");
        por2.add("•  Management Head \n");
        por2.add("•  Full Stack Development Manager \n");
        por2.add("•  Marketing & Promotions Manager \n");
        por2.setAlignment(Element.ALIGN_LEFT);

        porTable.addCell(por1);
        porTable.addCell(por2);

        porTable.completeRow();

        bigT2.addCell(porTable);

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
        ActivityCompat.requestPermissions(PDFActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE,
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