
/*function get_source(document_body){
    var listName = document_body.getElementsByTagName('input');
    for(i=0;i<listName.length;i++){
        document.writeln(listName[i].title);
    }
    //return Array.prototype.slice.call(listName);
    return listName.length;
}*/

function get_source(document_body){
    var listName = document_body.getElementsByTagName('input');
    for(i=0;i<listName.length;i++){
        if(listName[i].title=="로그인")listName[i].onclick = function(){
            for(j=0;j<MyDB.getSize();j++){
                if(MyDB.getRecord(j).getURL()==location.href){
                    document.getElementById("id").value = MyDB.getRecord(j).getID();
                    document.getElementById("pw").value = MyDB.getRecord(j).getPwd();
                }
            }
            //window.alert(location.href);
        }
    }
    //return Array.prototype.slice.call(listName);
    return listName.length;
}

chrome.extension.sendMessage({
    action: "getSource",
    source: get_source(document.body)
});
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
var MyDB = new DB('Kimtaejung','12345');
MyDB.AddRecord(new Record('justkukaro','12345678','https://www.naver.com/'));