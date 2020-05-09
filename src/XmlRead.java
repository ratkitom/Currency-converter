import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.net.URL;

public class XmlRead {

    private String[] currency= {"usd","eur","gbp","chf","pln"};
    private Double[]  currencyvalue=new Double[5];

    XmlRead()throws Exception{
        for(int i=0;i<4;i++){

            getrate("http://api.nbp.pl/api/exchangerates/rates/a/"+currency[i]+"/?format=xml",i);



        }
        currencyvalue[4]=1.0;

    }



    public Double getcurrencyvalue(int i) {

        return currencyvalue[i];

    }

    public String getcurrencymark(int i){


        return currency[i];
    }




    


    private  Document importxml(String url) throws Exception {
        DocumentBuilderFactory xmldoc = DocumentBuilderFactory.newInstance();
        xmldoc.setNamespaceAware(true);

        return xmldoc.newDocumentBuilder().parse(new URL(url).openStream());
    }



    private  void getrate  (String url,int iterator)throws Exception{


        XPath xpath=XPathFactory.newInstance().newXPath();
        XPathExpression expr;

        Object result;

        Document doc=this.importxml(url);




        expr= xpath.compile("//Rates/Rate/Mid//text()");

        result= expr.evaluate(doc, XPathConstants.NODESET);

        NodeList nodes = (NodeList) result;


        this.currencyvalue[iterator]=Double.parseDouble(nodes.item(nodes.getLength()-1).getNodeValue());






    }




}
