#!/usr/bin/env python

import sys
import json
import logging

logging.basicConfig(filename='changing_response.log', level=logging.DEBUG)
logging.debug('Changing Name')

#Tags das "Request" que serão tratadas
id_L10563 = "L10563"
id_623990 = "623990"
id_G08135 = "G08135"
id_262617 = "262617"
id_D06BF3A89EBCC9B3339 = "D06BF3A89EBCC9B3339"
id_D26C0AF7B13E2FD7429 = "D26C0AF7B13E2FD7429"
id_D46F351F444DA8EB49D = "D46F351F444DA8EB49D"
id_D56B858042EC19C177E = "D56B858042EC19C177E"
id_D66A2C494E0FC520C43 = "D66A2C494E0FC520C43"
id_D66D75BFF7FA01C21C4 = "D66D75BFF7FA01C21C4"
id_D86C1B56BE2E1A3A2AD = "D86C1B56BE2E1A3A2AD"
id_D566D774C10E20F9009 = "D566D774C10E20F9009"
id_D668FEC94E72F244D0C = "D668FEC94E72F244D0C"
id_D1653F54255DA5FE5A6 = "D1653F54255DA5FE5A6"
id_D06850CE415B6D6BF1E = "D06850CE415B6D6BF1E"
id_D568410DF6674708AE9 = "D568410DF6674708AE9"


def modifyResponseBody(pathXmlModified, payload_dic):
    #Modifica Status Code
    payload_dic['response']['status'] = 200

    #Carrega uma "response" pronta
    with open(pathXmlModified, 'r') as f: 
        dataXml = f.read()
    logging.debug("Utilizando novo metodo")
    #Carrega o "body" com o conteudo do arquivo
    payload_dic["response"]["body"] = "" + dataXml + ""

def defaultResponseBody(payload_dic):
    #Modifica Status Code
    payload_dic['response']['status'] = 200
            
    #Modifica body
    payload_dic["response"]["body"] = "Endpoint/Request/Response nao tratado"

