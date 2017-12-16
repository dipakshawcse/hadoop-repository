package hive.udf;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.hive.ql.exec.UDF;

public class XmlEmptyParse extends UDF{
	public String evaluate(String xmlData){
		String replaceValue = "NULL";
		Map<Integer, StringBuffer> xmlMap = new HashMap<Integer, StringBuffer>();
		xmlMap.put(1, new StringBuffer(xmlData));
		while (xmlMap.get(1).toString().contains("/>")) {
			int index = xmlMap.get(1).toString().indexOf("/>");
			String sm = xmlMap.get(1).toString().substring(0, index);
			int firstIndex = sm.lastIndexOf("<");
			String temp = xmlMap
					.get(1)
					.toString()
					.replace(
							xmlMap.get(1).toString()
									.substring(firstIndex, index + 2),
							"<"
									+ xmlMap.get(1).toString()
											.substring(firstIndex + 1, index)
									+ ">"
									+ replaceValue
									+ "</"
									+ xmlMap.get(1).toString()
											.substring(firstIndex + 1, index)
									+ ">");
			xmlMap.put(1, new StringBuffer(temp));
		}
		return xmlMap.get(1).toString();
	}
}
