package com.androidlearning.networktest.parse_xml

import android.util.Log
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

class ContentHandler : DefaultHandler() {

    private var nodeName = ""

    private lateinit var id: StringBuilder

    private lateinit var name: StringBuilder

    private lateinit var version: StringBuilder

    /**
     * 方法会在开始XML解析的时候调用
     */
    override fun startDocument() {
        id = StringBuilder()
        name = StringBuilder()
        version = StringBuilder()
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
        nodeName = localName.toString()
        Log.d("ContentHandler", "uri is $uri")
        Log.d("ContentHandler", "localName is $localName")
        Log.d("ContentHandler", "qName is $qName")
        Log.d("ContentHandler", "attributes is $attributes")
    }

    /**
     * 方法会在获取节点中内容的时候调用
     */
    override fun characters(ch: CharArray?, start: Int, length: Int) {
        // 根据当前节点名判断将内容添加到哪一个StringBuilder对象中
        when (nodeName) {
            "id" -> id.append(ch, start, length)
            "name" -> name.append(ch, start, length)
            "version" -> version.append(ch, start, length)
        }
    }

    /**
     * 方法会在完成解析某个节点的时候调用
     */
    override fun endElement(uri: String?, localName: String?, qName: String?) {
        if ("app" == localName) {
            Log.d("ContentHandler", "id is ${id.toString().trim()}")
            Log.d("ContentHandler", "name is ${name.toString().trim()}")
            Log.d("ContentHandler", "version is ${version.toString().trim()}")
            // 最后需要清空 StringBuilder
            id.setLength(0)
            name.setLength(0)
            version.setLength(0)
        }
    }

    /**
     * 方法会在完成整个XML解析的时候调用
     */
    override fun endDocument() {
        super.endDocument()
    }
}
