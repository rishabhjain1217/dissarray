public class ButtonLoader {

    private boolean oneDim = true;
    private boolean twoDim = false;
    private boolean arrList = false;
    private boolean sound = true;
    private boolean timer = false;
    private boolean loops = false;

    private static ButtonLoader ourInstance = new ButtonLoader();

    public static ButtonLoader getInstance(){ return ourInstance; }

    private ButtonLoader(){

    }

    public void setOneDim(boolean current){ oneDim = current; }
    public boolean getOneDim(){ return oneDim; }

    public boolean getArrList() {
        return arrList;
    }
    public void setArrList(boolean arrList) {
        this.arrList = arrList;
    }

    public boolean getSound() {
        return sound;
    }
    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public boolean getTimer() {
        return timer;
    }
    public void setTimer(boolean timer) {
        this.timer = timer;
    }

    public boolean getLoops() {
        return loops;
    }
    public void setLoops(boolean loops) {
        this.loops = loops;
    }

    public boolean getTwoDim() {
        return twoDim;
    }
    public void setTwoDim(boolean twoDim) {
        this.twoDim = twoDim;
    }


}
