/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _42_Utility;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
//import org.

/**
 *
 * @author 真也
 */
public class xml_util {
	private static SAXReader m_xml_reader = new SAXReader();
	
	public static ArrayList<String> get_node_by_xpath(
		String file_path,	//I		ファイルパス　※例："C:\\tmp\\member.xml"
		String s_xpath		//I		XPath　※例："/persons/person/job"
	)
	{
		ArrayList<String> out_arry = new ArrayList<>();
//		SAXReader reader = m_xml_reader;
		try {
			SAXReader reader = new SAXReader();
		
			Document document = reader.read(file_path);
//			List nodes = document.selectNodes(s_xpath);
			List nodes = document.selectNodes("/persons/person/job");

			for(Iterator i = nodes.iterator(); i.hasNext();) {
				Node node = (Node) i.next();
//				System.out.println("job:" + node.getText());
				out_arry.add(node.getText());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return out_arry;
	}
}
