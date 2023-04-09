'use strict';

const express = require('express');
const router = express.Router();
const reservasService = require('./reservas-service');


router.get('/', function (req, res) {
    reservasService.getAll((err, reservas) => {
            if (err) {
                res.status(500).send({
                    msg: err
                });
            } else if (reservas.length == 0){
            	res.status(500).send({
                    msg: "No existen reservas"
                });
            } else {
                res.status(200).send(reservas);
            }
        }
    );
});

router.delete('/:_id', function (req, res) {
    let _id = req.params._id;
    reservasService.remove(_id, (err) => {
            if (err) {
                res.status(500).send({
                	msg: err
            	});
            } else {
                res.status(200).send({
                    msg: 'La reserva ha sido borrada'
                });
            }
        }
    );
});




router.post('/', function (req, res) {
    let reserva = req.body;
    reservasService.add(reserva, (err, reserva) => {
            if (err) {
                res.status(500).send({
                    msg: err
                });
            } else if (reserva.length != 0){
                res.status(201).send({
                    msg: '¡Reserva creada!'
                });
            }
        }
    );
});


router.delete('/', function (req, res) {
    reservasService.removeAll((err) => {
        if (err) {
            res.status(500).send({
                msg: err
            });
        } else {
            res.status(200).send({
                msg: 'Reservas borradas'
            });
        }
    });
});


router.get('/:_id', function (req, res) {
    let _id = req.params._id;
    reservasService.get(_id, (err, reserva) => {
            if (err) {
                res.status(500).send({
                	msg: err
            	});
            } else if (reserva.length == 0){
            	res.status(500).send({
                    msg: "la reserva no existe"
                });
            } else {
                res.status(200).send(reserva);
            }
        }
    );
});


router.put('/:_id', function (req, res) {
    const _id = req.params._id;
    const updatedreserva = req.body;
    reservasService.update(_id, updatedreserva, (err, numUpdates) => {
        if (err) {
            res.status(500).send({
                msg: err
            });
	} else if(numUpdates.modifiedCount === 0) {
            res.status(500).send({
                msg: "No actualizada"
            });
        } else {
            res.status(200).send({
                msg: 'Reserva actualizada'
            });
        }
    });
});





module.exports = router;
