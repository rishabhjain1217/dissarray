/**
 * Created by Rishabh Jain AKA CodeGod on 06 06, 2019 at 11:55
 */
public class ArrayListPane extends ArrayPane {

    public ArrayListPane(ArrayListQuestion question)
    {
        super(question);
    }



    @Override
    void render() {
        this.setMaxSize(400, 50);
        int length = ((ArrayListQuestion) this.question).getArrayLength();
        for (int i = 0; i < length; ++i) {
            this.indexButtons.add(new IndexButton(new ArrayListIndex(i)));
            this.add(this.indexButtons.get(i).getButton(), 30 + i, 0);
        }
    }

}
