# **Documento de Requisitos**

## **Visão Geral**

Este documento apresenta os requisitos funcionais e não funcionais do sistema Meu Sobrinho através de histórias de usuário.

## **Atores**

* Cliente  
* Prestador de serviços


## **Histórias de Usuário**

### **US01 \- Cadastro de Cliente**

#### **História**

Como cliente, eu gostaria de me cadastrar para ter um perfil no site.

#### **Critérios de Aceitação**

* Apenas uma conta por E-mail é permitida.  
* Nome completo e E-mail e CPF são obrigatórios.  
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

Como prestador, eu gostaria de ter foto, descrição do meu trabalho, valor médio de serviço  e portefólio exibidos de forma pública no site.

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

Como prestador, eu gostaria de inserir minhas informações profissionais em meu perfil.

#### **Critérios de Aceitação**

	

* A inserção de descrição e valor médio de serviço são obrigatórios para o perfil se tornar público.  
* A inserção de foto de perfil e portfólio(PDF) são opcionais.  
* Todos os campos devem ser validados conforme seu tipo.


### **US08 \- Recuperação de Senha**

#### **História**

Como usuário, eu gostaria de recuperar meu acesso em caso de esquecimento de senha.

#### **Critérios de Aceitação**

* Dados obrigatórios para recuperação de senha: E-mail e resposta de segurança.  
* Pergunta e resposta de segurança são criadas pelo usuário no momento do cadastro.  
* A opção de recuperação de senha deve estar sempre disponível na tela de login.  
* Resposta de erro é exibida caso o E-mail apresentado não esteja cadastrado no sistema.  
* Resposta de erro é exibida caso a resposta de segurança não seja correta.  
* A nova senha deve ser confirmada duas vezes para concluir a alteração.  
* Senhas distintas causam mensagem de erro.

### **US09 \- Busca por Profissional**

#### **História**

Como cliente, eu gostaria de fazer uma pesquisa para encontrar um prestador que atenda as minhas necessidades.

#### **Critérios de Aceitação**

* O sistema deve oferecer uma barra de pesquisa na página principal.  
* O sistema deve retornar profissionais cujo nome ou categoria corresponda ao termo pesquisado.  
* O campo de texto não pode estar vazio.  
* Resultados de busca devem ser exibidos em lista com informações de nome do profissional, categoria de serviço.


### **US10 \- Filtro de Resultados de Pesquisa**

#### **História**

Como cliente, eu gostaria de filtrar minha pesquisa para conseguir resultados mais precisos.

#### **Critérios de Aceitação**

* O sistema deve oferecer opção de filtragem juntamente a barra de pesquisa.  
* Os tipos de filtragem devem ser por nome, categoria e preço médio.

### **US11 \- Cadastro em Categorias**

#### **História**

Como prestador de serviços, eu gostaria de me cadastrar em categorias específicas para disponibilizar meus serviços online e receber ofertas de clientes.

#### **Critérios de Aceitação**

* O sistema deve fornecer uma lista de categorias a serem selecionadas pelo prestador ao adicionar informações profissionais ao perfil.  
* Novas categorias podem ser cadastradas pelo prestador.

### **US12 \- Contratar Serviço**

#### **História**

Como cliente, gostaria de registrar que contratei o profissional.

#### **Critérios de Aceitação**

* Apenas o cliente pode iniciar esse processo.  
* A contratação apenas ocorre com confirmação de ambas as partes.

### **US13 \- Histórico de Serviço**

#### **História**

Como usuário , gostaria de consultar contratações passadas.

#### **Critérios de Aceitação**

* O prestador e o cliente devem ter acesso a seus históricos de contratação.

### **US14 \- Sistema de Avaliação**

#### **História**

Como cliente, eu gostaria de avaliar o serviço que recebi.

#### **Critérios de Aceitação**

* A avaliação está apenas disponível após conclusão do serviço.  
* As avaliações devem conter campos de avaliação por estrelas e por texto.  
* A avaliação por texto pode estar vazia.  
* O sistema deve permitir que clientes façam avaliações em texto de serviços.


### **US15 \- Troca de Mensagens**

#### **História**

Como cliente, eu gostaria de conversar com o prestador antes de contratar para tirar dúvidas.

#### **Critérios de Aceitação**

* O sistema deve permitir que apenas clientes iniciem trocas de mensagens pela primeira vez.  
* O sistema deve permitir que clientes enviem e recebam mensagens.  
* O sistema deve permitir que prestadores de serviços enviem e recebam mensagens.


## **Backlog do Produto**

| ID | Nome | Prioridade | Estimativa (dias) |
| :---- | :---- | :---- | :---- |
| US01 | Cadastro de Cliente | Alta | 2 dias |
| US02 | Cadastro de Prestador de Serviços | Alta | 3 dias |
| US03 | Autenticação de Usuário | Alta | 2 dias |
| US04 | Perfil de Usuário | Média | 2 dias |
| US05 | Perfil Profissional de Prestador de Serviço | Alta | 3 dias |
| US06 | Edição de Perfil de Usuário | Média | 2 dias |
| US07 | Edição Perfil Profissional de Prestador de Serviço | Alta | 2 dias |
| US08 | Recuperação de Senha | Baixa | 2 dias |
| US09 | Busca por Profissional | Alta | 3 dias |
| US10 | FIltro de Resultados de Pesquisa | Baixa | 2 dias |
| US11 | Cadastro em Categorias | Alta | 2 dias |
| US12 | Contratar Serviço | Alta | 3 dias |
| US13 | Histórico de Serviço | Média | 2 dias |
| US14 | Sistema de Avaliação | Média | 3 dias |
| US15 | Troca de Mensagem | Baixa | 4 dias |

## **Requisitos não Funcionais**

* ### Responsividade

O sistema deve ser utilizado em dispositivos móveis e desktops. 

* ### Compatibilidade 

  O sistema deve funcionar nos principais navegadores modernos (Chrome, Edge, Firefox). 


* ### Manutenibilidade 

    
  O desenvolvimento do sistema deve seguir o padrão MVC para facilitar a manutenção e evolução. 

* ### Segurança

    
  Senhas devem ser armazenadas de forma criptografada.  
  