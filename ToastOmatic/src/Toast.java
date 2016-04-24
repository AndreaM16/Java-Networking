public class Toast {

    public enum Status{DRY, BUTTERED, JAMMED};
    private Status status = Status.DRY;
    private final int id = 0;
    public Toast (int idn){
        idn=id;
    }

    public void buttered(){
        status = status.BUTTERED;
    }

    public void jam(){
        status = status.JAMMED;
    }

    public Status getStatus(){
        return status;
    }

    public int getId(){
        return id;
    }
}
