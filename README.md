# POC Dropshipping

Prova de conceito para o sistema de controle de vendas na modalidade dropshipping realizada no curso de pos graduação Arqitetura de software disttribuído da PUC Minas

# Pré requisitos
  - O sistema foi desenvolvido e testado utilizando linux Ubuntu 18.04.2 LTS 64 bits (http://releases.ubuntu.com/18.04/)
  - OpenJDK 8+ (Versão utilizada: 1.8.0_191) (https://openjdk.java.net/install/)
  - Docker Community Edition 1.11+ (https://docs.docker.com/install/linux/docker-ce/ubuntu/)
  - Docker compose (https://docs.docker.com/compose/install/)
  - Git
      ```sh
      apt-get install git
      ```
# Passo 1) Confluent platform

Confluent platform é um conjunto de ferramentas open source construído no topo do Apache kafka para prover facilidades para administração e monitoramento do cluster.
A confluent platform pode ser executada seguindo os passos descritos em: https://docs.confluent.io/current/quickstart/ce-docker-quickstart.html#ce-docker-quickstart

Abaixo a reprodução dos passos até o ponto necessário para a presente POC:
```sh
git clone https://github.com/confluentinc/cp-docker-images
cd cp-docker-images
git checkout 5.2.1-post
cd examples/cp-all-in-one/
docker-compose up -d --build
```
Verifique que todos os containers estão rodando:
```sh
docker-compose ps
```
Acesse a interface web no endereço:
http://localhost:9021/

# Passo 2) MySQL Database

Para executar o banco de dados MySQL utilizado no projeto, foi disponibilizado um Dockerfile capaz de iniciar um container com o MySQL e executar scripts de criação e inserção de dados.

A pasta raiz do projeto possui a pasta [docker mysql](docker/mysql)
```sh
cd docker

docker stop mysql-dropshipping && \
docker rm mysql-dropshipping && \
docker rmi mysql-dropshipping

docker build -t mysql-dropshipping .
docker run -d -p 3306:3306 --name mysql-dropshipping -e MYSQL_ROOT_PASSWORD=12345 -e MYSQL_ROOT_HOST=% mysql-dropshipping
```    
Aguarde o container estar apto a receber solicitações

Verifique se o container contendo o database está executando e pronto para executar tarefas (Senha setada nos passos anteriores):
```sh
mysql -h localhost -P 3306 --protocol=tcp -D ecommerce -u java -p
```
# Passo 3) Documentação da Web API - Serviços REST externos da POC

Acessar a pasta referente ao servidor node contendo a documentação em:[Documentação REST](nodejs-server-server)

Executar os comandos para subir um container docker:
```sh
docker build -t pocdropshipping-doc/node-web-app .
docker run -p 49160:8080 -d pocdropshipping-doc/node-web-app
```
Acessar a documentação em: http://localhost:49160/docs/

# Passo 4) Web API - API de serviços REST da POC (Sistema de vendas)
É possível executar a aplicação a partir do código fonte disponível em: [POCDropshipping](POCDropshipping) executando-a num servidor Apache Tomcat 9.0

Também é possível fazer o deploy do war da web no Apache Tomcat 9.0 por meio do war disponibilizado em: [POCDropshipping WAR](jars/POCDropshipping.war)

# Passo 5) Consumidor - Aplicação que obtem os dados da venda de produtos (Fornecedores)
É possível executar a aplicação a partir do código fonte disponível em: [get sales](get-sales-kafka)

Também é possível e recomendado executar a aplicação diretamente no terminal a partir do jar disponível em: [get sales jar](jars/getSales.jar)
É necessário enviar como argumentos o nome dos tópicos aos quais o processo fará subscriber. Exemplo de uso:
```sh
sudo java -jar getSales.jar provider3_sale
```

# Passo 7) Consumidor - Aplicação que monitora eventos de entrega para informar os envolvidos (Sistema de vendas)
Inicialmente é interessante gerar um servidor fake SMTP para verificar se os emails seriam enviados. Para isso é necessário rodar a aplicação [Fake SMTP ] (http://nilhcem.com/FakeSMTP/). O jar foi disponibilizado em: [fakeSMTP jar] (jars/fakeSMTP-2.0.jar). Portanto, basta executar:
```sh
sudo java -jar fakeSMTP-2.0.jar
```
