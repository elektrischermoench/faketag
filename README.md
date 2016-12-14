#faketag - the android based emulator of nfc tags 

Goal of this project is to create a simple android based emulator for nfc tags.
Processing of APDU's is based on a simple json file.

##Backgroud

This days more and more banks starts sending NFC-enabled credit cards to their customers. I've just reveived 
a NFC credit card from my bank too. This was my starting point for own investigations about nfc technology used
by banks. My first thougts were: "They will use some state of the art Crypto-Processors for the tags". 
I was proved wrong. Everything is just plaintext. My decision was to start this 
project to have an easy way of demonstraiting vulnerabilities in old-fashioned nfc tags which are used in payment systems.
This is not the first project to show this vulnerabilities and it won't be the last one.

##JSON file format

This emulator is processing apdu commands based on simple JSON files. Use the following format:

```json
[
	{"request":"ODBDQTlGN0YwMA==","response":"NjgwMA=="},
  	{"request":"MTIzNDU=","respone":"MTIzNDU="}
]
```

The responses are byte values which were dumped with my fork of bakomatinfos. See "How to create JSON dump" below.

So if you sniff sessions from real world nfc-tags you can generate a json-file like the above and
your able to emulate this nfc tag. At the moment only statically tags without any random data are 
supported.

You have to place the file into your ExternalStorageDirectory which you'll find with using:
```Java
Environment.getExternalStorageDirectory()
```

use ```faketag.json``` as filename. 

### How to create JSON dump?

I've added the functionality to dump your credit card into the faketag JSON file format with bankomatinfos. 
This is a great and useful tool originally created by John Zweng.
See here: https://github.com/elektrischermoench/bankomatinfos 
Just activate the "dump nfc tag function" under settings. 

<img src="https://github.com/elektrischermoench/faketag/blob/master/img/Screenshot_2015-03-09-09-42-56.png" alt="disabled NFC" width="300"/>
<img src="https://github.com/elektrischermoench/faketag/blob/master/img/Screenshot_2015-03-09-09-43-02.png" alt="enabled NFC" width="300"/>



