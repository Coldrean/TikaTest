# 测试 Tika
Apache Tika用于文件类型检测和从各种格式的文件中提取文本内容。

Tika的使用方式很简单：
```
    public void testTikaParseEmlWithDocAttachment() throws TikaException, SAXException, IOException {
        String filename="eml-with-doc-attachment-test.eml";
        AutoDetectParser autoParser = new AutoDetectParser();
        ContentHandler contentHandler = new ToXMLContentHandler();
        Metadata metadata = new Metadata();
        
        InputStream stream =this.getClass().getClassLoader().getResourceAsStream(Paths.get(inputDir,filename).toString());
        autoParser.parse(stream, contentHandler, metadata);
    }
```
其中，autoParser可以自动输入的检测文档的类型，调用合适的解析器去解析文档，将结果写入到Metadata和ContentHandler中，Metadata保存的是输入文档的元信息，
比如文档的创建人创建时间等等。文档的内容的内容保存在ContentHandler的Writer中。如果想要将文档输出为xhtml格式，可以选用ToXMLContentHandler类型的参数，
如果只想要输出文档的内容，可以用BodyContentHandler类型的参数。

## 目前测试来看，Tika主要功能时提取各种文档中的文字，无法提取图片

## Word
### 可提取的元信息
标题
作者
最后一次保存者
创建时间
最后一次修改时间
最后一次打印时间
文档字数
文档类型
页数
模板

## 输出形式
可以输出为xhtml文件，和纯文本文件，可以保留word中的表格结构。
可以提取页眉页脚批注，但是失去了位置信息
word中的图片，在输出为xhtml文件的时候，命名规则时从image1-N加扩展名

# Excel
###可提取的元信息
标题
作者
最后一次保存者
创建时间
最后一次修改时间
最后一次打印时间
文档类型

## 输出形式
可以输出为xhtml文件，和纯文本文件，可以保留Excel中的表格结构。

# Eml
### 可提取的元信息
邮件主题
创建人
创建时间
发件人
收件人
MIME-version
无附件的元信息

## 输出形式

可以输出为xhtml文件，



# PPT
### 可提取的元信息
标题
作者
最后一次保存者
创建时间
最后一次修改时间
最后一次打印时间
文档字数
文档类型
页数
模板

## 输出形式
可以输出为xhtml文件，可以保留PPT中的表格结构。

# PDF
### 可提取的元信息
标题
作者
创建时间
最后一次修改时间
文档类型
页数
创建工具

## 输出形式
可以输出为xhtml文件，可以保留word中的表格结构。

# 压缩包
支持的压缩包格式：
Tar, AR, ARJ, CPIO, Dump, Zip, 7Zip, Gzip, BZip2, XZ, LZMA, Z and Pack200.