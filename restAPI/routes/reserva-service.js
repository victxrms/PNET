'use strict';

const MongoClient = require('mongodb').MongoClient;
let db;
let ObjectId = require('mongodb').ObjectId;
const reservas = function () {
};

Movies.prototype.connectDb = function (callback) {
    MongoClient.connect("mongodb+srv://victor:puma@vms-pnet-2022-2023.dbcseeh.mongodb.net/?retryWrites=true&w=majority",
        {useNewUrlParser: true, useUnifiedTopology: true},
        function (err, database) {
            if (err) {
		console.log(err);
                callback(err);
            }

   	    db = database.db('vms-pnet-2022-2023').collection('reservas');
	    console.log("Conexión correcta");

            callback(err, database);
        });
};

Movies.prototype.add = function (reserva, callback) {
    return db.insertOne(reserva, callback);
};

Movies.prototype.get = function (_id, callback) {
    return db.find({_id: ObjectId(_id)}).toArray(callback);
};

Movies.prototype.getAll = function (callback) {
    return db.find({}).toArray(callback);
};

Movies.prototype.update = function (_id, actualizaReserva, callback) {
    delete updatedMovie._id;
    return db.updateOne({_id: ObjectId(_id)}, {$set: actualizaReserva}, callback);};

Movies.prototype.remove = function (_id, callback) {
    return db.deleteOne({_id: ObjectId(_id)}, callback);
};

Movies.prototype.removeAll = function (callback) {
    return db.deleteMany({}, callback);
};

module.exports = new reservas();


