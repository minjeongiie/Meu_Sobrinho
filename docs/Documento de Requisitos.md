# **Documento de Requisitos**

## **Visão Geral**

Este documento apresenta os requisitos funcionais e não funcionais do sistema Meu Sobrinho através de histórias de usuário.

## **Histórias de Usuário**

### **US01 \- Cadastro de Cliente**

#### **História**

Como cliente, eu gostaria de me cadastrar para ter um perfil no site.

#### **Critérios de Aceitação**

* Apenas uma conta por E-mail é permitida.  
* Nome completo e E-mail são obrigatórios.  
* O sistema deve pedir preenchimento de senha duas vezes no ato de cadastro.  
* Todos os campos devem ser validados conforme seu tipo.

### **US02 \- Cadastro de Prestador de Serviços**

#### **História**

Como prestador de serviços, eu gostaria de me cadastrar para ter um perfil no site.

#### **Critérios de Aceitação**

* Apenas uma conta por E-mail é permitida.  
* Nome completo, E-mail, e celular são obrigatórios.  
* O sistema permite cadastro por CPF ou CNPJ.  
* O sistema deve pedir preenchimento de senha duas vezes no ato de cadastro.  
* A seleção de pelo menos uma categoria é obrigatória.  
* Todos os campos devem ser validados conforme seu tipo.

### **US03 \- Autenticação de Usuário**

#### **História**

Como usuário, gostaria de fazer login no site para ter acesso às suas funcionalidades.

#### **Critérios de Aceitação**

* Mensagem de erro será exibida em caso de informações incorretas de login  
* O usuário será redirecionado após autenticação bem sucedida.

### **US04 \- Perfil de Usuário**

#### **História**

Como usuário, eu gostaria de visualizar meus dados pessoais.

#### **Critérios de Aceitação**

* Os dados de cadastro (não inclui senha) devem ser visíveis apenas para o proprietário do perfil.

### **US05 \- Perfil Profissional de Prestador de Serviço**

#### **História**

Como prestador, eu gostaria de ter foto, descrição do meu trabalho e portfólio exibidos de forma pública no site.

#### **Critérios de Aceitação**

* Informações de de um prestador de serviço disponíveis para consulta por qualquer usuário autenticado.   
* Perfil profissional deve incluir nome, categoria, foto de perfil, descrição.  
* Perfil profissional pode incluir portfólio em PDF.


### **US06 \- Edição de Perfil de Usuário**

#### **História**

Como usuário, eu gostaria de alterar meus dados pessoais.

#### **Critérios de Aceitação**

* Todos os campos devem ser validados conforme seu tipo.

### **US07 \- Edição Perfil Profissional de Prestador de Serviço**

#### **História**

Como prestador, eu gostaria de alterar minhas informações em meu perfil profissional.

#### **Critérios de Aceitação**

	

* A inserção de descrição é obrigatória para o perfil se tornar público.  
* A inserção de foto de perfil e portfólio(PDF) são opcionais.  
* Todos os campos devem ser validados conforme seu tipo.


### **US08 \- Recuperação de Senha**

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

### **US09 \- Busca por Profissional**

#### **História**

Como cliente, eu gostaria de buscar e encontrar um bom técnico de informática para fazer um orçamento.

#### **Critérios de Aceitação**

* O sistema deve oferecer uma barra de pesquisa na página principal.  
* Resultados de busca devem ser exibidos em lista com informações de nome do profissional, tipo de serviço e preço médio.


### **US10 \- Cadastro em Categorias**

#### **História**

Como prestador de serviços, eu gostaria de me cadastrar em categorias específicas para disponibilizar meus serviços online e receber ofertas de clientes.

#### **Critérios de Aceitação**

* O sistema deve fornecer sistema de categorização de serviços.

### **US11 \- Sistema de Avaliação**

#### **História**

Como cliente, eu gostaria de saber se o profissional que estou contratando é bom em seu trabalho por meio de avaliações e comentários.

#### **Critérios de Aceitação**

* O sistema deve permitir que clientes façam avaliações em estrelas após terem recebido um serviço.  
* Cliente e Profissional devem confirmar que o serviço foi finalizado antes da avaliação ficar disponível para ser realizada.  
* O sistema deve permitir que clientes façam avaliações em texto de serviços.


### **US12 \- Troca de Mensagens**

#### **História**

Como cliente, eu gostaria de conversar com o prestador antes de contratar para tirar dúvidas.

#### **Critérios de Aceitação**

* O sistema deve permitir que apenas clientes iniciem trocas de mensagens pela primeira vez.  
* O sistema deve permitir que clientes enviem e recebam mensagens.  
* O sistema deve permitir que prestadores de serviços enviem e recebam mensagens.


## **Backlog**

| ID | Nome | Prioridade | Estimativa (dias) |
| :---- | :---- | :---- | :---- |
| US01 | Login de Usuário | Alta |  |
| US02 | Perfil Profissional | Alta |  |
| US03 | Recuperação de Senha | Média |  |
| US04 | Busca por Profissional | Alta |  |
| US05 | Cadastro em Categorias | Alta |  |
| US06 | Sistema de Avaliação | Média |  |
| US07 | Troca de Mensagens | Baixa |  |

