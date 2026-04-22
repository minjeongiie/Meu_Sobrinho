# **Documento de Requisitos**

## **Visão Geral**

Este documento apresenta os requisitos funcionais e não funcionais do sistema Meu Sobrinho através de histórias de usuário.

## **Histórias de Usuário**

### **US01 \- Login de Usuário**

#### **História**

Como um novo usuário, eu gostaria de me cadastrar e fazer login no site para que eu possa gerenciar meus dados pessoais e serviços.

#### **Critérios de Aceitação**

* O sistema deve apresentar as opções de login, cadastro de cliente e cadastro de prestador na página inicial.  
* A página de cadastro de cliente deve conter um formulário requisitando nome completo, e-mail, senha e confirmação de senha.  
* A página de cadastro de prestador deve conter um formulário requisitando nome completo, CPF, celular, e-mail, senha, confirmação de senha.  
* Apenas uma conta é permitida por e-mail.  
* A página de login deve conter um formulário requisitando e-mail e senha.  
* O sistema deve permitir que o usuário acesse seu perfil para observação, alteração ou adição de dados pessoais.  
* Se o usuário inserir informações incorretas de login ele receberá mensagem de erro.

### **US02 \- Perfil Profissional**

#### **História**

Como prestador , eu gostaria ter um perfil com descrição e portfólio.

#### **Critérios de Aceitação**

* Com o usuário prestador logado o sistema deve permitir que sejam adicionadas, editadas e observadas informações de descrição e portfólio.


### **US03 \- Recuperação de Senha**

#### **História**

Como usuário, eu gostaria de recuperar meu acesso em caso de esquecimento de senha.

#### **Critérios de Aceitação**

* A tela de login deve disponibilizar um botão para tela de recuperação de senha.  
* O formulário de cadastro de usuário deve conter os campos de pergunta de segurança e resposta de segurança.   
* Na tela de recuperação de senha o sistema deve exibir um campo para preenchimento de e-mail a ser preenchido para prosseguir.  
* A tela dois de recuperação aparece somente se o e-mail preenchido estiver cadastrado no sistema, caso contrário apresenta mensagem de erro.  
* A tela dois de recuperação deve apresentar a pergunta de recuperação atrelada ao e-mail preenchido e um campo para resposta de recuperação.  
* A tela de alteração de senha apenas aparece em caso de resposta correta na tela dois de recuperação.  
* A tela de alterar senha deve conter os campos de nova senha e confirmar nova senha

### **US04 \- Busca de Profissional**

#### **História**

Como cliente, eu gostaria de buscar e encontrar um bom técnico de informática para fazer um orçamento.

#### **Critérios de Aceitação**

* O sistema deve oferecer uma barra de pesquisa na página principal.  
* Resultados de busca devem ser exibidos em lista com informações de nome do profissional, tipo de serviço e preço médio.


### **US05 \- Cadastro em Categorias**

#### **História**

Como prestador de serviços, eu gostaria de me cadastrar em categorias específicas para disponibilizar meus serviços online e receber ofertas de clientes.

### **US06 \- Sistema de Avaliação**

#### **História**

Como cliente, eu gostaria de saber se o profissional que estou contratando é bom em seu trabalho por meio de avaliações e comentários.

### **US07 \- Troca de Mensagens**

#### **História**

Como cliente, eu gostaria de conversar com o prestador antes de contratar para tirar dúvidas.