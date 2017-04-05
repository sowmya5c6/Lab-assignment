
var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var url = 'mongodb://sowmya:sowmya@ds139430.mlab.com:39430/demoase';
MongoClient.connect(url, function(err, db) {
  assert.equal(null, err);
  console.log("Connected correctly to server.");
  db.close();
});