import java.net.*;
import java.io.*;
import java.util.Scanner; 

public class Email {
    public static void main(String[] args) throws MalformedURLException, IOException{
    	//System.getProperties().put("proxySet", "true");
		//System.getProperties().put("proxyHost", "152.78.128.51");
		//System.getProperties().put("proxyPort", "3128");
        //URL url = new URL("https://secure.ecs.soton.ac.uk/people/jd8g19");


    	System.out.println("Enter a user name: ")
    	Scanner in = new Scanner(System.in);
    	//String input = "dem";
    	String input = in.nextLine();


        String urlStr = String.join("https://www.ecs.soton.ac.uk/people/",input);
    	URL url = new URL(urlStr);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        String inputLine;
        int firstTag = 0;
        int secondTag = 0;
        while ((inputLine = reader.readLine()) != null) {
        	if(inputLine.indexOf("title") != -1) {
        		firstTag = inputLine.indexOf("title");
    			secondTag = inputLine.indexOf("title", firstTag +1);
    			break;
        	}
        }
        if (firstTag != 0 && secondTag != 0){
        	String title = inputLine;
        	title = title.trim().replace("title>","");
        	title = title.replace("<","");
        	title = title.replace("/","");
        	//System.out.println(title.indexOf("|"));
        	System.out.println(title.substring(0,title.indexOf("|")));

        	//String partsOfTitle[] = title.split(" | ");
        	//System.out.println(partsOfTitle[0]);
        	//for (String temp: partsOfTitle){
        	//	if (temp == "|") {
        	//		System.out.println("BREAK");
        	//		break;
        	//	}
			//   System.out.println(temp);
			//}
        	//System.out.println(firstTag);
        	//System.out.println(secondTag);

        }
    
        reader.close();
    }
}
