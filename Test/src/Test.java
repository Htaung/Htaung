import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {
	static String initLogger = "private static Logger logger = Logger.getLogger(CLASSNAME.class);";
	
	static String initVar1 = "long startTime;";
	static String initVar2 = "long endTime;";
	
	static String initJSPUtil = "if(JspUtil.checkLogin(request)){\n" 
			+ "\t\tJspUtil.printUserAndTenantInfo(request);\n" 
			+ "\t}";
	
	static String functionName = "isThemeParkProduct";
	static String className = "NewShoppingCartDao";
	
	

	static String startTime = "\tlong startTime = System.currentTimeMillis();";
	//static String debugstartTime = "debug(\"Start Time of FUNCTIONNAME function in CLASSNAME class :: \"+startTime);";
	static String debugstartTime = "\tlogger.debug(\"Start Time of FUNCTIONNAME function in CLASSNAME class :: \"+startTime);";
	
	
	static String endTime = "long endTime = System.currentTimeMillis();";
	//static String debugEndTIme = "debug(\"End Time of FUNCTIONNAME function in CLASSNAME class :: \"+endTime);";
	//static String debugCollapseTime = "debug(\"FUNCTIONNAME function in CLASSNAME timing. completeloadTime: \" + (endTime - startTime));";
	
	static String debugEndTIme = "logger.debug(\"End Time of FUNCTIONNAME function in CLASSNAME class :: \"+endTime);";
	static String debugCollapseTime = "logger.debug(\"FUNCTIONNAME function in CLASSNAME timing. completeloadTime: \" + (endTime - startTime));";
	
	public static void main(String[] args) {
		//constructLog();
		testJavaScriptEngine();
		/*try {
			
			String s = "//STIX/SisticFiles/SISTIC/16-08-2016/1471342183756/2199";
		
			if(s.startsWith("//")){
				System.out.println("s contain ///==>" + s);
				s =  s.substring(1);
			}
			
			System.out.println("aftter==>" + s);
			
			//constructLog();
			//testURL();
			//processPage("http://www.ema.europa.eu");
			//processPage("http://www.mit.edu");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public static void constructLog(){
		debugstartTime = debugstartTime.replace("FUNCTIONNAME",functionName);
		debugstartTime = debugstartTime.replace("CLASSNAME",className);
		
		
		debugEndTIme = debugEndTIme.replace("FUNCTIONNAME",functionName);
		debugEndTIme = debugEndTIme.replace("CLASSNAME",className);
		
		debugCollapseTime = debugCollapseTime.replace("FUNCTIONNAME",functionName);
		debugCollapseTime = debugCollapseTime.replace("CLASSNAME",className);
		
		initLogger = initLogger.replace("CLASSNAME",className);
		
		System.out.println(initLogger);
		
		System.out.println(initVar1);
		System.out.println(initVar2);
		
		System.out.println(initJSPUtil);
		
		
		System.out.println("\n" + startTime);
		System.out.println(debugstartTime);
		
		System.out.println("\n" + endTime);
		System.out.println(debugEndTIme);
		
		System.out.println(debugCollapseTime);
	}
	
	public static void testURL() throws Exception{
		URL url = new URL("http://www.ema.europa.eu/ema/index.jsp?curl=WEB-INF/web.xml");
		System.out.println("url.getProtocol()" + url.getProtocol());
		System.out.println("url.getHost()" + url.getHost() );
		System.out.println("url.getPort()" + url.getPort());
		System.out.println("url.toString();" + url);
		
		URLConnection urlCon = url.openConnection();		
		
		System.out.println("Content Length==>"+ urlCon.getContentLength());
		System.out.println("Content Type==>" + urlCon.getContentType());
		System.out.println("Content==>" + urlCon.getContent());
		//urlCon.setRequestProperty("curl", "pages/news_and_events/news/2017/09/news_detail_002810.jsp&mid=WC0b01ac058004d5c1");
		Map<String,List<String>>  ss = urlCon.getRequestProperties();
		Set<String> keys = ss.keySet();
		for(String s: keys){
			System.out.println("key==>" + s);
			System.out.println("value==>" + ss.get(s));
		}
		
		InputStream is =  urlCon.getInputStream();
		int b;
		b = is.read();
		while(b != -1){
			System.out.print((char) b);
			b = is.read();
		}
		
	}
	
	public static void processPage(String URL) throws IOException{
			//get useful information
			Document doc = Jsoup.connect("http://www.ema.europa.eu/").get();
			//Document doc = Jsoup.connect("http://www.mit.edu/").get();
			//Document doc = Jsoup.connect(URL).get();
			System.out.println("doc.text==>" + doc.text());
			System.out.println(URL);
			if(doc.text().contains("research")){
				System.out.println(URL);
			}
 
			//get all links and recursively call the processPage method
			Elements questions = doc.select("a[href]");
			for(Element link: questions){
				if(link.attr("href").contains("mit.edu"))
					processPage(link.attr("abs:href"));
			}
		}
	
	public static void testJavaScriptEngine(){
		String arg = "hallo'); var fImport = new JavaImporter(java.io.File); with(fImport) { var f = new File('TestNewFile1'); f.createNewFile(); } //";
		try {
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("JavaScript");
			System.out.println(arg);
			engine.eval("print('"+ arg + "')");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void testJavaScriptEngine1(){
		String arg = "hallo'); var fImport = new JavaImporter(java.io.File); with(fImport) { var f = new File('TestNewFile1'); f.createNewFile(); } //";
		arg = "../uploads/evil";
		
		try {
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("JavaScript");
			engine.eval(new java.io.FileReader("resources/" + arg + ".js"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

