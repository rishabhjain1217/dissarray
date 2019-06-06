public class ButtonLoader {

    /**Singleton for the status for the buttons. Adds button persistence */

    private boolean ONEDIM_Toggle_Status = true; //Starts ON
    private boolean TWODIM_Toggle_Status  = false;
    private boolean ARRAYLIST_Toggle_Status  = false;
    private boolean SOUND_Toggle_Status  = true; //Starts ON
    private boolean TIMER_Toggle_Status  = false;
    private boolean LOOPS_Toggle_Status  = false;

    private static ButtonLoader ourInstance = new ButtonLoader();

    public static ButtonLoader getInstance(){ return ourInstance; }

    private ButtonLoader(){

    }

    public void setOneDim(boolean current){ ONEDIM_Toggle_Status = current; }
    public boolean getOneDim(){ return ONEDIM_Toggle_Status; }

    public boolean getArrList() {
        return ARRAYLIST_Toggle_Status;
    }
    public void setArrList(boolean arrList) {
        this.ARRAYLIST_Toggle_Status = arrList;
    }

    public boolean getSound() {
        return SOUND_Toggle_Status;
    }
    public void setSound(boolean sound) {
        this.SOUND_Toggle_Status = sound;
    }

    public boolean getTimer() {
        return TIMER_Toggle_Status;
    }
    public void setTimer(boolean timer) {
        this.TIMER_Toggle_Status = timer;
    }

    public boolean getLoops() {
        return LOOPS_Toggle_Status;
    }
    public void setLoops(boolean loops) {
        this.LOOPS_Toggle_Status = loops;
    }

    public boolean getTwoDim() {
        return TWODIM_Toggle_Status;
    }
    public void setTwoDim(boolean twoDim) {
        this.TWODIM_Toggle_Status = twoDim;
    }


}
