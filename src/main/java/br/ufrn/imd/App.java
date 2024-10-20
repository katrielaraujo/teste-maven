package br.ufrn.imd;

import java.math.BigDecimal;

import br.ufrn.imd.model.Discente;
import br.ufrn.imd.model.Disciplina;
import br.ufrn.imd.model.Docente;
import br.ufrn.imd.model.Matricula;
import br.ufrn.imd.model.StatusAprovacao;
import br.ufrn.imd.model.Turma;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Discente aluno = new Discente("Aluno Zé");
        Docente professor = new Docente("Professor João");
        Disciplina disciplina = new Disciplina("Teste de Software");

        Turma turma = new Turma(professor, disciplina);
        Matricula matricula = new Matricula(aluno,turma);

        matricula.cadastrarNota1(BigDecimal.valueOf(3));
        matricula.cadastrarNota2(BigDecimal.valueOf(3));
        matricula.cadastrarNota3(BigDecimal.valueOf(3));
        matricula.cadastrarFrequencia(75);

        matricula.consolidarParcialmente();
        System.out.println(matricula.getStatus());
    }
}
