public class poesie_generator {

    
    static String nomi[] = {"cane", "gatto", "mare", "tubo", "corso", "caso", "ciclo", "sole", "fatto", "metodo", "mattino", "secolo", "dibattito", "cassetto", "dipartimento", "portale", "collegio", "pagliaccio", "giornale", "mulino", "cittadino", "salone", "cowboy", "fisco"};
    static String verbi[] = {"stirare", "rompere", "prendere", "indossare", "mangiare", "leggere", "suonare", "assicurare", "bloccare", "calciare", "consigliare", "delegare", "disarticolare", "divorare", "erigere", "inibire", "incendiare", "intortare", "insaporire", "intralciare"};
    
    static int nomiLen = nomi.length-1;
    static int verbiLen = verbi.length-1;
    public static void main(String[] args) {

        System.out.println(nomiLen);
        System.out.println(verbiLen);
        /*
        <nome> <verbo coniugato> <nome>, 
        <verbo all'infinito>, <verbo all'infinito>,<verbo all'infinito> +"!"
        <nome> <verbo coniugato> <nome> con <nome>, 
        <nome al plurale>, <nome al plurale>, <nome al plurale> + "!"
        per 3 volte.
        */
        for(int i = 0; i<3; i++) {
            System.out.println(first_line());
            System.out.println(second_line());
            System.out.println(third_line());
            System.out.println(fourth_line());
            System.out.println("\n");
        }
    }

    public static String first_line() {
        String rVerb = verbi[randNum(verbiLen, 0)];
        rVerb = rVerb.substring(0, rVerb.length()-2);
        return nomi[randNum(nomiLen, 0)] +" "+ rVerb +" "+ nomi[randNum(nomiLen, 0)]+",";
    }
    
    public static String second_line() {
        return verbi[randNum(verbiLen, 0)] +", "+ verbi[randNum(verbiLen, 0)] +", "+ verbi[randNum(verbiLen, 0)]+"!";
    }

    public static String third_line() {
        String rVerb = verbi[randNum(verbiLen, 0)];
        rVerb = rVerb.substring(0, rVerb.length()-2);
        return nomi[randNum(nomiLen, 0)] +" "+ rVerb +" "+ nomi[randNum(nomiLen, 0)]+ " con "+ nomi[randNum(nomiLen, 0)] + ",";
    }

    public static String fourth_line() {
        String rNome1 = plurale(verbiLen, nomi[randNum(nomiLen, 0)]) ;
        String rNome2 = plurale(verbiLen, nomi[randNum(nomiLen, 0)]) ;
        String rNome3 = plurale(verbiLen, nomi[randNum(nomiLen, 0)]) ;

        return rNome1+ " " + rNome2 + " " + rNome3+"!";
    } 

    public static String plurale(int N, String start) {
        String rNome = start;

        String nPlur = "";
        char[] vowels = {'a','e','i','o','u'};
        boolean isBroken = false;

        for (char j : vowels) {
            if(rNome.charAt(rNome.length()-1) == j) {
                for (char n : vowels) {
                    if(rNome.charAt(rNome.length()-2) == n) {
                        nPlur= rNome.substring(0, rNome.length()-1);
                        isBroken = true;
                        break;
                    }
                }
                if (isBroken) break;
                nPlur= rNome.replace(rNome.charAt(rNome.length()-1), 'i');
            }
        }
        return nPlur;
    } 

    public static int randNum(int max, int min) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
