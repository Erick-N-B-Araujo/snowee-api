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

def main():
    data = sys.stdin.readlines()
    payload = data[0]
    payload_dict = json.loads(payload)
    
    body = payload_dict["response"]["body"]

    strBody = str(body)
    
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
            rootElement.clear
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

            #mayCountry = ET.Element("Country")
            #logging.debug(ET.tostring(mayCountry))
            #mayCountry.text = "Brazil"
            #logging.debug(ET.tostring(mayCountry))
            #payload_dict["response"]["body"] = mayCountry
            #logging.debug(payload_dict)
            tree.write('xml/newData.xml')
            #json.dumps(payload_dict)
    #print(json.dumps(payload_dict))

if __name__ == "__main__":
    main()
