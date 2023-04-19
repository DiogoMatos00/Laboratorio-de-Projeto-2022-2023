//Objeto para pergunta mas pode não vir a ser usado.

import java.util.List;

public class questions{
    private String id;
    private String description;
    private List<String> answer; // Não sei o q se vai utilizar por isso fica lista por agr.
    private int difficulty;
    private String subject;
    private String theme;
    private String type;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAnswer() {
        return answer;
    }
    
    public int getDifficulty() {
        return difficulty;
    }

    public String getSubject() {
        return subject;
    }

    public String getTheme() {
        return theme;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setType(String type) {
        this.type = type;
    }
}