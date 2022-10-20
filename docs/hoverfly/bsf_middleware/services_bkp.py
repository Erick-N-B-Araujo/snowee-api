#!/usr/bin/env python

import sys
import json
import logging

logging.basicConfig(filename='changing_response.log', level=logging.DEBUG)
logging.debug('Changing Name')

#Tags das "Request" que serão tratadas
tagCustomerAccount = "CustomerAccountCardInquiryRequest"
tagGeneralInquiries = "GeneralInquiries"
tagQueryLoanListRequest = "QueryLoanListRequest"
cptClient = "L10563"
custId = "G08135"
panId_D568410DF6674708AE9 = "D568410DF6674708AE9"
panId_D668FEC94E72F244D0C = "D668FEC94E72F244D0C"
def main():

    #Recebe e captura o par(Request, Response)
    payload = sys.stdin.readlines()[0]
    logging.debug(payload)

    #Transforma a carga em objeto Json
    payload_dic = json.loads(payload)

    #Coleta a "request" e valida o "body"
    request = payload_dic['request']['body']
    if request == "":
        logging.debug("Request Vazia")
    else:
        strRequestReaded: any = str(request)
        logging.debug("String Request")
        logging.debug(strRequestReaded)

        if tagCustomerAccount in strRequestReaded:        
            #Modifica Status Code
            payload_dic['response']['status'] = 200

            #Carrega uma "response" pronta
            with open('bsf_responses/credit_card/resCustomerAccountCardInquiry.xml', 'r') as f: 
                dataXml = f.read()

            #Carrega o "body" com o conteudo do arquivo
            payload_dic["response"]["body"] = "" + dataXml + ""

        elif tagGeneralInquiries in strRequestReaded:
            #Modifica Status Code
            payload_dic['response']['status'] = 200

            #Carrega uma "response" pronta
            with open('bsf_responses/current_saving/resAccounts_Current_Loan_Mortgage.xml', 'r') as f: 
                dataXml = f.read()

            #Carrega o "body" com o conteudo do arquivo
            payload_dic["response"]["body"] = "" + dataXml + ""

        elif tagQueryLoanListRequest in strRequestReaded:         
            #Modifica Status Code
            payload_dic['response']['status'] = 200

            #Carrega uma "response" pronta
            with open('bsf_responses/loan/resQueryLoanList.xml', 'r') as f: 
                dataXml = f.read()

            #Carrega o "body" com o conteudo do arquivo
            payload_dic["response"]["body"] = "" + dataXml + ""

        elif custId in strRequestReaded:            
            #Modifica Status Code
            payload_dic['response']['status'] = 200

            #Carrega uma "response" pronta
            with open('bsf_responses/credit_card/resCustomerAccountCardInquiryTransactions_CustId_G08135_.xml', 'r') as f: 
                dataXml = f.read()

            #Carrega o "body" com o conteudo do arquivo
            payload_dic["response"]["body"] = "" + dataXml + ""

        elif panId_D568410DF6674708AE9 in strRequestReaded:           
            #Modifica Status Code
            payload_dic['response']['status'] = 200

            #Carrega uma "response" pronta
            with open('bsf_responses/credit_card/transactions/resCreditCardTransactionInquiry_PAN_D568410DF6674708AE9.xml', 'r') as f: 
                dataXml = f.read()

            #Carrega o "body" com o conteudo do arquivo
            payload_dic["response"]["body"] = "" + dataXml + ""

        elif panId_D668FEC94E72F244D0C in strRequestReaded:   
            #Modifica Status Code
            payload_dic['response']['status'] = 200

            #Carrega uma "response" pronta
            with open('bsf_responses/credit_card/transactions/resCreditCardTransactionInquiry_PAN_D668FEC94E72F244D0C.xml', 'r') as f: 
                dataXml = f.read()

            #Carrega o "body" com o conteudo do arquivo
            payload_dic["response"]["body"] = "" + dataXml + ""
        
        else:
            #Modifica Status Code
            payload_dic['response']['status'] = 200
            
            #Modifica body
            payload_dic["response"]["body"] = "Endpoint/Request/Response nao tratado"
   
        #Retorna o par(Request, Response)
        print(json.dumps(payload_dic))

if __name__ == "__main__":
    main()