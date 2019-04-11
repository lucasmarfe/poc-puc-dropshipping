'use strict';

var utils = require('../utils/writer.js');
var Shopping = require('../service/ShoppingService');

module.exports.getPetById = function getPetById (req, res, next) {
  var cnpj = req.swagger.params['cnpj'].value;
  Shopping.getPetById(cnpj)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};
