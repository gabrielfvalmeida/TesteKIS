import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.StringTokenizer;

public class Exam {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String vowels = "aeiouy";
        String prefix;
        String stem;
        String auxInput;
        char firstLetter;
        String line = scan.nextLine();

        StringTokenizer st = new StringTokenizer(line, " ");
        while (st.hasMoreTokens()) {
            StringBuilder sb = new StringBuilder();
            prefix = "";
            stem = "";
            String input = st.nextToken();
            String regex = "[\\p{Punct}]";
            Pattern pattern = Pattern.compile(regex);           //o regex para pontuação não estava funcionando com o método .matches,
            Matcher matcher = pattern.matcher(input);           //então tive que usar de 2 formas

            boolean havePunct = matcher.find();

            if (havePunct) {
                auxInput = input.substring(0, input.length() - 1);           //o auxInput eu utilizei para tirar a pontuação do final se necessário
            } else {                                                         //pois atrapalhava o uso do regex para checar se eram todas vogais
                auxInput = input;
            }

            boolean isAllVowels = Pattern.matches("^[aeiouyAEIOUY]+$", auxInput);     //a outra forma como usei regex

            if (!Objects.equals(input, "")) {
                firstLetter = input.charAt(0);
                auxInput = auxInput.toLowerCase();

                for (int i = 0; i < auxInput.length(); i++) {
                    if (!vowels.contains(String.valueOf(auxInput.charAt(i)))) {
                        prefix = sb.append(auxInput.charAt(i)).toString();
                    } else {
                        stem = auxInput.substring(i);
                        break;
                    }
                }

                String punct = havePunct ? input.substring(input.length() - 1) : "";  //escreve a pontuação quando tiver

                printTranslation(auxInput,stem,prefix,punct,firstLetter,isAllVowels);       //apesar de ter muitos argumentos,
            }                                                                               //preferi fazer assim para que quem fosse ver o código
        }                                                                                   //pelo menos tivesse a opção de minimizar a função, que ficou grande
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void printTranslation(String temp_match, String stem, String prefix, String punct, char firstLetter, boolean isAllVowels) {
        if (isNumeric(temp_match)) {
            System.out.print(temp_match);
            System.out.print(" ");
        } else {
            String translation;
            if (Character.isUpperCase(firstLetter)) {
                if (isAllVowels) {
                    System.out.print(stem.substring(0, 1).toUpperCase() + stem.substring(1) + "yay" + punct);
                    System.out.print(" ");
                } else {
                    translation = stem + prefix + "ay" + punct;
                    System.out.print(translation.substring(0, 1).toUpperCase() + translation.substring(1));
                    System.out.print(" ");
                }
            } else {
                if (isAllVowels) {
                    System.out.print(stem + "yay" + punct);
                    System.out.print(" ");
                } else {
                    translation = stem + prefix + "ay" + punct;
                    System.out.print(translation);
                    System.out.print(" ");
                }
            }
        }
    }
}
