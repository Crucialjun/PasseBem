package mz.co.passebem.passebem.model;

public class Answer {
    private final int checkedId;
    private final int index;

    public Answer(int checkedId, int mCurrentIndex) {
        this.checkedId = checkedId;
        this.index = mCurrentIndex;
    }

    public int getCheckedId() {
        return checkedId;
    }

    public int getIndex() {
        return index;
    }
}
