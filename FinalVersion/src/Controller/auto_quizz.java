package Controller;

import java.util.Hashtable;
import java.util.List;

public class auto_quizz {
    public static void MakeQuizz(int nQuestions, int difficulty, List<String> topics, List<String> question_type){
        // Pegar nome, n_perguntas, UC's, % dos Temas, tipos de pergunta
        // List<String> topics exemple = [topic:A, topic:B, ...]

        Hashtable<String, String> dict;
        int nQ = (int) Math.floor(nQuestions / topics.size());


        for(int i = 0; i<topics.size(); i++){
            String t = topics.get(i);

            //BUSCAR AGR nQ PERGUNTAS RANDOM pelo o tema a ser trabalhado e tudo adicionado ao final.
        }

        //topics.get(0).get("percentage");




        // Pegar na % dos temas e descobrir quantas perguntas vão ser de cada tema.

        // Pegar em cada tema e tentar ter todas as perguntas para o nivel de dificuldade escolhido

        // Se não existir perguntas suficientes é fazer aquilo q esta no quadro pensado com o andré

        // Após ter todas as preguntas apresentar no front-end
    }
}