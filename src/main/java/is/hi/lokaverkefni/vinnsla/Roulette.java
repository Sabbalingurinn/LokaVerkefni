package is.hi.lokaverkefni.vinnsla;

public class Roulette {

    private static Roulette instance;

    private int[] toluVedmal = {-1, -1, -1}; //Geymir töluleg veðmál notenda
    private String[] rauttSvartVedmal = new String[3]; //Geymir veðmál notenda á svart og rautt
    private int fjoldiBet = 0; //Fjoldi veðmála
    private final int[] RAUDARTOLUR = {32, 19, 21, 25, 34, 27, 36, 30, 23, 5, 16, 1, 14, 9, 18, 7, 12, 3};
    private final int[] SVARTARTOLUR = {15, 4, 2, 17, 6, 13, 11, 8, 10, 24, 33, 20, 31, 22, 29, 28, 35, 26};
    private int sidastaTala; //Síðasta tala sem kom upp í snúningi
    private int mogulegurSigur; //Heildarvinnings eða tapsupphæð eftir snúning

    /**
     * Skilar eina rúlettu hjólinu
     *
     * @return Roulette.
     */
    public static Roulette getInstance(){
        if(instance == null){
            instance = new Roulette();
        }
        return instance;
    }

    /**
     * Framkvæmir snúning á rúllettuhjólinu og reiknar út mögulegan vinning eða tap.
     *
     * @param bet Fjöldi peninga sem veðjað er í hverju veðmáli.
     * @return Heildarvinnings eða tapsupphæð eftir snúning.
     */
    public int spin(int bet) {

        this.mogulegurSigur = 0;
        this.sidastaTala = (int) (Math.random() * 38);


        String svartEdaRautt = svortRaudTala();

        for(int i = 0; i < fjoldiBet; i++){
            if(svartEdaRautt.equals(rauttSvartVedmal[i])){
                mogulegurSigur = mogulegurSigur + bet;
            } else if(this.sidastaTala == toluVedmal[i]){
                mogulegurSigur = mogulegurSigur + 35*bet;
            } else {
                mogulegurSigur = mogulegurSigur - bet;
            }
        }

        nullStilla();
        return mogulegurSigur;
    }

    /**
     * Stillir aftur breytur sem tengjast veðmálum til að undirbúa fyrir næsta snúning.
     */
    public void nullStilla(){
        setFjoldiBet(0);
        toluVedmal = new int[]{-1, -1, -1};
        rauttSvartVedmal = new String[3];
    }

    /**
     * Ákveður hvort síðasta tala var rauð, svört eða græn.
     *
     * @return Skilar staf 'R' fyrir rauða, 'B' fyrir svarta, 'G' fyrir græna.
     */
    public String svortRaudTala(){
        for(int i = 0; i < RAUDARTOLUR.length; i++){
            if(RAUDARTOLUR[i] == this.sidastaTala){
                return "R";
            }
        }
        for(int i = 0; i < SVARTARTOLUR.length; i++){
            if(SVARTARTOLUR[i] == this.sidastaTala){
                return "B";
            }
        }
        return "G";
    }

    /**
     * Bætir við veðmáli á rautt eða svart.
     *
     * @param svartRautt Strengur sem táknar lit veðmáls ('R' eða 'B').
     */
    public void baetaVidSvartRauttBet(String svartRautt){
        rauttSvartVedmal[fjoldiBet] = svartRautt;
        fjoldiBet++;
    }

    /**
     * Bætir við veðmáli á ákveðna tölu.
     *
     * @param tala Tölulegt gildi veðmáls.
     */
    public void baetaVidToluVedmal(int tala){
        toluVedmal[fjoldiBet] = tala;
        fjoldiBet++;
    }

    /**
     * Nær í síðustu tölu sem kom upp á rúllettuhjólinu.
     *
     * @return Tala sem datt út í síðasta snúningi.
     */
    public int getSidastaTala() {
        return sidastaTala;
    }

    /**
     * Stillir fjölda gerðra veðmála.
     *
     * @param i Nýr fjöldi veðmála.
     */
    public void setFjoldiBet(int i){
        fjoldiBet = i;
    }

    /**
     * Skilar mögulegan vinning eða tap.
     *
     * @return Heildarvinnings eða tapsupphæð.
     */
    public int getMogulegurSigur() {
        return mogulegurSigur;
    }
}
