
from hoverpy import HoverPy
from argparse import ArgumentParser
from lxml import objectify
from lxml import etree
import sys
import json
import logging
import requests
import pysimplesoap

logging.basicConfig(filename='modify_payload_soap.log', level=logging.DEBUG)
logging.debug('Middleware "modify_request" called')

def main():
    payload = sys.stdin.readlines()[0]
    logging.debug(payload)
    payload_dict = json.loads(payload)

    if "response" in payload_dict and "body" in payload_dict["response"]:
        body = payload_dict["response"]["body"]
        try:
            root = objectify.fromstring(body)
            ns = "{http://ws.cdyne.com/}"
            logging.debug("transforming")

            ipe = ns + "ResolveIPResponse"
            ipt = ns + "ResolveIPResult"

            root.Body[ipe][ipt].City = "New York"
            objectify.deannotate(root.Body[ipe][ipt].City)
            etree.cleanup_namespaces(root.Body[ipe][ipt].City)

            payload_dict["response"]["body"] = etree.tostring(root)
        finally:
            print("Passou")

    print(json.dumps(payload_dict))

if __name__ == "__main__":
    main()