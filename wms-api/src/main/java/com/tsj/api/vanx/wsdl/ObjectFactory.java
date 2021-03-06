
package com.tsj.api.vanx.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.tempuri package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _String_QNAME = new QName("http://tempuri.org/", "string");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tempuri
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TestDatabaseConnectionResponse }
     * 
     */
    public TestDatabaseConnectionResponse createTestDatabaseConnectionResponse() {
        return new TestDatabaseConnectionResponse();
    }

    /**
     * Create an instance of {@link SendRecvResponse }
     * 
     */
    public SendRecvResponse createSendRecvResponse() {
        return new SendRecvResponse();
    }

    /**
     * Create an instance of {@link SendRecv }
     * 
     */
    public SendRecv createSendRecv() {
        return new SendRecv();
    }

    /**
     * Create an instance of {@link TestDatabaseConnection }
     * 
     */
    public TestDatabaseConnection createTestDatabaseConnection() {
        return new TestDatabaseConnection();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

}
