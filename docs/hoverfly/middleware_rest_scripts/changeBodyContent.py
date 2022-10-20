#!/usr/bin/env python

import sys
import json
import logging
import random

logging.basicConfig(filename='changing_responseName_middleware.log', level=logging.DEBUG)
logging.debug('Changing Name')

def main():

    payload = sys.stdin.readlines()[0]
    logging.debug(payload)
    payload_dic = json.loads(payload)
    payload_dic['response']['status'] = 213

    if "response" in payload_dic and "body" in payload_dic["response"]:
        payload_dic["response"]["body"] = "{'Teste': 'BSF'}\n"
    logging.debug(payload_dic)
    print(json.dumps(payload_dic))
    

if __name__ == "__main__":
    main()