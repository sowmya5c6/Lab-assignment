
var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var url = 'mongodb://root:root@ds161099.mlab.com:61099/asesample';
var findUser = function(db, callback) {
    var cursor =db.collection('users').find( );
    cursor.each(function(err, doc) {
        assert.equal(err, null);
        if (doc != null) {
            console.log(doc);
        } else {
            callback();
        }
    });
};
var findUserwithName = function(db,callback) {
    var cursor = db.collection('users').find({"fname":"sree"});
    cursor.each(function(err,doc) {
        assert.equal(err,null);
        if(doc != null)
        {
            console.log("First Name:" + doc.fname);
            var hey=doc.fname;
            localStorage.setItem()
            console.log("Last Name:" + doc.lname);
            console.log("city:" + doc.address.city);
        }
    });
}
/*var findUserwithUniversity = function(db, callback) {
    var cursor = db.collection('hello').find({"education.university":"UMKC"});
    cursor.each(function(err,doc){
       assert.equal(err,null);
       if(doc != null)
       {
           console.log("First Name:" + doc.fname);
           console.log("University:" + doc.education.university);
           console.log("Degree:" + doc.education.degree);
           console.log("Major:" + doc.education.major);
           console.log("mail:" + doc.mail);
       }
    });
}*/
MongoClient.connect(url, function(err, db) {
    assert.equal(null, err);
   findUserwithName(db, function() {
       db.close();
    });
});