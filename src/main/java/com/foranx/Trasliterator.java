package com.foranx;

import java.text.Normalizer;

public class Trasliterator  {
    public void transliterate(String input) {
        if (isLatinAlphabet(input)) {
            System.out.println("Latin: \t" + input);
            System.out.println("Russian: \t" + fromLatinToRussian(input));
        }
        else {
            System.out.println("Latin (from Uzbek): \t" + fromUzbekToLatin(input));
            System.out.println("Latin (from Russian): \t" + fromRussianToLatin(input));
            System.out.println("Cyrillic: \t\t" + input);
        }
    }

    private static boolean isLatinAlphabet(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                return false;
            }
        }
        return true;
    }

    public String fromUzbekToLatin(String input) {
        String normalizedText = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toUpperCase();

        StringBuilder result = new StringBuilder(input.length()*2);

        for (int i = 0; i < normalizedText.length(); i++) {
            char currentChar = normalizedText.charAt(i);
            char nextChar = (i + 1 < normalizedText.length()) ? normalizedText.charAt(i + 1) : '\0';

            // Транслитерация букв
            switch (currentChar) {
                case 'А': result.append("A"); break;
                case 'Б': result.append("B"); break;
                case 'В': result.append("V"); break;
                case 'Г': result.append("G"); break;
                case 'Д': result.append("D"); break;
                case 'Е': result.append("E"); break;
                case 'Ё': result.append("E"); break;
                case 'Ж': result.append("J"); break;
                case 'З': result.append("Z"); break;
                case 'И': result.append("I"); break;
                case 'Й': result.append("Y"); break;
                case 'К': result.append("K"); break;
                case 'Л': result.append("L"); break;
                case 'М': result.append("M"); break;
                case 'Н': result.append("N"); break;
                case 'О': result.append("O"); break;
                case 'П': result.append("P"); break;
                case 'Р': result.append("R"); break;
                case 'С': result.append("S"); break;
                case 'Т': result.append("T"); break;
                case 'У': result.append("U"); break;
                case 'Ф': result.append("F"); break;
                case 'Х': result.append("X"); break;
                case 'Ц': result.append("S"); break;
                case 'Ч': result.append("CH"); break;
                case 'Ш': result.append("SH"); break;
                case 'Щ': result.append("SH"); break;
                case 'Ъ': result.append("HH"); break;
                case 'Ы': result.append("I"); break;
                case 'Ь': result.append(""); break;
                case 'Э': result.append("E"); break;
                case 'Ю': result.append("YU"); break;
                case 'Я': result.append("YA"); break;
                case '\"': result.append("'"); break;
                case '\\':  result.append(""); break;
                default: result.append(currentChar); break;
            }

            // Обработка особых случаев
            if (currentChar == 'Г' || currentChar == 'г') {
                if (nextChar == 'Ъ' || nextChar == 'ъ' || nextChar == 'ь') {
                    result.append("ʻ");
                    i++;
                }
            } else if (currentChar == 'Х' || currentChar == 'х') {
                if (nextChar == 'Ъ' || nextChar == 'ъ' || nextChar == 'ь') {
                    result.append("ʻ");
                    i++;
                } else if (nextChar == 'Ч' || nextChar == 'ч') {
                    result.append("CH");
                    i++;
                }
            } else if (currentChar == 'Ц' || currentChar == 'ц') {
                if (nextChar == 'Ь' || nextChar == 'ь') {
                    result.append("'");
                    i++;
                }
            }
        }
        return result.toString().toUpperCase();
    }

    public String fromRussianToLatin(String input){
        StringBuilder sb = new StringBuilder(input.length()*2);
        for(char ch: input.toUpperCase().toCharArray()){
            switch (ch) {
                case 'А': sb.append("A"); break;
                case 'Б': sb.append("B"); break;
                case 'В': sb.append("V"); break;
                case 'Г': sb.append("G"); break;
                case 'Д': sb.append("D"); break;
                case 'Е': sb.append("E"); break;
                case 'Ё': sb.append("JE"); break;
                case 'Ж': sb.append("ZH"); break;
                case 'З': sb.append("Z"); break;
                case 'И': sb.append("I"); break;
                case 'Й': sb.append("Y"); break;
                case 'К': sb.append("K"); break;
                case 'Л': sb.append("L"); break;
                case 'М': sb.append("M"); break;
                case 'Н': sb.append("N"); break;
                case 'О': sb.append("O"); break;
                case 'П': sb.append("P"); break;
                case 'Р': sb.append("R"); break;
                case 'С': sb.append("S"); break;
                case 'Т': sb.append("T"); break;
                case 'У': sb.append("U"); break;
                case 'Ф': sb.append("F"); break;
                case 'Х': sb.append("KH"); break;
                case 'Ц': sb.append("C"); break;
                case 'Ч': sb.append("CH"); break;
                case 'Ш': sb.append("SH"); break;
                case 'Щ': sb.append("JSH"); break;
                case 'Ъ': sb.append("HH"); break;
                case 'Ы': sb.append("IH"); break;
                case 'Ь': sb.append("JH"); break;
                case 'Э': sb.append("EH"); break;
                case 'Ю': sb.append("JU"); break;
                case 'Я': sb.append("JA"); break;
                case '\"': sb.append("'"); break;
                case '\\': sb.append(""); break;
                default: sb.append(String.valueOf(ch)); break;
            }
        }
        return sb.toString();
    }
    
    public String fromLatinToRussian(String input) {
        input = input.toUpperCase();
        StringBuilder result = new StringBuilder(input.length()*2);
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            char nextChar = (i + 1 < input.length()) ? input.charAt(i + 1) : '\0';
            char next2Char = (i + 2 < input.length()) ? input.charAt(i + 2) : '\0';

            // Транслитерация букв
            switch (currentChar) {
                case 'A': result.append("А"); break;
                case 'B': result.append("Б"); break;
                case 'D': result.append("Д"); break;
                case 'E': result.append("Е"); break;
                case 'F': result.append("Ф"); break;
                case 'G': result.append("Г"); break;
                case 'I': break;
                case 'J': break;
                case 'K': break;
                case 'L': result.append("Л"); break;
                case 'M': result.append("М"); break;
                case 'N': result.append("Н"); break;
                case 'O': result.append("О"); break;
                case 'P': result.append("П"); break;
                case 'R': result.append("Р"); break;
                case 'S': break;
                case 'T': result.append("Т"); break;
                case 'U': result.append("У"); break;
                case 'V': result.append("В"); break;
                case 'Y': result.append("Й"); break;
                case 'Z': break;
                case '\"': result.append("'"); break;
                case '\\': result.append(""); break;
            }

            // Обработка особых случаев
            if (currentChar == 'C' && nextChar == 'H') {
                result.append("Ч");
                i++;
            } else if (currentChar == 'C') result.append("Ц");

            if (currentChar == 'H' && nextChar == 'H') {
                result.append("Ъ");
                i++;
            }

            if (currentChar == 'I' && nextChar == 'H') {
                result.append("Ы");
                i++;
            } else if (currentChar == 'I') result.append('И');

            if (currentChar == 'J' && nextChar == 'E') {
                result.append("Ё");
                i++;
            }
            else if (currentChar == 'J' && nextChar == 'S' && next2Char == 'H') {
                result.append("Щ");
                i += 2;
            }
            else if (currentChar == 'J' && nextChar == 'H') {
                result.append("Ь");
                i++;
            }
            else if (currentChar == 'J' && nextChar == 'U') {
                result.append("Ю");
                i++;
            }
            else if (currentChar == 'J' && nextChar == 'A') {
                result.append("Я");
                i++;
            }

            if (currentChar == 'K' && nextChar == 'H') {
                result.append("Х");
                i++;
            } else if (currentChar == 'K') result.append('К');

            if (currentChar == 'S' && nextChar == 'H') {
                result.append("Ш");
                i++;
            } else if (currentChar == 'S') result.append('С');

            if (currentChar == 'Z' && nextChar == 'H') {
                result.append("Ж");
                i++;
            } else if (currentChar == 'Z') result.append('З');
        }
        return result.toString();
    }
}