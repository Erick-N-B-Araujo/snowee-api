from hoverpy import simulate, modify
import requests

@simulate("requests.db", delays=[("time.json.com", 1000)])
def simulated_latency():
    print(requests.get("http://time.jsontest.com").json())

@modify(middleware="python middleware.py")
def modified_request():
    print(requests.get("http://time.jsontest.com").json())

simulated_latency()
modified_request()