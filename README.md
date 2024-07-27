Olá, esse é um pequeno projeto Backend que busca integrar uma aplicação a o banco de dados PostgreSQL rodando em nuvem na Amazom AWS. Aqui vou dar uma explicada geral sobre o que exatamente está sendo comunicado com o Banco.

- A começar pela tela de cadastro;
- ![Captura de tela 2024-07-26 181749](https://github.com/user-attachments/assets/6f790777-d81a-4b5c-8521-d330801edb9b)

  Aqui foram implementadas algumas coisas como, o programa não deixar cadastrar Email/CPF que já se encontra no Banco de dados, gerar um Id unico para cada conta, a data em que o cadastro foi feito e uma maior segurança na senha. Por ser um  dado sensivel, ela passa por uma encriptação que gera um hash baseado em sha-256
  e o guarda, dessa maneira estaremos comparando duas chaves hash para executar o login do usuário, nunca a senha base, trazendo mais segurança.

  Segue exemplo de como os dados são armazenados:
  
  
![banco](https://github.com/user-attachments/assets/66840ffd-d04b-4200-9812-c82d158eac78)

Em seguida temos a tela de login;

![Captura de tela 2024-07-26 181920](https://github.com/user-attachments/assets/fb874c61-fc3b-4c8f-b290-01d261016d3b)

aqui não temos nada de especial, apenas geramos um hash da senha e verificamos se ele é o mesmo guardado em banco referente a o Email que está tentando fazer login.

Após as etapas citadas a cima, chegamos na tela de transações;

![Captura de tela 2024-07-26 221613](https://github.com/user-attachments/assets/fda50904-c8e4-4e6e-a9d8-0d288f11ff1a)

Unico detalhe aqui é que seu saldo e a data está sempre sendo registrado no banco toda vez que uma operação é realizada.


Vale lembrar que todos os dados são ficticios e que isso não deve ser levado como referência para um produto final, é apenas um material meu de estudo. Dito isso, bons estudos para vocês também.

See you all in space, cowboy.
