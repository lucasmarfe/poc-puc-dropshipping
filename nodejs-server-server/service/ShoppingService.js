'use strict';


/**
 * Busca as vendas para um fornecedor específico
 * Retorna a lista de produtos que precisam ser despachados pelo fornecedor especificado
 *
 * cnpj Long CNPJ do fornecedor
 * returns ApiResponse
 **/
exports.getPetById = function(cnpj) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = "";
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}

