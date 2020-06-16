public class MarkovModel {
    String text = "";
    int order = 0;
    ST st = new ST();

    // creates a Markov model of order k for the specified text
    public MarkovModel(String t, int k){
        text = t;
        order = k;
        for(int i = 0; i < text.length(); i++){
            String[] arr = new String[order+1];
            for(int j = 0; j < arr.length; j++){
                arr[j] = "";
            }

            for(int j = 0; j < arr.length; j++){
                arr[j] += text.charAt((i+j)%text.length()) + "";
            }

            String key = "";
            for(int j = 0; j < arr.length-1; j++){
                key += arr[j];
            }

            String value = arr[arr.length-1];

            if(!st.contains(key)){
                st.put(key, value);
            }
            else{
                st.put(key, st.get(key) + value);
            }
        }

        // for(int i = order; i < text.length(); i++){
        //     if(!st.contains(text.substring(i-order,i))) {
        //         st.put(text.substring(i - order, i), text.substring(i, i + 1));
        //     }
        //     else{
        //         st.put(text.substring(i - order, i), st.get(text.substring(i - order, i)) + text.substring(i, i + 1));
        //     }
        // }
        // st.put(text.substring(text.length()-order), text.substring(0, 1));
        // st.put(text.substring(text.length()-1) + text.substring(0, 1), text.substring(1,2));
    }

    // returns the order k of this Markov model
    public int order(){
        return order;
    }

    public static ST getOccuringChar(String str)
    {
        // Create an array of size 256 i.e. ASCII_SIZE
        int count[] = new int[9000];
        ST st = new ST();

        int len = str.length();

        // Initialize count array index
        for (int i = 0; i < len; i++)
            count[str.charAt(i)]++;

        // Create an array of given String size
        char ch[] = new char[str.length()];
        for (int i = 0; i < len; i++) {
            ch[i] = str.charAt(i);
            int find = 0;
            for (int j = 0; j <= i; j++) {

                // If any matches found
                if (str.charAt(i) == ch[j])
                    find++;
            }

            if (find == 1)
                st.put(str.charAt(i), count[str.charAt(i)]);

        }

        return st;
    }

    // returns a string representation of the Markov model (as described below)
    public String toString(){
        String s = "";
        for (Object key : st.keys()) {
            s += key + ": ";
            ST sc = getOccuringChar((String)(st.get((Comparable) key)));
            for (Object k : sc.keys()) {
                s += k + " ";
                s += sc.get((Comparable) k) + " ";
            }
            s += "\n";
        }
        return s;
    }

    // returns the number of times the specified kgram appears in the text
    public int freq(String kgram){
        return ((String)(st.get((Comparable) kgram))).length();
    }

    // returns the number of times the character c follows the specified
    // kgram in the text
    public int freq(String kgram, char c){
        return (int)(getOccuringChar((String)(st.get((Comparable) kgram))).get(c));
    }

    // returns a random character that follows the specified kgram in the text,
    // chosen with weight proportional to the number of times that character
    // follows the specified kgram in the text
    public char random(String kgram){
        int rand = (int) (Math.random() * freq(kgram))+1;
        ST temp = getOccuringChar((String)(st.get((Comparable) kgram)));
        for(Object k : temp.keys()){
            rand -= freq(kgram, (char)(k));
            if(rand <= 0)
                return (char)(k);
        }
        return 0;
    }

    // tests this class by directly calling all instance methods
    public static void main(String[] args){
        MarkovModel a = new MarkovModel("gagggagaggcgagaaa", 2);
        System.out.println(a);
        System.out.println(a.freq("ga"));
        System.out.println(a.freq("ga", 'g'));
        for(int i = 0; i < 5; i ++)
            System.out.println(a.random("ga"));
    }
}