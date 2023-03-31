'use strict';

const express = require('express');
const router = express.Router();
const reservaService = require('./reserva-service');

router.get('/', function (req, res) {
    reservaService.getAll((err, reservas) => {
            if (err) {
                res.status(500).send({
                    msg: err
                });
            } else if (reservas.length == 0){
            	res.status(500).send({
                    msg: "reservas null"
                });
            } else {
                res.status(200).send(reservas);
            }
        }
    );
});

router.post('/', function (req, res) {
    let reserva = req.body;
    reservaService.add(movie, (err, movie) => {
            if (err) {
                res.status(500).send({
                    msg: err
                });
            } else if (movie.length != 0){
                res.status(201).send({
                    msg: 'Reserva creada!'
                });
            }
        }
    );
});


router.delete('/', function (req, res) {
    moviesService.removeAll((err) => {
        if (err) {
            res.status(500).send({
                msg: err
            });
        } else {
            res.status(200).send({
                msg: 'Films deleted!'
            });
        }
    });
});


router.get('/:_id', function (req, res) {
    let _id = req.params._id;
    moviesService.get(_id, (err, movie) => {
            if (err) {
                res.status(500).send({
                	msg: err
            	});
            } else if (movie.length == 0){
            	res.status(500).send({
                    msg: "movie is null"
                });
            } else {
                res.status(200).send(movie);
            }
        }
    );
});


router.put('/:_id', function (req, res) {
    const _id = req.params._id;
    const updatedMovie = req.body;
    moviesService.update(_id, updatedMovie, (err, numUpdates) => {
        if (err) {
            res.status(500).send({
                msg: err
            });
	} else if(numUpdates.modifiedCount === 0) {
            res.status(500).send({
                msg: "Not updated"
            });
        } else {
            res.status(200).send({
                msg: 'Film updated!'
            });
        }
    });
});


/* COMPLETAR:
router.delete('COMPLETAR', function (req, res) {
    let _id = COMPLETAR;
    moviesService.COMPLETAR(_id, (err) => {
       COMPLETAR
    });
});*/

module.exports = router;
