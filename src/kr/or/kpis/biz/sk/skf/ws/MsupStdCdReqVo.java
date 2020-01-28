/**
 * MsupStdCdReqVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kr.or.kpis.biz.sk.skf.ws;

public class MsupStdCdReqVo  implements java.io.Serializable {
    private java.lang.String aplHbin;

    private java.lang.String apiKey;

    private java.lang.String msupStdCd;

    public MsupStdCdReqVo() {
    }

    public MsupStdCdReqVo(
           java.lang.String aplHbin,
           java.lang.String apiKey,
           java.lang.String msupStdCd) {
           this.aplHbin = aplHbin;
           this.apiKey = apiKey;
           this.msupStdCd = msupStdCd;
    }


    /**
     * Gets the aplHbin value for this MsupStdCdReqVo.
     * 
     * @return aplHbin
     */
    public java.lang.String getAplHbin() {
        return aplHbin;
    }


    /**
     * Sets the aplHbin value for this MsupStdCdReqVo.
     * 
     * @param aplHbin
     */
    public void setAplHbin(java.lang.String aplHbin) {
        this.aplHbin = aplHbin;
    }


    /**
     * Gets the apiKey value for this MsupStdCdReqVo.
     * 
     * @return apiKey
     */
    public java.lang.String getApiKey() {
        return apiKey;
    }


    /**
     * Sets the apiKey value for this MsupStdCdReqVo.
     * 
     * @param apiKey
     */
    public void setApiKey(java.lang.String apiKey) {
        this.apiKey = apiKey;
    }


    /**
     * Gets the msupStdCd value for this MsupStdCdReqVo.
     * 
     * @return msupStdCd
     */
    public java.lang.String getMsupStdCd() {
        return msupStdCd;
    }


    /**
     * Sets the msupStdCd value for this MsupStdCdReqVo.
     * 
     * @param msupStdCd
     */
    public void setMsupStdCd(java.lang.String msupStdCd) {
        this.msupStdCd = msupStdCd;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MsupStdCdReqVo)) return false;
        MsupStdCdReqVo other = (MsupStdCdReqVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.aplHbin==null && other.getAplHbin()==null) || 
             (this.aplHbin!=null &&
              this.aplHbin.equals(other.getAplHbin()))) &&
            ((this.apiKey==null && other.getApiKey()==null) || 
             (this.apiKey!=null &&
              this.apiKey.equals(other.getApiKey()))) &&
            ((this.msupStdCd==null && other.getMsupStdCd()==null) || 
             (this.msupStdCd!=null &&
              this.msupStdCd.equals(other.getMsupStdCd())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAplHbin() != null) {
            _hashCode += getAplHbin().hashCode();
        }
        if (getApiKey() != null) {
            _hashCode += getApiKey().hashCode();
        }
        if (getMsupStdCd() != null) {
            _hashCode += getMsupStdCd().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MsupStdCdReqVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.skf.sk.biz.kpis.or.kr/", "msupStdCdReqVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplHbin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aplHbin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apiKey");
        elemField.setXmlName(new javax.xml.namespace.QName("", "apiKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msupStdCd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "msupStdCd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
