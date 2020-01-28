/**
 * MsupStdCdResVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kr.or.kpis.biz.sk.skf.ws;

public class MsupStdCdResVo  implements java.io.Serializable {
    private java.lang.String aplHbin;

    private java.lang.String apiKey;

    private java.lang.String msupStdCd;

    private java.lang.String result;

    private java.lang.String resultMessage;

    private kr.or.kpis.biz.sk.skf.ws.MsupStdCdVo[] msupStdCdList;

    public MsupStdCdResVo() {
    }

    public MsupStdCdResVo(
           java.lang.String aplHbin,
           java.lang.String apiKey,
           java.lang.String msupStdCd,
           java.lang.String result,
           java.lang.String resultMessage,
           kr.or.kpis.biz.sk.skf.ws.MsupStdCdVo[] msupStdCdList) {
           this.aplHbin = aplHbin;
           this.apiKey = apiKey;
           this.msupStdCd = msupStdCd;
           this.result = result;
           this.resultMessage = resultMessage;
           this.msupStdCdList = msupStdCdList;
    }


    /**
     * Gets the aplHbin value for this MsupStdCdResVo.
     * 
     * @return aplHbin
     */
    public java.lang.String getAplHbin() {
        return aplHbin;
    }


    /**
     * Sets the aplHbin value for this MsupStdCdResVo.
     * 
     * @param aplHbin
     */
    public void setAplHbin(java.lang.String aplHbin) {
        this.aplHbin = aplHbin;
    }


    /**
     * Gets the apiKey value for this MsupStdCdResVo.
     * 
     * @return apiKey
     */
    public java.lang.String getApiKey() {
        return apiKey;
    }


    /**
     * Sets the apiKey value for this MsupStdCdResVo.
     * 
     * @param apiKey
     */
    public void setApiKey(java.lang.String apiKey) {
        this.apiKey = apiKey;
    }


    /**
     * Gets the msupStdCd value for this MsupStdCdResVo.
     * 
     * @return msupStdCd
     */
    public java.lang.String getMsupStdCd() {
        return msupStdCd;
    }


    /**
     * Sets the msupStdCd value for this MsupStdCdResVo.
     * 
     * @param msupStdCd
     */
    public void setMsupStdCd(java.lang.String msupStdCd) {
        this.msupStdCd = msupStdCd;
    }


    /**
     * Gets the result value for this MsupStdCdResVo.
     * 
     * @return result
     */
    public java.lang.String getResult() {
        return result;
    }


    /**
     * Sets the result value for this MsupStdCdResVo.
     * 
     * @param result
     */
    public void setResult(java.lang.String result) {
        this.result = result;
    }


    /**
     * Gets the resultMessage value for this MsupStdCdResVo.
     * 
     * @return resultMessage
     */
    public java.lang.String getResultMessage() {
        return resultMessage;
    }


    /**
     * Sets the resultMessage value for this MsupStdCdResVo.
     * 
     * @param resultMessage
     */
    public void setResultMessage(java.lang.String resultMessage) {
        this.resultMessage = resultMessage;
    }


    /**
     * Gets the msupStdCdList value for this MsupStdCdResVo.
     * 
     * @return msupStdCdList
     */
    public kr.or.kpis.biz.sk.skf.ws.MsupStdCdVo[] getMsupStdCdList() {
        return msupStdCdList;
    }


    /**
     * Sets the msupStdCdList value for this MsupStdCdResVo.
     * 
     * @param msupStdCdList
     */
    public void setMsupStdCdList(kr.or.kpis.biz.sk.skf.ws.MsupStdCdVo[] msupStdCdList) {
        this.msupStdCdList = msupStdCdList;
    }

    public kr.or.kpis.biz.sk.skf.ws.MsupStdCdVo getMsupStdCdList(int i) {
        return this.msupStdCdList[i];
    }

    public void setMsupStdCdList(int i, kr.or.kpis.biz.sk.skf.ws.MsupStdCdVo _value) {
        this.msupStdCdList[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MsupStdCdResVo)) return false;
        MsupStdCdResVo other = (MsupStdCdResVo) obj;
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
              this.msupStdCd.equals(other.getMsupStdCd()))) &&
            ((this.result==null && other.getResult()==null) || 
             (this.result!=null &&
              this.result.equals(other.getResult()))) &&
            ((this.resultMessage==null && other.getResultMessage()==null) || 
             (this.resultMessage!=null &&
              this.resultMessage.equals(other.getResultMessage()))) &&
            ((this.msupStdCdList==null && other.getMsupStdCdList()==null) || 
             (this.msupStdCdList!=null &&
              java.util.Arrays.equals(this.msupStdCdList, other.getMsupStdCdList())));
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
        if (getResult() != null) {
            _hashCode += getResult().hashCode();
        }
        if (getResultMessage() != null) {
            _hashCode += getResultMessage().hashCode();
        }
        if (getMsupStdCdList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMsupStdCdList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMsupStdCdList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MsupStdCdResVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.skf.sk.biz.kpis.or.kr/", "msupStdCdResVo"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("result");
        elemField.setXmlName(new javax.xml.namespace.QName("", "result"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "resultMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msupStdCdList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "msupStdCdList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.skf.sk.biz.kpis.or.kr/", "msupStdCdVo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