def main():

    #Recebe e captura o par(Request, Response)
    payload = sys.stdin.readlines()[0]
    #logging.debug(payload)

    #Transforma a carga em objeto Json
    payload_dic = json.loads(payload)

    #Coleta a "request" e valida o "body"
    request = payload_dic['request']['body']
    if request == "":
        logging.debug("Request Vazia")
    else:
        strRequestReaded: any = str(request)
        logging.debug("Request Recebida")

        if "QueryLoanListRequest" in strRequestReaded and "CustomerCode" in strRequestReaded and id_L10563 in strRequestReaded:
            modifyResponseBody('bsf_responses/loan/resQueryLoanList_CustCode_L10563.xml', payload_dic)
        
        elif "GeneralInquiries" in strRequestReaded and "CPT" in strRequestReaded and id_L10563 in strRequestReaded:
            modifyResponseBody('bsf_responses/current_saving/resGeneralInquiries_CPT_L10563.xml', payload_dic)
        
        elif "CustomerAccountCardInquiryRequestDetails" in strRequestReaded and "CustomerCode" in strRequestReaded and id_623990 in strRequestReaded:
            modifyResponseBody('bsf_responses/credit_card/resCustomerAccountCardInquiry_CustCode_623990.xml', payload_dic)

        elif "CustomerAccountCardInquiryRequestDetails" in strRequestReaded and "CustomerCode" in strRequestReaded and id_G08135 in strRequestReaded:
            modifyResponseBody('bsf_responses/credit_card/resCustomerAccountCardInquiryTransactions_CustId_G08135.xml', payload_dic)
        
        elif "CreditCardTransactionInquiryRequest" in strRequestReaded and "Pan" in strRequestReaded and id_D06BF3A89EBCC9B3339 in strRequestReaded:
            modifyResponseBody('bsf_responses/credit_card/transactions/resCreditCardTransactionInquiry_PAN_D06BF3A89EBCC9B3339.xml', payload_dic)

        elif "CreditCardTransactionInquiryRequest" in strRequestReaded and "Pan" in strRequestReaded and id_D26C0AF7B13E2FD7429 in strRequestReaded:
            modifyResponseBody('bsf_responses/credit_card/transactions/resCreditCardTransactionInquiry_PAN_D26C0AF7B13E2FD7429.xml', payload_dic)

        elif "CreditCardTransactionInquiryRequest" in strRequestReaded and "Pan" in strRequestReaded and id_D46F351F444DA8EB49D in strRequestReaded:
            modifyResponseBody('bsf_responses/credit_card/transactions/resCreditCardTransactionInquiry_PAN_D46F351F444DA8EB49D.xml', payload_dic)
        
        elif "CreditCardTransactionInquiryRequest" in strRequestReaded and "Pan" in strRequestReaded and id_D56B858042EC19C177E in strRequestReaded:
            modifyResponseBody('bsf_responses/credit_card/transactions/resCreditCardTransactionInquiry_PAN_D56B858042EC19C177E.xml', payload_dic)
        
        elif "CreditCardTransactionInquiryRequest" in strRequestReaded and "Pan" in strRequestReaded and id_D66A2C494E0FC520C43 in strRequestReaded:
            modifyResponseBody('bsf_responses/credit_card/transactions/resCreditCardTransactionInquiry_PAN_D66A2C494E0FC520C43.xml', payload_dic)
        
        elif "CreditCardTransactionInquiryRequest" in strRequestReaded and "Pan" in strRequestReaded and id_D66D75BFF7FA01C21C4 in strRequestReaded:
            modifyResponseBody('bsf_responses/credit_card/transactions/resCreditCardTransactionInquiry_PAN_D66D75BFF7FA01C21C4.xml', payload_dic)
        
        elif "CreditCardTransactionInquiryRequest" in strRequestReaded and "Pan" in strRequestReaded and id_D86C1B56BE2E1A3A2AD in strRequestReaded:
            modifyResponseBody('bsf_responses/credit_card/transactions/resCreditCardTransactionInquiry_PAN_D86C1B56BE2E1A3A2AD.xml', payload_dic)
        
        elif "CreditCardTransactionInquiryRequest" in strRequestReaded and "Pan" in strRequestReaded and id_D566D774C10E20F9009 in strRequestReaded:
            modifyResponseBody('bsf_responses/credit_card/transactions/resCreditCardTransactionInquiry_PAN_D566D774C10E20F9009.xml', payload_dic)
        
        elif "CreditCardTransactionInquiryRequest" in strRequestReaded and "Pan" in strRequestReaded and id_D668FEC94E72F244D0C in strRequestReaded:
            modifyResponseBody('bsf_responses/credit_card/transactions/resCreditCardTransactionInquiry_PAN_D668FEC94E72F244D0C.xml', payload_dic)
        
        elif "CreditCardTransactionInquiryRequest" in strRequestReaded and "Pan" in strRequestReaded and id_D1653F54255DA5FE5A6 in strRequestReaded:
            modifyResponseBody('bsf_responses/credit_card/transactions/resCreditCardTransactionInquiry_PAN_D1653F54255DA5FE5A6.xml', payload_dic)
        
        elif "CreditCardTransactionInquiryRequest" in strRequestReaded and "Pan" in strRequestReaded and id_D06850CE415B6D6BF1E in strRequestReaded:
            modifyResponseBody('bsf_responses/credit_card/transactions/resCreditCardTransactionInquiry_PAN_D06850CE415B6D6BF1E.xml', payload_dic)
        
        elif "CreditCardTransactionInquiryRequest" in strRequestReaded and "Pan" in strRequestReaded and id_D568410DF6674708AE9 in strRequestReaded:
            modifyResponseBody('bsf_responses/credit_card/transactions/resCreditCardTransactionInquiry_PAN_D568410DF6674708AE9.xml', payload_dic)
        
        elif "AccountBalances" in strRequestReaded and "CPT" in strRequestReaded and id_262617 in strRequestReaded:
            modifyResponseBody('bsf_responses/current_saving/balance/resGeneralInquiriesBalance_CPT_262617.xml', payload_dic)
        
        else:
            defaultResponseBody(payload_dic)
        #Retorna o par(Request, Response)
        print(json.dumps(payload_dic))

if __name__ == "__main__":
    main()