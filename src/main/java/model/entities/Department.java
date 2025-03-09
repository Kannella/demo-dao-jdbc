package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {

    private Integer id;
    private String name;

    public Department() {

    }

    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Gerando o hashcode e o equals para que meus objetos possam ser comparados pelo conteudo e nao pela referencia de ponteiros
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /*
    Em Java, para que um objeto possa ser convertido em uma sequência de bytes (serializado) e,
    posteriormente, reconstruído (desserializado), ele precisa implementar a interface Serializable.
    Isso é útil para:

        Trafegar objetos em rede (exemplo: comunicação entre sistemas).
        Gravar objetos em arquivos (exemplo: salvar estados de um programa).
        Armazenamento em cache (exemplo: persistência temporária de dados).

    Exemplo

    public class SerializacaoExemplo {
        public static void main(String[] args) {
            Pessoa pessoa = new Pessoa("João", 30);

            // Serializando o objeto
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("pessoa.ser"))) {
                out.writeObject(pessoa);
                System.out.println("Objeto serializado com sucesso!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Desserializando o objeto
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("pessoa.ser"))) {
                Pessoa pessoaLida = (Pessoa) in.readObject();
                System.out.println("Objeto desserializado: " + pessoaLida);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    */
}
