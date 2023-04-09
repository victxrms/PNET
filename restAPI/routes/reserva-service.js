'use strict';

const MongoClient = require('mongodb').MongoClient;
let db;
let ObjectId = require('mongodb').ObjectId;
const Reservas = function () {
};

Reservas.prototype.connectDb = function (callback) {
    MongoClient.connect("mongodb+srv://testPNET:testPNET123@cluster0.mfjkdgc.mongodb.net/?retryWrites=true&w=majority",
        {useNewUrlParser: true, useUnifiedTopology: true},
        function (err, database) {
            if (err) {
		  console.log(err);
                callback(err);
            }

   	  db = database.db('PNET-reservas').collection('reservas');
	    console.log("Conexión correcta");

            callback(err, database);
        });
};

Reservas.prototype.getAll = function(callback) {
  //En el ejemplo sólo pone esta línea, pero el error se mantiene al ponerla: return db.find({}).toArray(callback); 
  db.find({}).toArray((err, reservas) => {
      if (err) {
        callback(err);
      } else {
        callback(null, reservas);
      }
    });
  };

Reservas.prototype.remove = function (_id, callback) {
  return db.deleteOne({_id: ObjectId(_id)}, callback);
};

module.exports = new Reservas();
