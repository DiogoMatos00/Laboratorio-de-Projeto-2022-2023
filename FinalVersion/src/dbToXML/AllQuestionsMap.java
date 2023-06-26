package dbToXML;

import java.util.HashMap;
import java.util.Map;

public class AllQuestionsMap {
    public static Map<Integer, String> combineQuestions(String essayFilePath, String matchFilePath, String shortAnswerFilePath, String multipleChoiceFilePath) {
        Map<Integer, String> combinedMap = new HashMap<>();

        // Convert essay questions to Moodle XML
        Map<String, String> essayMap = Essay.convertToMoodleXML(essayFilePath);
        addToCombinedMap(essayMap, combinedMap);

        // Convert match questions to Moodle XML
        Map<String, String> matchMap = Match.convertToMoodleXML(matchFilePath);
        addToCombinedMap(matchMap, combinedMap);

        // Convert short answer questions to Moodle XML
        Map<String, String> shortAnswerMap = ShortAnswer.convertToMoodleXML(shortAnswerFilePath);
        addToCombinedMap(shortAnswerMap, combinedMap);

        // Convert multiple choice questions to Moodle XML
        Map<Integer, String> multipleChoiceMap = MultipleChoice.processAllQuestions(multipleChoiceFilePath);
        combinedMap.putAll(multipleChoiceMap);

        return combinedMap;
    }

    private static void addToCombinedMap(Map<String, String> sourceMap, Map<Integer, String> combinedMap) {
        for (Map.Entry<String, String> entry : sourceMap.entrySet()) {
            int questionId = Integer.parseInt(entry.getKey());
            String moodleXML = entry.getValue();
            combinedMap.put(questionId, moodleXML);
        }
    }
}
