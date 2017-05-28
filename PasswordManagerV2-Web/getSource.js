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
var URL_list = function(){
    /*
     * Private Field
     */
    var _URLArr = [];
    var _URLSize = 0;
    var _URLID = [];
    var _URLPwd = [];
    var _URLLog = [];
    var _addition = [];

    /*
     * Public Field
     */
    this.AddURL = function (url,urlid,urlpwd,urllog,addition){
        _URLArr[_URLSize] = url;
        _URLID[_URLSize] = urlid;
        _URLPwd[_URLSize] = urlpwd;
        _URLLog[_URLSize] = urllog;
        _addition[_URLSize] = addition;
        _URLSize++;
    }

    this.getURL = function(index){
        return _URLArr[index];
    }

    this.getURLID = function (index) {
        return _URLID[index];
    }

    this.getURLPwd = function (index) {
        return _URLPwd[index];
    }

    this.getURLLog = function (index) {
        return _URLLog[index];
    }

    this.getAddition = function (index){
        return _addition[index];
    }
    this.getSize = function () {
        return _URLSize;
    }
}
var MyDB = new DB('Kimtaejung','12345');
MyDB.AddRecord(new Record('id','1234','https://www.naver.com/'));
MyDB.AddRecord(new Record('id','1234','https://logins.daum.net/accounts/loginform.do?url=http%3A%2F%2Fwww.daum.net'));
MyDB.AddRecord(new Record('id','1234','https://www.facebook.com/'));
MyDB.AddRecord(new Record('id','1234','http://lms.pknu.ac.kr/ilos/main/member/login_form.acl'));

var UList = new URL_list();
UList.AddURL('https://www.naver.com/','id','pw','로그인','title');
UList.AddURL('https://logins.daum.net/accounts/loginform.do?url=http%3A%2F%2Fwww.daum.net','id','inputPwd','loginBtn','id');
UList.AddURL('https://www.facebook.com/','email','pass','u_0_x','id')
UList.AddURL('http://lms.pknu.ac.kr/ilos/main/member/login_form.acl','usr_id','usr_pwd','login_btn','id');

/*function get_source(document_body){
    var listName = document_body.getElementsByTagName('input');
    for(i=0;i<listName.length;i++){
        document.writeln(listName[i].title);
    }
    //return Array.prototype.slice.call(listName);
    return listName.length;
}*/

/*function get_source(document_body){
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
}*/

function get_source(document_body){
    var listName = document_body.getElementsByTagName('input');

    for(i=0;i<UList.getSize();i++){
        if(UList.getAddition(i)=='title' && UList.getURL(i)==location.href){
            for(k=0;k<listName.length;k++){
                if(listName[k].title==UList.getURLLog(i)){
                    listName[k].onclick = function () {
                        for(j=0;j<MyDB.getSize();j++){
                            if(MyDB.getRecord(j).getURL()==location.href){
                                for(i=0;i<UList.getSize();i++){
                                    if(UList.getURL(i)==location.href){
                                        document.getElementById(UList.getURLID(i)).value = MyDB.getRecord(j).getID();
                                        document.getElementById(UList.getURLPwd(i)).value = MyDB.getRecord(j).getPwd();
                                        break;
                                    }
                                }
                                break
                            }
                        }
                    }
                }
            }
        }
        if(UList.getAddition(i)=='id' && UList.getURL(i)==location.href){
            document.getElementById(UList.getURLLog(i)).onclick = function () {
                for(j=0;j<MyDB.getSize();j++){
                    if(MyDB.getRecord(j).getURL()==location.href){
                        for(i=0;i<UList.getSize();i++){
                            if(UList.getURL(i)==location.href){
                                document.getElementById(UList.getURLID(i)).value = MyDB.getRecord(j).getID();
                                document.getElementById(UList.getURLPwd(i)).value = MyDB.getRecord(j).getPwd();
                                break;
                            }
                        }
                        break
                    }
                }
            }
        }
    }
    return 1;
}

chrome.extension.sendMessage({
    action: "getSource",
    source: get_source(document.body)
});
