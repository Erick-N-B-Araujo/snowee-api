#!/usr/bin/env python

from cgitb import text
import sys
import json
import logging
import random
from lxml import objectify
from lxml import etree
from io import StringIO, BytesIO
import xml.etree.ElementTree as ET
from bs4 import BeautifulSoup 

methodName = "ResolveIPResult"
mustNotHave = "wsdl:definitions"

logging.basicConfig(filename='middleware.log', level=logging.DEBUG)
logging.debug('Middleware "modify_request" called')

data='''<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
   <soap:Body>
      <ResolveIPResponse xmlns="http://ws.cdyne.com/">
         <ResolveIPResult>
            <City>New York</City>
            <StateProvince>H9</StateProvince>
            <Country>United Kingdom</Country>
            <Organization />
            <Latitude>51.5092</Latitude>
            <Longitude>-0.09550476</Longitude>
            <AreaCode>0</AreaCode>
            <TimeZone />
            <HasDaylightSavings>false</HasDaylightSavings>
            <Certainty>90</Certainty>
            <RegionName />
            <CountryCode>GB</CountryCode>
         </ResolveIPResult>
      </ResolveIPResponse>
   </soap:Body>
</soap:Envelope>
'''

def main():
    data = sys.stdin.readlines()
    payload = data[0]
    payload_dict = json.loads(payload)
    logging.debug("PAYLOAD: "+payload)
    logging.debug("PAYLOAD_DICT: "+json.dumps(payload_dict))
    
    body = payload_dict["response"]["body"]
    logging.debug("BODY: "+json.dumps(body))

    strBody = str(body)
    logging.debug("BODY STR: "+strBody)
    
    if body == "":
        logging.debug("Body vazio!")
    
    if methodName in strBody:
        logging.debug("Metodo detectado")
        if mustNotHave in strBody:
            logging.debug("Este e o XML de Requisicao!")
        else:
            logging.debug("Transformando XML!")
            with open('xml/newData.xml', 'w') as wf: 
                wf.write(strBody)
            with open('xml/newData.xml', 'r') as f: 
                dataXml = f.read()
            logging.debug(dataXml)    
            Bs_data = BeautifulSoup(dataXml, "xml")

            rootElement = Bs_data.find_all('soap:Envelope')
            logging.debug(rootElement)

            countryElement = Bs_data.find_all('Country')
            logging.debug("COUNTRIE: ")
            logging.debug(countryElement)
            countryElement.text = "Brazil"
            logging.debug("COUNTRIE: ")
            logging.debug(countryElement)

            reqSOAP = BeautifulSoup(strBody, 'xml')
            reqSOAP.prettify()
            logging.debug(reqSOAP)

            country = reqSOAP.find("Country")
            state = country.text
            logging.debug(country)
            logging.debug(state)
            BeautifulSoup
            #country.replace("Brazil")
            logging.debug(country)

            tree = ET.parse('xml/newData.xml')
            root = tree.getroot()
            logging.debug("Root XML: ")
            logging.debug(ET.tostring(root))
            logging.debug(root.attrib)
            root.set("BIRULEIBE", 'LEIBE')
            logging.debug(root.attrib)
            logging.debug(ET.tostring(root))

            mayCountry = ET.Element("Country")
            logging.debug(ET.tostring(mayCountry))
            mayCountry.text = "Brazil"
            logging.debug(ET.tostring(mayCountry))
            
            tree.write('xml/newData.xml')
            #b_unique = Bs_data.find_all('Country')
            #logging.debug(b_unique)

            #print(b_unique)
            #myroot = ET.fromstring(data)
            #root = etree.fromstring(xml)
            logging.debug("Coletado XML!")
            logging.debug(dataXml)
            #logging.debug(myroot)
            try:
                logging.debug("Tentando popular")
                #root = objectify.fromstring(strBody)
                #logging.debug(root)
            except Exception:
                logging.debug("EXCEPTION")
                pass

if __name__ == "__main__":
    main()
