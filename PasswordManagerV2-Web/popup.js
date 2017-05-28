chrome.extension.onMessage.addListener(function(request, sender) {
    if (request.action == "getSource") {
        //document.body.innerText = request.source;
        /*for(i=0;i<request.source.length;i++){
            document.writeln(request.source[i]);
        }*/
        document.body.innerText = request.source;
        //document.body.innerText = request.source;
    }
});

/*function onWindowLoad() {
    chrome.tabs.executeScript(null, {
        file: "getSource.js"}, function() {
        if (chrome.extension.lastError) {
            document.body.innerText = 'There was an error injecting script : \n' + chrome.extension.lastError.message;
        }
    });
}*/

function onWindowLoad(){
    chrome.tabs.executeScript(null, {
        file: "getSource.js"}, function() {
        if (chrome.extension.lastError) {
            document.body.innerText = 'There was an error injecting script : \n' + chrome.extension.lastError.message;
        }
    });
}
window.onload = onWindowLoad;

var DB = function(ID,MasterPassword){
    /*
     * Private Field
     */
    var _ID = ID;
    var _MasterPassword = MasterPassword;
    var _RecordArr = [];
    var _Size = 0;
    /*
     * Public Field
     */
    this.getID = function(){
        return _ID;
    }
    this.setID = function(ID) {
        _ID = ID;
    }
    this.getMasterPassword = function(){
        return _MasterPassword;
    }
    this.setMasterPassword = function(MasterPassword){
        _MasterPassword = MasterPassword;
    }
    this.AddRecord = function(Record) {
        _RecordArr[_Size] = Record;
        _Size++;
    }
    this.getRecord = function(index){
        return _RecordArr[index];
    }
    this.getSize = function(){
        return _Size;
    }
}

var Record = function(ID,Pwd,URL){
    /*
     * Private Field
     */
    var _ID = ID;
    var _Pwd = Pwd;
    var _URL = URL;
    
    /*
     * Public Field
     */
    this.getID = function () {
        return _ID;
    }
    this.getPwd = function () {
        return _Pwd;
    }
    this.getURL = function () {
        return _URL;
    }
}



