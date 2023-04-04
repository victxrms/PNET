'use strict';

const express = require('express');
const router = express.Router();
const reservasService = require('./reserva-service');


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

module.exports = router;
