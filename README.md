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
```
