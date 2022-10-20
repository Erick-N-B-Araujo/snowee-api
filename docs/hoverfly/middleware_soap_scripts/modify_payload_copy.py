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
import xmltodict

logging.basicConfig(filename='middleware.log', level=logging.DEBUG)
logging.debug('Middleware "modify_request" called')
methodName = "soap:Envelope"

def main():
    data = sys.stdin.readlines()
    payload = data[0]
    payload_dict = json.loads(payload)
    logging.debug(payload)
    logging.debug(payload_dict)
    body = payload_dict["response"]["body"]
    strbodyreaded: any = str(body)
    if body == "":
        logging.debug("Body vazio!")

    if methodName in strbodyreaded:
        logging.debug("Metodo detectado")
        logging.debug("Transformando XML!")
        with open('xml/newData.xml', 'w') as wf: 
                wf.write(strbodyreaded)
        with open('xml/newData.xml', 'r') as f: 
                dataXml = f.read()
        logging.debug(dataXml)
        soup = BeautifulSoup(dataXml, 'lxml')
        logging.debug(soup)

        xml_bodyTag = soup.find('soap:envelope')
        logging.debug(xml_bodyTag)

        xml_countryTag = xml_bodyTag.find('country')
        logging.debug(xml_countryTag)
        xml_countryTag.string = "Brazil"
        logging.debug(xml_countryTag)
        logging.debug(xml_bodyTag)
        strbodymodified: any = str(xml_bodyTag)
        with open('xml/newData_modified.xml', 'w') as wf: 
            wf.write(strbodymodified)

        with open('xml/newData_modified.xml') as xml_file:
            data_dict = xmltodict.parse(xml_file.read())
            json_data = json.dumps(data_dict)
            logging.debug(json_data)
            #payload_dict["response"]["body"] = json_data
            with open("json/data.json", "w") as json_file:
                json_file.write(json_data)

        #payload_dict["response"]["body"] = json.loads(strbodymodified)
    
    print(json.dumps(payload_dict))

if __name__ == "__main__":
    main()
