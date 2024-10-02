public class Locomotiva extends Vagone{

    public void setLink(Vagone vagone, boolean isHead) {
        if (isHead) {
            setNext(vagone);
        } else {
            setPrev(vagone);
        }
    }


}
