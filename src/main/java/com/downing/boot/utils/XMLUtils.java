package com.downing.boot.utils;

import com.downing.boot.exception.LogicException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * http://mengqingyu.iteye.com/blog/1670396
 */
public class XMLUtils {

    /**
     * XML请求解析到Map对象
     *
     * @param xml
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static Map<String, String> parse(String xml) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // XXE 漏洞 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=23_5
        String feature;
        feature = "http://apache.org/xml/features/disallow-doctype-decl";
        dbf.setFeature(feature, true);

        // If you can't completely disable DTDs, then at least do the following:
        // Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-general-entities
        // Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-general-entities
        // JDK7+ - http://xml.org/sax/features/external-general-entities
        feature = "http://xml.org/sax/features/external-general-entities";
        dbf.setFeature(feature, false);

        // Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-parameter-entities
        // Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-parameter-entities
        // JDK7+ - http://xml.org/sax/features/external-parameter-entities
        feature = "http://xml.org/sax/features/external-parameter-entities";
        dbf.setFeature(feature, false);

        // Disable external DTDs as well
        feature = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
        dbf.setFeature(feature, false);

        // and these as well, per Timothy Morgan's 2014 paper: "XML Schema, DTD, and Entity Attacks"
        dbf.setXIncludeAware(false);
        dbf.setExpandEntityReferences(false);

        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xml)));
        // 获取ROOT元素
        Element rootElement = document.getDocumentElement();
        NodeList nodeList = rootElement.getChildNodes();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            // 过滤掉空白文字型节点
            if (Node.TEXT_NODE != node.getNodeType()) {
                map.put(node.getNodeName(), node.getTextContent());
            }
        }
        return map;
    }

    /**
     * 将Map转换成XML数据
     *
     * @param root Root Node Name
     */
    public static String parseToString(String root, Map<String, String> map) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.newDocument();
            Element element = document.createElement(root);
            document.appendChild(element);
            map.keySet().stream().forEach(item -> {
                Element subElement = document.createElement(item);
                subElement.setTextContent(map.get(item));
                element.appendChild(subElement);
            });

            ByteArrayOutputStream byteRep = new ByteArrayOutputStream();
            TransformerFactory tfFactory = TransformerFactory.newInstance();
            Transformer tFormer = tfFactory.newTransformer();

            StreamResult result = new StreamResult(byteRep);
            tFormer.transform(new DOMSource(document), result);
            return byteRep.toString();
        } catch (Exception e) {
            throw new LogicException(e.getMessage());
        }
    }

}
