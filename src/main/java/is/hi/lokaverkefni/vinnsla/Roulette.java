package is.hi.lokaverkefni.vinnsla;

public class Roulette {

    private int[] toluVedmal = {-1, -1, -1};
    private String[] rauttSvartVedmal = new String[3];
    private int fjoldiBet = 0;
    private final int[] RAUDARTOLUR = {32, 19, 21, 25, 34, 27, 36, 30, 23, 5, 16, 1, 14, 9, 18, 7, 12, 3};
    private final int[] SVARTARTOLUR = {15, 4, 2, 17, 6, 13, 11, 8, 10, 24, 33, 20, 31, 22, 29, 28, 35, 26};
    private int sidastaTala;

    public int spin(int bet) {

        int mogulegurSigur = 0;
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

    public void nullStilla(){
        setFjoldiBet(0);
        toluVedmal = new int[]{-1, -1, -1};
        rauttSvartVedmal = new String[3];
    }

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

    public void baetaVidSvartRauttBet(String svartRautt){
        rauttSvartVedmal[fjoldiBet] = svartRautt;
        fjoldiBet++;
    }

    public void baetaVidToluVedmal(int tala){
        toluVedmal[fjoldiBet] = tala;
        fjoldiBet++;
    }

    public int getSidastaTala() {
        return sidastaTala;
    }

    public void setSidastaTala(int sidastaTala) {
        this.sidastaTala = sidastaTala;
    }

    public void setFjoldiBet(int i){
        fjoldiBet = i;
    }
}
