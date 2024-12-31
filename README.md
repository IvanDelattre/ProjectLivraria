| Classe         | Relação                 | Classe Relacionada |
|----------------|-------------------------|---------------------|
| Cliente        | 1 .. 0..*               | Pedido              |
| Pedido         | 1 .. 0..*               | ItemDePedido        |
| ItemDePedido   | 0..* .. 1               | Livro               |
| Pedido         | 1 .. 0..*               | Fatura              |
| Fatura         | 1 .. 1..*               | ItemFaturado        |

# Regras de Negócio implementadas no projeto

1. **Operações de Inclusão, Exclusão e Alteração**
   - **Cliente e Livro**: Inclusão, exclusão e alteração.
   - **Demais classes**: Inclusão e remoção.

2. **Remoção Condicional**
   - Um objeto só pode ser removido se não estiver associado a nenhuma outra classe.
   - Exemplo: Um cliente só pode ser removido se não houver nenhum pedido associado a ele.

3. **Atributos Derivados na Classe Fatura**
   - Adicionar dois atributos derivados:
     - Valor total da fatura.
     - Valor total do desconto.
   - A partir da quarta fatura emitida para um determinado cliente, ele terá direito a 5% de desconto no valor total da fatura.

4. **Remoção de Faturas**
   - Exceção à regra de remoção condicional: ao remover uma fatura, todos os itens faturados devem ser removidos em cascata e o estoque deve ser atualizado.

5. **Faturamento de Pedidos**
   - Verificar a quantidade disponível no estoque para cada produto listado nos itens de pedido.
   - Atualizar a quantidade em estoque do livro e a quantidade a faturar do ItemDePedido a cada item faturado cadastrado.
   - Um pedido pode ser faturado mais de uma vez, caso alguma quantidade pedida de um livro não exista no estoque no momento do faturamento.

6. **Mensagem de Estoque Zerado**
   - Ao faturar um pedido, se nenhum item de pedido puder ser faturado devido ao estoque zerado para todos os itens, uma mensagem comunicando esse fato deverá ser exibida.

7. **Cancelamento de Pedidos e Faturas**
   - Possibilidade de cancelar um pedido e uma fatura, informando uma data de cancelamento.
   - Ao cancelar uma fatura, o estoque dos livros deve ser atualizado, assim como na remoção de uma fatura.
   - Um cliente só pode cancelar uma fatura após o faturamento de pelo menos 3 pedidos.

8. **Exceções Customizadas**
   - Utilizar exceções customizadas em todos os casos, não lançar `RuntimeException` ou `Exception`.

9. **Novos Atributos**
   - Adicionar o atributo `endereço` na classe `Cliente`.
   - Adicionar o atributo `endereço de entrega` na classe `Pedido`.

10. **Relatórios**
    - Lista contendo a quantidade faturada, o nome do livro e a data da fatura de itens faturados de um determinado livro em um determinado mês e ano.
    - Lista dos produtos que nunca foram faturados.
    - Para um determinado ano e mês, a lista dos produtos faturados com a respectiva quantidade.

11. **Simulação de Envio de Email**
    - Implementar uma simulação de envio de email utilizando thread.
    - Um email deve ser enviado sempre que um cliente finalizar o cadastro de um pedido.

12. **Injeção de Dependências**
    - Acrescentar o uso de `@Autowired` para injeção de dependências.

13. **Persistência em Disco**
    - Todas as informações armazenadas nos Maps definidos nas classes de implementação dos DAOs devem ser salvas em disco.

