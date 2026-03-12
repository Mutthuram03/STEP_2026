import java.util.*;

public class PlagiarismDetector {

private HashMap<String, Set<Integer>> ngramMap;

public PlagiarismDetector() {
    ngramMap = new HashMap<>();
}

public void addDocument(int docId, String text) {

    String[] words = text.split(" ");

    for (int i = 0; i < words.length - 2; i++) {

        String ngram = words[i] + " " + words[i + 1] + " " + words[i + 2];

        ngramMap.putIfAbsent(ngram, new HashSet<>());

        ngramMap.get(ngram).add(docId);
    }
}

public void checkDocument(int docId, String text) {

    String[] words = text.split(" ");
    int matches = 0;

    for (int i = 0; i < words.length - 2; i++) {

        String ngram = words[i] + " " + words[i + 1] + " " + words[i + 2];

        if (ngramMap.containsKey(ngram)) {
            matches++;
        }
    }

    System.out.println("Document " + docId + " similarity matches: " + matches);
}

public static void main(String[] args) {

    PlagiarismDetector detector = new PlagiarismDetector();

    detector.addDocument(1, "this is a sample essay for plagiarism detection system");
    detector.addDocument(2, "this system checks plagiarism in student essay");

    detector.checkDocument(3, "this is a sample essay for testing plagiarism");
}
}
