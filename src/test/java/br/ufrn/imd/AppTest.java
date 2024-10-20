package br.ufrn.imd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import br.ufrn.imd.model.Discente;
import br.ufrn.imd.model.Disciplina;
import br.ufrn.imd.model.Docente;
import br.ufrn.imd.model.Matricula;
import br.ufrn.imd.model.StatusAprovacao;
import br.ufrn.imd.model.Turma;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @ParameterizedTest
    @CsvSource({
        "6.0, 7.0, 8.0, 80, APR, PROVADO",
        "3.0, 3.0, 3.0, 75, REC, RECUPERACAO",
        "2.0, 2.0, 2.0, 75, REP, REPROVADO POR MEDIA",
        "6.0, 7.0, 8.0, 60, REPF, REPROVADO POR FALTA",
        "2.0, 2.0, 2.0, 55, REPMF, REPROVADO POR MEDIA E FALTA"
    })
    @DisplayName("Aluno {5}")
    public void alunoAprovado(BigDecimal nota1,BigDecimal nota2,
    BigDecimal nota3,Integer frenquencia, String stat){

        Discente aluno = new Discente("Aluno Zé");
        Docente professor = new Docente("Professor João");
        Disciplina disciplina = new Disciplina("Teste de Software");

        Turma turma = new Turma(professor, disciplina);
        Matricula matricula = new Matricula(aluno,turma);

        matricula.cadastrarNota1(nota1);
        matricula.cadastrarNota2(nota2);
        matricula.cadastrarNota3(nota3);
        matricula.cadastrarFrequencia(frenquencia);
        StatusAprovacao status = StatusAprovacao.valueOf(stat);
        matricula.consolidarParcialmente();

        //assertTrue(matricula.calcularMediaParcial().equals(BigDecimal.valueOf(7.00)));
        assertEquals(status, matricula.getStatus());   
    }
}
