package com.sturgeon.common.config;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class AbstractXMLConfig implements Config {
    private static Logger logger = LoggerFactory.getLogger(AbstractXMLConfig.class);

    private Document      document;

    private String        path;

    private URL           resource;

    public AbstractXMLConfig(String path) {
        this.path = ClassLoaderUtil.getAbsolutePath(path);
        this.resource = ClassLoaderUtil.getClassLoader().getResource(path);
        SAXReader saxReader = new SAXReader();
        try {
            document = saxReader.read(resource);
        } catch (DocumentException e) {
            logger.error("xml file not exits!", e);
        }
    }
    
    protected int getInt(String path) {
        String text = null;
        try {
            text = document.selectSingleNode(path).getText().trim();
            return Integer.parseInt(text);
        } catch (Exception e) {
            logger.error("", e);
            return 0;
        }
    }
    
    protected String getString(String path) {
        String text = null;
        try {
            text = document.selectSingleNode(path).getText().trim();
            return text;
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
    protected List<String> getStrings(String path) {
        List<String> datas = new ArrayList<String>();
        try {
            List<Element> selectNodes = document.selectNodes(path);
            for (Element e : selectNodes) {
                if (e == null) {
                    continue;
                }
                datas.add(e.getTextTrim());
            }
        } catch (Exception e) {
            logger.error("xml read error!");
        }
        
        return datas;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public URL getResource() {
        return resource;
    }
}
