#!/usr/bin/env python

from hoverpy import HoverPy
import pysimplesoap
import requests
from argparse import ArgumentParser

parser = ArgumentParser(description="Perform proxy testing/URL list creation")

#Adiciona uma opção, coloca uma descrição, e coloca para salvar os logs
parser.add_argument("--capture", help="capture the data", action="store_true")
args = parser.parse_args()


with HoverPy(capture=args.capture):
    #Faz o GET e coleta o IP
    ipAddress = requests.get("http://ip.jsontest.com/myip").json()["ip"]
    #urllib2 lida bem com proxies
    pysimplesoap.transport.set_http_wrapper("urllib2")
    #Builda o SOAPClient
    client = pysimplesoap.client.SoapClient(
        #URL com o schema description URL
        wsdl='http://ws.cdyne.com/ip2geo/ip2geo.asmx?WSDL'
    )
    #Imprime
    print(
        #Invoca o metodo do client, e passa os parâmetros
        client.ResolveIP(ipAddress=ipAddress, licenseKey="0")
    )