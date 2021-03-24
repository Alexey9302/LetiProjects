package sem4.compression.shannonfano;

import sem4.associativearray.mapka.Mapka;

/**
 * author: Bikchurin Alexey, 9302;
 *
 * How to use:
 * String result = Compression.encode("aaabbc");
 * System.out.print(result);
 */
public class Compression {

    private static char currentSymbol = 'a';

    private static final Mapka<Character, String> code = new Mapka<>();
    private static Mapka.Entry<Character, Double>[] sort;

    private Compression() {

    }

    /**
     * Encode some string
     *
     * @param decodeStr source string
     * @return encode string
     */
    public static String encode(String decodeStr) {
        sort = getSortedValues(decodeStr);
        search(' ', " ", 0, sort.length - 1);
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < decodeStr.length(); i++){
            result.append(code.find(decodeStr.charAt(i)))
                    .append(" ");
        }

        return result.toString();
    }

    /**
     * Encode string with getting EncodeInfo object
     * that contain info for test
     *
     * @param decodeStr source string
     * @return info that contain result of program
     */
    public static EncodeInfo encodeWithGettingObject(String decodeStr){
        String result = encode(decodeStr);
        int resultLen = result.replaceAll(" ", "").length();

        return new EncodeInfo(result, decodeStr.length(), resultLen);
    }

    /**
     * Encode string with full information about the result of program
     *
     * @param decodeStr source string
     * @return full information about the result of program
     */
    public static String encodeWithInfo(String decodeStr) {
        EncodeInfo result = encodeWithGettingObject(decodeStr);
        StringBuilder info = new StringBuilder(result.toString());

        for (Mapka.Entry<Character, Double> characterDoubleEntry : sort) {
            char key = characterDoubleEntry.getKey();
            String keyStr = key + "";

            if(key == ' ')
                keyStr = "Space";
            else if(key == '\n')
                keyStr = "LineBreak";

            info.append(keyStr)
                    .append(" => ")
                    .append(code.find(key))
                    .append("\n");
        }

        return info.toString();
    }

    /**
     * Decode some string
     *
     * @param encodeStr source string
     * @return decode string
     */
    public static String decode(String encodeStr){
        Mapka<String, Character> frequency = new Mapka<>();
        String[] codes = encodeStr.split(" ");
        StringBuilder decodeStr = new StringBuilder();
        currentSymbol = 'a';

        for(String codeElement: codes) {
            if(!frequency.contains(codeElement))
                frequency.insert(codeElement, currentSymbol++);

            decodeStr.append(frequency.find(codeElement));
        }

        return decodeStr.toString();
    }

    private static void search(char last, String lastCode, int l, int r){
        String newCode = (last != ' ')? lastCode + last : "";
        double midValue = 0;
        double sum = 0;
        int m = l;
        int i;

        if(l == r){
            code.insert(sort[l].getKey(), newCode);
            return;
        }

        for(i = l; i < r + 1; i++)
            midValue += sort[i].getValue();
        midValue /= 2;

        for(i = l; sum + sort[i].getValue() < midValue && i < r + 1; i++, m++)
            sum += sort[i].getValue();

        search('1', newCode, l, m);
        search('0', newCode, m + 1, r);
    }

    private static Mapka.Entry<Character, Double>[] getSortedValues(String decodeStr){
        Mapka<Character, Double> frequency = new Mapka<>();

        for(int i = 0; i < decodeStr.length(); i++)
            increment(frequency, decodeStr.charAt(i));

        return frequency.getSortEntryArray((o1, o2) ->
                (int)Math.ceil(o2.getValue() - o1.getValue())
        );
    }

    private static void increment(Mapka<Character, Double> frequency, char symbol){
        if(!frequency.contains(symbol))
            frequency.insert(symbol, 0D);

        frequency.insert(symbol, frequency.find(symbol) + 1);
    }

    /**
     * Class for tests
     */
    public static class EncodeInfo {

        private final String result;
        private final int decodeLen;
        private final int encodeLen;

        public EncodeInfo(String result, int decodeLen, int encodeLen) {
            this.result = result;
            this.decodeLen = decodeLen * 8;
            this.encodeLen = encodeLen;
        }

        public String getResult() {
            return result;
        }

        public int getDecodeLen() {
            return decodeLen;
        }

        public int getEncodeLen() {
            return encodeLen;
        }

        public double getRatio(){
            return (double)decodeLen / encodeLen;
        }

        @Override
        public String toString() {
            StringBuilder info = new StringBuilder();

            info.append("Result: ")
                    .append(result)
                    .append("\n")
                    .append("Compression ratio: ")
                    .append(getRatio())
                    .append("\n")
                    .append("Before: ")
                    .append(getDecodeLen())
                    .append("\n")
                    .append("After: ")
                    .append(getEncodeLen())
                    .append("\n")
                    .append("Codes:")
                    .append("\n");

            return info.toString();
        }
    }
}
