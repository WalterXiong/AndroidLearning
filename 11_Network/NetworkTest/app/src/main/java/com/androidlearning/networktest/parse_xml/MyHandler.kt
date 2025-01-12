package com.androidlearning.networktest.parse_xml

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

class MyHandler : DefaultHandler() {

    /**
     * 方法会在开始XML解析的时候调用
     */
    override fun startDocument() {
        super.startDocument()
    }

    /**
     * 方法会在开始解析某个节点的时候调用
     */
    override fun startElement(
        uri: String?,
        localName: String?,
        qName: String?,
        attributes: Attributes?
    ) {
        super.startElement(uri, localName, qName, attributes)
    }

    /**
     * 方法会在获取节点中内容的时候调用
     */
    override fun characters(ch: CharArray?, start: Int, length: Int) {
        super.characters(ch, start, length)
    }

    /**
     * 方法会在完成解析某个节点的时候调用
     */
    override fun endElement(uri: String?, localName: String?, qName: String?) {
        super.endElement(uri, localName, qName)
    }

    /**
     * 方法会在完成整个XML解析的时候调用
     */
    override fun endDocument() {
        super.endDocument()
    }
}