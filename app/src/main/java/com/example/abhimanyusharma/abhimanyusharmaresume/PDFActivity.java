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
        BaseColor myColor2 = new BaseColor(95, 96, 108);//GRAYY

        //Font allHeadingFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, myColor1);
        //Font headingFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD | Font.UNDERLINE, BaseColor.GRAY);
        //Font specialFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.DARK_GRAY);
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
        tbar.setWidths(new int[] {4, 2, 3, 5});

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

        //java.util.List<WorkExperienceData> list3 = WorkExperienceData.getWorkExperienceValue(u_userEmail);

        //for (int i = 0; i < list3.size(); i++)
        //{
            //WorkExperienceData wed = list3.get(i);

            //String status = "";
            //if (wed.getW_radioGroup().equals("Current Employee")){
            //    status = "Working";
            //}
            //else{
            //    status = "Worked";
            //}

            Paragraph prWork = new Paragraph();
            prWork.setFont(normalBoldFont);
            prWork.add("1. Chief Technical Officer, Working at Resume Inc.");
            //prWork.add("\u2022  " + u_w_designation +  u_w_organization);
            prWork.setAlignment(Element.ALIGN_LEFT);
            //document.add(prWork);

            Paragraph pr2 = new Paragraph();
            pr2.setFont(smallFont);
            //pe2.add("    " + u_w_start_date + " \u2014 " + u_w_end_date);
            pr2.add("(JUN 2017 \u2014 CURRENT)");
            pr2.setAlignment(Element.ALIGN_LEFT);
            //document.add(pr2);

            Paragraph pr3 = new Paragraph();
            pr3.setFont(normalFont);
            //pr3.add("    " + u_w_description);
            pr3.add("Worked on Scientific and Technical issues of Resume Inc.");
            pr3.setAlignment(Element.ALIGN_LEFT);
            //document.add(pe3);

            //Add table Rows

            wbar.addCell(prWork);
            wbar.addCell(pr2);
            wbar.addCell(pr3);
        //}

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

        //java.util.List<ProjectExperienceData> list2 = ProjectExperienceData.getProjectExperienceValue(u_userEmail);

        //for (int i = 0; i < list2.size(); i++) {
            //ProjectExperienceData ped = list2.get(i);

            Paragraph pe1 = new Paragraph();
            pe1.setFont(normalBoldFont);
            pe1.add("1. Resume Inc.    " + "(JULY - 2017)");
            pe1.setAlignment(Element.ALIGN_LEFT);

            Paragraph pe2 = new Paragraph();
            pe2.setFont(smallFont);
            pe2.add("https://github.com/abhimanyu96edu/AbhimanyuSharmaResume");
            pe2.setAlignment(Element.ALIGN_LEFT);

            Paragraph pe3 = new Paragraph();
            pe3.setFont(normalFont);
            pe3.add("Worked on Scientific and Technical issues of Resume Inc. and created Resume Production Android App");
            pe3.setAlignment(Element.ALIGN_LEFT);
            //document.add(pe2);

            pebar.addCell(pe1);
            pebar.addCell(pe2);
            pebar.addCell(pe3);
        //}
        //pebar.completeRow();
        bigT1.addCell(pebar);

        bigT1.addCell(addEmptyLine(1));
        bigT1.addCell(addHeadTitle("Languages"));
        bigT1.addCell(addEmptyLine(1));

        //java.util.List<LanguageData> listL = LanguageData.getLanguageValue(u_userEmail);
        //list.setSymbolIndent(42);

        PdfPTable languageTable = new PdfPTable(2);//use autocomplete()
        languageTable.setWidthPercentage(100);
        languageTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        //for (int i = 0; i < listL.size(); i++) {
            //LanguageData ld = listL.get(i);

            Paragraph prLanguage = new Paragraph();
            prLanguage.setFont(normalFont);
            prLanguage.add("•  French \n");
            prLanguage.add("•  German \n");
            prLanguage.add("•  Spanish \n");
            prLanguage.add("•  Spanish \n");
            prLanguage.setAlignment(Element.ALIGN_LEFT);

            languageTable.addCell(prLanguage);
            //document.add(prSkills);
        //}
        languageTable.completeRow();
        bigT1.addCell(languageTable);

        //________________________________________________________________________
        //EDUCATION

        //bigT2.addCell(addColorLineEmptyTable(1));
        bigT2.addCell(addHeadTitle("Education Qualification"));
        bigT2.addCell(addEmptyLine(1));


        PdfPTable ebar = new PdfPTable(1);
        ebar.setWidthPercentage(100.0f);
        ebar.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        //java.util.List<EducationData> list1 = EducationData.getEducationValue(u_userEmail);

        //for (int i = 0; i < list1.size(); i++) {
            //EducationData ed = list1.get(i);

            //Generate Contents
            Paragraph e1 = new Paragraph();
            e1.setFont(normalFont);
            e1.add("\u2022 B.Tech from AKTU University (State Government)\n");
            e1.add( " (2018)" + "\n");
            e1.add(" Scored 73% (till 6th sem)\n");
            e1.setAlignment(Element.ALIGN_LEFT);

            ebar.addCell(e1);
        //}
        //ebar.completeRow();
        bigT2.addCell(ebar);

        //________________________________________________________________________
        //SKILLS

        //bigT2.addCell(addColorLineEmptyTable(1));
        bigT2.addCell(addEmptyLine(1));
        bigT2.addCell(addHeadTitle("Skills and Competences"));
        bigT2.addCell(addEmptyLine(1));


        PdfPTable sbar = new PdfPTable(1);
        sbar.setWidthPercentage(100.0f);
        sbar.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        //java.util.List<TechnicalSkillsData> list4 = TechnicalSkillsData.getTs_descriptionValue(u_userEmail);

        //for (int i = 0; i < list4.size(); i++) {
            //TechnicalSkillsData tsd = list4.get(i);

            Paragraph prSkills = new Paragraph();
            prSkills.setFont(normalFont);
            prSkills.add("•  Android App Development\n");
            prSkills.add("•  Java Programming\n");
            prSkills.add("•  Algorithms & Software Development\n");
            prSkills.add("•  C/C++ Programming");
            prSkills.setAlignment(Element.ALIGN_LEFT);

            sbar.addCell(prSkills);
        //}
        //sbar.completeRow();
        bigT2.addCell(sbar);

        //________________________________________________________________________
        //ACHIEVEMENTSjh

        //bigT2.addCell(addColorLineEmptyTable(1));
        bigT2.addCell(addEmptyLine(1));
        bigT2.addCell(addHeadTitle("Achievements"));
        bigT2.addCell(addEmptyLine(1));

        PdfPTable abar = new PdfPTable(1);
        abar.setWidthPercentage(100);
        abar.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        //java.util.List<AchievementData> list5 = AchievementData.getAchievementValue(u_userEmail);


        //for (int i = 0; i < list5.size(); i++) {
            //AchievementData ad = list5.get(i);

            Paragraph prAchievements = new Paragraph();
            prAchievements.setFont(normalFont);
            prAchievements.add("\u2022   CTO at Resume Inc.\n");
            prAchievements.add("\u2022   Full Stack Developer at Resume Inc.\n");
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
        ActivityCompat.requestPermissions(PDFActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }



    private Paragraph addHeadTitle(String s) {

        BaseColor myColor2 = new BaseColor(2, 177, 178);//GREENY
        Font allHeadingFont = new Font(Font.FontFamily.HELVETICA, 14, Font.UNDERLINE| Font.BOLD, myColor2);
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