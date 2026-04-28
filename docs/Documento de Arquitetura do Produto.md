# **Documento de Arquitetura do Produto**

## **Introdução**

Este documento descreve a arquitetura do sistema “Meu sobrinho”, apresentando o stack e a justificativa para a escolha.

## **Visão Geral da Arquitetura**

O sistema utiliza a arquitetura Cliente-Servidor juntamente com a arquitetura MVC (Model, View e Controller).

As camadas principais da aplicação se dividem em:

**View**: responsável pela interface com o usuário, onde será usado JSP, HTML, CSS e JavaScript.  
**Controller**: responsável pela lógica de negócio, onde será utilizado Servlet  
**Model**: responsável pelo acesso e manipulação dos dados, onde será utilizado java  
**Banco de Dados**: responsável pelo armazenamento, organização e gerenciamento de dados.

![][image1]

## **Justificativa da Arquitetura**

A escolha do modelo Cliente-Servidor foi motivada por permitir o acesso por navegadores sem necessidade de instalação.

A escolha do padrão MVC foi motivada pela boa organização e separação de responsabilidades, além de facilitar a manutenção.

## **Tecnologias Utilizadas**

### **Tecnologias do Sistema**

Frontend: JSP, HTML, CSS e JavaScript.  
Backend: Servlet e Java.  
Banco de Dados: MySQL.

### **Ferramentas de Desenvolvimento**

Controle de Tarefas: Trello.  
Controle de Versão: Git e GitHub.  
Comunicação: Telegram.  
Design: Figma e Canva.  
Documentação: Google Docs.  
IDE: VsCode, Eclipse.

[image1]: <data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAloAAAA9CAIAAAD6V1B2AAAE00lEQVR4Xu3azWscdRzH8UU89ipU/A8Uz3qxVKFeRKwI9dKC4kVLoYKCeBCxBU/V3gLVg6CXKK1WitoHAga1SVpMCdoUU9uap3Zt0+xDkn3M5tvf7kwms9/f7nSym4fZmfeLz2HmNw878yPMp7PdlAAAkHgpPQAAQPJQhwAAUIcAAFCHAAAIdQgAgFCHAAAIdQgAgFCHAAAIdQgAgFCHAAAIdQgAgFCHAAAIdQgAgFCHAAAIdQgAgFCHAAAIdQgAgFCHAAAIdQgAgFCHAAAIdQgAgFCHAAAIdQgAgFCHAAAIdQgAgFCHAABI+DrcmToVp+jbi7BMXl59Nz7Z976+wSj77j959uf4ZDyrbzDKdvx6K06p6fuLtP2Tt+MUfXtttKhDuzwSkrvpkp6LrXX8G10eycm2s8sjIXl5QE/F1rPLIyH5PbvNz5xcrWaXR0Ki58Krwyce/d6uhySneZZC8R8+M1XQm9uzuyHJGRjR8/NQ16eazhBerqK7IeHpgP/wF87rrQGe+3PWrockR09QCP7Dz8wt6c3t2d2Q5Lw55VZj6ugHf9ll0EHMuQqFZf/IG3svShfvmm+9NhRwuHP19niU4/4l+thlsLFp+RHSeA1dWWm9NZY59Kl/1l12GYTJ7nMyu/pPnSNjeutmx7vsO41rsHfoLM/8pEc2MLcWvSl3nb9fsMug+0ibt8wbxepgpmiPB6TdqcLvEJH4Zt11wCqD7tM3l/HOXxUZWSra+0Q//Zl8yn5qd5Y7s0Vp7qfT/dPeyOOPnPpnPO/MV6WyYkYmrtVXvZ39q2bh0h9zJ45fNzHLe3cNmsEfv107m391357f1Oeq1Wgmm6nIVrWR8dVpd8Eb/OKku/pxn8znZGKyKd6B/qPUau/GOHxJP7UfGiNT1oMmB4frm9SeZ2fdBf/41JLsOru2jzrEvzx0r/VWb8GrQ2NyUa5mm2LGXx/UHxGQTa1Df15qfDFrP7U3JN6Zh3Kl5frnuKtm4WKu5CxcmG9qYmefYm0lu1yzx52Fy/mSP3uu3Pbv0EMZydW/mLVroPuYOrxaKvtHrhTczzo8879ZmChVfskvmngXYBaO3b3v7W9Wz+UXnYWxYkmd/+9i+ZP0Pf/IJt1I/ctS+2FNdrZ6mQtmn8HJoQOX9a6rbs7oJzVxsi7qy1J/sgt6Z8+LF/TDmph8OaEnKph9Bi8B7Ic12dHqZS6YfQYnTw/X3xbasWuA7G9U7NpPaZ567Iz9KE9m1v5w1sMc+PnRa3o0HPs5nsyMjuuZCcOpw3L9fXvdlqr6IZ7YdKabY18ZS9uP8mRGT0045sDnR1v8JCQMuw+Smben096ctPhlqcMuiRhH3/y2StorY6TYJRHvRIpdEjFOtfGf99Fh90SMo29+Vds6DPb1iZs99Db52ZEOX9qiqa9fN0pk894xKZb19fe0g8O6USKbD0f1xfe0fwvVJ4en7V6JZj66Ma9voJf9kF2wSyWaeWc6PbCwjh/Z+nVYhwAAxAl1CAAAdQgAAHUIAIBQhwAACHUIAIBQhwAACHUIAIBQhwAACHUIAIBQhwAACHUIAIBQhwAACHUIAIBQhwAACHUIAIBQhwAACHUIAIBQhwAACHUIAIBQhwAACHUIAIBQhwAACHUIAIBQhwAAGA8ARwAcn7+8TG8AAAAASUVORK5CYII=>