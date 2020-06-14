//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package efo.handlers;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Map.Entry;

import org.apache.tika.sax.ToTextContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class MyHandler extends ToTextContentHandler {
    private final String encoding;
    protected boolean inStartElement = false;
    protected final Map<String, String> namespaces = new HashMap();
    private MyHandler.ElementInfo currentElement;

    public MyHandler(OutputStream stream, String encoding) throws UnsupportedEncodingException {
        super(stream, encoding);
        this.encoding = encoding;
    }

    public MyHandler(String encoding) {
        this.encoding = encoding;
    }

    public MyHandler() {
        this.encoding = null;
    }

    public void startDocument() throws SAXException {
        if (this.encoding != null) {
            this.write("<?xml version=\"1.0\" encoding=\"");
            this.write(this.encoding);
            this.write("\"?>\n");
        }

        this.currentElement = null;
        this.namespaces.clear();
    }

    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        try {
            if (this.currentElement != null && prefix.equals(this.currentElement.getPrefix(uri))) {
                return;
            }
        } catch (SAXException var4) {
        }

        this.namespaces.put(uri, prefix);
    }

    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        this.lazyCloseStartElement();
        this.currentElement = new MyHandler.ElementInfo(this.currentElement, this.namespaces);
        this.write('<');
        this.write(this.currentElement.getQName(uri, localName));

        for(int i = 0; i < atts.getLength(); ++i) {
            this.write(' ');
            this.write(this.currentElement.getQName(atts.getURI(i), atts.getLocalName(i)));
            this.write('=');
            this.write('"');
            char[] ch = atts.getValue(i).toCharArray();
            this.writeEscaped(ch, 0, ch.length, true);

            this.write('"');
        }

        Iterator var9 = this.namespaces.entrySet().iterator();

        while(var9.hasNext()) {
            Entry<String, String> entry = (Entry)var9.next();
            this.write(' ');
            this.write("xmlns");
            String prefix = (String)entry.getValue();
            if (prefix.length() > 0) {
                this.write(':');
                this.write(prefix);
            }

            this.write('=');
            this.write('"');
            char[] ch = ((String)entry.getKey()).toCharArray();
            this.writeEscaped(ch, 0, ch.length, true);
            this.write('"');
        }

        this.namespaces.clear();
        this.inStartElement = true;
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (this.inStartElement) {
            this.write(" />");
            this.inStartElement = false;
        } else {
            this.write("</");
            this.write(qName);
            this.write('>');
        }

        this.namespaces.clear();
        this.currentElement = this.currentElement.parent;
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        this.lazyCloseStartElement();
        String tmp=new String(Arrays.copyOfRange(ch,start,start+length));
        System.out.println(tmp);
        this.writeEscaped(ch, start, start + length, true);
    }

    private void lazyCloseStartElement() throws SAXException {
        if (this.inStartElement) {
            this.write('>');
            this.inStartElement = false;
        }

    }

    protected void write(char ch) throws SAXException {
        super.characters(new char[]{ch}, 0, 1);
    }

    protected void write(String string) throws SAXException {
        super.characters(string.toCharArray(), 0, string.length());
    }

    private int writeCharsAndEntity(char[] ch, int from, int to, String entity) throws SAXException {
        super.characters(ch, from, to - from);
        this.write('&');
        this.write(entity);
        this.write(';');
        return to + 1;
    }

    private void writeEscaped(char[] ch, int from, int to, boolean attribute) throws SAXException {
        int pos = from;

        while(true) {
            while(pos < to) {
                if (ch[pos] == '<') {
                    from = pos = this.writeCharsAndEntity(ch, from, pos, "lt");
                } else if (ch[pos] == '>') {
                    from = pos = this.writeCharsAndEntity(ch, from, pos, "gt");
                } else if (ch[pos] == '&') {
                    from = pos = this.writeCharsAndEntity(ch, from, pos, "amp");
                } else if (attribute && ch[pos] == '"') {
                    from = pos = this.writeCharsAndEntity(ch, from, pos, "quot");
                } else {
                    ++pos;
                }
            }

            super.characters(ch, from, to - from);
            return;
        }
    }

    private static class ElementInfo {
        private final MyHandler.ElementInfo parent;
        private final Map<String, String> namespaces;

        public ElementInfo(MyHandler.ElementInfo parent, Map<String, String> namespaces) {
            this.parent = parent;
            if (namespaces.isEmpty()) {
                this.namespaces = Collections.emptyMap();
            } else {
                this.namespaces = new HashMap(namespaces);
            }

        }

        public String getPrefix(String uri) throws SAXException {
            String prefix = (String)this.namespaces.get(uri);
            if (prefix != null) {
                return prefix;
            } else if (this.parent != null) {
                return this.parent.getPrefix(uri);
            } else if (uri != null && uri.length() != 0) {
                throw new SAXException("Namespace " + uri + " not declared");
            } else {
                return "";
            }
        }

        public String getQName(String uri, String localName) throws SAXException {
            String prefix = this.getPrefix(uri);
            return prefix.length() > 0 ? prefix + ":" + localName : localName;
        }
    }
}
