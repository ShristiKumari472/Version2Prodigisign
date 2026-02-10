package FlowDSc;

import java.util.ArrayList;
import java.util.List;

import BaseDSc.common;

public class MasterClassDSC extends common {

	
	// ✅ Main class
    public static void main(String[] args) throws Exception {
        MasterClassDSC obj = new MasterClassDSC();
        obj.runFlow();
    }

    public void runFlow() throws Exception {

      
        setupChrome();
        System.out.println("DFGT");
        openBrowser("https://v2-uat-applydsc.prodigisign.org/buydsc");
        // DGFT value for apply Page 
        dscPage("3", "5", "2", "2");
        //Complete floow
        //loginWithMobile();
        login();
        panPage();
        gstModule("DGFT");
        emailVerification();
        addressPage();
        /*
        // 🔹 All attachment files 
        List<String> attachments = new ArrayList<>();
        attachments.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        attachments.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        attachments.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        attachments.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        attachments.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        attachments.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        attachments.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        attachments.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        uploadDocumentsUntilDone(attachments);
        */
        List<String> files = new ArrayList<>();
        
        files.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        
        uploadDocuments(files);
        
        
        SkipVideo();
        //videoUpload();      
        finalSubmitUsingPaylater();    
        System.out.println("DGFT DSC process completed successfully.");
        
        System.out.println("Organzation Signing");
        openBrowser("https://v2-uat-applydsc.prodigisign.org/buydsc");
        dscPage("3", "1", "2", "2");
        //loginWithMobile();
        login();
        panPage();
        gstModule("ORG");
        emailVerification();
        addressPage();
        /*
        // 🔹 All attachment files (order matters)
        attachments.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        attachments.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        attachments.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        // 🔹 Upload everything (single call)
        uploadDocumentsUntilDone(attachments);
        */
        files.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        files.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        files.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        files.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        files.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        files.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        files.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        
        
        uploadDocuments(files);
        SkipVideo();
        finalSubmitUsingPaylater();
        System.out.println(" Organzation Signing DSC process completed successfully.");
        
        
        System.out.println("Foreign Organzation Signing");
        openBrowser("https://v2-uat-applydsc.prodigisign.org/buydsc");
        dscPage("4", "1", "2", "2");
        //loginWithMobile();
        login();
        panPage();
        gstModule("ORG");
        emailVerification();
        addressPage();
        /*
        // 🔹 All attachment files (order matters)
        attachments.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        attachments.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        attachments.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        // 🔹 Upload everything (single call)
        uploadDocumentsUntilDone(attachments);
        */
        
    files.add("C:\\Users\\shris\\Downloads\\printpg.pdf");
        
        uploadDocuments(files);
        SkipVideo();
        finalSubmitUsingPaylater();
        System.out.println(" Foreign Organzation Signing DSC process completed successfully.");
        
        
    
        
        
        
        
        
        
        
        
        
       
    }
}