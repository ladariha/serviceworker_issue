# serviceworker_issue

1. Download, extract and prepare sources for web application from https://drive.google.com/open?id=0B6Sd5ov8j7IUNy1lT0d2TU9tMnc , it will be saved as worker1.zip in e.g. ```$SOME_PATH```
  * ```$ cd $SOME_PATH```
  * ```$ unzip worker1.zip```
  * ```$ cd worker/rest-mockup```
  * ```$ npm install```
  * ```$ node main.js```

2. Now you should be able to open http://localhost:8776/app/index.html in browser
3. Get sources for the test and execute it:

    $ ```cd $SOME_PATH```
    $ ```git clone https://github.com/ladariha/serviceworker_issue```
    $ ```cd serviceworker_issue```
    $ ```mvn test```
    
It usually takes ~7 minutes to get the Service worker response error
