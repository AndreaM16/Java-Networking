public abstract class NumberGenerator {

    public boolean canceled = false;
     public abstract int next();

    public void cancel(){
        canceled = true;
    }

    public boolean isCanceled(){
        return canceled;
    }
}
