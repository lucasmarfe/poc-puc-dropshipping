'use strict';

var utils = require('../utils/writer.js');
var Shipping = require('../service/ShippingService');

module.exports.uploadFile = function uploadFile (req, res, next) {
  var body = req.swagger.params['body'].value;
  var cnpj = req.swagger.params['cnpj'].value;
  Shipping.uploadFile(body,cnpj)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};
